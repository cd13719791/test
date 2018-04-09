package com.moyou.moyouRms.service.usercrowd;

import java.util.List;

import com.moyou.moyouRms.model.usercrowd.UserCrowdRecommend;

public interface UserCrowdRecommendService {
	
	/**
	 * 查询群推荐列表
	 * @param userCrowdRecommend
	 * @return
	 */
	List<UserCrowdRecommend> queryUserCrowdRecommendList(UserCrowdRecommend userCrowdRecommend);
	/**
	 * 添加群到推荐表
	 * @param userCrowd
	 */
	void addUserCrowdRecommend(UserCrowdRecommend userCrowd);
	/**
	 * 修改群推荐状态
	 */
	void updateUserCrowdRecommendState(UserCrowdRecommend userCrowdRecommend);
	/**
	 * 根据群条件查询推荐群信息
	 * @param id
	 * @return
	 */
	UserCrowdRecommend getUserCrowdRecommend(int id);
	/**
	 * 获取推荐群
	 * @param crowdId
	 * @param modeType
	 * @return
	 */
	UserCrowdRecommend getUserCrowdRecommend(int crowdId, int modeType);
}
