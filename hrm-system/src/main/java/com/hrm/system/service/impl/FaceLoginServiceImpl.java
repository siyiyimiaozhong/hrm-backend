package com.hrm.system.service.impl;

import com.baidu.aip.util.Base64Util;
import com.hrm.common.utils.BaiduAiUtil;
import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.QRCodeUtil;
import com.hrm.core.constant.ResultCode;
import com.hrm.core.pojo.FaceLoginResult;
import com.hrm.core.pojo.QRCode;
import com.hrm.core.exception.BusinessException;
import com.hrm.model.system.entity.User;
import com.hrm.system.dao.UserDao;
import com.hrm.system.service.FaceLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 14:31
 * @Description: 人脸识别登录业务层实现类
 */
@Service
public class FaceLoginServiceImpl implements FaceLoginService {

    @Value("${qr.url}")
    private String url;

    private final IdWorker idWorker;
    private final UserDao userDao;
    private final QRCodeUtil qrCodeUtil;
    private final BaiduAiUtil baiduAiUtil;
    private final RedisTemplate redisTemplate;

    public FaceLoginServiceImpl(IdWorker idWorker,
                                UserDao userDao,
                                QRCodeUtil qrCodeUtil,
                                BaiduAiUtil baiduAiUtil,
                                RedisTemplate redisTemplate) {
        this.idWorker = idWorker;
        this.userDao = userDao;
        this.qrCodeUtil = qrCodeUtil;
        this.baiduAiUtil = baiduAiUtil;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public QRCode getQRCode() {
        //1.创建唯一标识
        String code = idWorker.nextId() + "";
        //2.生成二维码(url地址)
        String content = url + "?code=" + code;
        System.out.println(content);
        String file = qrCodeUtil.crateQRCode(content);
        System.out.println(file);
        //3.存入当前二维码状态（存入redis）
        FaceLoginResult result = new FaceLoginResult("-1");
        redisTemplate.boundValueOps(getCacheKey(code)).set(result, 10, TimeUnit.MINUTES);//状态对象，失效时间，单位
        return new QRCode(code, file);
    }

    @Override
    public FaceLoginResult checkQRCode(String code) {
        return null;
    }

    @Override
    public String loginByFace(String code, MultipartFile attachment) {
        try {
            //1.调用百度云AI查询当前的用户
            String userId = baiduAiUtil.faceSearch(Base64Util.encode(attachment.getBytes()));
            //2.自动登录（token）
            FaceLoginResult result = new FaceLoginResult("0");
            if (userId != null) {
                //自己模拟登录
                //查询用户对象
                Optional<User> userOptional = this.userDao.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    //获取subject
                    Subject subject = SecurityUtils.getSubject();
                    //调用login方法登录
                    subject.login(new UsernamePasswordToken(user.getMobile(), user.getPassword()));
                    //获取token（sessionId）
                    String token = subject.getSession().getId() + "";
                    result = new FaceLoginResult("1", token, userId);
                }
            }
            //3.修改二维码的状态
            redisTemplate.boundValueOps(getCacheKey(code)).set(result, 10, TimeUnit.MINUTES);
            return userId;
        } catch (Exception e) {
            throw new BusinessException(ResultCode.LOGIN_FAILED);
        }
    }

    @Override
    public Boolean checkFace(MultipartFile attachment) {
        String image = null;
        try {
            image = Base64Util.encode(attachment.getBytes());
            return baiduAiUtil.faceCheck(image);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 构造缓存key
     *
     * @param code
     * @return
     */
    private String getCacheKey(String code) {
        return "qrcode_" + code;
    }
}
