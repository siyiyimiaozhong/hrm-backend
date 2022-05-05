package com.hrm.api.social_security;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.social_security.dto.ArchiveDto;
import com.hrm.model.social_security.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 19:04
 * @Description: 社保相关Api
 */
@RequestMapping("/social_securitys")
public interface SocialSecurityControllerApi {
    @GetMapping("/settings")
    Result<CompanySettings> settings();

    // historys/202202/newReport

    /**
     * 制作新报表
     * 切换统计周期
     */
    @PutMapping("/historys/{yearMonth}/newReport")
    Result<Object> updateSettings(@PathVariable String yearMonth);

    /**
     * 保存企业设置
     */
    @PostMapping("/settings")
    Result<Object> saveSettings(@RequestBody CompanySettings companySettings);

    /**
     * 查询企业员工的社保信息列表
     */
    @PostMapping("/list")
    Result<PageResult<Map<String, Object>>> list(@RequestBody ArchiveDto archiveDto);

    /**
     * 根据用户id查询用户的社保数据
     */
    @GetMapping("/{id}")
    Result<Map<String, Object>> findById(@PathVariable("id") String id);

    /**
     * 保存或更新用户社保
     */
    @PutMapping("/{id}")
    Result<Object> saveUserSocialSecurity(@PathVariable("id") String id, UserSocialSecurity uss);

    /**
     * 根据城市id查询参保城市的参保项目
     */
    @GetMapping("/payment_item/{id}")
    Result<List<CityPaymentItem>> findPaymentItem(@PathVariable String id);

    /**
     * 查询月份数据报表
     * /historys/202202?yearMonth=202202&opType=1
     * opType : 1 (当月数据)
     * : 其他(历史归档的详细记录)
     */
    @GetMapping("/historys/{yearMonth}")
    Result<List<ArchiveDetail>> historysDetail(@PathVariable String yearMonth, int opType);

    /**
     * 数据归档
     * /historys/202202/archive
     */
    @PostMapping("/historys/{yearMonth}/archive")
    Result<Object> historysDetail(@PathVariable String yearMonth);

    /**
     * 查询历史归档列表
     * /historys/2022/list
     */
    @GetMapping("/historys/{year}/list")
    Result<List<Archive>> historysList(@PathVariable String year);

    /**
     * 根据用户id和考勤年月查询用户考勤归档明细
     * <p>
     * /social_securitys/historys/data?userId=1063705482939731968&yearMonth=201906
     */
    @GetMapping("/historys/data")
    Result<ArchiveDetail> historysData(@RequestParam("userId") String userId, @RequestParam("yearMonth") String yearMonth);
}
