package com.moyou.moyouRms.task.schedule.job.diary;

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
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.diary.DiaryService;
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
public class AutoAddReadNumberDiaryJob extends BaseTask implements Job {
	String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
	static final int DOCOUNT = 20;// 每次执行多少条
	/* 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(AutoAddReadNumberDiaryJob.class);
	// 自动发布说说，假用户存到redis的key前缀
	// private static String AUTO_ADD_DIARY_READ_NUBERN_REDIS_KEY_PREFIX =
	// "AUTO_ADD_DIARY_READ_NUBERN_REDIS_KEY_PREFIX";//发布的专辑se
	private static String AUTO_ADD_DIARY_REPEAT_READ_NUBERN_KEY_PREFIX = "AUTO_ADD_DIARY_REPEAT_READ_NUBERN_KEY_PREFIX3";// 防重复专辑

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
			DiaryService diaryService = (DiaryService) SpringBeanUtils
					.getFirstBeanOfType(DiaryService.class);
			int executeDataCount = ts.getExecuteDataCount();// 获取需要自动发布的说说数据条数
			PageBean page = new PageBean(1, DOCOUNT);// 每次执行多少篇故事
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			Calendar lastDate = Calendar.getInstance();
			Calendar startDate = Calendar.getInstance();
			lastDate.add(Calendar.DATE, -3);// 日期回滚7天
			startDate.add(Calendar.DATE, 1);
			map.put("startTime", sdf.format(lastDate.getTime()).toString());
			map.put("endTime", sdf.format(startDate.getTime()).toString());
			// map.put("rand", true);
			map.put("orderBy", 4);
			page.setConditions(map);
			// 获取需要自动发布的说说数据
			List<Diary> diaryList = diaryService.selectDiaryListBySelective(page);
			List<Diary> repeatDiary = filterRepeatDiary();
			if (repeatDiary != null && repeatDiary.size() > 0) {
				for (Diary d : diaryList) {// 去重
					for (int j = 0; j < repeatDiary.size(); j++) {
						if (repeatDiary.get(j).getId() == d.getId()) {
							diaryList.remove(d);
						}
					}
				}
			}
			// 移除在防重复保护规则中的用户
			if (diaryList != null && diaryList.size() > 0) {
				// 则开始模拟增加阅读量
				long[] longs = RandomStringUtil
						.getNumberByCount(diaryList.size(), executeDataCount);// 随机阅读量数组
				if (longs == null) {
					System.err.println("操作条数非法");
					return;
				}
				for (int i = 0; i < diaryList.size(); i++) {
					addReadNumber(diaryList.get(i), Integer.valueOf(longs[i] + ""));
					// 将发布信息的假用户存到redis，防止重复使用同一个用户
					stringRedisTemplate.opsForValue().set(
							AUTO_ADD_DIARY_REPEAT_READ_NUBERN_KEY_PREFIX + ":"
									+ System.currentTimeMillis(), JsonUtil.toJson(diaryList),
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
			// 保存异常日志
			String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
			SmsSendUtil.send(strs, "", this.getClass() + "陌友" + appName, CONSTANT.REG_SCHEDULE,
					ResponseEnum.SCHEDULE_DOES_NOT_DO.getValue());
			TaskLogManager.saveTaskLog(jobGroup + ":" + jobName, jobClass,
					TaskLogManager.EXCEPTION, e.toString());
		}
	}

	/**
	 * 增加阅读量
	 * 
	 * @param executeDataCount
	 * @param u
	 * @param diaryRobot
	 */
	public void addReadNumber(Diary diary, int executeDataCount) {
		DiaryService diaryService = (DiaryService) SpringBeanUtils
				.getFirstBeanOfType(DiaryService.class);
		diary.setReadTotal(executeDataCount);
		diaryService.updateDiaryReadCount(diary);
	}

	/**
	 * 不能重复使用的故事
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<Diary> filterRepeatDiary() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(AUTO_ADD_DIARY_REPEAT_READ_NUBERN_KEY_PREFIX
				+ ":*");
		List<Diary> diaryList = new ArrayList<>();
		if (keys != null && keys.size() > 0) {
			keys.forEach(key -> {
				String usersJson = stringRedisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				if (users != null && users.size() != 0) {
					users.forEach(u -> {
						diaryList.add(JsonUtil.toObject(u, Diary.class));
					});
				}
			});
		}
		return diaryList;
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

	// public static void main(String[] args) {
	// for (long string : RandomStringUtil.generate(500, 2)) {
	// // System.out.println(string);
	// }
	// }
}
