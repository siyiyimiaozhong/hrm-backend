package com.hrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 12:22
 * @Description: 公共控制器
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        //TODO 获取企业id
        this.companyId = "1";
        this.companyName = "";
    }
}
