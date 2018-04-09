package com.moyou.moyouRms.dao.task.log;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.dao.BaseDao;
import com.moyou.moyouRms.model.po.task.log.TaskLog;
/**
 * 动态任务数据层
 */
public interface TaskLogDao extends BaseDao<TaskLog>{

	List<TaskLog> queryNeerTaskLog(Map<String, Object> map);
	
}
