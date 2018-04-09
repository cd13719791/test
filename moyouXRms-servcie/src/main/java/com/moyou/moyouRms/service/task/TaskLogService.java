package com.moyou.moyouRms.service.task;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.po.task.log.TaskLog;
import com.moyou.moyouRms.service.BaseService;
/**
 * 定时任务service
 */
public interface TaskLogService extends BaseService<TaskLog>{
	List<TaskLog> queryNeerTaskLog(Map<String,Object> map) ;

}
