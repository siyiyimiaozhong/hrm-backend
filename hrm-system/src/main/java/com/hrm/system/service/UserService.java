package com.hrm.system.service;

import com.hrm.core.pojo.PageResult;
import com.hrm.model.system.entity.User;
import com.hrm.model.system.dto.UserDto;
import com.hrm.model.system.dto.UserRoleDto;
import com.hrm.model.system.vo.ProfileVo;
import com.hrm.model.system.vo.UserSimpleVo;
import com.hrm.model.system.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:21
 * @Description: 用户业务层接口
 */
public interface UserService {
    /**
     * 校验用户信息并保存
     *
     * @param user
     */
    void checkAndInsert(User user);

    /**
     * 校验并更新用户信息
     *
     * @param user
     */
    void checkAndUpdate(String id, User user);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(String id);

    /**
     * 通过手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    void delete(String id);

    /**
     * 根据条件分页查询
     *
     * @param userDto
     * @return
     */
    PageResult<User> findAll(UserDto userDto);

    /**
     * 添加角色信息
     *
     * @param userRoleDto
     */
    void assignRoles(UserRoleDto userRoleDto);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    String login(String mobile, String password);

    /**
     * 获取用户信息
     *
     * @return
     */
    ProfileVo profile();

    /**
     * 获取用户简单信息
     *
     * @param companyId
     * @return
     */
    List<UserSimpleVo> getSimpleInfo(String companyId);

    /**
     * 获取导入用户信息excel模板
     *
     * @param response
     */
    void getUserTemplateExcel(HttpServletResponse response);

    /**
     * 根据excel导入用户信息
     *
     * @param companyId
     * @param companyName
     * @param file
     */
    void importUserByExcel(String companyId, String companyName, MultipartFile file);

    /**
     * 上传用户头像，转化为DataURL返回给前端
     *
     * @param id
     * @param file
     * @return
     */
    String uploadImage(String id, MultipartFile file);
}
