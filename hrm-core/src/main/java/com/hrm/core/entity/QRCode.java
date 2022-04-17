package com.hrm.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 11:32
 * @Description: 二维码实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCode implements Serializable {
    private static final long serialVersionUID = 4375387973088123002L;
    /**
     * 随机生成码
     */
    private String code;
    /**
     * Base64 二维码文件
     */
    private String file;
}