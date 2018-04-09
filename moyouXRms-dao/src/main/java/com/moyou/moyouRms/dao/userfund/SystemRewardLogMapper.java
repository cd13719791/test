package com.moyou.moyouRms.dao.userfund;

import java.util.List;

import com.moyou.moyouRms.model.userfund.SystemRewardLog;

public interface SystemRewardLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SystemRewardLog record);

	int insertSelective(SystemRewardLog record);

	SystemRewardLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SystemRewardLog record);

	int updateByPrimaryKey(SystemRewardLog record);

	List<SystemRewardLog> selectByRewardId(Integer id);

	/**
	 * 查询最近一小时内需要执行的systemRewardLog
	 * 
	 * @return
	 */
	List<SystemRewardLog> selectOneHourNeedDoingData();

}