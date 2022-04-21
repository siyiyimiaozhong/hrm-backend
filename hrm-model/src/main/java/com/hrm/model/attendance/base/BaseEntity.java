package com.hrm.model.attendance.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 09:07
 * @Description: entity基础类
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {


    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String remarks;
}
