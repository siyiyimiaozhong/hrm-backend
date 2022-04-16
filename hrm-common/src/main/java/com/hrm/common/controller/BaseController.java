package com.hrm.common.controller;

import com.hrm.model.system.vo.ProfileVo;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
    protected Claims claims;
//    // jwt方式
//    @ModelAttribute
//    public void setResAndReq(HttpServletRequest request, HttpServletResponse response) {
//        this.request = request;
//        this.response = response;
//
//        Object obj = request.getAttribute("user_claims");
//
//        if(obj != null) {
//            this.claims = (Claims) obj;
//            this.companyId = (String)claims.get("companyId");
//            this.companyName = (String)claims.get("companyName");
//        }
//    }

    /**
     * 使用shiro获取
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAnReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            //2.获取安全数据
            ProfileVo result = (ProfileVo) principals.getPrimaryPrincipal();
            this.companyId = result.getCompanyId();
            this.companyName = result.getCompany();
        }
    }

}
