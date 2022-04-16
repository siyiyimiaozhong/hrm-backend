package com.hrm.system.service.impl;

import com.hrm.common.properties.QiniuyunConfig;
import com.hrm.common.service.BaseService;
import com.hrm.common.utils.*;
import com.hrm.core.constant.FileImportTemplateEnum;
import com.hrm.core.constant.ResultCode;
import com.hrm.core.constant.UserConstant;
import com.hrm.core.entity.PageResult;
import com.hrm.core.entity.Result;
import com.hrm.core.exception.BusinessException;
import com.hrm.core.template.UserTemplate;
import com.hrm.model.company.Department;
import com.hrm.model.system.Role;
import com.hrm.model.system.User;
import com.hrm.model.system.dto.UserDto;
import com.hrm.model.system.dto.UserRoleDto;
import com.hrm.model.system.vo.ProfileVo;
import com.hrm.model.system.vo.UserSimpleVo;
import com.hrm.model.system.vo.UserVo;
import com.hrm.system.client.DepartmentFeignClient;
import com.hrm.system.dao.RoleDao;
import com.hrm.system.dao.UserDao;
import com.hrm.system.service.UserService;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:22
 * @Description: 用户业务层实现类
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    private final IdWorker idWorker;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final DepartmentFeignClient departmentFeignClient;
    private final QiniuyunConfig qiniuyunConfig;
    @Value("${user.default-password}")
    private String DEFAULT_PASSWORD;

    public UserServiceImpl(IdWorker idWorker,
                           UserDao userDao,
                           RoleDao roleDao,
                           DepartmentFeignClient departmentFeignClient,
                           QiniuyunConfig qiniuyunConfig) {
        this.idWorker = idWorker;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.departmentFeignClient = departmentFeignClient;
        this.qiniuyunConfig = qiniuyunConfig;
    }

    @Override
    public void checkAndInsert(User user) {
        //TODO 校验逻辑
        String id = idWorker.nextId() + "";
        user.setId(id);
        String password = new Md5Hash(DEFAULT_PASSWORD, user.getMobile(), 3).toString();
        user.setLevel(UserConstant.USER);
        user.setPassword(password);
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
    public User findByMobile(String mobile) {
        return this.userDao.findByMobile(mobile);
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
        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
            password = new Md5Hash(password, mobile, 3).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(mobile, password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionId
            //5.构造返回结果
            return (String) subject.getSession().getId();
        } catch (Exception e) {
            throw new BusinessException(ResultCode.WRONG_USERNAME_OR_PASSWORD);
        }
    }

    @Override
    public ProfileVo profile() {
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        //2.获取安全数据
        return (ProfileVo) principals.getPrimaryPrincipal();
//        User user = this.userDao.findById(userId).get();
//        if (null == user) {
//            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
//        }
//
//        // 根据不同的用户级别获取用户权限
//        ProfileVo profileVo = null;
//        if (UserConstant.USER.equals(user.getLevel())) {
//            profileVo = new ProfileVo(user);
//        } else {
//            Map<String, Object> map = new HashMap<>();
//            if (UserConstant.CO_ADMIN.equals(user.getLevel())) {
//                map.put("enVisible", "1");
//            }
//            List<Permission> list = this.permissionService.findAll(map);
//            profileVo = new ProfileVo(user, list);
//        }
//        return profileVo;
    }

    @Override
    public List<UserSimpleVo> getSimpleInfo(String companyId) {
        List<UserSimpleVo> simpleVos = new LinkedList<>();
        List<User> all = this.userDao.findAll(super.getSpec(companyId));
        for (User user : all) {
            simpleVos.add(new UserSimpleVo(user.getId(), user.getUsername()));
        }
        return simpleVos;
    }

    @Override
    public void getUserTemplateExcel(HttpServletResponse response) {
        InputStream resourceAsStream =
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(FileImportTemplateEnum.USER_EXCEL.getPath());
        ExcelExportUtil<UserTemplate> excelExportUtil
                = new ExcelExportUtil<>(UserTemplate.class, 3, 1);
        excelExportUtil.setStartCell(1);
        try {
            excelExportUtil.export(response, resourceAsStream, new LinkedList<>(), FileImportTemplateEnum.USER_EXCEL.getName());
        } catch (Exception e) {
            throw new BusinessException(ResultCode.EXPORT_FAILED);
        }
    }

    @Override
    public void importUserByExcel(String companyId, String companyName, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (null == fileName || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
            throw new BusinessException(ResultCode.EXCEL_FILE_TYPE_ERROR);
        }
        ExcelImportUtil<UserTemplate> userTemplateExcelImportUtil = new ExcelImportUtil<>(UserTemplate.class);
        try {
            List<UserTemplate> userTemplates = userTemplateExcelImportUtil.readExcel(file.getInputStream(), 3, 1);
            if (CommonUtils.isEmpty(userTemplates)) {
                return;
            }
            List<User> users = userTemplates.stream().map(userTemplate -> getUserByUserTemplate(companyId, companyName, userTemplate)).collect(Collectors.toList());
            this.userDao.saveAll(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String uploadImage(String id, MultipartFile file) {
//        //1.根据id查询用户
//        User user = userDao.findById(id).get();
//        //2.使用DataURL的形式存储图片（对图片byte数组进行base64编码）
//        String encode = null;
//        try {
//            encode = "data:image/png;base64,"+ Base64.encode(file.getBytes());
//            System.out.println(encode);
//            //3.更新用户头像地址
//            user.setStaffPhoto(encode);
//            userDao.save(user);
//            //4.返回
//            return encode;
//        } catch (IOException e) {
//            throw new BusinessException(ResultCode.PICTURE_UPLOAD_FAILED);
//        }
//    }

    @Override
    public String uploadImage(String id, MultipartFile file) {
        try {
            //1.根据id查询用户
            User user = userDao.findById(id).get();
            //2.将图片上传到七牛云存储，获取请求路径
            String imgUrl = new QiniuUploadUtil(this.qiniuyunConfig).upload(user.getId(), file.getBytes());//上传图片名，图片的byte数组
            //3.更新用户头像地址
            user.setStaffPhoto(imgUrl);
            userDao.save(user);
            //4.返回
            return imgUrl;
        } catch (IOException e) {
            throw new BusinessException(ResultCode.PICTURE_UPLOAD_FAILED);
        }
    }

    /**
     * 将UserTemplate对象转化为User对象
     *
     * @param companyId
     * @param companyName
     * @param userTemplate
     * @return
     */
    private User getUserByUserTemplate(String companyId, String companyName, UserTemplate userTemplate) {
        User user = new User(userTemplate);

        user.setPassword(new Md5Hash(DEFAULT_PASSWORD, user.getMobile(), 3).toString());
        user.setId(idWorker.nextId() + "");
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        user.setInServiceStatus(UserConstant.EMPLOYED);
        user.setEnableState(UserConstant.DISABLE);
        user.setLevel(UserConstant.USER);

        Result<Department> departmentResult = this.departmentFeignClient.findByCode(user.getDepartmentId(), companyId);
        Department department = departmentResult.getData();
        if (null != department) {
            user.setDepartmentId(department.getId());
            user.setDepartmentName(department.getName());
        }
        return user;
    }
}
