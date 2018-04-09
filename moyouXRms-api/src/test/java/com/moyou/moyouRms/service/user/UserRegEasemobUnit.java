package com.moyou.moyouRms.service.user;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.user.GenerateNumberRecordMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.easemob.entity.EaseMobUserInfo;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.model.user.GenerateNumberRecord;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;
import com.moyou.moyouRms.util.UUIDUtil;

/**
 * 
 * 注册环信
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserRegEasemobUnit extends BaseJunit4 {
	@Autowired
	private UserService service;
	@Resource
	private EaseMobService easeMobService;
	@Resource
	private SecretRobotService secretRobotService;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private UserService userService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	GenerateNumberRecordMapper numberRecordMapper;
	@SuppressWarnings("unused")
	@Test
	@Rollback(false)
	public void unit() {
		try {
			List<User> list = service.queryFakeUser();
			for (int i = 0; i < list.size(); i++) {
				// 注册环信账号
				User u = list.get(i);
				EaseMobUserInfo em = easeMobService.getUsernamePassword(u.getPushChatId(),
						u.getPushChatId());
				boolean isSuccess = easeMobService.addUser(em.getUsername(),
						em.getPassword(), u.getNickname());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	private Integer initMoyouId() {
		int moyouId = 0;
		String moyouIdString = redisTemplate.opsForList().rightPop(CONSTANT.MOYOUID_LIST);
		if (StringUtils.isEmpty(moyouIdString)) {
			// moyouId取值区间
			GenerateNumberRecord numberRecord = numberRecordMapper.queryRangeOfMoyouId();
			int startNum = numberRecord.getStartNumber(), endNum = numberRecord.getEndNumber();
			int surplus = endNum - startNum;// 区间值
			if (numberRecord.getRegisterCount() >= surplus) {// 用户注册超过区间，需要新生成一条区间值记录
				startNum = endNum;
				endNum = startNum + surplus;
				GenerateNumberRecord newGenerateNumberRecord = new GenerateNumberRecord();
				newGenerateNumberRecord.setCreateTime(new Date());
				newGenerateNumberRecord.setStartNumber((startNum));
				newGenerateNumberRecord.setEndNumber((endNum));
				newGenerateNumberRecord.setRegisterCount(0);
				newGenerateNumberRecord.setId(UUIDUtil.getUUID());
				numberRecordMapper.insert(newGenerateNumberRecord);
			}
			List<String> moyouIdList = Lists.newArrayList();
			for (; startNum < endNum; startNum++) {
				moyouIdList.add(startNum + "");
			}
			Collections.shuffle(moyouIdList);
			redisTemplate.opsForList().leftPushAll(CONSTANT.MOYOUID_LIST, moyouIdList);
			moyouId = Integer.parseInt(redisTemplate.opsForList().rightPop(CONSTANT.MOYOUID_LIST));
		} else {
			moyouId = Integer.parseInt(moyouIdString);
		}
		boolean isExist = isExistMoyouId(moyouId+"");
		if (isExist) {// 存在，则再次回调方法获取id
			return initMoyouId();
		}
		return moyouId;
	}
	public boolean isExistMoyouId(String moyouId) {
		String id = userInfoMapper.queryContaninsMoyouId(moyouId);
		if (id != null) {
			return true;
		}
		return false;
	}

	@Test
	@Rollback(false)
	public void unit1() {
		try {
			easeMobService.addUser("BF6064E3EAB883D6E03C023814235E63", "321",
					"哈哈");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Rollback(false)
	public void unit3() {
		try {
			List<UserInfo> userInfo = userInfoMapper.queryUserPushChatId();
			for (UserInfo userInfo2 : userInfo) {
				easeMobService.disconnectIMUser(userInfo2.getPushChatId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
