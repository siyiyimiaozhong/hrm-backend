package com.hrm.model.attendance.entity;

import com.hrm.model.attendance.base.BaseEntity;
import com.hrm.model.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 09:11
 * @Description: 考勤归档信息详情表实体类
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "atte_archive_monthly_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveMonthlyInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4530233664229978364L;

    @Id
    private String id;

    private String userId;
    private String atteArchiveMonthlyId;
    private String name;

    private String workNumber;
    private String mobile;
    private String atteSolution;

    private String department;
    private String workCity;
    private String yearLeaveDays;

    private String leaveDays;
    private String sickLeaveDays;
    private String longSickLeaveDays;

    private String marraiageLeaveDays;
    private String funeralLeaveDays;
    /**
     * 产假
     */
    private String maternityLeaveDays;

    private String rewardMaternityLeaveDays;

    /**
     * 陪产假
     */
    private String paternityLeaveDays;
    /**
     * 探亲假
     */
    private String homeLeavaDays;

    /**
     * 工伤假
     */
    private String accidentialLeaveDays;
    private String dayOffLeaveDays;
    /**
     * 产检假
     */
    private String doctorOffLeaveDays;

    /**
     * 流产假
     */
    private String abortionLeaveDays;
    private String normalDays;
    private String outgoingDays;

    private String onBusinessDays;
    private String laterTimes;
    private String earlyTimes;

    private Integer signedTimes;
    /**
     * 日均时长（自然日）
     */
    private String hoursPerDays;
    private String hoursPerWorkDay;

    private String hoursPerRestDay;
    private String clockRate;
    private String absenceDays;

    private Integer isFullAttendanceint;
    private String actualAtteUnofficialDays;
    private String actualAtteOfficialDays;

    /**
     * 应出勤工作日
     */
    private String workingDays;
    private String salaryStandards;
    /**
     * 计薪天数调整
     */
    private String salaryAdjustmentDays;

    /**
     * 工作时长
     */
    private String workHour;

    /**
     * 计薪天数（非正式）
     */
    private String salaryUnofficialDays;
    /**
     * 计薪天数（正式）
     */
    private String salaryOfficialDays;


    private String archiveDate;


    public ArchiveMonthlyInfo(User user) {
        this.userId = user.getId();
        this.name = user.getUsername();
        this.workNumber = user.getWorkNumber();
        this.department = user.getDepartmentName();
        this.mobile = user.getMobile();
    }


    public void setStatisData(Map<String, Object> map) {
        this.normalDays = map.get("at1").toString(); //正常
        this.absenceDays = map.get("at2").toString(); //旷工
        this.laterTimes = map.get("at3").toString();//迟到
        this.earlyTimes = map.get("at4").toString(); //早退
        this.leaveDays = map.get("at8").toString(); //事假
        this.dayOffLeaveDays = map.get("at17").toString(); //调休
        //平均工作日21.75
        this.workingDays = "21.75";
        //是否全勤
        this.isFullAttendanceint = Integer.parseInt(this.normalDays) >= 21.75 ? 0 : 1;
        //出勤天数 = 正常 + 早退 + 迟到
        this.actualAtteOfficialDays = Integer.parseInt(this.laterTimes) +
                Integer.parseInt(this.normalDays) +
                Integer.parseInt(this.earlyTimes) + "";
        //出勤天数 = 计薪天数
        this.salaryOfficialDays = this.actualAtteOfficialDays;
    }
}
