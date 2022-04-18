package com.hrm.api.system;

import com.hrm.core.entity.FaceLoginResult;
import com.hrm.core.entity.QRCode;
import com.hrm.core.entity.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 16:32
 * @Description: 人脸识别登录Api
 */
@RequestMapping("/sys/faceLogin")
public interface FaceLoginControllerApi {
    /**
     * 获取刷脸登录二维码
     *
     * @return QRCode对象（code，image）
     */
    @GetMapping("/qrcode")
    Result<QRCode> qrcode();

    /**
     * 检查二维码：登录页面轮询调用此方法，根据唯一标识code判断用户登录情况 查询二维码扫描状态
     *
     * @param code
     * @return FaceLoginResult state ：-1，0，1 （userId和token）
     */
    @GetMapping("/qrcode/{code}")
    Result<FaceLoginResult> qrcodeCeck(@PathVariable(name = "code") String code);

    /**
     * 人脸登录：根据落地页随机拍摄的面部头像进行登录
     * 根据拍摄的图片调用百度云AI进行检索查找
     *
     * @param code
     * @param attachment
     * @return
     */
    @PostMapping("/{code}")
    Result<Object> loginByFace(@PathVariable(name = "code") String code, @RequestParam(name = "file") MultipartFile attachment);

    /**
     * 图像检测，判断图片中是否存在面部头像
     *
     * @param attachment
     * @return
     */
    @PostMapping("/checkFace")
    Result<Object> checkFace(@RequestParam(name = "file") MultipartFile attachment);
}
