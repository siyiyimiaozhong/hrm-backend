package com.hrm.common.handler;

import com.hrm.common.entity.Result;
import com.hrm.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-17 00:17
 * @Description: 自定义公共异常处理器
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public Result<Object> businessException(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        return Result.error(e.getResultCode());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<Object> err(HttpServletRequest request, HttpServletResponse response) {
        return Result.error();
    }
}
