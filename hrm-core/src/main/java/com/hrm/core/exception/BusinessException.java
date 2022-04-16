package com.hrm.core.exception;

import com.hrm.core.constant.ResultCode;
import lombok.Getter;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-17 00:16
 * @Description: 业务异常类
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -9118505261060853881L;

    private ResultCode resultCode;

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
