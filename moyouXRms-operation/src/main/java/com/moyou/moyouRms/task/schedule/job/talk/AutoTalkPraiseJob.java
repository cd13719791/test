package com.moyou.moyouRms.task.schedule.job.talk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.TalkPraiseSateEnum;
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

import edu.emory.mathcs.backport.java.util.Collections;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 说说自动点赞[假用户给真实用户发表的说说点赞]
 * @author liuxinyi
 * @date 2017年3月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class AutoTalkPraiseJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoTalkPraiseJob.class);
	// 说说自动点赞，假用户存到redis的key前缀
	// private static String AUTO_TALK_PRAISE_JOB_USER_IDS_REDIS_KEY_PREFIX =
	// "AUTO_TALK_PRAISE_JOB_NEW_USER_IDS_REDIS_KEY_PREFIX";
	private static String AUTO_TALK_PRAISE_JOB_TALK_REDIS_KEY_PREFIX = "AUTO_TALK_PRAISE_JOB_TALK_REDIS_KEY_PREFIX1";

	@Override
	@SuppressWarnings("all")
	public void execute(JobExecutionContext context) {
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
			int userRepeatOperationSecond = ts.getUserRepeatOperationSecond();// 单个小号多少秒不重复操作
			UserService userService = (UserService) SpringBeanUtils
					.getFirstBeanOfType(UserService.class);
			TalkService talkService = (TalkService) SpringBeanUtils
					.getFirstBeanOfType(TalkService.class);
			TalkMapper talkMapper = (TalkMapper) SpringBeanUtils
					.getFirstBeanOfType(TalkMapper.class);
			List<Talk> repeatTalkList = filterRepeatTalk();
			List<User> userIds = userService.queryFakeUser();// 在数据库获取用来点赞的假用户
			if (userIds != null) {
				if (userIds.size() > 0) {
					int executeDataCount = ts.getExecuteDataCount();
					Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000 * 3);
					SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");// 取24小时内的说说
					String time = matter1.format(as);
					String time2 = matter1.format(new Date());
					// 由近7天的真用户发表的所有说说，取出近 executeDataCount 条
					List<Talk> talkList = talkService.queryRealUserTalkByDateLimit(time, time2,
							executeDataCount + 1);
					List<Talk> newReapeatTalk = new ArrayList<Talk>();
					LOG.info(talkList.size() + "条需要执行的说说点赞");
					talkList.forEach(item -> {// 去重
						for (Talk talk : repeatTalkList) {
							if (item.getId().intValue() == talk.getId().intValue()) {
								newReapeatTalk.add(item);
							}
						}
					});
					talkList.removeAll(newReapeatTalk);// 移除在防重复保护规则中的用户
					Collections.shuffle(userIds);
					// if(userIds.size()<=executeDataCount){
					// executeDataCount=userIds.size();
					// }
					// List<User> currentUserIds= userIds.subList(0,
					// executeDataCount);
					LOG.info(talkList.size() + "条需要执行的说说点赞（判断了重复数据）");
					if (talkList != null && talkList.size() > 0) {// 查询到有说说数据，则开始假用户的模拟点赞

						// super.addTaskCount(talkList.size());
						talkList.forEach(talk -> {
							int talkId = talk.getId();
							Map<String, Object> params = new HashMap<>();
							params.put("talkId", talkId);
							params.put("userId", userIds.get(talkList.indexOf(talk)).getUserId());
							TalkPraiseSateEnum isPraise = talkService.queryPraiseState(params);
							if (isPraise == TalkPraiseSateEnum.NO_PRAISE) {
								int dateLong = (int) (Math.random() * 1000 * 60 * 50);
								Long lovalue = new Date().getTime() + dateLong;
								Timer timer = new Timer();
								TimerTask task = new TimerTask() {
									@Override
									public void run() {
										// 查询talk是否存在 存在返回1
										if (talkMapper.selectTalkIsExistByTalkId(talkId).intValue() == 0) {
											LOG.info("[" + jobName + "说说不存在，return]---id=["
													+ talkId + "]");
											// lowerTaskCount();// 执行完一条 计数器减1
											return;
										}
										talkService.insertPraise(params); // 开始点赞
									}
								};
								timer.schedule(task, new Date(lovalue));
								LOG.info("[" + jobName + "]---id=[" + talkId + "]");
							}
						});
						// });
						// 将点赞的说说存到redis，防止重复使用同一个说说多次点赞
						stringRedisTemplate.opsForValue().set(
								AUTO_TALK_PRAISE_JOB_TALK_REDIS_KEY_PREFIX + ":"
										+ System.currentTimeMillis(), JsonUtil.toJson(talkList),
								userRepeatOperationSecond, TimeUnit.SECONDS);
						// 保存日志
						TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
								TaskLogManager.NORMAL, scheduleJob.getAliasName() + "任务正常运行");
					}
				}
			}
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

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<Talk> filterRepeatTalk() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(AUTO_TALK_PRAISE_JOB_TALK_REDIS_KEY_PREFIX
				+ ":*");
		List<Talk> userIdList = new ArrayList<>();
		if (keys != null) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users == null || users.size() == 0) {
					return;
				}
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, Talk.class));
				});
			});
		}
		return userIdList;
	}

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	// @SuppressWarnings("all")
	// private List<User> filterRepeatUser() {
	// StringRedisTemplate stringRedisTemplate =
	// (StringRedisTemplate)SpringBeanUtils.getFirstBeanOfType(StringRedisTemplate.class);
	// RedisTemplate<String, String> redisTemplate = (RedisTemplate<String,
	// String>)SpringBeanUtils.getFirstBeanOfType(RedisTemplate.class);
	// Set<String> keys =
	// stringRedisTemplate.keys(AUTO_TALK_PRAISE_JOB_USER_IDS_REDIS_KEY_PREFIX+":*");
	// List<User> userIdList = new ArrayList<>();
	// if (keys != null) {
	// keys.forEach(key -> {
	// String usersJson = redisTemplate.opsForValue().get(key);
	// List users = JsonUtil.toObject(usersJson, List.class);
	// if(users==null||users.size()==0){
	// return;
	// }
	// users.forEach(u -> {
	// userIdList.add(JsonUtil.toObject(u, User.class));
	// });
	// });
	// }
	// return userIdList;
	// }

	public static void main(String[] args) {

		Integer zero = 128;
		if (zero.intValue() == 128) {
			System.out.println("=");
		}
	}

}
