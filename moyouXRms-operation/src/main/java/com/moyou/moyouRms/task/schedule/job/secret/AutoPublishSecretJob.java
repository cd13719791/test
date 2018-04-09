package com.moyou.moyouRms.task.schedule.job.secret;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.schedule.ScheduleManager;
import com.moyou.moyouRms.manager.schedule.TaskLogManager;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretContent;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobotContent;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.secret.SecretContentService;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 秘密自动发布
 * @author 陈旭
 * @date 2017/03/28
 * @version 1.0.0
 */
public class AutoPublishSecretJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoPublishSecretJob.class);
	// 自动发布说说，假用户存到redis的key前缀
	private static String AUTO_PUBLISH_SECRET_JOB_USER_REDIS_KEY_PREFIX = "AUTO_PUBLISH_SECRET_JOB_USER_REDIS_KEY_PREFIX";// 发布专辑的user
																															// redis
																															// key
	private static String AUTO_PUBLISH_SECRET_REPEAT_USER_REDIS_KEY_PREFIX = "AUTO_PUBLISH_SECRET_REPEAT_USER_REDIS_KEY_PREFIX";// 防重复userRedisKey

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
			SecretRobotService secretRobotService = (SecretRobotService) SpringBeanUtils
					.getFirstBeanOfType(SecretRobotService.class);
			// DiaryService diaryService = (DiaryService) SpringBeanUtils
			// .getFirstBeanOfType(DiaryService.class);
			// DiaryContentService diaryContentService = (DiaryContentService)
			// SpringBeanUtils
			// .getFirstBeanOfType(DiaryContentService.class);
			int executeDataCount = ts.getExecuteDataCount();// 获取需要自动发布的说说数据条数
			PageBean page = new PageBean(1, 100);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", DiaryRobot.UNPUBLISH_STATE);// 0未发布1发布
			map.put("willPush", true);
			// Date as = new Date(new Date().getTime()-24*60*60*1000);
			// SimpleDateFormat matter1 = new
			// SimpleDateFormat("yyyy-MM-dd");//取24小时内的说说
			// String time1 = matter1.format(as);
			// String time2=matter1.format(new Date());
			page.setConditions(map);
			// 获取需要自动发布的说说数据
			List<SecretRobot> secretRobotList = secretRobotService.selectSecretRobotList(page);
			List<User> userIds = new ArrayList<User>();
			// 移除在防重复保护规则中的用户
			if (secretRobotList != null && secretRobotList.size() > 0) {
				// super.addTaskCount(secretRobotList.size());
				// 则开始假用户的模拟发布说说
				for (SecretRobot secretRobot : secretRobotList) {
					Timer time = new Timer();
					TimerTask task = new TimerTask() {
						@Override
						public void run() {
							try {
								// lowerTaskCount();
								int sex = (int) Math.random() * 2 + 1;
								// 随机拿一个假用户
								User u = userService.queryUserId(sex,
										AUTO_PUBLISH_SECRET_JOB_USER_REDIS_KEY_PREFIX,
										AUTO_PUBLISH_SECRET_REPEAT_USER_REDIS_KEY_PREFIX);
								insertSecretByTimerTask(u, secretRobot);
								userIds.add(u);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								time.cancel();
							}
						}
					};
					time.schedule(task, secretRobot.getPushTime());
				}
				// 将发布信息的假用户存到redis，防止重复使用同一个用户
				stringRedisTemplate.opsForValue().set(
						AUTO_PUBLISH_SECRET_JOB_USER_REDIS_KEY_PREFIX + ":"
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
	 * 发布秘密方法
	 * 
	 * @param u
	 * @param diaryRobot
	 */
	public void insertSecretByTimerTask(User u, SecretRobot secretRobot) {
		SecretRobotService secretRobotService = (SecretRobotService) SpringBeanUtils
				.getFirstBeanOfType(SecretRobotService.class);
		SecretService secretService = (SecretService) SpringBeanUtils
				.getFirstBeanOfType(SecretService.class);
		SecretContentService secretContentService = (SecretContentService) SpringBeanUtils
				.getFirstBeanOfType(SecretContentService.class);

		UserInfoService userInfoService = (UserInfoService) SpringBeanUtils
				.getFirstBeanOfType(UserInfoService.class);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(u.getUserId());
		userInfo = userInfoService.selectUserInfoByUserId(userInfo);
		// System.out.println("AutoPublishSecretJob"+"[166]");
		if (secretRobotService.selectByPrimaryKey(secretRobot.getId()).getState() == SecretRobot.PUBLISH_STATE) {
			return;
		}
		if (u != null) {// 开始发布
			Secret t = new Secret();
			// t.setCity(userInfo.getCity());
			t.setCreateTime(secretRobot.getPushTime());
			t.setSecretTitle(secretRobot.getSecretTitle());
			t.setState(Integer.valueOf(Secret.NORMAL));
			// t.setCommentTotal(secretRobot.getCommentTotal()); 陈旭后面看看 为什么这么做
			t.setCommentTotal(0);
			t.setUserId(u.getUserId());
			t.setImageTotal(secretRobot.getImageTotal());
			t.setFirstContent(secretRobot.getFirstContent());
			t.setFirstImage(secretRobot.getFirstImage());
			t.setLocation(userInfo.getCity());
			t.setAnonymousAvatarId(secretRobot.getAnonymousAvatarId());
			t.setExtendData(secretRobot.getExtendData());
			LOG.info("秘密*标题 " + "[" + t.getSecretTitle() + "]成功运行" + "本机ip:[ " + getIp() + " ]");
			secretService.insertSelective(t);
			// 专辑发布end 专辑资源发布start
			List<SecretRobotContent> srList = secretRobot.getSecretContentList();
			if (srList != null && srList.size() > 0) {
				srList.forEach(cr -> {
					SecretContent tr = new SecretContent();
					tr.setContentType(cr.getContentType());
					tr.setCreateTime(cr.getCreateTime());
					tr.setSecretId(t.getId());
					tr.setSorting(cr.getSorting());
					tr.setTextOrPicture(cr.getTextOrPicture());
					tr.setExtendData(cr.getExtendData());
					secretContentService.insertSelective(tr);
				});
			}
			// 发布后修改源数据
			SecretRobot dRobot = new SecretRobot();
			dRobot.setId(secretRobot.getId());
			dRobot.setState(SecretRobot.PUBLISH_STATE);// 0未发布1发布
			dRobot.setUserId(u.getUserId());
			secretRobotService.updatePushStateByPrimaryKey(dRobot);
			// 修改用户发布数据
			UserCountService userCountService = (UserCountService) SpringBeanUtils
					.getFirstBeanOfType(UserCountService.class);
			UserCount userCount = new UserCount();
			userCount.setUserId(u.getUserId());
			userCount = userCountService.queryUserCount(userCount);
			if (null == userCount) {
				userCount = new UserCount();
				userCount.setUserId(u.getUserId());
				userCount.setUserSecretCount(1);
				userCountService.insertSelective(userCount);
			} else {
				int count = userCount.getUserSecretCount() == null ? 0 : userCount
						.getUserSecretCount();
				userCount.setUserSecretCount(count + 1);
				userCountService.updateUserCountSecretJia1(u);
			}
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
		Set<String> keys = stringRedisTemplate
				.keys(AUTO_PUBLISH_SECRET_REPEAT_USER_REDIS_KEY_PREFIX + ":*");
		List<User> userIdList = new ArrayList<>();
		if (keys != null) {
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
		User userId = JsonUtil.toObject(
				stringRedisTemplate.opsForList().leftPop(
						AUTO_PUBLISH_SECRET_REPEAT_USER_REDIS_KEY_PREFIX), User.class);// 从redis中取出一条userId
		if (userId != null) {
			UserInfo u = new UserInfo();
			u.setUserId(userId.getUserId());
			u = userInfoService.selectUserInfoByUserId(u);
			// System.out.println("AutoPublishSecretJob"+"[264]");
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
			// System.out.println("AutoPublishSecretJob"+"[289]");
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

	public static void main(String[] args) {

		// System.out.println(JsonUtil.toJson(getIp()));
	}
}
