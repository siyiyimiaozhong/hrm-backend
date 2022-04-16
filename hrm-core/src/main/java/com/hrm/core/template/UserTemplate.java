package com.hrm.core.template;

import com.hrm.core.annotation.ExcelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-06 22:31
 * @Description: 用户信息Excel模板类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTemplate implements Serializable {
    private static final long serialVersionUID = -1605433741449451697L;

    @ExcelAttribute(name = "用户名", sort = 1)
    private String username;
    @ExcelAttribute(name = "手机号", sort = 2)
    private String mobile;
    @ExcelAttribute(name = "工号", sort = 3)
    private String workNumber;
    @ExcelAttribute(name = "聘用形式", sort = 4)
    private Integer formOfEmployment;
    @ExcelAttribute(name = "入职时间", sort = 5)
    private Date timeOfEntry;
    @ExcelAttribute(name = "部门编码", sort = 6)
    private String departmentId;
}
