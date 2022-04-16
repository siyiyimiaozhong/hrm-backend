package com.hrm.model.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:05
 * @Description: 用户简介信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleVo {
    private String id;
    private String username;
}
