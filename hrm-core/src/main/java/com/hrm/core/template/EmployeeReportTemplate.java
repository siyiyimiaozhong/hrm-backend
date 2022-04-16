package com.hrm.core.template;

import com.hrm.core.annotation.ExcelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-15 00:16
 * @Description: 员工报告导出模板类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReportTemplate implements Serializable {
    private static final long serialVersionUID = -4368119735966769975L;

    @ExcelAttribute(name = "编号", sort = 0)
    private String userId;
    @ExcelAttribute(name = "姓名", sort = 1)
    private String username;
    @ExcelAttribute(name = "手机", sort = 2)
    private String mobile;
    @ExcelAttribute(name = "最高学历", sort = 3)
    private String theHighestDegreeOfEducation;
    @ExcelAttribute(name = "国家地区", sort = 4)
    private String nationalArea;
    @ExcelAttribute(name = "护照号", sort = 5)
    private String passportNo;
    @ExcelAttribute(name = "籍贯", sort = 6)
    private String nativePlace;
    @ExcelAttribute(name = "生日", sort = 7)
    private String birthday;
    @ExcelAttribute(name = "属相", sort = 8)
    private String zodiac;
    @ExcelAttribute(name = "入职时间", sort = 9)
    private String timeOfEntry;
    @ExcelAttribute(name = "离职类型", sort = 10)
    private String typeOfTurnover;
    @ExcelAttribute(name = "离职原因", sort = 11)
    private String reasonsForLeaving;
    @ExcelAttribute(name = "离职时间", sort = 12)
    private String resignationTime;
}
