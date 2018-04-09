package com.moyou.moyouRms.task.schedule.job.talk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkComment;
import com.moyou.moyouRms.model.talkrobot.TalkRobotComment;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 说说自动评论[假用户给真实用户发表的说说评论需求文档0.9.2]
 *           目前规则，1小时运行一次，取最近30条说说，拿假用户,然后从评论随机拿没有评论过该说说的评论
 *           ,在一个小时内的随机的某个时间给说说评论。假用户冷却根据设置时间，评论冷却24小时
 * @author liuxinyi
 * @date 2017年3月7日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AutoCommentJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoCommentJob.class);
	// 说说自动评论，假用户存到redis的key前缀
	private static String AUTO_TALK_COMMENT_JOB_USER_IDS_REDIS_KEY_PREFIX = "AUTO_TALK_COMMENT_JOB_USER_IDS_REDIS_KEY_PREFIX";

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
			TalkService talkService = (TalkService) SpringBeanUtils
					.getFirstBeanOfType(TalkService.class);
			TalkRobotService talkRobotService = (TalkRobotService) SpringBeanUtils
					.getFirstBeanOfType(TalkRobotService.class);
			TalkMapper talkMapper = (TalkMapper) SpringBeanUtils
					.getFirstBeanOfType(TalkMapper.class);
			// 获取所有可用的评论
			List<TalkRobotComment> talkRobotCommentList = talkRobotService
					.queryAllTalkRobotCommentList();
			int executeDataCount = ts.getExecuteDataCount();// 需要拿来评论说说的用户数量和被评论的说说数量
			List<User> repeatUserList = filterRepeatUser();
			List<User> userIds = userService.queryFakeUser();// 在数据库获取用来评论的假用户
			if (userIds != null) {
				userIds.removeAll(repeatUserList);// 移除在防重复保护规则中的用户
				Collections.shuffle(userIds);// 打乱数据顺序
				int userCount = executeDataCount;
				if (userIds.size() < executeDataCount) {
					userCount = userIds.size();
				}
				// userIds = userIds.subList(0, userCount);
				List<User> currentUserIds = userIds.subList(0, userCount);
				if (userIds.size() > 0 && talkRobotCommentList != null
						&& talkRobotCommentList.size() > 0) {
					Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
					SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");// 取24小时内的说说
					String time = matter1.format(as);
					String time2 = matter1.format(new Date());
					// System.out.println(time);
					// String startTime =
					// DateTimeUtil.formatDate(DateTimeUtil.addDate(new Date()))
					// +" 00:00:00";
					// String endTime = DateTimeUtil.formatDate(new Date())
					// +" 23:59:59";
					// 由近7天的真用户发表的所有说说，取出近 executeDataCount 条
					List<Talk> talkList = talkService.queryRealUserTalkByDateLimit(time, time2,
							executeDataCount);
					List<TalkRobotComment> results = new ArrayList<TalkRobotComment>();
					Collections.shuffle(talkList);// 打乱数据顺序
					int talkCount = executeDataCount;
					if (talkList.size() < executeDataCount) {
						talkCount = talkList.size();
					}
					List<Talk> shuffleTalkList = talkList.subList(0, talkCount);
					// super.addTaskCount(shuffleTalkList.size());
					if (talkList != null && talkList.size() > 0) {// 查询到有说说数据，则开始假用户的模拟评论
						// this.addTaskCount(shuffleTalkList.size());// 往redis
						// 技术起中加计数
						for (Talk talk : shuffleTalkList) {
							List<TalkRobotComment> oldComment = talkRobotCommentList;
							List<TalkRobotComment> newComment = new ArrayList<>();
							for (int i = talkRobotCommentList.size() - 1; i >= 0; i--) {
								for (int j = talk.getCommentText().size() - 1; j >= 0; j--) {
									if (talkRobotCommentList.get(i).getContent()
											.equals(talk.getCommentText().get(j))) {
										newComment.add(talkRobotCommentList.get(i));
									}
								}
							}// 说说内容去重
							if (currentUserIds.size() < (shuffleTalkList.indexOf(talk) + 1)) {// 假用户个数不够，则跳出循环
								LOG.info("假用户个数不够给说说评论,currentUserIds.size():"
										+ currentUserIds.size() + "shuffleTalkList.size():"
										+ shuffleTalkList.size());
								continue;
							}
							if (!talk.getUserIdList().contains(
									currentUserIds.get(shuffleTalkList.indexOf(talk)).getUserId()
											+ "")
									&& oldComment.size() > 0)

							{// 用户没有评论过这个说说//这个说说能背评论的内容不为空
								if (talk.getCommentText().size() >= 1)
									continue;// 用户没有评论过这个说说//这个说说能背评论的内容不为空
								int val = (int) (Math.random() * (ts.getIntervalSecond() * 1000) + 1);// 随机在下次执行前
																										// 执行掉这个
								Date date = new Date(new Date().getTime() + val);
								Timer time1 = new Timer();
								TimerTask task = new TimerTask() {
									@Override
									public void run() {
										try {
											// lowerTaskCount();
											int talkId = talk.getId();
											// 查询talk是否存在 存在返回1
											if (talkMapper.selectTalkIsExistByTalkId(talkId)
													.intValue() == 0) {
												return;
											}
											Random random = new Random();
											int commentIndex = random.nextInt(oldComment.size());
											TalkRobotComment talkRobotComment = oldComment
													.get(commentIndex);
											TalkComment talkComment = new TalkComment();
											talkComment.setContent(talkRobotComment.getContent());
											talkComment.setTalkId(talkId);
											talkComment.setUserId(currentUserIds.get(
													shuffleTalkList.indexOf(talk)).getUserId());
											talkComment.setParentId(0);
											talkComment.setCreateTime(new Date());
											LOG.info("说说评论:talkComment(:"
													+ JsonUtil.toJson(talkComment));
											if (talkService.insertComment(talkComment).intValue() != 0) {// 开始评论
																											// 评论过的内容加入集合
																											// 等待结束任务
																											// 添加进redis
																											// 缓存cd
												results.add(oldComment.get(commentIndex));
												// 修改评论使用次数
												talkRobotService
														.updateTalkRobotCommentUseCount(talkRobotComment
																.getId());
											}
										} catch (Exception e) {
											LOG.error("任务[" + jobName + "]异常", e);
											e.printStackTrace();
										} finally {
											time1.cancel();
										}
									}
								};
								// LOG.info("任务[" + jobName + "] 执行时间[" + date +
								// "]");
								time1.schedule(task, date);

							}

						}

						// }
						stringRedisTemplate.opsForValue().set(
								TalkRobotComment.TALK_ROBOT_COMMENT_IN_REDIS_REPEAT + ":"
										+ System.currentTimeMillis(), JsonUtil.toJson(results),
								TalkRobotComment.TALK_ROBOT_COMMENT_24_HOURS, TimeUnit.HOURS);
						// 将发布信息的假用户存到redis，防止重复使用同一个用户
						stringRedisTemplate.opsForValue().set(
								AUTO_TALK_COMMENT_JOB_USER_IDS_REDIS_KEY_PREFIX + ":"
										+ System.currentTimeMillis(),
								JsonUtil.toJson(currentUserIds), userRepeatOperationSecond,
								TimeUnit.SECONDS);
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
					TaskLogManager.EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<User> filterRepeatUser() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(AUTO_TALK_COMMENT_JOB_USER_IDS_REDIS_KEY_PREFIX
				+ ":*");
		List<User> userIdList = new ArrayList<>();
		if (keys != null) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users == null) {
					return;
				}
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, User.class));
				});
			});
		}
		return userIdList;
	}
}
