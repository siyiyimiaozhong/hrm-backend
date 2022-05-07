package com.hrm.social_security.service.impl;

import com.alibaba.fastjson.JSON;
import com.hrm.common.utils.IdWorker;
import com.hrm.core.pojo.Result;
import com.hrm.model.employee.entity.UserCompanyPersonal;
import com.hrm.model.social_security.entity.Archive;
import com.hrm.model.social_security.entity.ArchiveDetail;
import com.hrm.model.social_security.entity.CityPaymentItem;
import com.hrm.model.social_security.entity.UserSocialSecurity;
import com.hrm.social_security.client.PersonalInfoFeignClient;
import com.hrm.social_security.dao.ArchiveDao;
import com.hrm.social_security.dao.ArchiveDetailDao;
import com.hrm.social_security.dao.UserSocialSecurityDao;
import com.hrm.social_security.service.ArchiveService;
import com.hrm.social_security.service.PaymentItemService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:55
 * @Description: 社保归档业务处理层实现类
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveDao archiveDao;
    private final ArchiveDetailDao archiveDetailDao;
    private final UserSocialSecurityDao userSocialSecurityDao;
    private final PersonalInfoFeignClient personalInfoFeignClient;
    private final PaymentItemService paymentItemService;
    private final IdWorker idWorker;

    public ArchiveServiceImpl(ArchiveDao archiveDao,
                              ArchiveDetailDao archiveDetailDao,
                              UserSocialSecurityDao userSocialSecurityDao,
                              PersonalInfoFeignClient personalInfoFeignClient,
                              PaymentItemService paymentItemService,
                              IdWorker idWorker) {
        this.archiveDao = archiveDao;
        this.archiveDetailDao = archiveDetailDao;
        this.userSocialSecurityDao = userSocialSecurityDao;
        this.personalInfoFeignClient = personalInfoFeignClient;
        this.paymentItemService = paymentItemService;
        this.idWorker = idWorker;
    }

    @Override
    public List<ArchiveDetail> historysDetail(String companyId, String yearMonth, int opType) {
        List<ArchiveDetail> list = null;
        if (opType == 1) {
            //未归档,查询当月的详细数据
            list = this.getReports(yearMonth, companyId);
        } else {
            //已归档的数据
            //1.根据月和企业id查询归档历史
            Archive archive = this.findArchive(companyId, yearMonth);
            //2.如果归档历史存在,查询归档明细
            if (archive != null) {
                list = this.findAllDetailByArchiveId(archive.getId());
            }
        }
        return list;
    }

    @Override
    public void archive(String yearMonth, String companyId) {
        //1.查询归档明细数据
        List<ArchiveDetail> archiveDetails = getReports(yearMonth, companyId);
        //1.1 计算当月,企业与员工支出的所有社保金额
        BigDecimal enterMoney = new BigDecimal(0);
        BigDecimal personMoney = new BigDecimal(0);
        for (ArchiveDetail archiveDetail : archiveDetails) {
            BigDecimal t1 = archiveDetail.getProvidentFundEnterprises() == null ? new BigDecimal(0) : archiveDetail.getProvidentFundEnterprises();
            BigDecimal t2 = archiveDetail.getSocialSecurityEnterprise() == null ? new BigDecimal(0) : archiveDetail.getSocialSecurityEnterprise();
            BigDecimal t3 = archiveDetail.getProvidentFundIndividual() == null ? new BigDecimal(0) : archiveDetail.getProvidentFundIndividual();
            BigDecimal t4 = archiveDetail.getSocialSecurityIndividual() == null ? new BigDecimal(0) : archiveDetail.getSocialSecurityIndividual();
            enterMoney = enterMoney.add(t1).add(t2);
            personMoney = enterMoney.add(t3).add(t4);
        }
        //2.查询当月是否已经归档
        Archive archive = this.findArchive(companyId, yearMonth);
        //3.不存在已归档的数据,保存
        if (archive == null) {
            archive = new Archive();
            archive.setCompanyId(companyId);
            archive.setYearsMonth(yearMonth);
            archive.setId(idWorker.nextId() + "");
        }
        //4.如果存在已归档数据,覆盖
        archive.setEnterprisePayment(enterMoney);
        archive.setPersonalPayment(personMoney);
        archive.setTotal(enterMoney.add(personMoney));
        archiveDao.save(archive);

        Archive finalArchive = archive;
        archiveDetails = archiveDetails.stream().peek(archiveDetail -> {
            archiveDetail.setId(idWorker.nextId() + "");
            archiveDetail.setArchiveId(finalArchive.getId());
        }).collect(Collectors.toList());
        this.archiveDetailDao.saveAll(archiveDetails);
        // 删除用户缴纳信息数据
        this.userSocialSecurityDao.deleteAll();
    }

    @Override
    public List<Archive> findArchiveByYear(String companyId, String year) {
        return archiveDao.findByCompanyIdAndYearsMonthLike(companyId, year + "%");
    }

    @Override
    public ArchiveDetail findUserArchiveDetail(String userId, String yearMonth) {
        return archiveDetailDao.findByUserIdAndYearsMonth(userId, yearMonth);
    }

    public List<ArchiveDetail> getReports(String yearMonth, String companyId) {
        //查询用户的社保列表 (用户和基本社保数据)
        Page<Map<String, Object>> userSocialSecurityItemPage = userSocialSecurityDao.findPage(companyId, null);

        List<ArchiveDetail> list = new ArrayList<>();

        for (Map<String, Object> map : userSocialSecurityItemPage) {
            String userId = (String) map.get("userId");
            String mobile = (String) map.get("mobile");
            String username = (String) map.get("username");
            String departmentName = (String) map.get("departmentName");
            ArchiveDetail vo = new ArchiveDetail(userId, mobile, username, departmentName);
            vo.setTimeOfEntry((Date) map.get("timeOfEntry"));
            //获取个人信息
            Result<UserCompanyPersonal> personalResult = this.personalInfoFeignClient.getPersonalInfo(vo.getUserId());
            if (personalResult.isSuccess()) {
                UserCompanyPersonal userCompanyPersonal = JSON.parseObject(JSON.toJSONString(personalResult.getData()), UserCompanyPersonal.class);
                vo.setUserCompanyPersonal(userCompanyPersonal);
            }
            //社保相关信息
            getOtherData(vo, yearMonth);
            list.add(vo);
        }
        return list;
    }

    /**
     * 根据归档历史id,查询归档明细
     *
     * @param id
     * @return
     */
    public List<ArchiveDetail> findAllDetailByArchiveId(String id) {
        return archiveDetailDao.findByArchiveId(id);
    }

    /**
     * 查询归档历史
     *
     * @param companyId
     * @param yearMonth
     * @return
     */
    public Archive findArchive(String companyId, String yearMonth) {
        return archiveDao.findByCompanyIdAndYearsMonth(companyId, yearMonth);
    }

    /**
     * 获取其他社保相关信息
     *
     * @param vo
     * @param yearMonth
     */
    public void getOtherData(ArchiveDetail vo, String yearMonth) {

        Optional<UserSocialSecurity> optionalUserSocialSecurity = this.userSocialSecurityDao.findById(vo.getUserId());
        if (!optionalUserSocialSecurity.isPresent()) {
            return;
        }
        UserSocialSecurity userSocialSecurity = optionalUserSocialSecurity.get();

        BigDecimal socialSecurityCompanyPay = new BigDecimal(0);

        BigDecimal socialSecurityPersonalPay = new BigDecimal(0);

        List<CityPaymentItem> cityPaymentItemList = paymentItemService.findAllByCityId(userSocialSecurity.getProvidentFundCityId());

        for (CityPaymentItem cityPaymentItem : cityPaymentItemList) {
            if (cityPaymentItem.getSwitchCompany()) {
                BigDecimal augend;
                if (cityPaymentItem.getPaymentItemId().equals("4") && userSocialSecurity.getIndustrialInjuryRatio() != null) {
                    augend = userSocialSecurity.getIndustrialInjuryRatio().multiply(userSocialSecurity.getSocialSecurityBase());
                } else {
                    augend = cityPaymentItem.getScaleCompany().multiply(userSocialSecurity.getSocialSecurityBase());
                }
                BigDecimal divideAugend = augend.divide(new BigDecimal(100));
                socialSecurityCompanyPay = socialSecurityCompanyPay.add(divideAugend);
            }
            if (cityPaymentItem.getSwitchPersonal()) {
                BigDecimal augend = cityPaymentItem.getScalePersonal().multiply(userSocialSecurity.getSocialSecurityBase());
                BigDecimal divideAugend = augend.divide(new BigDecimal(100));
                socialSecurityPersonalPay = socialSecurityPersonalPay.add(divideAugend);
            }
        }

        vo.setSocialSecurity(socialSecurityCompanyPay.add(socialSecurityPersonalPay));
        vo.setSocialSecurityEnterprise(socialSecurityCompanyPay);
        vo.setSocialSecurityIndividual(socialSecurityPersonalPay);
        vo.setUserSocialSecurity(userSocialSecurity);
        vo.setSocialSecurityMonth(yearMonth);
        vo.setProvidentFundMonth(yearMonth);
    }
}
