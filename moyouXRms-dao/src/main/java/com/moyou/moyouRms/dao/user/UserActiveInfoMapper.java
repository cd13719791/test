package com.moyou.moyouRms.dao.user;

import com.moyou.moyouRms.model.user.ChatBackground;
import com.moyou.moyouRms.model.user.UserActiveInfo;

public interface UserActiveInfoMapper {
    int insert(UserActiveInfo record);

    int insertSelective(UserActiveInfo record);
    
    /**
     * update UserActiveInfo
     * 
     * @param activeInfo
     * @return
     */
    int updateUserActiveInfo(UserActiveInfo activeInfo);
    
    /**
     * 查询用户位置信息
     * 
     * @param userId
     * @return
     */
    String queryUserLatAndLng(ChatBackground userId);
    /**
     * 修改坐标
     * @param activeInfo
     * @return
     */
    int updateUserLatAndLon(UserActiveInfo activeInfo);
    Integer selectActiveInfoUserIdByUserId(Integer userId);
    /**
     * 查询
     * @param userId
     * @return
     */
    UserActiveInfo selectUserLatAndLon(Integer userId);
}