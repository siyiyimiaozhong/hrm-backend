package com.hrm.attendance.service.impl;

import com.hrm.attendance.dao.*;
import com.hrm.attendance.service.AttendanceService;
import com.hrm.common.utils.DateUtil;
import com.hrm.common.utils.ExcelImportUtil;
import com.hrm.common.utils.IdWorker;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.template.AttendanceTemplate;
import com.hrm.model.attendance.bo.AttendanceItemBO;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.attendance.entity.Attendance;
import com.hrm.model.attendance.entity.AttendanceConfig;
import com.hrm.model.attendance.entity.CompanySettings;
import com.hrm.model.system.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:06
 * @Description: 考勤业务层实现类
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Value("${atte.holidays}")
    private String holidays;
    @Value("${atte.wordingDays}")
    private String wordingDays;

    private final UserDao userDao;
    private final AttendanceDao attendanceDao;
    private final AttendanceConfigDao attendanceConfigDao;
    private final IdWorker idWorker;
    private final DeductionDictDao deductionDictDao;
    private final CompanySettingsDao companySettingsDao;

    public AttendanceServiceImpl(UserDao userDao,
                                 AttendanceDao attendanceDao,
                                 AttendanceConfigDao attendanceConfigDao,
                                 IdWorker idWorker,
                                 DeductionDictDao deductionDictDao,
                                 CompanySettingsDao companySettingsDao) {
        this.userDao = userDao;
        this.attendanceDao = attendanceDao;
        this.attendanceConfigDao = attendanceConfigDao;
        this.idWorker = idWorker;
        this.deductionDictDao = deductionDictDao;
        this.companySettingsDao = companySettingsDao;
    }

    @Override
    public void importAttendanceExcel(MultipartFile file, String companyId) {
        try {
            //1.将导入的excel文件解析为vo的list集合
            List<AttendanceTemplate> list = new ExcelImportUtil<AttendanceTemplate>(AttendanceTemplate.class).readExcel(file.getInputStream(), 1, 0);
            //2.循环list集合
            for (AttendanceTemplate template : list) {
                //2.1 根据上传的手机号码查询用户
                User user = userDao.findByMobile(template.getMobile());
                //2.2 构造考勤对象
                Attendance attendance = new Attendance(template, user);
                attendance.setDay(template.getAtteDate());
                //2.3 判断是否休假
                /**
                 * 1.将国家的假日记录到数据库
                 * 2.文件
                 */
                if (this.holidays.contains(template.getAtteDate())) {
                    attendance.setAdtStatu(23); //休息
                } else if (DateUtil.isWeekend(template.getAtteDate()) && !this.wordingDays.contains(template.getAtteDate())) {
                    attendance.setAdtStatu(23);
                } else {
                    //2.4 判断迟到,早退的状态
                    //i 查询当前员工部门的上班时间,查询当前员工部门的下班时间
                    AttendanceConfig ac = this.attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId, user.getDepartmentId());
                    //ii 比较,上班时间是否晚于规定上班时间 (迟到)
                    if (!DateUtil.comparingDate(ac.getAfternoonStartTime(), template.getInTime())) { //第一个参数 : 规定时间 , 第二参数 : 打卡时间
                        attendance.setAdtStatu(3);//迟到
                    } else if (DateUtil.comparingDate(ac.getAfternoonEndTime(), template.getOutTime())) {
                        attendance.setAdtStatu(4);//早退
                    } else {
                        //正常
                        attendance.setAdtStatu(1);
                    }
                }

                //2.5 查询用户是否已经有考勤记录,如果不存在,保存数据库
                Attendance byUserIdAndDay = this.attendanceDao.findByUserIdAndDay(user.getId(), template.getAtteDate());

                if (byUserIdAndDay == null) {
                    attendance.setId(idWorker.nextId() + "");
                    this.attendanceDao.save(attendance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户的考勤数据
     * 1.考勤月
     * 2.分页
     * 3.查询
     *
     * @param companyId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAtteDate(String companyId, int page, int pageSize) {
        //1.考勤月
        CompanySettings settings = this.companySettingsDao.findById(companyId).get();
        String dataMonth = settings.getDataMonth();
        //2.分页查询用户
        Page<User> page1 = this.userDao.findPage(companyId, new PageRequest(page - 1, pageSize));
        List<AttendanceItemBO> list = new ArrayList<>();
        //3.循环所有的用户,获取每个用户每天的考勤情况
        for (User user : page1.getContent()) {
            AttendanceItemBO bo = new AttendanceItemBO();
            BeanUtils.copyProperties(user, bo);
            List<Attendance> attendanceRecord = new ArrayList<>();
            //获取当前月所有的天数
            String[] days = new String[0];//传递20220201,
            try {
                days = DateUtil.getDaysByYearMonth(dataMonth);
                //循环每天查询考勤记录
                for (String day : days) {
                    Attendance attendance = this.attendanceDao.findByUserIdAndDay(user.getId(), day);
                    if (attendance == null) {
                        attendance = new Attendance();
                        attendance.setAdtStatu(2); //如果当天不存在考勤记录,旷工
                        attendance.setId(user.getId());
                        attendance.setDay(day);
                    }
                    attendanceRecord.add(attendance);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //封装到attendanceRecord
            bo.setAttendanceRecord(attendanceRecord);
            list.add(bo);
        }

        Map<String, Object> map = new HashMap<>();
        //1.数据,分页对象
        PageResult<AttendanceItemBO> pr = new PageResult<>(page1.getTotalElements(), list);
        map.put("data", pr);
        //2.待处理的考勤数量
        map.put("tobeTaskCount", 0);
        //3.当前考勤的月份
        int i = Integer.parseInt(dataMonth.substring(4));
        map.put("monthOfReport", i);
        return map;
    }

    @Override
    public void updateAtte(Attendance attendance) {
        //1.查询考勤是否存在,更新
        Attendance vo = this.attendanceDao.findByUserIdAndDay(attendance.getUserId(), attendance.getDay());
        //2.如果不存在,设置对象id,保存
        if (vo == null) {
            attendance.setId(idWorker.nextId() + "");
        } else {
            attendance.setId(vo.getId());
        }
        this.attendanceDao.save(attendance);
    }

    /**
     * 查询归档数据
     * @param atteDate
     * @param companyId
     * @return
     */
    @Override
    public List<ArchiveMonthlyInfo> getReports(String atteDate, String companyId) {
        //1.查询所有企业用户
        List<User> users = userDao.findByCompanyId(companyId);
        //2.循环遍历用户列表,统计每个用户当月的考勤记录
        List<ArchiveMonthlyInfo> list = new ArrayList<>();
        for (User user : users) {
            ArchiveMonthlyInfo info = new ArchiveMonthlyInfo(user);
            //统计每个用户的考勤记录
            Map<String, Object> map = attendanceDao.statisByUser(user.getId(), atteDate + "%");
            System.out.println(user.getId() + "--" + atteDate);
            info.setStatisData(map);
            list.add(info);
        }
        return list;
    }

    /**
     * 新建报表,将companySetting中的月份修改为指定的数据
     * @param yearMonth
     * @param companyId
     */
    @Override
    public void newReports(String yearMonth, String companyId) {
        Optional<CompanySettings> optional = companySettingsDao.findById(companyId);
        if (optional.isPresent()) {
            CompanySettings settings = optional.get();
            settings.setDataMonth(yearMonth);
            companySettingsDao.save(settings);
        }
    }
}
