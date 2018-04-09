package com.moyou.moyouRms.service.everyactivity;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;

public interface EveryActivityService {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivity record);

	int insertSelective(EveryActivity record);

	EveryActivity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivity record);

	int updateByPrimaryKey(EveryActivity record);

	/**
	 * 每日活动初始化
	 * 
	 * @return
	 */
	EveryActivity selectEveryActivityList();

	List<EveryActivity> selectActivityInfoList(PageBean pb);

	Map<String, Object> selectActivitySum();

	/**
	 * 查询最新的活动规则
	 * 
	 * @return
	 */
	EveryActivity selectEveryActivityLimit();

	/**
	 * 查找昨天执行的规则
	 * 
	 * @return
	 */
	EveryActivity selectYesterdayActivity();

	/**
	 * 修改活动状态
	 * 
	 * @return
	 */
	int updateState();

}