package com.moyou.moyouRms.service.usergold;

import com.moyou.moyouRms.model.userGold.UserGold;

public interface UserGoldService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserGold record);

	int insertSelective(UserGold record);

	UserGold selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserGold record);

	int updateByPrimaryKey(UserGold record);

	UserGold selectByUserId(String userId);

	/**
	 * 查询金币，只用于修改金币时，会加上行级锁，效率慢
	 * 
	 * @param userId
	 * @return
	 */
	UserGold selectByUserIdLock(String userId);

	/**
	 * 添加或修改用户金币
	 * 
	 * @param userId
	 * @param userGold
	 */
	Integer addOrUpdateGold(int userId, int userGold);

	/**
	 * 添加或修改主播金币
	 * 
	 * @param userId
	 * @param userGold
	 */
	Integer addOrUpdateGoldForLive(int userId, int userGold);

	/**
	 * 查询直播金币
	 * 
	 * @param userId
	 * @return
	 */
	UserGold queryLiveUserGoldByUserId(Integer userId);
}
