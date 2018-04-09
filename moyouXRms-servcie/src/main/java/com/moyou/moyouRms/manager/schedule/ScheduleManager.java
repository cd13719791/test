package com.moyou.moyouRms.manager.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.response.Msg;

public class ScheduleManager {

	/** 日志对象 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ScheduleManager.class);

	/** 任务调度的参数key */
	public static final String JOB_PARAM_KEY = "scheduleJob";

	/**
	 * 获取触发器key
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
		return TriggerKey.triggerKey(jobName, jobGroup);
	}

	/**
	 * 获取表达式触发器
	 * 
	 * @param scheduler
	 *            the scheduler
	 * @param jobName
	 *            the job name
	 * @param jobGroup
	 *            the job group
	 * @return cron trigger
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler,
			String jobName, String jobGroup) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			return (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			LOG.error("获取定时任务CronTrigger出现异常", e);
		}
		return null;
	}

	/**
	 * 创建任务
	 *
	 * @param scheduler
	 *            the scheduler
	 * @param scheduleJob
	 *            the schedule job
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static Msg createScheduleJob(Scheduler scheduler,
			ScheduleJob scheduleJob) throws Exception {
		// 任务执行类
		Class<? extends Job> jobClass = (Class<? extends Job>) Class
				.forName(scheduleJob.getJobClass());
		return createScheduleJob(scheduler, scheduleJob.getJobName(),
				scheduleJob.getJobGroup(), scheduleJob.getCronExpression(),
				scheduleJob, jobClass);
	}

	/**
	 * 创建定时任务
	 * 
	 * @param scheduler
	 *            the scheduler
	 * @param jobName
	 *            the job name
	 * @param jobGroup
	 *            the job group
	 * @param cronExpression
	 *            the cron expression
	 * @param isSync
	 *            the is sync
	 * @param param
	 *            the param
	 * @throws Exception
	 */
	public static Msg createScheduleJob(Scheduler scheduler, String jobName,
			String jobGroup, String cronExpression, Object param,
			Class<? extends Job> jobClass) throws Exception {
		Msg msg = Msg.makeMsg();
		//
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.withIdentity(jobName, jobGroup).build();
		// 放入参数，运行时的方法可以获取
		jobDetail.getJobDataMap().put(ScheduleManager.JOB_PARAM_KEY, param);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(cronExpression);
		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
				.build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("任务构建失败"+e);
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("任务构建失败:["+jobName+"]");
			msg.setCode(RESPONSE.ERROR);
		}
		return msg;

	}

	/**
	 * 更新定时任务
	 * 
	 * @param scheduler
	 *            the scheduler
	 * @param scheduleJob
	 *            the schedule job
	 * @throws SchedulerException
	 */
	public static void updateScheduleJob(Scheduler scheduler,
			ScheduleJob scheduleJob) throws SchedulerException {
		updateScheduleJob(scheduler, scheduleJob.getJobName(),
				scheduleJob.getJobGroup(), scheduleJob.getCronExpression(),
				scheduleJob);
	}

	/**
	 * 更新定时任务
	 *
	 * @param scheduler
	 *            the scheduler
	 * @param jobName
	 *            the job name
	 * @param jobGroup
	 *            the job group
	 * @param cronExpression
	 *            the cron expression
	 * @param param
	 *            the param
	 * @throws SchedulerException
	 */
	public static void updateScheduleJob(Scheduler scheduler, String jobName,
			String jobGroup, String cronExpression, Object param)
			throws SchedulerException {
		TriggerKey triggerKey = ScheduleManager
				.getTriggerKey(jobName, jobGroup);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
				.cronSchedule(cronExpression);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 获取jobKey
	 * 
	 * @param jobName
	 *            the job name
	 * @param jobGroup
	 *            the job group
	 * @return the job key
	 */
	public static JobKey getJobKey(String jobName, String jobGroup) {
		return JobKey.jobKey(jobName, jobGroup);
	}

	/**
	 * 删除定时任务
	 *
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public static void deleteScheduleJob(Scheduler scheduler, String jobName,
			String jobGroup) throws SchedulerException {
		scheduler.deleteJob(getJobKey(jobName, jobGroup));
	}

	/**
	 * 恢复任务
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public static void resumeJob(Scheduler scheduler, String jobName,
			String jobGroup) throws SchedulerException {
		JobKey jobKey = getJobKey(jobName, jobGroup);
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 暂停任务
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public static void pauseJob(Scheduler scheduler, String jobName,
			String jobGroup) throws SchedulerException {
		JobKey jobKey = getJobKey(jobName, jobGroup);
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 运行一次任务
	 * 
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public static void runOnce(Scheduler scheduler, String jobName,
			String jobGroup) throws SchedulerException {
		JobKey jobKey = getJobKey(jobName, jobGroup);
		scheduler.triggerJob(jobKey);
	}
}
