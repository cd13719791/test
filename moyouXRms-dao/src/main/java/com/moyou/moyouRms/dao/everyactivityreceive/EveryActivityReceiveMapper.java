package com.moyou.moyouRms.dao.everyactivityreceive;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivityreceive.EveryActivityReceive;

public interface EveryActivityReceiveMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivityReceive record);

	int insertSelective(EveryActivityReceive record);

	EveryActivityReceive selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivityReceive record);

	int updateByPrimaryKey(EveryActivityReceive record);

	List<EveryActivityReceive> selectActivityReceiveList(PageBean pb);

	Integer selectActivityDoingCount(@Param("activityId") Integer activityId);

	Integer selectUnDoingFundSum(@Param("activityId") Integer activityId);

	@Deprecated
	List<EveryActivityReceive> selectActivityReceiveForTestUnit();
}