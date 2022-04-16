package com.hrm.company.service.impl;

import com.hrm.common.utils.IdWorker;
import com.hrm.company.dao.CompanyDao;
import com.hrm.company.service.CompanyService;
import com.hrm.core.constant.ResultCode;
import com.hrm.core.constant.StateConstant;
import com.hrm.core.exception.BusinessException;
import com.hrm.model.company.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-14 7:29
 * @Description: company业务层实现类
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao;
    private final IdWorker idWorker;

    public CompanyServiceImpl(CompanyDao companyDao, IdWorker idWorker) {
        this.companyDao = companyDao;
        this.idWorker = idWorker;
    }

    @Override
    public void save(Company company) {
        // 基本属性设置
        Long id = this.idWorker.nextId();
        company.setId(id + "");
        company.setAuditState(StateConstant.FAIL);
        company.setState(StateConstant.ACTIVATION);
        this.companyDao.save(company);
    }

    @Override
    public void update(String id, Company company) {
        Company temp = this.companyDao.findById(id).get();
        if (null == temp) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        this.companyDao.save(temp);
    }

    @Override
    public void deleteById(String id) {
        Company company = this.companyDao.findById(id).get();
        if (null == company) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        this.companyDao.deleteById(id);
    }

    @Override
    public Company get(String id) {
        Optional<Company> company = this.companyDao.findById(id);
        if (!company.isPresent()) {
            throw new RuntimeException("参数验证失败");
        }
        return company.get();
    }

    @Override
    public List<Company> findAll() {
        return this.companyDao.findAll();
    }
}
