package com.moyou.moyouRms.task.redpacket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.service.redpacket.RedPacketService;
import com.moyou.moyouRms.task.BaseTask;
import com.moyou.moyouRms.util.SmsSendUtil;
import com.moyou.moyouRms.util.StringUtil;

/**
 * 红包退还定时任务
 * 
 * @author liuxinyi
 * @date 2017年4月5日
 * @version 1.0.0
 */
@Component
public class RedPacketBackTask extends BaseTask {
	@Autowired
	private RedPacketService redPacketService;

	// @Autowired
	// private Scheduler scheduler;

	@Scheduled(cron = "0 0 0/1 * * ?")
	// @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行
	public void taskCycle() {
		logger.info(StringUtil.getTraceInfo() + ":[task start]");
		try {
			/*
			 * System.out.println("task===================================");
			 * System.out.println(DateTimeUtil.formatDate(new Date(),
			 * "yyyy-MM-dd HH:mm:ss")); ScheduleJob scheduleJob = new
			 * ScheduleJob(); scheduleJob.setAliasName("redpacket back");
			 * scheduleJob.setCreateTime(new Date()); // 30 0 0 1 1 ? 2012
			 * 在2012年1月1日午夜过30秒时 String cronExpression = "30 29 10 5 4 ? 2017";
			 * scheduleJob.setCronExpression(cronExpression);
			 * scheduleJob.setDescription("红包退还定时任务"); scheduleJob.setJobClass(
			 * "com.moyou.moyouRms.task.redpacket.executor.RedPacketBackExecutor"
			 * ); scheduleJob.setJobGroup("redpacket");// 重要必填
			 * scheduleJob.setJobName("redpacket back");// 重要必填
			 * ScheduleManager.createScheduleJob(scheduler, scheduleJob);
			 */
			// 红包退还
			redPacketService.createRedPacketBack();
		} catch (Exception e) {
			String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
			SmsSendUtil.send(strs, getClass().getName(), RedPacketBackTask.class + "陌友",
					CONSTANT.REG_SCHEDULE, ResponseEnum.SCHEDULE_ERRO.getValue());
			logger.error(StringUtil.getTraceInfo() + ":[" + e.getMessage() + "]", e);
		} finally {
			logger.info(StringUtil.getTraceInfo() + ":[task end]");
		}
	}
}
