package com.moyou.moyouRms.task.schedule.job.diary;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryContent;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.userdynamic.UserDynamic;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.diaryrobot.DiaryRobotService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userdynamic.UserDynamicService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;
import com.moyou.moyouRms.util.StringUtil;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 自动发布专辑
 * @author 陈旭
 * @date 2017/03/28
 * @version 1.0.0
 */
public class AutoPublishDiaryJob extends BaseTask implements Job {
	/* 日志对象 */
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	private static final Logger LOG = LoggerFactory.getLogger(AutoPublishDiaryJob.class);
	// 自动发布说说，假用户存到redis的key前缀
	// private static String AUTO_PUBLISH_DIARY_JOB_USER_IDS_REDIS_KEY_PREFIX =
	// "AUTO_PUBLISH_DIARY_JOB_USER_IDS_REDIS_KEY_PREFIX";
	// private static String AUTO_PUBLISH_USER_IDS_REDIS_KEY_PREFIX =
	// "AUTO_PUBLISH_USER_IDS_REDIS_KEY_PREFIX";
	private static String AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX = "AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX";// 发布专辑的user
																														// redis
																														// key
	private static String AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX = "AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX";// 防重复userRedisKey
	@Resource
	UserNotificationMapper userNotificationMapper;

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
			int userRepeatOperationSecond = ts.getUserRepeatOperationSecond();// 单个小号多少秒不重复操作
			UserService userService = (UserService) SpringBeanUtils
					.getFirstBeanOfType(UserService.class);
			DiaryRobotService diaryRobotService = (DiaryRobotService) SpringBeanUtils
					.getFirstBeanOfType(DiaryRobotService.class);
			// DiaryService diaryService = (DiaryService) SpringBeanUtils
			// .getFirstBeanOfType(DiaryService.class);
			// DiaryContentService diaryContentService = (DiaryContentService)
			// SpringBeanUtils
			// .getFirstBeanOfType(DiaryContentService.class);
			int executeDataCount = ts.getExecuteDataCount();// 获取需要自动发布的说说数据条数
			PageBean page = new PageBean(1, 100);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pushState", DiaryRobot.UNPUBLISH_STATE);// 0未发布1发布
			// map.put("pushTime",true);
			map.put("willPush", true);
			page.setConditions(map);
			// 获取需要自动发布的说说数据
			List<DiaryRobot> diaryRobotList = diaryRobotService.queryDiaryRobotList(page);
			// List<User> repeatUserList = filterRepeatUser();
			// List<User> userIds = userService.queryUserIdList(sex,
			// executeDataCount, AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX,
			// AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX);// 在数据库获取用来评论的假用户
			List<User> userIds = new ArrayList<User>();
			// 移除在防重复保护规则中的用户
			if (diaryRobotList != null && diaryRobotList.size() > 0) {
				// 则开始假用户的模拟发布说说
				// super.addTaskCount(diaryRobotList.size());
				for (DiaryRobot diaryRobot : diaryRobotList) {
					Timer time = new Timer();
					TimerTask task = new TimerTask() {
						@Override
						public void run() {
							try {
								// lowerTaskCount();
								int sex = diaryRobot.getSex() == null ? Math.random() > 0.5 ? 1 : 0
										: diaryRobot.getSex();
								// 随机拿一个假用户
								User u = userService.queryUserId(sex,
										AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX,
										AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX);
								insertDairyByTimerTask(u, diaryRobot);
								userIds.add(u);
							} catch (Exception e) {
								LOG.error("任务[" + jobName + "]异常", e);
								e.printStackTrace();
							} finally {
								time.cancel();
							}
						}
					};
					time.schedule(task, diaryRobot.getPushTime());
				}
				// 将发布信息的假用户存到redis，防止重复使用同一个用户
				stringRedisTemplate.opsForValue().set(
						AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX + ":"
								+ System.currentTimeMillis(), JsonUtil.toJson(userIds),
						userRepeatOperationSecond, TimeUnit.SECONDS);

				// 保存日志
				TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
						TaskLogManager.NORMAL, scheduleJob.getAliasName() + "任务正常运行");
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
	 * 发布专辑方法
	 * 
	 * @param u
	 * @param diaryRobot
	 */
	public void insertDairyByTimerTask(User u, DiaryRobot diaryRobot) {
		DiaryRobotService diaryRobotService = (DiaryRobotService) SpringBeanUtils
				.getFirstBeanOfType(DiaryRobotService.class);
		DiaryService diaryService = (DiaryService) SpringBeanUtils
				.getFirstBeanOfType(DiaryService.class);
		DiaryContentService diaryContentService = (DiaryContentService) SpringBeanUtils
				.getFirstBeanOfType(DiaryContentService.class);
		UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
				.getFirstBeanOfType(UserInfoService.class);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(u.getUserId());
		userInfo = userInfoService.selectUserInfoByUserId(userInfo);
		// System.out.println("AutoPublishDiaryJob[167]");
		if (diaryRobotService.selectByPrimaryKey(diaryRobot.getId()).getPushState() == DiaryRobot.PUBLISH_STATE) {
			return;
		}
		if (u != null) {// 开始发布
			Diary t = new Diary();
			t.setCity(userInfo.getCity());
			t.setCreateTime(diaryRobot.getPushTime());
			t.setDiaryTitle(diaryRobot.getDiaryTitle());
			t.setState(Integer.valueOf(Diary.DIARY_NORMAL_NUMBER));
			t.setCommentTotal(0);
			t.setPraiseTotal(0);
			t.setRewardTotal(0);
			t.setCreatorId(u.getUserId());
			t.setPicTotal(Integer.valueOf(diaryRobot.getDiaryContentRobot().stream()
					.filter(s -> s.getContenType() == DiaryContentRobot.PIC).count()
					+ ""));
			t.setSourceType(diaryRobot.getSourceType());
			StringBuilder searchContent = new StringBuilder("");
			diaryRobot.getDiaryContentRobot().forEach(s -> {
				if (s.getContenType() == DiaryContentRobot.TEXT) {
					searchContent.append(s.getTextOrPicture());
				}
			});
			t.setSearchContent(searchContent.toString());
			LOG.info("故事标题 " + "[" + t.getDiaryTitle() + "]成功运行" + "本机ip:[ "
					+ JsonUtil.toJson(getIp()) + " ]");

			diaryService.insertSelective(t);
			UserDynamicService userDynamicService = (UserDynamicService) SpringBeanUtils
					.getFirstBeanOfType(UserDynamicService.class);
			userDynamicService.insertSelective(new UserDynamic(t.getCreatorId(), t.getId(),
					UserDynamic.DIARY, new Date()));
			/**
			 * 添加动态数据
			 */

			// 专辑发布end 专辑资源发布start
			List<DiaryContentRobot> drList = diaryRobot.getDiaryContentRobot();
			if (drList != null && drList.size() > 0) {
				drList.forEach(cr -> {
					DiaryContent tr = new DiaryContent();
					tr.setContenType(cr.getContenType());
					tr.setCreateTime(cr.getCreateTime());
					tr.setDiaryId(t.getId());
					tr.setSorting(cr.getSorting());
					tr.setTextOrPicture(cr.getTextOrPicture());
					tr.setExtendData(cr.getExtendData());
					diaryContentService.insertSelective(tr);
				});
			}
			// 发布后修改源数据
			DiaryRobot dRobot = new DiaryRobot();
			dRobot.setId(diaryRobot.getId());
			dRobot.setPushState(DiaryRobot.PUBLISH_STATE);// 0未发布1发布
			dRobot.setCreatorId(u.getUserId());
			if (StringUtil.isNotEmpty(t.getSourceType())
					&& t.getSourceType() == Diary.SOURCE_TYPE_HTML) {
				t.setH5Url(PropertiesUtil.getValueByKey(CONSTANT.SYSTEM_H5_DIARY_PATH,
						CONSTANT.SYS_CONF_PATH) + t.getId());
				diaryService.updateh5UrlById(t);// 添加h5url参数
			}
			diaryRobotService.updatePushStateByPrimaryKey(dRobot);// 修改发布状态和url
			// 修改用户发布数据
			UserCountService userCountService = (UserCountService) SpringBeanUtils
					.getFirstBeanOfType(UserCountService.class);
			UserCount userCount = new UserCount();
			userCount.setUserId(u.getUserId());
			userCount = userCountService.queryUserCount(userCount);
			if (null == userCount) {
				userCount = new UserCount();
				userCount.setUserId(u.getUserId());
				userCount.setUserStoryFolderCount(1);
				userCountService.insertSelective(userCount);
			} else {
				userCount.setUserStoryFolderCount(userCount.getUserStoryFolderCount() + 1);
				userCountService.updateUserCountDiaryJia1(u);
			}
			// 推送start
			// JpushService jpushService = (JpushService) SpringBeanUtils
			// .getFirstBeanOfType(JpushService.class);
			// UserNotification userNotification =
			// userNotificationMapper.queryUserNotificationByDiaryId(t.getId());
			// userNotification.setActionType(2);
			// userNotification.setBusinessId(t.getId());
			// userNotification.setBusinessType(2);
			// userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_PRAISE_DIARY);
			// userNotification.setSendUserId(t.getCreatorId());
			// jpushService.sendNotificationPushCustomMsgToDB(userNotification);
			// 推送end
		}
	}

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<User> filterRepeatUser() {
		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringBeanUtils
				.getFirstBeanOfType(StringRedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX
				+ ":*");
		List<User> userIdList = new ArrayList<>();
		if (keys != null && keys.size() > 0) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, User.class));
				});
			});
		}
		return userIdList;
	}

	@SuppressWarnings("all")
	private UserInfo getUserByRedis(Integer sex) {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		// UserService userService =
		// (UserService)SpringBeanUtils.getFirstBeanOfType(UserService.class);
		UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
				.getFirstBeanOfType(UserInfoService.class);
		User userId = JsonUtil
				.toObject(
						stringRedisTemplate.opsForList().leftPop(
								AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX), User.class);// 从redis中取出一条userId
		if (userId != null) {
			UserInfo u = new UserInfo();
			u.setUserId(userId.getUserId());
			u = userInfoService.selectUserInfoByUserId(u);
			// System.out.println("AutoPublishDiaryJob***********" +"[270]");
			if (u.getGender() == sex) {
				return u;
			} else {
				// redisTemplate.opsForList().rightPush(
				// AUTO_PUBLISH_USER_IDS_REDIS_KEY_PREFIX, userId);
				// 这里不执行插入操作，等待redis 中user数据用完后从新拉取 ，要不然会有死循环
				return getUserByRedis(sex);
			}
		} else {
			return null;
		}

	}

	// 获取根据性别随机获取一个用户
	@SuppressWarnings("unused")
	private User getPublishuser(int sex, List<User> userList) {
		UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
				.getFirstBeanOfType(UserInfoService.class);
		Collections.shuffle(userList);// 随机用户
		User returnUser = null;
		for (User user : userList) {
			UserInfo ui = new UserInfo();
			ui.setUserId(user.getUserId());
			ui = userInfoService.selectUserInfoByUserId(ui);
			// System.out.println("AutoPublishDiaryJob[295]");
			if ((ui.getGender() == 0 && sex == 1) || (ui.getGender() == 1 && sex == 0)) {// 如果都是男或都是女这执行发布
				returnUser = user;
				break;
			}
		}
		return returnUser;
	}

	// private List<User> getRepeatUserByRedis(int sex,int number,String
	// rediskey, String repeatRedisKey){
	// UserService userService = (UserService) SpringBeanUtils
	// .getFirstBeanOfType(UserService.class);
	// List<User>
	// userList=userService.queryUserIdList(sex,number,rediskey,repeatRedisKey);
	// return userList;
	// }
	@SuppressWarnings("all")
	private static List getIp() {
		Enumeration allNetInterfaces = null;
		List list = new ArrayList();
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			// System.out.println(netInterface.getName());
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					// System.out.println("本机的IP = " + ip.getHostAddress());
					list.add(ip.getHostAddress());
				}
			}
		}
		return list;
	}
}
