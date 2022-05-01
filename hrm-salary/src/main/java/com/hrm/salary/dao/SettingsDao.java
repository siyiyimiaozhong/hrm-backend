package com.hrm.salary.dao;

import com.hrm.model.salary.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:32
 * @Description: 工资-设置信息表持久层接口
 */
public interface SettingsDao extends JpaRepository<Settings, String>, JpaSpecificationExecutor<Settings> {
}
