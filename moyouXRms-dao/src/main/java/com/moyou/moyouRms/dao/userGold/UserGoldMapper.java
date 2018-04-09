package com.moyou.moyouRms.dao.userGold;

import com.moyou.moyouRms.model.userGold.UserGold;

public interface UserGoldMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserGold record);

	int insertSelective(UserGold record);

	UserGold selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserGold record);

	int updateByPrimaryKey(UserGold record);

	UserGold selectByUserId(String userId);

	UserGold selectByUserIdLock(String userId);

	/**
	 * 根据用户ID查询用户金币
	 * 
	 * @param userId
	 * @return
	 */
	int queryUserGoldByUserId(Integer userId);

	UserGold queryLiveUserGoldByUserId(Integer userId);

	int updateByPrimaryKeyForVerSion(UserGold ug);
}