package com.hrm.model.salary.entity;

import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.social_security.entity.ArchiveDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:17
 * @Description: 工资归档详情表实体类
 */
@Entity
@Table(name = "sa_archive_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryArchiveDetail implements Serializable {

    private static final long serialVersionUID = 6021094301665428271L;

    /**
     * id
     */
    @Id
    private String id;
    /**
     * 归档id
     */
    private String archiveId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 姓名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 在职状态
     */
    private String inServiceStatus;
    /**
     * 聘用形式
     */
    private String formOfEmployment;
    /**
     * 银行卡号
     */
    private String bankCardNumber;
    /**
     * 开户行
     */
    private String openingBank;

    //社保相关
    /**
     * 公积金个人
     */
    private BigDecimal providentFundIndividual;
    /**
     * 社保个人
     */
    private BigDecimal socialSecurityIndividual;
    /**
     * 养老个人
     */
    private BigDecimal oldAgeIndividual;
    /**
     * 医疗个人
     */
    private BigDecimal medicalIndividual;
    /**
     * 失业个人
     */
    private BigDecimal unemployedIndividual;
    /**
     * 大病个人
     */
    private BigDecimal aPersonOfGreatDisease;
    /**
     * 社保扣款 个人社保扣款
     */
    private BigDecimal socialSecurity;
    /**
     * 公积金扣款 个人公积金扣款
     */
    private BigDecimal totalProvidentFundIndividual;
    /**
     * 社保企业
     */
    private BigDecimal socialSecurityEnterprise;
    /**
     * 养老企业
     */
    private BigDecimal pensionEnterprise;
    /**
     * 医疗企业
     */
    private BigDecimal medicalEnterprise;
    /**
     * 失业企业
     */
    private BigDecimal unemployedEnterprise;
    /**
     * 工伤企业
     */
    private BigDecimal industrialInjuryEnterprise;
    /**
     * 生育企业
     */
    private BigDecimal childbearingEnterprise;
    /**
     * 大病企业
     */
    private BigDecimal bigDiseaseEnterprise;
    /**
     * 公积金企业
     */
    private BigDecimal providentFundEnterprises;
    /**
     * 公积金社保企业
     */
    private BigDecimal socialSecurityProvidentFundEnterprises;
    /**
     * 公积金需纳税额 ？
     */
    private BigDecimal taxToProvidentFund;

    //考勤相关
    /**
     * 计薪天数
     */
    private BigDecimal officialSalaryDays;
    /**
     * 考勤扣款
     */
    private String attendanceDeductionMonthly;
    /**
     * 计薪标准
     */
    private BigDecimal salaryStandard;

    //薪资相关
    /**
     * 最新工资基数合计 基本工资+岗位工资
     */
    private BigDecimal currentSalaryTotalBase;
    /**
     * 最新基本工资基数
     */
    private BigDecimal currentBaseSalary;
    /**
     * 当月基本工资基数
     */
    private BigDecimal baseSalaryByMonth;
    /**
     * 计税方式
     */
    private String taxCountingMethod;
    /**
     * 当月纳税基本工资 = 当月基本工资基数
     */
    private BigDecimal baseSalaryToTaxByMonth;
    /**
     * 税前工资合计 （当月基本工资+当月岗位工资）
     */
    private BigDecimal salaryBeforeTax;
    /**
     * 工资合计 （基本工资+岗位工资+津贴
     */
    private BigDecimal salary;
    /**
     * 应纳税工资 基本工资 + 岗位工资 + （【开关】津贴）
     */
    private BigDecimal salaryByTax;
    /**
     * 税前实发  基本工资 + 岗位工资 + 津贴 - 五险一金  ？
     */
    private BigDecimal paymentBeforeTax;
    /**
     * 应扣税 （工资 + 【开关】津贴）* 阶梯税率 - 速算扣除数
     */
    private BigDecimal tax;
    /**
     * 税后工资合计 税前工资 - 税
     */
    private BigDecimal salaryAfterTax;
    /**
     * 实发工资  基本工资+岗位工资 + 津贴 - 五险一金 -税
     */
    private BigDecimal payment;
    /**
     * 实发工资备注
     */
    private String paymentRemark;
    /**
     * 薪酬成本  0
     */
    private BigDecimal salaryCost;
    /**
     * 企业人工成本 0
     */
    private BigDecimal enterpriseLaborCost;
    /**
     * 调薪金额 当月的
     */
    private BigDecimal salaryChangeAmount;
    /**
     * 调薪比例
     */
    private BigDecimal salaryChangeScale;
    /**
     * 调薪生效时间
     */
    private String effectiveTimeOfPayAdjustment;
    /**
     * 调薪原因
     */
    private String causeOfSalaryAdjustment;
    /**
     * 注释  --
     */
    private String remark;
    /**
     * 发薪月数   0
     */
    private Integer paymentMonths;

    public SalaryArchiveDetail(String userId, String mobile, String username, String departmentName) {
        this.userId = userId;
        this.mobile = mobile;
        this.username = username;
        this.departmentName = departmentName;
    }

    public BigDecimal getProvidentFundEnterprises() {
        return this.providentFundEnterprises == null ? new BigDecimal(0) : this.providentFundEnterprises;
    }

    public BigDecimal getSocialSecurityEnterprise() {
        return this.socialSecurityEnterprise == null ? new BigDecimal(0) : this.socialSecurityEnterprise;
    }

    public BigDecimal getSocialSecurityIndividual() {
        return this.socialSecurityIndividual == null ? new BigDecimal(0) : this.socialSecurityIndividual;
    }

    public BigDecimal getProvidentFundIndividual() {
        return this.providentFundIndividual == null ? new BigDecimal(0) : this.providentFundIndividual;
    }

    public BigDecimal getSalaryBeforeTax() {
        return this.salaryBeforeTax == null ? new BigDecimal(0) : this.salaryBeforeTax;
    }

    public BigDecimal getEntTotal() {
        return getProvidentFundEnterprises().add(getSocialSecurityEnterprise());
    }

    public BigDecimal getPerTotal() {
        return getSocialSecurityIndividual().add(getProvidentFundIndividual());
    }


    //设置用户属性
    public void setUser(Map map) {
        this.username = map.get("username").toString();
        this.departmentName = map.get("departmentName").toString();
        this.mobile = map.get("mobile").toString();
        this.userId = map.get("id").toString();
        this.workNumber = map.get("workNumber").toString();
        this.inServiceStatus = map.get("inServiceStatus").toString();
    }

    //设置社保属性
    public void setSocialInfo(ArchiveDetail socialInfo) {
        this.providentFundIndividual = socialInfo.getProvidentFundIndividual();
        this.providentFundEnterprises = socialInfo.getProvidentFundEnterprises();
        this.socialSecurityEnterprise = socialInfo.getSocialSecurityEnterprise();
        this.socialSecurityIndividual = socialInfo.getSocialSecurityIndividual();
        //社保合计
        this.socialSecurityProvidentFundEnterprises = this.providentFundEnterprises.add(this.socialSecurityEnterprise);
    }

    //设置考勤属性
    public void setAtteInfo(ArchiveMonthlyInfo atteInfo) {
        //员工考勤天数
        this.officialSalaryDays = new BigDecimal(atteInfo.getSalaryOfficialDays());
    }

    //设置员工工资属性
    public void setUserSalary(UserSalary userSalary) {
        if (userSalary != null) {
            this.currentSalaryTotalBase = userSalary.getCurrentBasicSalary().add(userSalary.getCurrentPostWage());
            this.currentBaseSalary = userSalary.getCurrentBasicSalary();
            this.baseSalaryByMonth = userSalary.getCurrentBasicSalary();
        } else {
            this.currentSalaryTotalBase = BigDecimal.ZERO;
            this.currentBaseSalary = BigDecimal.ZERO;
            this.baseSalaryByMonth = BigDecimal.ZERO;
        }
    }

    // 计算工资
    public void calSalary(Settings settings) {
        //计算福利津贴
        BigDecimal money = BigDecimal.ZERO;
        if (settings.getCommunicationSubsidyScheme() == 3) {
            money = money.add(settings.getCommunicationSubsidyAmount());
        } else {
            money = money.add(settings.getCommunicationSubsidyAmount().multiply(this.officialSalaryDays));
        }
        if (settings.getHousingSubsidyScheme() == 3) {
            money = money.add(settings.getHousingSubsidyAmount());
        } else {
            money = money.add(settings.getHousingSubsidyAmount().multiply(this.officialSalaryDays));
        }
        if (settings.getLunchAllowanceScheme() == 3) {
            money = money.add(settings.getLunchAllowanceAmount());
        } else {
            money = money.add(settings.getLunchAllowanceAmount().multiply(this.officialSalaryDays));
        }
        if (settings.getTransportationSubsidyScheme() == 3) {
            money = money.add(settings.getTransportationSubsidyAmount());
        } else {
            money = money.add(settings.getTransportationSubsidyAmount().multiply(this.officialSalaryDays));
        }

        //津贴
        this.salaryChangeAmount = money;

        //计算考勤扣款
        BigDecimal attendanceMoney = this.currentSalaryTotalBase;

        // 20 , 10000, 500  19
        if (officialSalaryDays.compareTo(new BigDecimal(21.75)) <= 0) {
            attendanceMoney = this.currentSalaryTotalBase.
                    divide(new BigDecimal(21.75), 2, ROUND_HALF_UP).
                    multiply(this.officialSalaryDays);
            this.attendanceDeductionMonthly = this.currentSalaryTotalBase.subtract(attendanceMoney).toString();
        } else {
            this.attendanceDeductionMonthly = "0";
        }

        this.currentSalaryTotalBase = attendanceMoney.add(money);

        //计算应纳税工资 (岗位工资 + 基本工资 + 补助 - 缴纳公积金和社保)

        this.salaryByTax = this.currentSalaryTotalBase.subtract(this.providentFundIndividual).subtract(this.socialSecurityIndividual);
        this.salaryByTax = this.salaryByTax.compareTo(BigDecimal.ZERO) >= 0 ? this.salaryByTax : BigDecimal.ZERO;

        //计算税(扣除员工社保和员工公积金部门)
        this.tax = getTax(salaryByTax);

        //计算实发工资
        this.payment = attendanceMoney.subtract(tax);
        this.payment = this.payment.compareTo(BigDecimal.ZERO) >= 0 ? this.payment : BigDecimal.ZERO;
    }

    private BigDecimal getTax(BigDecimal salary) {
        //salary * 阶梯税率 - 速算扣除数
        BigDecimal easyNum = new BigDecimal(0);
        BigDecimal stageNum = new BigDecimal(0.03);
        if (salary.doubleValue() <= 3500) {
            return BigDecimal.ZERO;
        } else if (salary.doubleValue() > 1500) {
            easyNum = new BigDecimal(105);
            stageNum = new BigDecimal(0.1);
        } else if (salary.doubleValue() > 4500) {
            easyNum = new BigDecimal(555);
            stageNum = new BigDecimal(0.2);
        } else if (salary.doubleValue() > 9000) {
            easyNum = new BigDecimal(1005);
            stageNum = new BigDecimal(0.25);
        } else if (salary.doubleValue() > 35000) {
            easyNum = new BigDecimal(2755);
            stageNum = new BigDecimal(0.3);
        } else if (salary.doubleValue() > 55000) {
            easyNum = new BigDecimal(5505);
            stageNum = new BigDecimal(0.35);
        } else if (salary.doubleValue() > 55000) {
            easyNum = new BigDecimal(5505);
            stageNum = new BigDecimal(0.35);
        } else if (salary.doubleValue() > 80000) {
            easyNum = new BigDecimal(13505);
            stageNum = new BigDecimal(0.45);
        }
        salary = salary.multiply(stageNum);
        salary = salary.subtract(easyNum);
        return salary;
    }
}
