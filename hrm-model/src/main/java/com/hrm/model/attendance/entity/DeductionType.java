package com.hrm.model.attendance.entity;

import com.hrm.model.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 12:51
 * @Description: 扣款类型表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_deduction_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductionType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4550898361035258197L;

    @Id
    private String code;
    private String description;
}