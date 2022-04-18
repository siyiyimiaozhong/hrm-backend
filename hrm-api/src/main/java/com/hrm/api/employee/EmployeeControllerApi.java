package com.hrm.api.employee;

import com.hrm.core.entity.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:26
 * @Description: 员工接口Api
 */
@RequestMapping("/employees")
public interface EmployeeControllerApi {
    /**
     * 导入员工信息
     *
     * @param attachment
     * @return
     */
    @PostMapping("/import")
    Result<Object> importData(@RequestParam("file") MultipartFile attachment);

    /**
     * 当月人事报表导出
     *
     * @param month 年-月 2022-02
     * @return
     */
    @GetMapping("/export/{month}")
    void export(@PathVariable("month") String month, HttpServletResponse response);

    /**
     * 根据id获取员工简介pdf
     *
     * @param id
     */
    @GetMapping("/{id}/pdf")
    void exportPdf(@PathVariable("id") String id);
}
