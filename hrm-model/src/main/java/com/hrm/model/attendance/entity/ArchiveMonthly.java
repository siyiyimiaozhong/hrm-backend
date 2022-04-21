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
 * @CreateTime: Created in 2022-04-20 09:05
 * @Description: 考勤归档信息表实体类
 */
@Table(name = "atte_archive_monthly")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveMonthly extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4640010359890022961L;

    @Id
    private String id;
    private String companyId;
    private String departmentId;

    private String archiveYear;
    private String archiveMonth;
    private Integer totalPeopleNum;

    private Integer fullAttePeopleNum;
    private Integer isArchived;
}
