package com.moyou.moyouRms.dao.userSign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.userSign.UserSign;

public interface UserSignMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserSign record);

	int insertSelective(UserSign record);

	UserSign selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserSign record);

	int updateByPrimaryKey(UserSign record);

	/**
	 * 根据用户id 获取用户签到记录
	 * 
	 * @param userId
	 * @return
	 */
	UserSign getUSerSignByUserId(String userId);

	List<UserInfo> selectOffLineUser(@Param("day") int day);
}