package com.hrm.core.constant;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-13 7:21
 * @Description: 公共返回码
 */
public enum ResultCode {

    /**
     * 通用成功
     */
    SUCCESS(true, 10000, "操作成功！"),
    /**
     * 通用错误
     */
    FAIL(false, 10001, "操作失败"),
    /**
     * 参数验证失败
     */
    PARAMETER_VALIDATION_FAILED(false, 10002, "参数验证失败"),
    /**
     * 服务器内部错误
     */
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),


    /**
     * 用户操作返回码
     */
    UNAUTHENTICATED(false, 20001, "您还未登录"),
    WRONG_USERNAME_OR_PASSWORD(false, 20002, "用户名或者密码错误"),
    PICTURE_UPLOAD_FAILED(false, 20003, "图片上传失败"),
    FAILED_TO_EXPORT_PDF(false, 20004, "导出PDF失败"),

    /**
     * 企业操作返回码
     */

    /**
     * 权限操作返回码
     */
    UNAUTHORISE(false, 40001, "权限不足"),

    /**
     * 其他操作返回码
     */
    EXCEL_FILE_TYPE_ERROR(false, 50001, "只能上传后缀是.xlsx或者.xls的文件"),
    EXPORT_FAILED(false, 50002, "导出失败"),
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
