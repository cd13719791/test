package com.moyou.moyouRms.service.user;

import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserCount;

public interface UserCountService {

	int insert(UserCount record);

	int insertSelective(UserCount record);

	/**
	 * 查询用户统计数据* @param UserCount
	 * 
	 * @return
	 */
	UserCount queryUserCount(UserCount u);

	// int updateUserCountTalkJia1(UserCount u);

	int updateUserCountDiaryJia1(User u);

	int updateUserCountSecretJia1(User u);

	/**
	 * 修改关注数量
	 * 
	 * @param userCount
	 * @return
	 */
	int updateInterestCountJIA1(Integer userCount);

	int updateFollowersCountJIA1(Integer userId);

	UserCount selectUserCountByUserId(String userId);

}
