package com.hrm.employee.service.impl;

import com.hrm.core.entity.PageResult;
import com.hrm.employee.dao.ArchiveDao;
import com.hrm.employee.service.ArchiveService;
import com.hrm.model.employee.Archive;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 12:20
 * @Description: 月度员工归档业务层实现类
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {
    private final ArchiveDao archiveDao;

    public ArchiveServiceImpl(ArchiveDao archiveDao) {
        this.archiveDao = archiveDao;
    }

    @Override
    public PageResult<Archive> list(String companyId, Integer pageSize, Integer page, String year) {
        Page<Archive> searchPage = this.archiveDao.findAll(createSpecification(companyId, year), PageRequest.of(page - 1, pageSize));
        return new PageResult<>(searchPage.getTotalElements(), searchPage.getContent());
    }

    private Specification<Archive> createSpecification(String companyId, String year) {
        return new Specification<Archive>() {
            @Override
            public Predicate toPredicate(Root<Archive> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 企业id
                if (!CommonUtils.isBlank(companyId)) {
                    predicateList.add(cb.like(root.get("companyId").as(String.class), companyId));
                }
                if (!CommonUtils.isBlank(year)) {
                    predicateList.add(cb.like(root.get("mouth").as(String.class), year));
                }
                return cb.and(predicateList.toArray(new Predicate[0]));
            }
        };
    }
}
