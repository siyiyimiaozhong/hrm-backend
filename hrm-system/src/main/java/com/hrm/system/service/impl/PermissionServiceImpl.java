package com.hrm.system.service.impl;

import com.hrm.common.constants.ResultCode;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.BeanMapUtils;
import com.hrm.common.utils.IdWorker;
import com.hrm.domain.system.Permission;
import com.hrm.domain.system.PermissionApi;
import com.hrm.domain.system.PermissionMenu;
import com.hrm.domain.system.PermissionPoint;
import com.hrm.system.constant.PermissionConstant;
import com.hrm.system.dao.PermissionApiDao;
import com.hrm.system.dao.PermissionDao;
import com.hrm.system.dao.PermissionMenuDao;
import com.hrm.system.dao.PermissionPointDao;
import com.hrm.system.service.PermissionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:46
 * @Description: 权限业务层实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final IdWorker idWorker;
    private final PermissionDao permissionDao;
    private final PermissionApiDao permissionApiDao;
    private final PermissionMenuDao permissionMenuDao;
    private final PermissionPointDao permissionPointDao;

    public PermissionServiceImpl(IdWorker idWorker,
                                 PermissionDao permissionDao,
                                 PermissionApiDao permissionApiDao,
                                 PermissionMenuDao permissionMenuDao,
                                 PermissionPointDao permissionPointDao) {
        this.idWorker = idWorker;
        this.permissionDao = permissionDao;
        this.permissionApiDao = permissionApiDao;
        this.permissionMenuDao = permissionMenuDao;
        this.permissionPointDao = permissionPointDao;
    }


    @Override
    public void checkAndInsert(Map<String, Object> map) throws Exception {
        // TODO 需添加校验逻辑 最好重新设计权限实体类
        String id = this.idWorker.nextId() + "";
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        perm.setId(id);
        int type = perm.getType();
        switch (type) {
            case PermissionConstant.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(id);
                this.permissionMenuDao.save(menu);
                break;
            case PermissionConstant.PERMISSION_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(id);
                this.permissionPointDao.save(point);
                break;
            case PermissionConstant.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(id);
                this.permissionApiDao.save(api);
                break;
            default:
                throw new BusinessException(ResultCode.FAIL);
        }
        this.permissionDao.save(perm);
    }

    @Override
    public void checkAndUpdate(String id, Map<String, Object> map) throws Exception {
        // TODO 需添加校验逻辑 最好重新设计权限实体类
        map.put("id", id);
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        Permission permission = this.permissionDao.findById(perm.getId()).get();
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        permission.setEnVisible(perm.getEnVisible());
        int type = perm.getType();
        switch (type) {
            case PermissionConstant.PERMISSION_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(perm.getId());
                this.permissionMenuDao.save(menu);
                break;
            case PermissionConstant.PERMISSION_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(perm.getId());
                this.permissionPointDao.save(point);
                break;
            case PermissionConstant.PERMISSION_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(perm.getId());
                this.permissionApiDao.save(api);
                break;
            default:
                throw new BusinessException(ResultCode.FAIL);
        }
        this.permissionDao.save(permission);
    }

    @Override
    public List<Permission> findAll(Map<String, Object> map) {
        //1.需要查询条件
        Specification<Permission> spec = new Specification<Permission>() {
            /**
             * 动态拼接查询条件
             * @return
             */
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))) {
                    list.add(criteriaBuilder.equal(root.get("pid").as(String.class), (String) map.get("pid")));
                }
                //根据enVisible查询 0:查询所有saas平台的最高权限 1:查询企业的权限
                if (!StringUtils.isEmpty(map.get("enVisible"))) {
                    list.add(criteriaBuilder.equal(root.get("enVisible").as(String.class), (String) map.get("enVisible")));
                }
                //根据类型 type 0：菜单+按钮 1：菜单 2：按钮 3：API接口
                if (!StringUtils.isEmpty(map.get("type"))) {
                    String ty = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                    if ("0".equals(ty)) {
                        in.value(1).value(2);
                    } else {
                        in.value(Integer.parseInt(ty));
                    }
                    list.add(in);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(spec);
    }

    @Override
    public Map<String, Object> findById(String id) {
        Permission perm = permissionDao.findById(id).get();
        int type = perm.getType();

        Object object = null;

        if (type == PermissionConstant.PERMISSION_MENU) {
            object = permissionMenuDao.findById(id).get();
        } else if (type == PermissionConstant.PERMISSION_POINT) {
            object = permissionPointDao.findById(id).get();
        } else if (type == PermissionConstant.PERMISSION_API) {
            object = permissionApiDao.findById(id).get();
        } else {
            throw new BusinessException(ResultCode.FAIL);
        }

        Map<String, Object> map = BeanMapUtils.beanToMap(object);
        map.put("name", perm.getName());
        map.put("type", perm.getType());
        map.put("code", perm.getCode());
        map.put("description", perm.getDescription());
        map.put("pid", perm.getPid());
        map.put("enVisible", perm.getEnVisible());
        return map;
    }

    @Override
    public void delete(String[] ids) {
        if (0 == ids.length) {
            return;
        }
        for (String id : ids) {
            //1.通过传递的权限id查询权限
            Permission permission = permissionDao.findById(id).get();
            permissionDao.delete(permission);
            //2.根据类型构造不同的资源
            int type = permission.getType();
            switch (type) {
                case PermissionConstant.PERMISSION_MENU:
                    permissionMenuDao.deleteById(id);
                    break;
                case PermissionConstant.PERMISSION_POINT:
                    permissionPointDao.deleteById(id);
                    break;
                case PermissionConstant.PERMISSION_API:
                    permissionApiDao.deleteById(id);
                    break;
                default:
                    throw new BusinessException(ResultCode.FAIL);
            }
        }
    }
}
