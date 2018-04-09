package com.moyou.moyouRms.dao.userRecommend;

import com.moyou.moyouRms.model.userRecommend.UserRecommend;

public interface UserRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRecommend record);

    int insertSelective(UserRecommend record);

    UserRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRecommend record);

    int updateByPrimaryKey(UserRecommend record);
    int updateByParam(UserRecommend record);
	UserRecommend selectByUsreId(Integer id);
	int updateRecommedStatusByUserId(UserRecommend userRecommend);
}