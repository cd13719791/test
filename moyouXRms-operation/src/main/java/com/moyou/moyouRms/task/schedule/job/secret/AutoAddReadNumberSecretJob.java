package com.moyou.moyouRms.task.schedule.job.secret;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.task.schedule.basetask.BaseTask;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.RandomStringUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

/* 案例 同步和不同步的区别  非同步需要加@DisallowConcurrentExecution */
/**
 * @describe 自动发布专辑
 * @author 陈旭
 * @date 2017/03/28
 * @version 1.0.0
 */
public class AutoAddReadNumberSecretJob extends BaseTask implements Job {
	static final int DOCOUNT = 12;// 每次执行多少条
	/* 日志对象 */
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	private static final Logger LOG = LoggerFactory.getLogger(AutoAddReadNumberSecretJob.class);
	// 自动发布说说，假用户存到redis的key前缀
	// private static String AUTO_ADD_DIARY_READ_NUBERN_REDIS_KEY_PREFIX =
	// "AUTO_ADD_DIARY_READ_NUBERN_REDIS_KEY_PREFIX";//发布的专辑se
	private static String AUTO_ADD_SECRET_REPEAT_READ_NUBERN_KEY_PREFIX = "AUTO_ADD_SECRET_REPEAT_READ_NUBERN_KEY_PREFIX";// 防重复专辑

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
			SecretService secretService = (SecretService) SpringBeanUtils
					.getFirstBeanOfType(SecretService.class);
			int executeDataCount = ts.getExecuteDataCount();// 获取需要自动发布的说说数据条数
			PageBean page = new PageBean(1, DOCOUNT);// 每次执行多少篇故事
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			Calendar lastDate = Calendar.getInstance();
			Calendar startDate = Calendar.getInstance();
			startDate.add(Calendar.DATE, 1);
			map.put("endTime", sdf.format(startDate.getTime()).toString());
			lastDate.add(Calendar.DATE, -7);// 日期回滚7天
			map.put("startTime", sdf.format(lastDate.getTime()).toString());
			map.put("rand", true);
			map.put("orderBy", 2);
			page.setConditions(map);
			// 获取需要自动发布的说说数据
			List<Secret> secretList = secretService.selectSecretByPageBeanParam(page);
			List<Secret> repeatSecret = filterRepeatSecret();
			if (repeatSecret != null && repeatSecret.size() > 0) {
				for (Secret d : secretList) {// 去重
					for (int j = 0; j < repeatSecret.size(); j++) {
						if (repeatSecret.get(j).getId() == d.getId()) {
							secretList.remove(d);
						}
					}
				}
			}
			// 移除在防重复保护规则中的用户
			if (secretList != null && secretList.size() > 0) {
				// 则开始模拟增加阅读量
				long[] longs = RandomStringUtil.getNumberByCount(secretList.size(),
						executeDataCount);// 随机阅读量数组
				if (longs == null) {
					System.err.println("操作条数非法");
					return;
				}
				for (int i = 0; i < secretList.size(); i++) {
					addReadNumber(secretList.get(i), Integer.valueOf(longs[i] + ""));
					// 将发布信息的假用户存到redis，防止重复使用同一个用户
					stringRedisTemplate.opsForValue().set(
							AUTO_ADD_SECRET_REPEAT_READ_NUBERN_KEY_PREFIX + ":"
									+ System.currentTimeMillis(), JsonUtil.toJson(secretList),
							userRepeatOperationSecond, TimeUnit.SECONDS);
				}
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
	 * 增加阅读量
	 * 
	 * @param executeDataCount
	 * @param u
	 * @param SecretRobot
	 */
	public void addReadNumber(Secret secret, int executeDataCount) {
		SecretService secretService = (SecretService) SpringBeanUtils
				.getFirstBeanOfType(SecretService.class);
		secret.setReadTotal(executeDataCount);
		secretService.updateSecretReadCount(secret);
	}

	/**
	 * 不能重复使用的故事
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<Secret> filterRepeatSecret() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Set<String> keys = null;
		try {
			keys = stringRedisTemplate.keys(AUTO_ADD_SECRET_REPEAT_READ_NUBERN_KEY_PREFIX + ":*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Secret> secretList = new ArrayList<>();
		if (keys == null || keys.size() <= 0) {
			return secretList;
		}
		if (keys != null && keys.size() > 0) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users == null || users.size() == 0) {
					return;
				}
				users.forEach(u -> {
					secretList.add(JsonUtil.toObject(u, Secret.class));
				});
			});
		}
		return secretList;
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
						AUTO_ADD_SECRET_REPEAT_READ_NUBERN_KEY_PREFIX), User.class);// 从redis中取出一条userId
		if (userId != null) {
			UserInfo u = new UserInfo();
			u.setUserId(userId.getUserId());
			u = userInfoService.selectUserInfoByUserId(u);
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
			// System.out.println("AutoPublishSecretJob[295]");
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

	// public static void main(String[] args) {
	// Calendar startDate = Calendar.getInstance();SimpleDateFormat sdf = new
	// SimpleDateFormat("yyyy-MM-dd");
	// startDate.roll(Calendar.DATE, -7);
	// Calendar lastDate = Calendar.getInstance();
	// // System.out.println(sdf.format(lastDate.getTime()).toString());
	// lastDate.add(Calendar.DATE, -7);// 日期回滚7天
	// // System.out.println(sdf.format(lastDate.getTime()).toString());
	// }
}
