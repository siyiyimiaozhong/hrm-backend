package com.hrm.common.handler;

import com.hrm.core.constant.ResultCode;
import com.hrm.core.pojo.Result;
import com.hrm.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-17 00:17
 * @Description: 自定义公共异常处理器, 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Object> error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if (e.getClass() == BusinessException.class) {
            //类型转型
            BusinessException ce = (BusinessException) e;
            return Result.error(ce.getResultCode());
        } else {
            return Result.error(ResultCode.SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public Result<Object> error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return Result.error(ResultCode.UNAUTHORISE);
    }
}
