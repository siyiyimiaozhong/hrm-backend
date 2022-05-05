package com.hrm.social_security.service.impl;

import com.hrm.common.utils.CommonUtils;
import com.hrm.core.pojo.PageResult;
import com.hrm.model.social_security.dto.ArchiveDto;
import com.hrm.model.social_security.entity.UserSocialSecurity;
import com.hrm.social_security.client.UserFeignClient;
import com.hrm.social_security.dao.UserSocialSecurityDao;
import com.hrm.social_security.service.UserSocialService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:54
 * @Description: 用户社保信息业务层实现类
 */
@Service
public class UserSocialServiceImpl implements UserSocialService {

    private final UserSocialSecurityDao userSocialSecurityDao;
    private final UserFeignClient userFeignClient;
    private final EntityManager entityManager;

    public UserSocialServiceImpl(UserSocialSecurityDao userSocialSecurityDao, UserFeignClient userFeignClient, EntityManager entityManager) {
        this.userSocialSecurityDao = userSocialSecurityDao;
        this.userFeignClient = userFeignClient;
        this.entityManager = entityManager;
    }

    @Override
    public PageResult<Map<String, Object>> list(ArchiveDto archiveDto, String companyId) {
        StringBuilder sb = new StringBuilder(" bu.company_id=");
        sb.append(companyId);
        if (CommonUtils.isNotEmpty(archiveDto.getKeyword())) {
            sb.append(" and bu.username like %").append(archiveDto.getKeyword()).append("%");
        }
        if (CommonUtils.isNotEmpty(archiveDto.getDepartmentChecks())) {
            sb.append(" and bu.department_id in (").append(StringUtils.join(archiveDto.getDepartmentChecks(), ",")).append(")");
        }
        if (CommonUtils.isNotEmpty(archiveDto.getProvidentFundChecks())) {
            sb.append(" and ssuss.provident_fund_city_id in (").append(StringUtils.join(archiveDto.getProvidentFundChecks(), ",")).append(")");
        }
        if (CommonUtils.isNotEmpty(archiveDto.getSocialSecurityChecks())) {
            sb.append(" and ssuss.participating_in_the_city_id in (").append(StringUtils.join(archiveDto.getSocialSecurityChecks(), ",")).append(")");
        }
        Page<Map<String, Object>> userSocialSecurityPage = getDataByParams(sb.toString(), new PageRequest(archiveDto.getPage() - 1, archiveDto.getPageSize()));
//        Page<Map<String, Object>> userSocialSecurityPage = this.userSocialSecurityDao.findPage(companyId, new PageRequest(archiveDto.getPage() - 1, archiveDto.getPageSize()));
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

    private Page<Map<String, Object>> getDataByParams(String searchParams, Pageable pageable) {
        //查询数据，并设置分页
        StringBuffer querySql = new StringBuffer("SELECT\n" +
                "bu.id userId,\n" +
                "bu.username,\n" +
                "bu.mobile,\n" +
                "bu.work_number workNumber,\n" +
                "bu.department_name departmentName,\n" +
                "bu.time_of_entry timeOfEntry,\n" +
                "bu.time_of_dimission leaveTime,\n" +
                "ssuss.participating_in_the_city participatingInTheCity,\n" +
                "ssuss.participating_in_the_city_id participatingInTheCityId,\n" +
                "ssuss.provident_fund_city_id providentFundCityId,\n" +
                "ssuss.provident_fund_city providentFundCity,\n" +
                "ssuss.social_security_base socialSecurityBase,\n" +
                "ssuss.provident_fund_base providentFundBase \n" +
                "FROM\n" +
                "bs_user bu\n" +
                "LEFT JOIN ss_user_social_security ssuss ON bu.id = ssuss.user_id \n" +
                "WHERE ");
        querySql.append(searchParams);
        Query query = this.entityManager.createNativeQuery(querySql.toString());

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> promRuleDtos = query.getResultList();
        List<Map<String, Object>> res = new LinkedList<>();
        for (Object[] o : promRuleDtos) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", o[0]);
            map.put("username", o[1]);
            map.put("mobile", o[2]);
            map.put("workNumber", o[3]);
            map.put("departmentName", o[4]);
            map.put("timeOfEntry", o[5]);
            map.put("leaveTime", o[6]);
            map.put("participatingInTheCity", o[7]);
            map.put("participatingInTheCityId", o[8]);
            map.put("providentFundCityId", o[9]);
            map.put("providentFundCity", o[10]);
            map.put("socialSecurityBase", o[11]);
            map.put("providentFundBase", o[12]);
            res.add(map);
        }

        //获取总数
        StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM bs_user bu LEFT JOIN ss_user_social_security ssuss ON bu.id = ssuss.user_id WHERE ");
        countSql.append(searchParams);
        Query queryCount = this.entityManager.createNativeQuery(countSql.toString());
        long pageTotal = Long.parseLong(queryCount.getSingleResult().toString());
        return new PageImpl<>(res, pageable, pageTotal);
    }
}
