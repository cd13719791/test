package com.moyou.moyouRms.task.schedule.job.talk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.user.UserActiveInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkResource;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserActiveInfo;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;
import com.moyou.moyouRms.util.StringUtil;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 自动发布说说[假用户发布说说需求文档0.9.2]
 * @author liuxinyi
 * @date 2017年3月14日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class AutoPublishTalkJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	@Resource
	UserService userService;
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoPublishTalkJob.class);
	// 自动发布说说，假用户存到redis的key前缀
	private static String AUTO_PUBLISH_TALK_JOB_USER_IDS_REDIS_KEY_PREFIX = "AUTO_PUBLISH_NEW2_TALK_JOB_USER_IDS_REDIS_KEY_PREFIX";

	@SuppressWarnings("unchecked")
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
		// System.out.println( scheduleJob.getCronExpression());
		if (ts == null) {
			return;
		}
		try {

			int userRepeatOperationSecond = ts.getUserRepeatOperationSecond();// 单个小号多少秒不重复操作
			UserService userService = (UserService) SpringBeanUtils
					.getFirstBeanOfType(UserService.class);
			// UserMapper userMapper = (UserMapper)
			// SpringBeanUtils.getFirstBeanOfType(UserMapper.class);
			UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
					.getFirstBeanOfType(UserInfoService.class);
			UserActiveInfoMapper userActiveInfoMapper = (UserActiveInfoMapper) SpringBeanUtils
					.getFirstBeanOfType(UserActiveInfoMapper.class);
			TalkService talkService = (TalkService) SpringBeanUtils
					.getFirstBeanOfType(TalkService.class);
			TalkRobotService talkRobotService = (TalkRobotService) SpringBeanUtils
					.getFirstBeanOfType(TalkRobotService.class);
			int executeDataCount = ts.getExecuteDataCount();// 获取需要自动发布的说说数据条数
			PageBean page = new PageBean(1, executeDataCount);
			page.getConditions().put("releaseStatus", 0);// 0未发布1发布
			// 获取需要自动发布的说说数据
			List<TalkRobot> talkRobotList = talkRobotService.queryTalkRobotListForAutoPublish(page);
			if (talkRobotList.size() <= 0) {
				return;
			}
			List<User> repeatUserList = filterRepeatUser();
			// System.out.println(repeatUserList.size()
			// +"****************repeatUserList.size()");
			List<User> userIds = userService.queryFakeUser();// 在数据库获取用来评论的假用户
			// System.out.println(userIds.size()
			// +"****************userIds.size()");
			if (userIds != null) {
				userIds.removeAll(repeatUserList);// 移除在防重复保护规则中的用户
				if (userIds.size() > 0 && talkRobotList != null && talkRobotList.size() > 0) {
					// 则开始假用户的模拟发布说说
					repeatUserList.clear();
					// talkRobotList.forEach(talk -> {});
					for (int i = 0; i < talkRobotList.size(); i++) {
						TalkRobot talk = talkRobotList.get(i);
						int sex = talk.getSex();
						// 随机拿一个假用户
						User u = getPublishuser(sex, userIds);
						if (u == null) {
							continue;
						}
						repeatUserList.add(u);
						UserInfo uInfo = new UserInfo();
						uInfo.setUserId(u.getUserId());
						uInfo = userInfoService.selectUserInfoByUserId(uInfo);
						// System.out.println("AutoPublishTalkJob[ 102 ]");
						UserActiveInfo userActiveInfo = null;
						try {
							userActiveInfo = userActiveInfoMapper
									.selectUserLatAndLon(u.getUserId());
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
						if (u != null) {// 开始发布
							Talk t = new Talk();
							if (talk.getContent() != null) {
								t.setContent(talk.getContent());
							}
							t.setLatitude(updateLagLan(userActiveInfo.getLatitude().toString()));
							t.setLongitude(updateLagLan(userActiveInfo.getLongitude().toString()));
							t.setCreatorId(u.getUserId());
							t.setCity(uInfo.getCity());
							List<TalkResource> rList = new ArrayList<>();
							List<CommonResource> crList = talk.getResources();
							int id = 0;
							if (crList.isEmpty()) {
								LOG.info("任务"
										+ getClass()
										+ "["
										+ jobName
										+ ","
										+ talk.getId()
										+ "]没有图片 ,下次运行时间:"
										+ DateTimeUtil.formatDate(context.getNextFireTime(),
												"yyyy-MM-dd HH:mm:ss"));
								TalkRobot tr = new TalkRobot();
								tr.setId(talk.getId());
								tr.setPublish(new Date());
								tr.setReleaseStatus(1);// 0未发布1发布
								tr.setCreatorId(u.getUserId());
								talkRobotService.updateTalkPublishStatus(tr);
								id = tr.getId();
								try {
									SmsSendUtil.send(PHONE.CHEN_XV, jobName, "陌友" + talk.getId(),
											CONSTANT.REG_SCHEDULE,
											ResponseEnum.SCHEDULE_ERRO.getValue());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							;
							if (crList != null && crList.size() > 0) {
								for (int j = 0; j < crList.size(); j++) {
									CommonResource cr = crList.get(j);
									TalkResource tr = new TalkResource();
									tr.setUrl(cr.getUrl());
									tr.setPicOrder(Integer.parseInt(cr.getPicOrder() + ""));
									tr.setMediaType(Integer.valueOf(crList.get(0).getMediaType()));
									rList.add(tr);
								}
							}
							t.setTalkResource(rList);// 将资源放入发布的说说实体中
							if (crList == null || crList.size() == 0
									|| crList.get(0).getMediaType() == null) {
								LOG.info("任务["
										+ jobName
										+ "]==id=["
										+ id
										+ "]===没有图片："
										+ DateTimeUtil.formatDate(t.getCreateTime(),
												"yyyy:MM:dd hh:mm:ss"));
								continue;
							}
							t.setMediaType(crList.get(0).getMediaType());
							// 数据库做一次查询发布状态 避免重复发布
							if (talkRobotService.selectByPrimaryKey(talk.getId())
									.getReleaseStatus() == TalkRobot.PUBLISH) {
								return;
							}

							/**
								 * 
								 */
							Double d = Math.random() * 60 * 1000 * 5;// 随机在5分钟左右发布
							// Double d= Math.random()*60*1000;
							int i2 = d.intValue();
							Date date = new Date(new Date().getTime() + (Long.valueOf(i2)));
							t.setCreateTime(date);
							/**
							 * 随机发布时间
							 */
							// super.addTaskCount(1);
							// 没有重复 则添加
							// 按照随机出的发布时间 发布
							Timer time = new Timer();
							TimerTask task = new TimerTask() {
								@Override
								public void run() {
									try {
										talkService.insertTalk(t);
										// lowerTaskCount();
									} catch (Exception e) {
										LOG.error("任务[" + jobName + "]异常", e);
										e.printStackTrace();
									} finally {
										time.cancel();
									}
								}
							};
							time.schedule(task, t.getCreateTime());
							LOG.info("任务["
									+ jobName
									+ "]======执行时间："
									+ DateTimeUtil.formatDate(t.getCreateTime(),
											"yyyy:MM:dd hh:mm:ss"));
							// 发布后修改源数据
							TalkRobot tr = new TalkRobot();
							tr.setId(talk.getId());
							tr.setPublish(new Date());

							/**
								 * 
								 */
							tr.setPublish(date);
							// System.out.println(t.getCreateTime());
							/**
								 * 
								 */
							tr.setReleaseStatus(1);// 0未发布1发布
							tr.setCreatorId(u.getUserId());
							talkRobotService.updateTalkPublishStatus(tr);
							// 修改用户发布数据
							// UserCountService userCountService =
							// (UserCountService) SpringBeanUtils
							// .getFirstBeanOfType(UserCountService.class);
							// UserCount userCount=new UserCount();
							// userCount.setUserId(u.getUserId());
							// userCount=userCountService.queryUserCount(userCount);
							// if(null==userCount||null==userCount.getUserTalkCount()){
							// userCount=new UserCount();
							// userCount.setUserId(u.getUserId());
							// userCount.setUserTalkCount(1);
							// userCountService.insertSelective(userCount);
							// }else{
							// userCount.setUserTalkCount(userCount.getUserTalkCount()+1);
							// userCountService.updateUserCountTalkJia1(userCount);
							// }
						}

					}
					// 将发布信息的假用户存到redis，防止重复使用同一个用户
					stringRedisTemplate.opsForValue().set(
							AUTO_PUBLISH_TALK_JOB_USER_IDS_REDIS_KEY_PREFIX + ":"
									+ System.currentTimeMillis(), JsonUtil.toJson(repeatUserList),
							userRepeatOperationSecond, TimeUnit.SECONDS);
					// 保存日志

					TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
							TaskLogManager.NORMAL, scheduleJob.getAliasName() + "任务正常运行");
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
			e.printStackTrace();
		}
	}

	// 获取根据说说性别随机获取一个用户
	private User getPublishuser(int sex, List<User> userList) {
		UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
				.getFirstBeanOfType(UserInfoService.class);
		Collections.shuffle(userList);// 随机用户
		User returnUser = null;
		for (User user : userList) {
			UserInfo ui = new UserInfo();
			ui.setUserId(user.getUserId());
			ui = userInfoService.selectUserInfoByUserId(ui);
			if ((ui.getGender() == 0 && sex == 1) || (ui.getGender() == 1 && sex == 0)) {// 如果都是男或都是女这执行发布
				returnUser = user;
				break;
			}
		}
		return returnUser;
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
		// @SuppressWarnings("unchecked")
		// RedisTemplate<String, String> redisTemplate = (RedisTemplate<String,
		// String>) SpringBeanUtils
		// .getFirstBeanOfType(RedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(AUTO_PUBLISH_TALK_JOB_USER_IDS_REDIS_KEY_PREFIX
				+ ":*");
		List<User> userIdList = new ArrayList<>();
		if (keys != null) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users == null || users.size() == 0) {
					return;
				}
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, User.class));
				});
			});
		}
		return userIdList;
	}

	public static void main(String[] args) {
		System.out.println(updateLagLan("118.655458"));
	}

	private static Double updateLagLan(String s) {
		if (StringUtil.isEmpty(s)) {
			return 0D;
		}
		String temp = "0";
		String[] strs = s.split("\\.");
		if (strs.length > 0) {
			if (strs[1].length() > 3) {
				temp = new String(strs[0] + "." + strs[1].substring(0, 2)
						+ ((int) (Math.random() * 10000)));
			}
		}
		return Double.valueOf(temp);
	}
}
