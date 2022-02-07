package com.hrm.company.service.impl;

import com.hrm.common.utils.IdWorker;
import com.hrm.company.dao.DepartmentDao;
import com.hrm.company.service.CompanyService;
import com.hrm.company.service.DepartmentService;
import com.hrm.domain.company.Company;
import com.hrm.domain.company.Department;
import com.hrm.domain.company.vo.CompanyDepartmentListVo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 10:53
 * @Description: 部门业务处理实现类
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService {
    private final DepartmentDao departmentDao;
    private final IdWorker idWorker;
    private final CompanyService companyService;

    public DepartmentServiceImpl(DepartmentDao departmentDao, CompanyService companyService, IdWorker idWorker) {
        this.departmentDao = departmentDao;
        this.companyService = companyService;
        this.idWorker = idWorker;
    }


    @Override
    public void checkAndInsert(Department department) {
        //TODO 校验逻辑
        String id = idWorker.nextId() + "";
        department.setId(id);
        this.departmentDao.save(department);
    }

    @Override
    public void checkAndUpdate(String id, Department department) {
        Department dept = this.departmentDao.findById(id).get();
        //TODO 校验逻辑
        dept.setCode(department.getCode());
        dept.setIntroduce(department.getIntroduce());
        dept.setName(department.getName());
        this.departmentDao.save(dept);
    }

    @Override
    public Department findById(String id) {
        return this.departmentDao.findById(id).get();
    }

    @Override
    public CompanyDepartmentListVo findAllByCompanyId(String companyId) {
        Specification<Department> spec = this.getSpec(companyId);
        List<Department> departments = this.departmentDao.findAll(spec);
        Company company = this.companyService.get(companyId);
        return new CompanyDepartmentListVo(company, departments);
    }

    @Override
    public void delete(String[] ids) {
        if (0 == ids.length) {
            return;
        }
        for (String id : ids) {
            //TODO 校验逻辑
            this.departmentDao.deleteById(id);
        }
    }
}
