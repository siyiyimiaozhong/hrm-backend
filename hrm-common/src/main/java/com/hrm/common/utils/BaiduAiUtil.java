package com.hrm.common.utils;

import com.baidu.aip.face.AipFace;
import com.hrm.common.properties.BaiDuAiConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 11:38
 * @Description: 百度Ai工具类
 */
@Component
public class BaiduAiUtil {

    private final String imageType;
    private final String groupId;

    private final AipFace client;

    private final HashMap<String, String> options = new HashMap<>();

    public BaiduAiUtil(BaiDuAiConfig config) {
        String appId = config.getAppId();
        String apiKey = config.getApiKey();
        String secretKey = config.getSecretKey();
        this.imageType = config.getImageType();
        this.groupId = config.getGroupId();

        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        client = new AipFace(appId, apiKey, secretKey);
    }

    //判断用户是否已经注册了面部信息
    public Boolean faceExist(String userId) {
        //返回error_code：0 （存在），非0：不存在
        JSONObject res = client.getUser(userId, groupId, null);
        int errorCode = res.getInt("error_code");
        return errorCode == 0;
    }

    /**
     * 人脸注册 ：将用户照片存入人脸库中
     */
    public Boolean faceRegister(String userId, String image) {
        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        int errorCode = res.getInt("error_code");
        return errorCode == 0;
    }

    /**
     * 人脸更新 ：更新人脸库中的用户照片
     */
    public Boolean faceUpdate(String userId, String image) {
        // 人脸更新
        JSONObject res = client.updateUser(image, imageType, groupId, userId, options);
        int errorCode = res.getInt("error_code");
        return errorCode == 0;
    }

    /**
     * 人脸检测：判断上传图片中是否具有面部头像
     */
    public Boolean faceCheck(String image) {
        JSONObject res = client.detect(image, imageType, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject resultObject = res.getJSONObject("result");
            int faceNum = resultObject.getInt("face_num");//检测的人脸数量
            return faceNum == 1;
        } else {
            return false;
        }
    }

    /**
     * 人脸查找：查找人脸库中最相似的人脸并返回数据
     * 处理：用户的匹配得分（score）大于80分，即可认为是同一个用户
     */
    public String faceSearch(String image) {
        JSONObject res = client.search(image, imageType, groupId, options);
        if (res.has("error_code") && res.getInt("error_code") == 0) {
            JSONObject result = res.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            if (userList.length() > 0) {
                JSONObject user = userList.getJSONObject(0);
                double score = user.getDouble("score");
                if (score > 80) {
                    return user.getString("user_id");
                }
            }
        }
        return null;
    }
}