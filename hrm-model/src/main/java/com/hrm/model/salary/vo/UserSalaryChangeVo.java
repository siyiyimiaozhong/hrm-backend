package com.hrm.model.salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:51
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSalaryChangeVo implements Serializable {
    private static final long serialVersionUID = 1351618691339032442L;
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 调整基本工资
     */
    private BigDecimal adjustmentOfBasicWages;
    /**
     * 调整岗位工资
     */
    private BigDecimal adjustPostWages;
    /**
     * 调整生效时间
     */
    private String effectiveTimeOfPayAdjustment;
    /**
     * 调整原因
     */
    private String causeOfSalaryAdjustment;
    /**
     * 附件
     */
    private String enclosure;
}
