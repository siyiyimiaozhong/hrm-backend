package com.hrm.social_security.controller;

import com.hrm.api.social_security.SocialSecurityControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.social_security.entity.*;
import com.hrm.social_security.service.ArchiveService;
import com.hrm.social_security.service.CompanySettingsService;
import com.hrm.social_security.service.PaymentItemService;
import com.hrm.social_security.service.UserSocialService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 19:04
 * @Description: 社保前端控制器
 */
@RestController
public class SocialSecurityController extends BaseController implements SocialSecurityControllerApi {

    private final CompanySettingsService companySettingsService;
    private final UserSocialService userSocialService;
    private final PaymentItemService paymentItemService;
    private final ArchiveService archiveService;

    public SocialSecurityController(CompanySettingsService companySettingsService,
                                    UserSocialService userSocialService,
                                    PaymentItemService paymentItemService,
                                    ArchiveService archiveService) {
        this.companySettingsService = companySettingsService;
        this.userSocialService = userSocialService;
        this.paymentItemService = paymentItemService;
        this.archiveService = archiveService;
    }

    /**
     * 查询企业是否设置过社保
     *
     * @return
     */
    @Override
    public Result<CompanySettings> settings() {
        CompanySettings cs = companySettingsService.findById(super.companyId);
        return Result.success(cs);
    }

    /**
     * 制作新报表
     * 切换统计周期
     *
     * @param yearMonth
     * @return
     */
    @Override
    public Result<Object> updateSettings(String yearMonth) {
        this.companySettingsService.updateSettings(super.companyId, yearMonth);
        return Result.success();
    }

    /**
     * 保存企业设置
     *
     * @param companySettings
     * @return
     */
    @Override
    public Result<Object> saveSettings(CompanySettings companySettings) {
        this.companySettingsService.saveSettings(super.companyId, companySettings);
        return null;
    }

    /**
     * 查询企业员工的社保信息列表
     *
     * @param map
     * @return
     */
    @Override
    public Result<PageResult<Map<String, Object>>> list(@RequestBody Map<String, Object> map) {
        PageResult<Map<String, Object>> userSocialSecurityPageResult = this.userSocialService.list((Integer) map.get("page"), (Integer) map.get("pageSize"), super.companyId);
        return Result.success(userSocialSecurityPageResult);
    }

    /**
     * 根据用户id查询用户的社保数据
     *
     * @param id
     * @return
     */
    @Override
    public Result<Map<String, Object>> findById(@PathVariable("id") String id) {
        Map<String, Object> map = this.userSocialService.get(id);
        return Result.success(map);
    }

    /**
     * 保存或更新用户社保
     *
     * @param uss
     * @return
     */
    @Override
    public Result<Object> saveUserSocialSecurity(@PathVariable("id") String id, @RequestBody UserSocialSecurity uss) {
        this.userSocialService.saveUserSocialSecurity(uss);
        return Result.success();
    }

    /**
     * 根据城市id查询参保城市的参保项目
     *
     * @param id
     * @return
     */
    @Override
    public Result<List<CityPaymentItem>> findPaymentItem(String id) {
        List<CityPaymentItem> list = paymentItemService.findAllByCityId(id);
        return Result.success(list);
    }

    /**
     * 查询月份数据报表
     * /historys/202202?yearMonth=202202&opType=1
     * opType : 1 (当月数据)
     * : 其他(历史归档的详细记录)
     *
     * @param yearMonth
     * @param opType
     * @return
     */
    @Override
    public Result<List<ArchiveDetail>> historysDetail(String yearMonth, int opType) {
        List<ArchiveDetail> list = this.archiveService.historysDetail(super.companyId, yearMonth, opType);
        return Result.success(list);
    }

    /**
     * 数据归档
     * /historys/202202/archive
     *
     * @param yearMonth
     * @return
     */
    @Override
    public Result<Object> historysDetail(String yearMonth) {
        archiveService.archive(yearMonth, companyId);
        return Result.success();
    }

    /**
     * 查询历史归档列表
     * /historys/2022/list
     *
     * @param year
     * @return
     */
    @Override
    public Result<List<Archive>> historysList(String year) {
        List<Archive> list = archiveService.findArchiveByYear(companyId, year);
        return Result.success(list);
    }

    /**
     * 根据用户id和考勤年月查询用户考勤归档明细
     * /social_securitys/historys/data?userId=1063705482939731968&yearMonth=202202
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    @Override
    public Result<ArchiveDetail> historysData(String userId, String yearMonth) {
        ArchiveDetail archiveDetail = archiveService.findUserArchiveDetail(userId, yearMonth);
        return Result.success(archiveDetail);
    }
}
