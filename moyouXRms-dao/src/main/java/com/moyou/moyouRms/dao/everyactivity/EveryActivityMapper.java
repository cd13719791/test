package com.moyou.moyouRms.dao.everyactivity;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;

public interface EveryActivityMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivity record);

	int insertSelective(EveryActivity record);

	EveryActivity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivity record);

	int updateByPrimaryKey(EveryActivity record);

	EveryActivity selectEveryActivityList();

	List<EveryActivity> selectActivityInfoList(PageBean pb);

	Map<String, Object> selectActivitySum();

	EveryActivity selectEveryActivityLimit();

	EveryActivity selectYesterdayActivity();

	int updateState();
}