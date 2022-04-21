package com.hrm.system.service;

import com.hrm.core.pojo.FaceLoginResult;
import com.hrm.core.pojo.QRCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 14:30
 * @Description: 人脸识别登录业务层接口
 */
public interface FaceLoginService {
    /**
     * 创建并获取二维码
     *
     * @return
     */
    QRCode getQRCode();

    /**
     * 根据唯一标识，查询用户是否登录成功
     *
     * @param code
     * @return
     */
    FaceLoginResult checkQRCode(String code);

    /**
     * 扫描二维码之后，使用拍摄照片进行登录
     *
     * @param code
     * @param attachment
     * @return 登录成功之后返回的用户id 登录失败为null
     */
    String loginByFace(String code, MultipartFile attachment);

    /**
     * 图像检测 判断图片中是否存在面部头像
     *
     * @param attachment
     * @return
     */
    Boolean checkFace(MultipartFile attachment);
}
