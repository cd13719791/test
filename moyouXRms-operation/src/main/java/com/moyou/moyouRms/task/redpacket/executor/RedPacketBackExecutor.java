package com.moyou.moyouRms.task.redpacket.executor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.service.redpacket.RedPacketService;
import com.moyou.moyouRms.spring.SpringBeanUtils;

/**
 * @describe 红包退还执行类
 * @author liuxinyi
 * @date 2017年4月5日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class RedPacketBackExecutor implements Job {

	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(RedPacketBackExecutor.class);

	@SuppressWarnings("unused")
	@Override
	public void execute(JobExecutionContext context) {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleManager.JOB_PARAM_KEY);
		String jobName = scheduleJob.getJobName();
		String jobGroup = scheduleJob.getJobGroup();
		String jobClass = scheduleJob.getJobClass();
		try {
			RedPacketService redPacketService = (RedPacketService) SpringBeanUtils.getFirstBeanOfType(RedPacketService.class);
			// 保存日志
			TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogManager.NORMAL,
					scheduleJob.getAliasName() + "任务正常运行");
			LOG.info("任务" + getClass() + "[" + jobName + "]成功运行");
		} catch (Exception e) {
			LOG.error("任务[" + jobName + "]异常", e);
			// 保存异常日志
			TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass, TaskLogManager.EXCEPTION, e.getMessage());
		}
	}
}
