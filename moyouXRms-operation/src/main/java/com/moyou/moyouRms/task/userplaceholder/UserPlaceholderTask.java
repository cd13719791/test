package com.moyou.moyouRms.task.userplaceholder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.model.nearbyrank.NearbyRank;
import com.moyou.moyouRms.model.user.FakeUser;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.service.nearbyrank.NearbyRankService;
import com.moyou.moyouRms.service.provincecity.ProvinceCityService;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.task.BaseTask;
import com.moyou.moyouRms.util.StringUtil;

/**
 * 假用户24小时换一批占位定时任务
 * 
 * @author liuxinyi
 * @date 2017年4月12日
 * @version 1.0.0
 */
@Component
public class UserPlaceholderTask extends BaseTask {
	@Autowired
	private UserService userService;
	@Autowired
	private ProvinceCityService provinceCityService;
	@Autowired
	private TalkService talkService;

	@Resource
	private NearbyRankService nearbyRankService;

	@Scheduled(cron = "0 0 0 * * ?")
	// @Scheduled(cron =
	// "0/5 * * * * ? ")
	// 间隔5秒执行
	public void taskCycle() {
		logger.info(StringUtil.getTraceInfo() + ":[task start]");
		// try {
		// List<FakeUser> userList = userService.queryFakeUserList(new Page(1,
		// 3));//
		// // 在数据库获取假用户
		// Collections.shuffle(userList);// 打乱数据顺序
		// // 开始占位 目前规则是每个城市占位4个用户， 占位金币为200 24小时更新一次
		// userList.forEach(s -> {
		// talkService.createPlaceholder(s.getUserId(), 100, "");
		// });
		// } catch (Exception e) {
		// logger.error(StringUtil.getTraceInfo() + ":[" + e.getMessage() + "]",
		// e);
		// String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
		// SmsSendUtil.send(strs, getClass().getName(), this.getClass() + "陌友",
		// CONSTANT.REG_SCHEDULE, ResponseEnum.SCHEDULE_ERRO.getValue());
		//
		// } finally {
		// logger.info(StringUtil.getTraceInfo() + ":[task end]");
		// }
		golobal3();
	}

	@SuppressWarnings("all")
	public void golobal3() {
		// RedisTemplate<String, String> redisTemplate_KVString =
		// (RedisTemplate<String, String>) SpringBeanUtils
		// .getBean("redisTemplate_KVString", RedisTemplate.class);
		String rank33 = "rank33", rank233 = "rank233", rank333 = "rank333";
		// ValueOperations<String, String> opsForValue =
		// redisTemplate_KVString.opsForValue();
		// if (null == opsForValue.get(rank33)) { // init
		// opsForValue.set(rank33, "33_33_33_33_33_33_33_33_33_33_33");
		// opsForValue.set(rank233, "233_233_233_233_233_233_233_233_");
		// opsForValue.set(rank333, "333_333_333_333_333_333_333_333_");
		// }
		// SELECT count(*) from t_user WHERE type = 1
		List<FakeUser> fakeUser = userService.queryFakeUserList(new Page(1, 3));
		// int count = 1120; // count 由上面一行的SQL返回
		Random random = new Random();
		ArrayList<String> keyList = new ArrayList<>();
		keyList.add("33_33_33_33_33_33_33_33_33_33_33");
		keyList.add("233_233_233_233_233_233_233_233_");
		keyList.add("333_333_333_333_333_333_333_333_");
		// SELECT user_id FROM t_user WHERE type = 1 LIMIT offset , 3;
		ArrayList<Integer> userIds = new ArrayList<>(); // 由上面一条SQL返回结果
		fakeUser.forEach(s -> {
			userIds.add(s.getUserId());
		});
		Integer[] goldNum = { 100, 200, 300, 400, 500, 600, 700, 800 };
		ArrayList<Integer> goldList = new ArrayList<>(Arrays.asList(goldNum));
		Random goldIndexRandom = new Random();
		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			// 更新 t_nearby_rank 表
			int index = goldIndexRandom.nextInt(goldList.size());
			for (Integer userId : userIds) {
				int gold = goldList.get(index);
				nearbyRankService.updateNonEmptyNearbyRankById(new NearbyRank(key, userId, gold,
						new Date(), null));
				// update t_nearby_rank set user_id = #{userId} , gold = #{gold}
				// where id = #{key}

			}
		}
	}
}
