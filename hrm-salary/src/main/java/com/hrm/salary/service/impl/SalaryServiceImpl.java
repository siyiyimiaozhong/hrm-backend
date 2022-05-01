package com.hrm.salary.service.impl;

import com.hrm.core.constant.FormOfEmploymentEnum;
import com.hrm.core.pojo.PageResult;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.salary.vo.UserSalaryItemVo;
import com.hrm.salary.dao.UserSalaryDao;
import com.hrm.salary.service.SalaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:55
 * @Description: 薪酬业务层实现类
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    private final UserSalaryDao userSalaryDao;

    public SalaryServiceImpl(UserSalaryDao userSalaryDao) {
        this.userSalaryDao = userSalaryDao;
    }

    @Override
    public void saveUserSalary(UserSalary userSalary) {
        this.userSalaryDao.save(userSalary);
    }

    @Override
    public UserSalary findUserSalary(String userId) {
        return this.userSalaryDao.findById(userId).orElse(null);
    }

    @Override
    public PageResult<UserSalaryItemVo> findAll(Integer page, Integer pageSize, String companyId) {
        Page<Map<String, Object>> mapPage = this.userSalaryDao.findPage(companyId, new PageRequest(page - 1, pageSize));
        List<UserSalaryItemVo> userSalaryItemVos = getUserSalaryItemVo(mapPage.getContent());
        return new PageResult<>(mapPage.getTotalElements(), userSalaryItemVos);
    }

    private List<UserSalaryItemVo> getUserSalaryItemVo(List<Map<String, Object>> content) {
        List<UserSalaryItemVo> itemVos = new LinkedList<>();
        for (Map<String, Object> map : content) {
            UserSalaryItemVo userSalaryItemVo = new UserSalaryItemVo();
            userSalaryItemVo.setId(map.get("id").toString());
            userSalaryItemVo.setUsername(map.get("username").toString());
            userSalaryItemVo.setMobile(map.get("mobile").toString());
            userSalaryItemVo.setWorkNumber(map.get("workNumber").toString());
            userSalaryItemVo.setFormOfEmployment(FormOfEmploymentEnum.getEnumByKey((Integer) map.get("formOfEmployment")).getValue());
            userSalaryItemVo.setDepartmentName(map.get("departmentName").toString());

            Date timeOfEntry = (Date)map.get("timeOfEntry");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            userSalaryItemVo.setTimeOfEntry(format.format(timeOfEntry));

            if (null != map.get("currentBasicSalary") && null != map.get("currentPostWage")) {
                BigDecimal currentBasicSalary = (BigDecimal)map.get("currentBasicSalary");
                BigDecimal currentPostWage = (BigDecimal)map.get("currentPostWage");
                userSalaryItemVo.setWageBase(currentBasicSalary.add(currentPostWage));
                userSalaryItemVo.setIsFixed(true);
            } else {
                userSalaryItemVo.setIsFixed(false);
            }
            userSalaryItemVo.setSubsidyScheme("通用方案");

            itemVos.add(userSalaryItemVo);
        }
        return itemVos;
    }
}
