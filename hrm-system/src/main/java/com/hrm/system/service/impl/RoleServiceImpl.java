package com.hrm.system.service.impl;

import com.hrm.common.constants.ResultCode;
import com.hrm.common.entity.PageResult;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.service.BaseService;
import com.hrm.common.utils.IdWorker;
import com.hrm.domain.system.Permission;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.dto.RoleDto;
import com.hrm.domain.system.vo.RoleVo;
import com.hrm.system.constant.PermissionConstant;
import com.hrm.system.dao.PermissionDao;
import com.hrm.system.dao.RoleDao;
import com.hrm.system.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:41
 * @Description: 角色业务层实现类
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {
    private final IdWorker idWorker;
    private final RoleDao roleDao;
    private final PermissionDao permissionDao;

    public RoleServiceImpl(IdWorker idWorker, RoleDao roleDao, PermissionDao permissionDao) {
        this.idWorker = idWorker;
        this.roleDao = roleDao;
        this.permissionDao = permissionDao;
    }


    @Override
    public void assignPerms(RoleDto roleDto) {
        //TODO 角色id校验
        Role role = this.roleDao.findById(roleDto.getId()).get();
        //2.构造角色的权限集合
        Set<Permission> perms = new HashSet<>();
        for (String permId : roleDto.getPermIds()) {
            Permission permission = this.permissionDao.findById(permId).get();
            List<Permission> apiList = this.permissionDao.findByTypeAndPid(PermissionConstant.PERMISSION_API, permission.getId());
            perms.addAll(apiList);
            perms.add(permission);
        }
        role.setPermissions(perms);
        this.roleDao.save(role);
    }

    @Override
    public void checkAndInsert(Role role) {
        //TODO 角色校验
        //TODO 填充其他参数
        role.setId(idWorker.nextId() + "");
        this.roleDao.save(role);
    }

    @Override
    public void delete(String[] ids) {
        if (0 == ids.length) {
            return;
        }
        for (String id : ids) {
            this.roleDao.deleteById(id);
        }
    }

    @Override
    public RoleVo findRoleVoById(String id) {
        Role role = this.roleDao.findById(id).get();
        if (null == role) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        return new RoleVo(role);
    }

    @Override
    public PageResult<Role> page(String companyId, int page, int size) {
        Page<Role> pageResult = this.roleDao.findAll(getSpec(companyId), PageRequest.of(page - 1, size));
        return new PageResult<>(pageResult.getTotalElements(), pageResult.getContent());
    }

    @Override
    public List<Role> findAllByCompanyId(String companyId) {
        return this.roleDao.findAll(getSpec(companyId));
    }

    @Override
    public void checkAndUpdate(String id, Role role) {
        Role roleTmp = this.roleDao.findById(id).get();
        if (null == roleTmp) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        roleTmp.setDescription(role.getDescription());
        roleTmp.setName(role.getName());
        this.roleDao.save(roleTmp);
    }
}
