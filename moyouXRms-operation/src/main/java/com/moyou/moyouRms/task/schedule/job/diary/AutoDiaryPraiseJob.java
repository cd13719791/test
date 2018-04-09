package com.moyou.moyouRms.task.schedule.job.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.service.diary.DiaryService;
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
public class AutoDiaryPraiseJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	private static final Integer PRAISE_DIARY_COUNT = 100;
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoDiaryPraiseJob.class);
	// 说说自动点赞，假用户存到redis的key前缀
	// private static String AUTO_DIARY_PRAISE_JOB_USER_IDS_REDIS_KEY_PREFIX =
	// "AUTO_DIARY_PRAISE_JOB_USER_IDS_REDIS_KEY_PREFIX";
	private static String AUTO_DIARY_PRAISE_JOB_DIARY_REDIS_KEY_PREFIX = "AUTO_DIARY_PRAISE_JOB_DIARY_REDIS_KEY_PREFIX1";

	@SuppressWarnings("all")
	@Override
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
			DiaryService diaryService = (DiaryService) SpringBeanUtils
					.getFirstBeanOfType(DiaryService.class);
			PageBean page = new PageBean(1, PRAISE_DIARY_COUNT);
			List<Diary> repeatDiaryList = filterRepeatDiary();
			List<User> userIds = userService.queryFakeUser();// 在数据库获取用来点赞的假用户
			List<User> usedUser = new ArrayList();
			if (userIds != null) {
				// userIds.removeAll(repeatDiaryList);// 移除在防重复保护规则中的用户
				if (userIds.size() > 0) {
					// long[] a = RandomStringUtil.generate(
					// Long.valueOf(ts.getExecuteDataCount().toString()),
					// PRAISE_DIARY_COUNT);
					// List<Long> list = new ArrayList();
					// for (int i = 0; i < a.length; i++) {
					// list.add(a[i]);
					// }
					int executeDataCount = ts.getExecuteDataCount();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Map<String, Object> map = new HashMap<String, Object>();
					Calendar lastDate = Calendar.getInstance();
					Calendar startDate = Calendar.getInstance();
					lastDate.add(Calendar.DAY_OF_MONTH, -3);
					startDate.add(Calendar.DAY_OF_MONTH, +1);
					map.put("endTime", sdf.format(startDate.getTime()).toString());
					map.put("startTime", sdf.format(lastDate.getTime()).toString());
					map.put("rand", true);
					page.setConditions(map);
					List<Diary> newRepeatDiary = new ArrayList<Diary>();
					// 获取需要自动发布的说说数据
					List<Diary> diaryList = diaryService.selectDiaryListBySelective(page);
					LOG.info(diaryList.size() + "条需要执行的故事点赞");
					for (Diary diary2 : diaryList) {
						for (Diary diary3 : repeatDiaryList) {
							if (diary3.getId().intValue() == diary2.getId().intValue()) {
								newRepeatDiary.add(diary2);
							}
						}
					}
					diaryList.removeAll(newRepeatDiary);
					Collections.shuffle(userIds);
					// if (userIds.size() <= executeDataCount) {
					// executeDataCount = userIds.size();
					// }
					// List<User> currentUserIds= userIds.subList(0,
					// executeDataCount);
					LOG.info(diaryList.size() + "条需要执行的故事点赞（判断了重复数据）");
					if (diaryList != null && diaryList.size() > 0) {
						// super.addTaskCount(diaryList.size());
						diaryList.forEach(diary -> {
							// for (int i = 0; i < list.size(); i++) {
							// for (int j = 0; j < list.get(i); j++) {
								Collections.shuffle(userIds);
								int i = diaryList.indexOf(diary);
								Integer userId = userIds.get(i).getUserId();
								// usedUser.add(userIds.get(i));
								// userIds.remove(i);
								int talkId = diary.getId();
								Map<String, Object> params = new HashMap<>();
								params.put("diaryId", talkId);
								usedUser.add(userIds.get(0));
								params.put("userId", userId);
								userIds.subList(0, userIds.size() - 1);
								TalkPraiseSateEnum isPraise = diaryService.queryPraiseState(params);
								if (isPraise == TalkPraiseSateEnum.NO_PRAISE) {
									int dateLong = (int) (Math.random() * 1000 * 60 * 50);
									Long lovalue = new Date().getTime() + dateLong;
									Timer timer = new Timer();
									TimerTask task = new TimerTask() {
										@Override
										public void run() {
											// lowerTaskCount();
											// 查询故事是否存在 存在返回1
											if (diaryService.selectDiaryIsExistByDiaryId(
													Integer.valueOf(params.get("diaryId")
															.toString())).intValue() == 0) {
												LOG.info("[" + jobName + "故事不存在，return;]---id=["
														+ talkId + "]");
												return;
											}
											params.put("state",
													TalkPraiseSateEnum.YES_PRAISE.getValue());
											diaryService.insertPraise(params); // 开始点赞
										}
									};
									timer.schedule(task, new Date(lovalue));
									LOG.info("[" + jobName + "]---id=[" + talkId + "]执行时间"
											+ new Date(lovalue));

									// }
									// }
							}
						});
					}

					// 将点赞的故事存到redis，防止重复使用同一个故事多次点赞
					stringRedisTemplate.opsForValue()
							.set(AUTO_DIARY_PRAISE_JOB_DIARY_REDIS_KEY_PREFIX + ":",
									JsonUtil.toJson(diaryList), userRepeatOperationSecond,
									TimeUnit.SECONDS);
					// 保存日志
					TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
							TaskLogManager.NORMAL, scheduleJob.getAliasName() + "任务正常运行");
				}
				LOG.info("任务" + getClass() + "[" + jobName + "]成功运行 ,下次运行时间:"
						+ DateTimeUtil.formatDate(context.getNextFireTime(), "yyyy-MM-dd HH:mm:ss"));
			}
		} catch (Exception e) {
			LOG.error("任务[" + jobName + "]异常", e);
			String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
			SmsSendUtil.send(strs, jobName, this.getClass() + "陌友" + appName,
					CONSTANT.REG_SCHEDULE, ResponseEnum.SCHEDULE_ERRO.getValue());
			TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
					TaskLogManager.EXCEPTION, e.toString());
		}
	}

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	// @SuppressWarnings("all")
	// private List<User> filterRepeatUser() {
	// StringRedisTemplate stringRedisTemplate = (StringRedisTemplate)
	// SpringBeanUtils
	// .getFirstBeanOfType(StringRedisTemplate.class);
	// RedisTemplate<String, String> redisTemplate = (RedisTemplate<String,
	// String>) SpringBeanUtils
	// .getFirstBeanOfType(RedisTemplate.class);
	// Set<String> keys = stringRedisTemplate
	// .keys(AUTO_DIARY_PRAISE_JOB_USER_IDS_REDIS_KEY_PREFIX + ":*");
	// List<User> userIdList = new ArrayList<>();
	// if (keys != null) {
	// keys.forEach(key -> {
	// String usersJson = redisTemplate.opsForValue().get(key);
	// List users = JsonUtil.toObject(usersJson, List.class);
	// if (users == null || users.size() == 0) {
	// return;
	// }
	// users.forEach(u -> {
	// userIdList.add(JsonUtil.toObject(u, User.class));
	// });
	// });
	// }
	// return userIdList;
	// }
	@SuppressWarnings("all")
	private List<Diary> filterRepeatDiary() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Set<String> keys = null;
		try {
			keys = stringRedisTemplate.keys(AUTO_DIARY_PRAISE_JOB_DIARY_REDIS_KEY_PREFIX + ":*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (keys != null) {
			return new ArrayList<Diary>();
		}
		List<Diary> userIdList = new ArrayList<>();
		if (keys != null) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users == null || users.size() == 0) {
					return;
				}
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, Diary.class));
				});
			});
		}
		return userIdList;
	}
}
