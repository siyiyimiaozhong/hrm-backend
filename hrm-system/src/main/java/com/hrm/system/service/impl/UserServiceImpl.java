package com.hrm.system.service.impl;

import com.hrm.common.constants.ResultCode;
import com.hrm.common.entity.PageResult;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.JwtUtils;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.User;
import com.hrm.domain.system.dto.UserDto;
import com.hrm.domain.system.dto.UserRoleDto;
import com.hrm.domain.system.vo.ProfileVo;
import com.hrm.domain.system.vo.UserVo;
import com.hrm.system.dao.RoleDao;
import com.hrm.system.dao.UserDao;
import com.hrm.system.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:22
 * @Description: 用户业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final IdWorker idWorker;
    private final JwtUtils jwtUtils;
    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(IdWorker idWorker, JwtUtils jwtUtils, UserDao userDao, RoleDao roleDao) {
        this.idWorker = idWorker;
        this.jwtUtils = jwtUtils;
        this.userDao = userDao;
        this.roleDao = roleDao;
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
    public UserVo findUserVoById(String id) {
        User user = this.userDao.findById(id).get();
        return UserVo.toUserVo(user);
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

    @Override
    public void assignRoles(UserRoleDto userRoleDto) {
        User user = userDao.findById(userRoleDto.getId()).get();
        if (null == user) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        Set<Role> roles = new HashSet<>();
        for (String roleId : userRoleDto.getRoleIds()) {
            Role role = roleDao.findById(roleId).get();
            roles.add(role);
        }
        user.setRoles(roles);
        this.userDao.save(user);
    }

    @Override
    public String login(String mobile, String password) {
        User user = this.userDao.findByMobile(mobile);
        if (null == user || !user.getPassword().equals(password)) {
            throw new BusinessException(ResultCode.WRONG_USERNAME_OR_PASSWORD);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", user.getCompanyId());
        map.put("companyName", user.getCompanyName());
        return this.jwtUtils.createJwt(user.getId(), user.getUsername(), map);
    }

    @Override
    public ProfileVo profile(HttpServletRequest request) {
        //1.获取请求头信息：名称=Authorization
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new BusinessException(ResultCode.UNAUTHENTICATED);
        }
        //2.替换Bearer+空格
        String token = authorization.replace("Bearer ", "");
        //3.解析token
        Claims claims = jwtUtils.parseJwt(token);
        String userId = claims.getId();
        User user = this.userDao.findById(userId).get();
        if (null == user) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        return new ProfileVo(user);
    }
}
