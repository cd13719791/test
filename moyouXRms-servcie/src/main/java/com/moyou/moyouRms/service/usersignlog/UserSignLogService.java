package com.moyou.moyouRms.service.usersignlog;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.userSignLog.UserSignLog;

public interface UserSignLogService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserSignLog record);

	int insertSelective(UserSignLog record);

	UserSignLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserSignLog record);

	int updateByPrimaryKey(UserSignLog record);

	@SuppressWarnings("rawtypes")
	List<UserSignLog> selectUserSignLogListByDate(Map param);

	/**
	 * 查询
	 * 
	 * @param day7
	 * @return
	 */
}
