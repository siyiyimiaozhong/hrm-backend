package com.hrm.system.service.impl;

import com.hrm.common.entity.PageResult;
import com.hrm.common.utils.IdWorker;
import com.hrm.domain.system.User;
import com.hrm.domain.system.dto.UserDto;
import com.hrm.system.dao.UserDao;
import com.hrm.system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:22
 * @Description: 用户业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final IdWorker idWorker;

    public UserServiceImpl(UserDao userDao, IdWorker idWorker) {
        this.userDao = userDao;
        this.idWorker = idWorker;
    }

    @Override
    public void checkAndInsert(User user) {
        //TODO 校验逻辑
        String id = idWorker.nextId() + "";
        user.setId(id);
        user.setPassword("123456");
        user.setEnableState(1);
        this.userDao.save(user);
    }

    @Override
    public void checkAndUpdate(String id, User user) {
        User userInfo = this.userDao.findById(id).get();
        //TODO 校验逻辑
        userInfo.setUsername(user.getUsername());
        userInfo.setPassword(user.getPassword());
        userInfo.setDepartmentId(user.getDepartmentId());
        userInfo.setDepartmentName(user.getDepartmentName());
        this.userDao.save(userInfo);
    }

    @Override
    public User findById(String id) {
        return this.userDao.findById(id).get();
    }

    @Override
    public void delete(String[] ids) {
        if (0 == ids.length) {
            return;
        }
        for (String id : ids) {
            //TODO 校验逻辑
            this.userDao.deleteById(id);
        }
    }

    @Override
    public PageResult<User> findAll(UserDto userDto) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new LinkedList<>();
                // 根据请求的companyId是否为空构造查询条件
                if (!StringUtils.isEmpty(userDto.getCompanyId())) {
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class), userDto.getCompanyId()));
                }
                // 根据请求的部门id构建查询条件
                if (!StringUtils.isEmpty(userDto.getDepartmentId())) {
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class), userDto.getDepartmentId()));
                }
                if (!StringUtils.isEmpty(userDto.getHasDept())) {
                    // 根据请求的hasDept判断 是否分配部门 0:未分配 1:已分配
                    if ("1".equals(userDto.getHasDept())) {
                        list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    } else {
                        list.add(criteriaBuilder.isNull(root.get("departmentId")));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[0]));
            }
        };
        Page<User> userPage = this.userDao.findAll(specification, PageRequest.of(userDto.getPage() - 1, userDto.getSize()));
        return new PageResult<>(userPage.getTotalElements(), userPage.getContent());
    }
}
