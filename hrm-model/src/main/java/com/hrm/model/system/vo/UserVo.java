package com.hrm.model.system.vo;

import com.hrm.model.system.entity.Role;
import com.hrm.model.system.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-13 22:05
 * @Description: 用户信息vo
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2495228969858282808L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0为禁用 1为启用
     */
    private Integer enableState;
    /**
     * 创建时间
     */
    private Date createTime;

    private String companyId;

    private String companyName;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 入职时间
     */
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    private Integer formOfEmployment;

    /**
     * 工号
     */
    private String workNumber;

    /**
     * 管理形式
     */
    private String formOfManagement;

    /**
     * 工作城市
     */
    private String workingCity;

    /**
     * 转正时间
     */
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;

    private String departmentName;

    /**
     * 员工照片
     */
    private String staffPhoto;

    private List<String> roleIds = new LinkedList<>();

    public static UserVo toUserVo(User user) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        for (Role role : user.getRoles()) {
            userVo.roleIds.add(role.getId());
        }
        return userVo;
    }
}
