package com.hrm.common.controller;

import com.hrm.common.constants.ResultCode;
import com.hrm.common.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-16 21:05
 * @Description: 公共的错误跳转controller
 */
@RestController
@CrossOrigin
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value = "autherror")
    public Result<Object> autherror(int code) {
        return code == 1 ? Result.error(ResultCode.UNAUTHENTICATED) : Result.error(ResultCode.UNAUTHORISE);
    }
}