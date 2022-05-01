package com.hrm.salary.service.impl;

import com.hrm.common.utils.IdWorker;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.salary.entity.SalaryArchive;
import com.hrm.model.salary.entity.SalaryArchiveDetail;
import com.hrm.model.salary.entity.Settings;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.social_security.entity.ArchiveDetail;
import com.hrm.salary.dao.ArchiveDao;
import com.hrm.salary.dao.ArchiveDetailDao;
import com.hrm.salary.dao.UserSalaryDao;
import com.hrm.salary.service.ArchiveService;
import com.hrm.salary.service.FeignClientService;
import com.hrm.salary.service.SalaryService;
import com.hrm.salary.service.SettingsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:46
 * @Description: 工资归档业务层实现类
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveDao archiveDao;
    private final ArchiveDetailDao archiveDetailDao;
    private final UserSalaryDao userSalaryDao;
    private final IdWorker idWorker;
    private final SalaryService salaryService;
    private final SettingsService settingsService;
    private final FeignClientService feignClientService;

    public ArchiveServiceImpl(ArchiveDao archiveDao,
                              ArchiveDetailDao archiveDetailDao,
                              UserSalaryDao userSalaryDao,
                              IdWorker idWorker,
                              SalaryService salaryService,
                              SettingsService settingsService,
                              FeignClientService feignClientService) {
        this.archiveDao = archiveDao;
        this.archiveDetailDao = archiveDetailDao;
        this.userSalaryDao = userSalaryDao;
        this.idWorker = idWorker;
        this.salaryService = salaryService;
        this.settingsService = settingsService;
        this.feignClientService = feignClientService;
    }

    @Override
    public List<SalaryArchiveDetail> getHistoryDetail(String companyId, String yearMonth, int opType) {
        List<SalaryArchiveDetail> list = new LinkedList<>();
        //1.判断opType参数:如果==1,自己构造报表数据
        if (opType == 1) {
            list = this.getReports(yearMonth, companyId);
        } else {
            //2.如果!=1,查询归档历史表
            //2.1 查询主表数据
            SalaryArchive sa = this.findSalaryArchive(yearMonth, companyId);
            //2.2 根据主表的id,查询明细表的所有数据
            if (sa != null) {
                list = this.findSalaryArchiveDetail(sa.getId());
            }
        }
        return list;
    }

    @Override
    public SalaryArchive findSalaryArchive(String yearMonth, String companyId) {
        return this.archiveDao.findByCompanyIdAndYearsMonth(companyId, yearMonth);
    }

    @Override
    public List<SalaryArchiveDetail> findSalaryArchiveDetail(String id) {
        return this.archiveDetailDao.findByArchiveId(id);
    }

    @Override
    public List<SalaryArchiveDetail> getReports(String yearMonth, String companyId) {
        List<SalaryArchiveDetail> list = new ArrayList<>();

        //查询当前企业的福利津贴
        Settings settings = settingsService.findById(companyId);
        //1.查询所有的用户
        Page<Map<String, Object>> page = userSalaryDao.findPage(companyId, null);
        //2.遍历用户数据
        for (Map<String, Object> map : page.getContent()) {
            //3.构造SalaryArchiveDetail
            SalaryArchiveDetail detail = new SalaryArchiveDetail();
            detail.setUser(map); //构造用户数据
            //4.获取每个用户的社保数据
            ArchiveDetail socialInfo = feignClientService.getSocialInfo(detail.getUserId(), yearMonth);
            detail.setSocialInfo(socialInfo);
            //5.获取每个用户的考勤数据
            ArchiveMonthlyInfo atteInfo = feignClientService.getAtteInfo(detail.getUserId(), yearMonth);
            detail.setAtteInfo(atteInfo);
            //6.获取每个用户的薪资
            UserSalary userSalary = salaryService.findUserSalary(detail.getUserId());
            detail.setUserSalary(userSalary);
            //7.计算工资
            detail.calSalary(settings);
            list.add(detail);
        }
        return list;
    }
}
