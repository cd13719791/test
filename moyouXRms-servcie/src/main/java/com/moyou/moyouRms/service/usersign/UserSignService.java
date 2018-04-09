package com.moyou.moyouRms.service.usersign;

import java.util.List;

import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.model.userSign.UserSign;

public interface UserSignService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserSign record);

	int insertSelective(UserSign record);

	UserSign selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserSign record);

	int updateByPrimaryKey(UserSign record);

	/**
	 * 根据用户id 签到
	 * 
	 * @param userId
	 * @return
	 */
	UserGoldRule addUserCheck(String userId);

	/**
	 * 根据用户id 获取用户签到记录
	 * 
	 * @param userId
	 * @return
	 */
	UserSign getUSerSignByUserId(String userId);

	/**
	 * 查询多少天没签到过的用户
	 * 
	 * @param day
	 * @return
	 */
	List<UserInfo> selectOffLineUser(int day);

}
