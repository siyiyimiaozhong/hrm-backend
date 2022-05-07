package com.hrm.api.employee;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.employee.entity.Archive;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:50
 * @Description: 归档Api
 */
@RequestMapping("/employees/archives")
public interface ArchivesControllerApi {

    /**
     * 历史归档详情列表
     *
     * @param month
     * @param type
     * @return
     */
    @GetMapping("/{month}")
    Result<Object> archives(@PathVariable("month") String month, @RequestParam(name = "type") Integer type);

    /**
     * 归档更新
     *
     * @param month
     * @return
     */
    @PutMapping("/{month}")
    Result<Object> saveArchives(@PathVariable("month") String month);

    /**
     * 历史归档列表
     *
     * @param pageSize
     * @param page
     * @param year
     * @return
     */
    @GetMapping("/list")
    Result<PageResult<Archive>> list(@RequestParam("pagesize") Integer pageSize, @RequestParam("page") Integer page, @RequestParam("year") String year);
}
