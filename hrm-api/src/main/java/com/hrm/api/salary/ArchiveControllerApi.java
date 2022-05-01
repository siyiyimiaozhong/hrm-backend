package com.hrm.api.salary;

import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.SalaryArchiveDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:34
 * @Description: 薪酬归档Api接口
 */
@RequestMapping("/salarys")
public interface ArchiveControllerApi {

    /**
     * 制作薪资报表
     *  请求URL:/salarys/reports/202202?yearMonth=202202&opType=1
     *      参数:
     *          地址参数 : 202202
     *          请求参数 : opType(1:新制作的报表,其他:查询已归档的报表数据)
     * @param yearMonth
     * @param opType
     * @return
     */
    @GetMapping("/reports/{yearMonth}")
    Result<List<SalaryArchiveDetail>> historyDetail(@PathVariable String yearMonth, @RequestParam("opType") int opType);
}
