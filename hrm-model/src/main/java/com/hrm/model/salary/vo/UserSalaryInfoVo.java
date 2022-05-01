package com.hrm.model.salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSalaryInfoVo implements Serializable {
    private static final long serialVersionUID = 6118449241999147504L;
    //ID
    private String id;

    /**
     * 姓名
     */
    private String username;
    /**
     * 入职时间
     */
    private String timeOfEntry;
    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;
    /**
     * 最新工资基数
     */
    private BigDecimal latestSalaryBase;
    /**
     * 最新基本工资基数
     */
    private BigDecimal basicWageBaseForTheLatestMonth;
    /**
     * 最新岗位工资基数
     */
    private BigDecimal salaryBaseForTheLatestMonth;
    /**
     * 当月基本工资基数
     */
    private BigDecimal basicWageBaseForThatMonth;
    /**
     * 当月岗位工资基数
     */
    private BigDecimal salaryBaseForTheMonth;
    /**
     * 交通补贴金额
     */
    private BigDecimal transportationSubsidyAmount;
    /**
     * 通讯补贴金额
     */
    private BigDecimal communicationSubsidyAmount;
    /**
     * 午餐补贴金额
     */
    private BigDecimal lunchAllowanceAmount;
    /**
     * 住房补助金额
     */
    private BigDecimal housingSubsidyAmount;
    /**
     * 社保基数
     */
    private BigDecimal socialSecurityBase;
    /**
     * 社保企业
     */
    private BigDecimal socialSecurityCompanyBase;
    /**
     * 社保个人
     */
    private BigDecimal socialSecurityPersonalBase;
    /**
     * 公积金基数
     */
    private BigDecimal providentFundBase;
    /**
     * 公积金企业
     */
    private BigDecimal providentFundCompanyBase;
    /**
     * 公积金个人
     */
    private BigDecimal providentFundPersonalBase;
    /**
     * 实际出勤天数正式
     */
    private Integer actualAttendanceDaysAreOfficial;
    /**
     * 计薪天数正式
     */
    private BigDecimal officialSalaryDays;
    /**
     * 计薪标准
     */
    private BigDecimal salaryStandard;
    /**
     * 计税方式
     */
    private String taxCountingMethod;
}
