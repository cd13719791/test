package com.moyou.moyouRms.service.task.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.task.log.TaskLogDao;
import com.moyou.moyouRms.model.po.task.log.TaskLog;
import com.moyou.moyouRms.service.BaseServiceImp;
import com.moyou.moyouRms.service.task.TaskLogService;

@Service("TaskLogService")
public class TaskLogServiceImp extends BaseServiceImp<TaskLog> implements TaskLogService {
	@Resource
	TaskLogDao taskLogDao;
	/**
	 * 查询最近执行的日志 有就说明执行了。
	 * @param map
	 * @return
	 */
	public List<TaskLog> queryNeerTaskLog(Map<String,Object> map) {
		return taskLogDao.queryNeerTaskLog(map);
	};
	
}
