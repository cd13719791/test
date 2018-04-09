package com.moyou.moyouRms.service.task;

import java.util.List;

import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.service.BaseService;

/**
 * 定时任务配置service
 */
public interface TaskSetingService extends BaseService<TaskSeting> {
	/**
	 * 修改定时任务配置
	 * 
	 * @param ts
	 */
	void updateTaskSeting(TaskSeting ts);

	/**
	 * 根据计划id查找对应的业务任务配置信息
	 * 
	 * @param scheduleId
	 * @return
	 */
	TaskSeting selectByScheduleId(String scheduleId);

	/*
	 * 
	 * 初始化机器人设置
	 */
	List<TaskSeting> queryTaskSetingList();
	  /**
     * 机器人设置修改
     * @param record
     * @return
     */
    int updateByModeType(TaskSeting record);
}
