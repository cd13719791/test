package com.moyou.moyouRms.service.task.impl;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.task.TaskSetingMapper;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.service.BaseServiceImp;
import com.moyou.moyouRms.service.task.ScheduleJobService;
import com.moyou.moyouRms.service.task.TaskSetingService;

@Service
public class TaskSetingServiceImp extends BaseServiceImp<TaskSeting> implements
		TaskSetingService {
	@Autowired
	private TaskSetingMapper taskSetingMapper;
	@Autowired
	private ScheduleJobService scheduleJobService;

	@Override
	public void updateTaskSeting(TaskSeting ts) {
		ts.setUpdateTime(new Date());
		ScheduleJob sj = new ScheduleJob();
		sj.setScheduleJobId(ts.getScheduleId());
		List<ScheduleJob> sjList = scheduleJobService.find(sj);// 获取定时的调度计划
		if (sjList != null && sjList.size() == 0) {
			sj = sjList.get(0);
			sj.setExtend(ts);
			int intervalSecond = ts.getIntervalSecond();
			String cronExpression = "/" + intervalSecond + " * * * * ?";
			sj.setCronExpression(cronExpression);
			scheduleJobService.updateScheduleJob(sj);// 修改调度计划
			scheduleJobService.runOnce(sj);// 运行调度计划一次
			taskSetingMapper.updateByPrimaryKeySelective(ts);// 更新配置表数据
		}
	}

	@Override
	public TaskSeting selectByScheduleId(String scheduleId) {
		return taskSetingMapper.selectByScheduleId(scheduleId);
	}

	/*
	 * 
	 * 初始化机器人设置
	 */
	@Override
	public List<TaskSeting> queryTaskSetingList() {
		return taskSetingMapper.queryTaskSetingList();
	}

	/**
	 * 机器人设置修改
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int updateByModeType(TaskSeting record) {
		record.setUpdateTime(new Date());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**start
		 * 重启任务
		 */
		TaskSeting oldTaskSeting=taskSetingMapper.selectByModeType(record.getModeType());
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setScheduleJobId(oldTaskSeting.getScheduleId());
		List<ScheduleJob> scheduleJobs=scheduleJobService.find(scheduleJob);
		scheduleJobs.forEach(schedule ->{
//			schedule.setStatus(ScheduleJob.SCHEDULEJOB_START);//修改状态为启动
			scheduleJobService.updateScheduleJob(schedule);
		});
		scheduleJobService.runOnce(scheduleJob);
		/**end
		 * 重启任务
		 */
		return taskSetingMapper.updateByModeType(record);
	}

}
