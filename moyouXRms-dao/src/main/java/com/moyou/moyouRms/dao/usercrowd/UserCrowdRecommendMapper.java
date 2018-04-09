package com.moyou.moyouRms.dao.usercrowd;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.usercrowd.UserCrowdRecommend;

public interface UserCrowdRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCrowdRecommend record);

    int insertSelective(UserCrowdRecommend record);

    UserCrowdRecommend selectByPrimaryKey(Integer id);
    UserCrowdRecommend getUserCrowdRecommend(@Param("crowedId")Integer crowedId, @Param("modeType") Integer modeType);

    int updateByPrimaryKeySelective(UserCrowdRecommend record);

    List<UserCrowdRecommend> queryUserCrowdRecommendList(UserCrowdRecommend record);
    int updateRecomenstateToNo();
    int updateRecomenstateToNoByCrowdId(UserCrowdRecommend userCrowdRecommend);
}