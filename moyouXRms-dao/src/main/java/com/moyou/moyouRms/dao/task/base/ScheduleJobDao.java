package com.moyou.moyouRms.dao.task.base;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.dao.BaseDao;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
/**
 * 动态任务数据层
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJob>{
	/**
	 * 根据Id获取任务
	 */
	public ScheduleJob getScheduleJobById(@Param("scheduleJobId")String scheduleJobId);
	
	 /**
     * 启用禁用任务 0禁用，1启用
     * @param record
     * @return
     */
	public int updateStartOrStop(ScheduleJob record);
	
	/**
	 * 查询最近执行的任务
	 * @param scheduleJobName
	 * @return
	 */
	public ScheduleJob getScheduleJobByName(@Param("jobName")String jobName);
}
