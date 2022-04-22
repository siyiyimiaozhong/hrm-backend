package com.hrm.attendance.service.impl;

import com.hrm.attendance.dao.ArchiveMonthlyDao;
import com.hrm.attendance.dao.ArchiveMonthlyInfoDao;
import com.hrm.attendance.dao.AttendanceDao;
import com.hrm.attendance.dao.UserDao;
import com.hrm.attendance.service.ArchiveService;
import com.hrm.common.utils.IdWorker;
import com.hrm.model.attendance.entity.ArchiveMonthly;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.system.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:05
 * @Description: 归档业务层实现类
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final AttendanceDao attendanceDao;
    private final ArchiveMonthlyDao atteArchiveMonthlyDao;
    private final ArchiveMonthlyInfoDao archiveMonthlyInfoDao;
    private final UserDao userDao;
    private final IdWorker idWorker;

    public ArchiveServiceImpl(AttendanceDao attendanceDao,
                              ArchiveMonthlyDao atteArchiveMonthlyDao,
                              ArchiveMonthlyInfoDao archiveMonthlyInfoDao,
                              UserDao userDao,
                              IdWorker idWorker) {
        this.attendanceDao = attendanceDao;
        this.atteArchiveMonthlyDao = atteArchiveMonthlyDao;
        this.archiveMonthlyInfoDao = archiveMonthlyInfoDao;
        this.userDao = userDao;
        this.idWorker = idWorker;
    }

    /**
     * 数据归档
     * @param archiveDate
     * @param companyId
     */
    @Override
    public void saveArchive(String archiveDate, String companyId) {
        //1.查询所有企业用户
        List<User> users = this.userDao.findByCompanyId(companyId);

        //1.保存归档主表数据
        this.atteArchiveMonthlyDao.findByCompanyIdAndArchiveYear(companyId,archiveDate);

        ArchiveMonthly archiveMonthly = new ArchiveMonthly();
        archiveMonthly.setId(this.idWorker.nextId()+"");
        archiveMonthly.setCompanyId(companyId);
        archiveMonthly.setArchiveYear(archiveDate.substring(0,4)); //201908
        archiveMonthly.setArchiveMonth(archiveDate.substring(5));


        //2.保存归档明细表数据
        for (User user : users) {
            ArchiveMonthlyInfo info = new ArchiveMonthlyInfo(user);
            //统计每个用户的考勤记录
            Map<String, Object> map = this.attendanceDao.statisByUser(user.getId(),archiveDate +"%");
            info.setStatisData(map);
            info.setId(this.idWorker.nextId()+"");
            info.setAtteArchiveMonthlyId(archiveMonthly.getId());
            info.setArchiveDate(archiveDate);
            this.archiveMonthlyInfoDao.save(info);
        }

        //总人数
        archiveMonthly.setTotalPeopleNum(users.size());
        archiveMonthly.setFullAttePeopleNum(users.size());
        archiveMonthly.setIsArchived(0);

        this.atteArchiveMonthlyDao.save(archiveMonthly);
    }

    /**
     * 根据年份,查询当年的所有考勤历史
     * @param year
     * @param companyId
     * @return
     */
    @Override
    public List<ArchiveMonthly> findReportsByYear(String year, String companyId) {
        return this.atteArchiveMonthlyDao.findByCompanyIdAndArchiveYear(companyId,year);
    }

    /**
     * 查询归档详情列表
     * @param id
     * @return
     */
    @Override
    public List<ArchiveMonthlyInfo> findMonthlyInfoByAmid(String id) {
        return archiveMonthlyInfoDao.findByAtteArchiveMonthlyId(id);
    }

    /**
     * 根据用户id和年月查询归档明细
     * @param userId
     * @param yearMonth
     * @return
     */
    @Override
    public ArchiveMonthlyInfo findUserArchiveDetail(String userId, String yearMonth) {
        return archiveMonthlyInfoDao.findByUserIdAndArchiveDate(userId,yearMonth);
    }
}
