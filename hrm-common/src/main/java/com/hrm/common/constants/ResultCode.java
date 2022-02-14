package com.hrm.common.constants;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-13 7:21
 * @Description: 公共返回码
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(true, 10000, "操作成功！"),

    /**
     * 系统错误返回码
     */
    FAIL(false, 10001, "操作失败"),
    /**
     * 参数验证失败
     */
    PARAMETER_VALIDATION_FAILED(false, 10002, "参数验证失败"),

    UNAUTHENTICATED(false, 10003, "您还未登录"),

    UNAUTHORISE(false, 10004, "权限不足"),

    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    /**
     * 用户操作返回码
     */
    /**
     * 用户名或者密码错误
     */
    WRONG_USERNAME_OR_PASSWORD(false, 20001, "用户名或者密码错误"),

    /**
     * 企业操作返回码
     */

    /**
     * 权限操作返回码
     */

    /**
     * 其他操作返回码
     */
    ;

    /**
     * 操作是否成功
     */
    public boolean success;
    /**
     * 操作代码
     */
    public int code;
    /**
     * 提示信息
     */
    public String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
