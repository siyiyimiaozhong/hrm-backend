package com.hrm.attendance.service.impl;

import com.hrm.attendance.dao.*;
import com.hrm.attendance.service.AttendanceConfigService;
import com.hrm.common.utils.IdWorker;
import com.hrm.model.attendance.entity.AttendanceConfig;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:12
 * @Description: 考勤配置业务层实现类
 */
@Service
public class AttendanceConfigServiceImpl implements AttendanceConfigService {

    private final AttendanceConfigDao attendanceConfigDao;
    private final LeaveConfigDao leaveConfigDao;
    private final DeductionDictDao deductionDictDao;
    private final ExtraDutyConfigDao extraDutyConfigDao;
    private final ExtraDutyRuleDao extraDutyRuleDao;
    private final DayOffConfigDao dayOffConfigDao;
    private final IdWorker idWorker;

    public AttendanceConfigServiceImpl(AttendanceConfigDao attendanceConfigDao,
                                       LeaveConfigDao leaveConfigDao,
                                       DeductionDictDao deductionDictDao,
                                       ExtraDutyConfigDao extraDutyConfigDao,
                                       ExtraDutyRuleDao extraDutyRuleDao,
                                       DayOffConfigDao dayOffConfigDao,
                                       IdWorker idWorker) {
        this.attendanceConfigDao = attendanceConfigDao;
        this.leaveConfigDao = leaveConfigDao;
        this.deductionDictDao = deductionDictDao;
        this.extraDutyConfigDao = extraDutyConfigDao;
        this.extraDutyRuleDao = extraDutyRuleDao;
        this.dayOffConfigDao = dayOffConfigDao;
        this.idWorker = idWorker;
    }

    /**
     * 查询考勤设置
     * @param companyId
     * @param departmentId
     * @return
     */
    @Override
    public AttendanceConfig getAtteConfig(String companyId, String departmentId) {
        AttendanceConfig ac = attendanceConfigDao.findByCompanyIdAndDepartmentId(companyId, departmentId);
        if (null == ac) {
            ac = new AttendanceConfig();
            ac.setDepartmentId(departmentId);
        }
        return ac;
    }

    /**
     * 保存或者更新考勤设置
     * @param companyId
     * @param attendanceConfig
     */
    @Override
    public void saveAndUpdateAtteConfig(String companyId, AttendanceConfig attendanceConfig) {
        attendanceConfig.setCompanyId(companyId);
        //1.查询是否存在响应的考勤记录
        AttendanceConfig vo = attendanceConfigDao.findByCompanyIdAndDepartmentId(attendanceConfig.getCompanyId(), attendanceConfig.getDepartmentId());
        //2.如果存在,更新
        if(vo != null) {
            attendanceConfig.setId(vo.getId());
        }else {
            //3.如果不存在,设置id,保存
            attendanceConfig.setId(idWorker.nextId() +"");
        }
        attendanceConfigDao.save(attendanceConfig);
    }
}
