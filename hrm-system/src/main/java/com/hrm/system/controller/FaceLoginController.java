package com.hrm.system.controller;

import com.hrm.api.system.FaceLoginControllerApi;
import com.hrm.core.pojo.FaceLoginResult;
import com.hrm.core.pojo.QRCode;
import com.hrm.core.pojo.Result;
import com.hrm.system.service.FaceLoginService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 14:29
 * @Description: 人脸识别登录控制器
 */
@RestController
public class FaceLoginController implements FaceLoginControllerApi {
    private final FaceLoginService faceLoginService;

    public FaceLoginController(FaceLoginService faceLoginService) {
        this.faceLoginService = faceLoginService;
    }

    /**
     * 获取刷脸登录二维码
     *
     * @return QRCode对象（code，image）
     */
    @Override
    public Result<QRCode> qrcode() {
        QRCode qrCode = faceLoginService.getQRCode();
        return Result.success(qrCode);
    }

    /**
     * 检查二维码：登录页面轮询调用此方法，根据唯一标识code判断用户登录情况
     * 查询二维码扫描状态
     * 返回值：FaceLoginResult
     * state ：-1，0，1 （userId和token）
     */
    @Override
    public Result<FaceLoginResult> qrcodeCeck(@PathVariable(name = "code") String code) {
        FaceLoginResult checkQRCode = faceLoginService.checkQRCode(code);
        return Result.success(checkQRCode);
    }

    /**
     * 人脸登录：根据落地页随机拍摄的面部头像进行登录
     * 根据拍摄的图片调用百度云AI进行检索查找
     */
    @Override
    public Result<Object> loginByFace(@PathVariable(name = "code") String code, @RequestParam(name = "file") MultipartFile attachment) {
        //人脸登录获取用户id（不为null登录成功）
        String userId = faceLoginService.loginByFace(code, attachment);
        if (userId != null) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    /**
     * 图像检测，判断图片中是否存在面部头像
     */
    @Override
    public Result<Object> checkFace(@RequestParam(name = "file") MultipartFile attachment) {
        Boolean aBoolean = this.faceLoginService.checkFace(attachment);
        if (aBoolean) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }
}
