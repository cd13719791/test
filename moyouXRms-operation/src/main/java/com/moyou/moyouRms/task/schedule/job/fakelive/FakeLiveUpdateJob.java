package com.moyou.moyouRms.task.schedule.job.fakelive;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoom;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.service.liveshow.LiveFakeRoomService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

/**
 * @author created by Chenxv
 * @date 2017年10月10日 上午11:09:08
 */
public class FakeLiveUpdateJob extends BaseTask implements Job {
	private static final Logger LOG = LoggerFactory.getLogger(FakeLiveUpdateJob.class);
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);

	@SuppressWarnings("all")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(
				ScheduleManager.JOB_PARAM_KEY);
		String jobName = scheduleJob.getJobName();
		String jobGroup = scheduleJob.getJobGroup();
		String jobClass = scheduleJob.getJobClass();
		TaskSeting ts = (TaskSeting) scheduleJob.getExtend();
		if (ts == null) {
			return;
		}
		try {
			int executeDataCount = ts.getExecuteDataCount();// 更新运营直播的数量
			LiveFakeRoomService liveFakeRoomService = (LiveFakeRoomService) SpringBeanUtils
					.getFirstBeanOfType(LiveFakeRoomService.class);
			liveFakeRoomService.updateLiveFakeNoRecomment();
			List<LiveFakeRoom> liveFakeRoomList = liveFakeRoomService
					.selectLiveFakeRoomLimit(executeDataCount);
			liveFakeRoomList.forEach(s -> {
				s.setRecommendState(LiveFakeRoom.RECOMMEND_STATE_OK);
				s.setRecommendTme(new Date());
				s.setRecommendSort(liveFakeRoomList.indexOf(s));
				liveFakeRoomService.updateNonEmptyLiveFakeRoomById(s);
			});
			LOG.info("任务" + getClass() + "[" + jobName + "]成功运行 ,下次运行时间:"
					+ DateTimeUtil.formatDate(context.getNextFireTime(), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			LOG.error("任务[" + jobName + "]异常", e);
			String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
			SmsSendUtil.send(strs, jobName, this.getClass() + "陌友" + appName,
					CONSTANT.REG_SCHEDULE, ResponseEnum.SCHEDULE_ERRO.getValue());
			// 保存异常日志
			TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
					TaskLogManager.EXCEPTION, e.toString());
		}
	}
}
