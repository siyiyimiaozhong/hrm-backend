package com.hrm.model.attendance.bo;

import com.hrm.model.attendance.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-22 08:58
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ArchiveListBO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 9210657836519694714L;

    @Id
    private String id;

    /**
     * 总人数
     */
    private String totalPeople;

    /**
     * 全勤认数
     */
    private String fullAttendancePeople;

    /**
     * 月份
     */
    private String month;
}
