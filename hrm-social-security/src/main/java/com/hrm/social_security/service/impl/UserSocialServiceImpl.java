package com.hrm.social_security.service.impl;

import com.hrm.core.entity.PageResult;
import com.hrm.model.social_security.UserSocialSecurity;
import com.hrm.social_security.client.UserFeignClient;
import com.hrm.social_security.dao.UserSocialSecurityDao;
import com.hrm.social_security.service.UserSocialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:54
 * @Description: 用户社保信息业务层实现类
 */
@Service
public class UserSocialServiceImpl implements UserSocialService {

    private final UserSocialSecurityDao userSocialSecurityDao;
    private final UserFeignClient userFeignClient;

    public UserSocialServiceImpl(UserSocialSecurityDao userSocialSecurityDao, UserFeignClient userFeignClient) {
        this.userSocialSecurityDao = userSocialSecurityDao;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public PageResult<Map<String, Object>> list(Integer page, Integer pageSize, String companyId) {
        Page<Map<String, Object>> userSocialSecurityPage = this.userSocialSecurityDao.findPage(companyId, new PageRequest(page - 1, pageSize));
        return new PageResult<>(userSocialSecurityPage.getTotalElements(), userSocialSecurityPage.getContent());
    }

    @Override
    public Map<String, Object> get(String id) {
        Map<String, Object> map = new HashMap<>();
        //1.根据用户id查询用户数据
        Object obj = this.userFeignClient.get(id).getData();
        map.put("user", obj);
        //2.根据用户id查询社保数据
        Optional<UserSocialSecurity> optional = userSocialSecurityDao.findById(id);
        map.put("userSocialSecurity", optional.orElse(null));
        return map;
    }

    @Override
    public void saveUserSocialSecurity(UserSocialSecurity uss) {
        this.userSocialSecurityDao.save(uss);
    }
}
