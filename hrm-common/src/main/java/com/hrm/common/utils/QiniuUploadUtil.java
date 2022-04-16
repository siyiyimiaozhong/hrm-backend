package com.hrm.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.hrm.common.properties.QiniuyunConfig;

import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-16 18:41
 * @Description: 七牛云上传工具类
 */
public class QiniuUploadUtil {
    private String accessKey = null;
    private String secretKey = null;
    private String bucket = null;
    private String prix = null;
    private UploadManager manager;

    public QiniuUploadUtil(QiniuyunConfig properties) {
        this.accessKey = properties.getAccessKey();
        this.secretKey = properties.getSecretKey();
        this.bucket = properties.getBucket();
        this.prix = "http://" + properties.getDomainName() + "/";
        //初始化基本配置
        Configuration cfg = new Configuration(Zone.zone0());
        //创建上传管理器
        manager = new UploadManager(cfg);
    }

    //文件名 = key
    //文件的byte数组
    public String upload(String imgName , byte [] bytes) {
        Auth auth = Auth.create(accessKey, secretKey);
        //构造覆盖上传token
        String upToken = auth.uploadToken(bucket,imgName);
        try {
            Response response = manager.put(bytes, imgName, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //返回请求地址
            return prix+putRet.key+"?t="+new Date().getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
