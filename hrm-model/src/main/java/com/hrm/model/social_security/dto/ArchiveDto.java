package com.hrm.model.social_security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-04 18:21
 * @Description: 社保
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveDto implements Serializable {

    private static final long serialVersionUID = 5398537301599068485L;

    /**
     * 关键字
     */
    private String keyword;
    private Integer page;
    private Integer pageSize;
    /**
     * 部门ids
     */
    private List<String> departmentChecks;
    /**
     * 公积金城市ids
     */
    private List<String> providentFundChecks;
    /**
     * 社保城市ids
     */
    private List<String> socialSecurityChecks;
}
