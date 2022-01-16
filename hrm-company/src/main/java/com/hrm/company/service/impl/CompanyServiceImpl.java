package com.hrm.company.service.impl;

import com.hrm.common.constants.ResultCode;
import com.hrm.common.constants.StateConstant;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.IdWorker;
import com.hrm.company.dao.CompanyDao;
import com.hrm.company.service.CompanyService;
import com.hrm.domain.company.Company;
import org.springframework.stereotype.Service;

import java.util.List;

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
        company.setId(id);
        company.setAuditState(StateConstant.FAIL);
        company.setState(StateConstant.ACTIVATION);
        this.companyDao.save(company);
    }

    @Override
    public void update(Long id, Company company) {
        Company temp = this.companyDao.findById(id).get();
        if (null == temp) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        this.companyDao.save(temp);
    }

    @Override
    public void deleteById(Long id) {
        Company company = this.companyDao.findById(id).get();
        if (null == company) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        this.companyDao.deleteById(id);
    }

    @Override
    public Company get(Long id) {
        Company company = this.companyDao.findById(id).get();
        if (null == company) {
            throw new RuntimeException("参数验证失败");
        }
        return company;
    }

    @Override
    public List<Company> findAll() {
        return this.companyDao.findAll();
    }
}
