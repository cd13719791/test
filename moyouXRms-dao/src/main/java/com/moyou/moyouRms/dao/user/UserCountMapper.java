package com.moyou.moyouRms.dao.user;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.user.UserCount;

public interface UserCountMapper {
	int insert(UserCount record);

	int insertSelective(UserCount record);

	/**
	 * 查询用户统计数据* @param UserCount
	 * 
	 * @return
	 */
	UserCount queryUserCount(UserCount u);

	// 加一操作
	// int updateUserCountTalkJia1(Integer userId);

	int updateUserCountDiaryJia1(Integer userId);

	int updateUserCountSecretJia1(Integer userId);

	int updateFansCountJIA1(Integer userId);

	// 减一操作]
	/**
	 * 根据创建者Id,在删除一条说说的时候说说总量减一
	 * 
	 * @param creatorId
	 * @return
	 */
	Integer updateUserCountTalkJian1(Integer creatorId);

	// 日记
	Integer updateUserCountDiaryJian1(Integer userId);

	// 秘密
	Integer updateUserCountSecretJian1(Integer userId);

	int updateInterestCountJIA1(Integer userId);

	int updateFollowersCountJIA1(Integer userId);

	UserCount selectUserCountByUserId(@Param("userId") String userId);
}
