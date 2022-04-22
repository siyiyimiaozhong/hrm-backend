package com.hrm.model.attendance.bo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-22 09:06
 * @Description:
 */
@Entity
@Data
public class DaysMonthlyBO implements Serializable {

    private static final long serialVersionUID = 4981562663339590582L;

    @Id
    private String day;
}
