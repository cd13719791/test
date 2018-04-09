package com.moyou.moyouRms.service.userrecommend;

import com.moyou.moyouRms.model.userRecommend.UserRecommend;

public interface UserRecommendService {
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