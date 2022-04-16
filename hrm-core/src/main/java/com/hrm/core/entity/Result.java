package com.hrm.core.entity;

import com.hrm.core.constant.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-13 7:21
 * @Description: 公共返回对象
 */
@Data
@NoArgsConstructor
public class Result<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultCode.SERVER_ERROR);
    }

    public static <T> Result<T> error(ResultCode code) {
        return new Result<>(code);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.SERVER_ERROR.code, message, false);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAIL);
    }
}
