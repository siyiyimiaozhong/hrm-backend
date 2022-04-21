package com.hrm.attendance.dao;

import com.hrm.model.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:26
 * @Description: 用户信息持久层接口
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByMobile(String mobile);

    List<User> findByCompanyId(String companyId);

    @Query(value = "select * from bs_user where company_id=?1", nativeQuery = true)
    Page<User> findPage(String companyId, Pageable pageable);
}