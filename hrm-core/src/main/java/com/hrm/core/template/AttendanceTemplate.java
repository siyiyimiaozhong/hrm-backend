package com.hrm.core.template;

import com.hrm.core.annotation.ExcelAttribute;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 00:31
 * @Description: 考勤导入模板
 */
@Data
public class AttendanceTemplate implements Serializable {
    private static final long serialVersionUID = 6580750044938418557L;

    @ExcelAttribute(sort = 0, format = "String", name = "员工姓名")
    private String username;
    @ExcelAttribute(sort = 1, format = "String", name = "手机号")
    private String mobile;
    @ExcelAttribute(sort = 2, format = "String", name = "工号")
    private String workNumber;
    @ExcelAttribute(sort = 3, format = "Date", name = "上班时间")
    private Date inTime;
    @ExcelAttribute(sort = 4, format = "Date", name = "下班时间")
    private Date outTime;
    @ExcelAttribute(sort = 5, format = "String", name = "考勤日期")
    private String atteDate;
}
