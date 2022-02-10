package com.hrm.system.dao;

import com.hrm.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:20
 * @Description: 用户持久层接口
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
}
