package com.hrm.salary.service.impl;

import com.hrm.common.utils.CommonUtils;
import com.hrm.core.constant.FormOfEmploymentEnum;
import com.hrm.core.pojo.PageResult;
import com.hrm.model.salary.dto.UserSalaryItemDto;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.salary.vo.UserSalaryItemVo;
import com.hrm.salary.dao.UserSalaryDao;
import com.hrm.salary.service.SalaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:55
 * @Description: 薪酬业务层实现类
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    private final UserSalaryDao userSalaryDao;
    private final EntityManager entityManager;

    public SalaryServiceImpl(UserSalaryDao userSalaryDao, EntityManager entityManager) {
        this.userSalaryDao = userSalaryDao;
        this.entityManager = entityManager;
    }

    @Override
    public void saveUserSalary(UserSalary userSalary) {
        this.userSalaryDao.save(userSalary);
    }

    @Override
    public UserSalary findUserSalary(String userId) {
        return this.userSalaryDao.findById(userId).orElse(null);
    }

    @Override
    public PageResult<UserSalaryItemVo> findAll(String companyId, UserSalaryItemDto userSalaryItemDto) {
        StringBuilder sb = new StringBuilder(" bu.company_id=");
        sb.append(companyId);
        if (CommonUtils.isNotEmpty(userSalaryItemDto.getDepartmentChecks())) {
            sb.append(" and bu.department_id in (").append(StringUtils.join(userSalaryItemDto.getDepartmentChecks(), ",")).append(")");
        }
        if (CommonUtils.isNotEmpty(userSalaryItemDto.getApprovalsTypeChecks())) {
            sb.append(" and bu.form_of_employment in (").append(StringUtils.join(userSalaryItemDto.getApprovalsTypeChecks(), ",")).append(")");
        }
        if (CommonUtils.isNotEmpty(userSalaryItemDto.getApprovalsStateChecks())) {
            sb.append(" and bu.in_service_status in (").append(StringUtils.join(userSalaryItemDto.getApprovalsStateChecks(), ",")).append(")");
        }
//        Page<Map<String, Object>> mapPage = this.userSalaryDao.findPage(companyId, new PageRequest(userSalaryItemDto.getPage() - 1, userSalaryItemDto.getPageSize()));
        Page<Map<String, Object>> mapPage = getDataByParams(sb.toString(), new PageRequest(userSalaryItemDto.getPage() - 1, userSalaryItemDto.getPageSize()));
        List<UserSalaryItemVo> userSalaryItemVos = getUserSalaryItemVo(mapPage.getContent());
        return new PageResult<>(mapPage.getTotalElements(), userSalaryItemVos);
    }

    private List<UserSalaryItemVo> getUserSalaryItemVo(List<Map<String, Object>> content) {
        List<UserSalaryItemVo> itemVos = new LinkedList<>();
        for (Map<String, Object> map : content) {
            UserSalaryItemVo userSalaryItemVo = new UserSalaryItemVo();
            userSalaryItemVo.setId(map.get("id").toString());
            userSalaryItemVo.setUsername(map.get("username").toString());
            userSalaryItemVo.setMobile(map.get("mobile").toString());
            userSalaryItemVo.setWorkNumber(map.get("workNumber").toString());
            userSalaryItemVo.setFormOfEmployment(FormOfEmploymentEnum.getEnumByKey((Integer) map.get("formOfEmployment")).getValue());
            userSalaryItemVo.setDepartmentName(map.get("departmentName").toString());

            Date timeOfEntry = (Date)map.get("timeOfEntry");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            userSalaryItemVo.setTimeOfEntry(format.format(timeOfEntry));

            if (null != map.get("currentBasicSalary") && null != map.get("currentPostWage")) {
                BigDecimal currentBasicSalary = (BigDecimal)map.get("currentBasicSalary");
                BigDecimal currentPostWage = (BigDecimal)map.get("currentPostWage");
                userSalaryItemVo.setWageBase(currentBasicSalary.add(currentPostWage));
                userSalaryItemVo.setIsFixed(true);
            } else {
                userSalaryItemVo.setIsFixed(false);
            }
            userSalaryItemVo.setSubsidyScheme("通用方案");

            itemVos.add(userSalaryItemVo);
        }
        return itemVos;
    }

    private Page<Map<String, Object>> getDataByParams(String searchParams, Pageable pageable) {
        //查询数据，并设置分页
        StringBuffer querySql = new StringBuffer("SELECT\n" +
                "bu.id,\n" +
                "bu.username,\n" +
                "bu.mobile,\n" +
                "bu.work_number workNumber,\n" +
                "bu.in_service_status inServiceStatus,\n" +
                "bu.department_name departmentName,\n" +
                "bu.department_id departmentId,\n" +
                "bu.time_of_entry timeOfEntry,\n" +
                "bu.form_of_employment formOfEmployment,\n" +
                "sauss.current_basic_salary currentBasicSalary,\n" +
                "sauss.current_post_wage currentPostWage \n" +
                "FROM\n" +
                "bs_user bu\n" +
                "LEFT JOIN sa_user_salary sauss ON bu.id = sauss.user_id \n" +
                "WHERE");
        querySql.append(searchParams);
        Query query = this.entityManager.createNativeQuery(querySql.toString());

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> promRuleDtos = query.getResultList();
        List<Map<String, Object>> res = new LinkedList<>();
        for (Object[] o : promRuleDtos) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", o[0]);
            map.put("username", o[1]);
            map.put("mobile", o[2]);
            map.put("workNumber", o[3]);
            map.put("inServiceStatus", o[4]);
            map.put("departmentName", o[5]);
            map.put("departmentId", o[6]);
            map.put("timeOfEntry", o[7]);
            map.put("formOfEmployment", o[8]);
            map.put("currentBasicSalary", o[9]);
            map.put("currentPostWage", o[10]);
            res.add(map);
        }

        //获取总数
        StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM bs_user bu LEFT JOIN ss_user_social_security ssuss ON bu.id = ssuss.user_id WHERE ");
        countSql.append(searchParams);
        Query queryCount = this.entityManager.createNativeQuery(countSql.toString());
        long pageTotal = Long.parseLong(queryCount.getSingleResult().toString());
        return new PageImpl<>(res, pageable, pageTotal);
    }
}
