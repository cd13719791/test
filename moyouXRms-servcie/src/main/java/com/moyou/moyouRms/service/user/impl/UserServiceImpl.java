package com.moyou.moyouRms.service.user.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.moyou.moyouRms.confing.SysConf;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.constants.enums.UserFundSrarchCategoryEnum;
import com.moyou.moyouRms.dao.user.GenerateNumberRecordMapper;
import com.moyou.moyouRms.dao.user.UserActiveInfoMapper;
import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.user.UserListMapper;
import com.moyou.moyouRms.dao.user.UserMapper;
import com.moyou.moyouRms.dao.userGold.UserGoldMapper;
import com.moyou.moyouRms.dao.userfund.UserFundMapper;
import com.moyou.moyouRms.easemob.entity.EaseMobUserInfo;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.statistics.NewUserResult;
import com.moyou.moyouRms.model.user.FakeUser;
import com.moyou.moyouRms.model.user.GenerateNumberRecord;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserActiveInfo;
import com.moyou.moyouRms.model.user.UserCacheInfo;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserList;
import com.moyou.moyouRms.model.user.UserRegCondition;
import com.moyou.moyouRms.model.user.UserReturnBaseInfo;
import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.model.userRecommend.UserRecommend;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.jpush.MessagePushService;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.service.userrecommend.UserRecommendService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.Md5Util;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;
import com.moyou.moyouRms.util.file.CMyFileUtils;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserListMapper userListMapper;
	@Resource
	private UserMapper userMapper;
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserFundMapper userFundMapper;
	@Resource
	UserGoldMapper userGoldMapper;
	@Resource
	UserCountMapper userCountMapper;
	@Resource
	UserFundLogService userFundLogService;
	@Resource
	MessagePushService messagePushService;
	@Resource
	MsgSystemXService msgSystemXService;
	@Resource
	private UserActiveInfoMapper activeInfoMapper;
	@Resource
	private UserGoldService userGoldService;
	@Resource
	EaseMobService easeMobService;
	@Resource
	UserRecommendService userRecommendService;
	@Resource
	GenerateNumberRecordMapper numberRecordMapper;
	@Resource
	LiveUserInfoService liveUserInfoService;
	@Resource
	UserCountService userCountService;

	// private static String AUTO_PUBLISH_USER_IDS_REDIS_KEY_PREFIX =
	// "AUTO_PUBLISH_USER_IDS_REDIS_KEY_PREFIX";

	@Override
	public List<UserList> queryAllUsers(PageBean pb) {
		List<UserList> list = userListMapper.selectUserListALL(pb);
		for (int i = 0; i < list.size(); i++) {
			java.util.Date date = new Date();
			if (null == list.get(i).getBirthday()) {
				continue;
			}
			java.util.Date mydate = (list.get(i).getBirthday());
			long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) + 1;
			int year = (int) Double.parseDouble(new java.text.DecimalFormat("").format(day / 365f));
			list.get(i).setAge(year + "");
			list.get(i).setIsLiveUser(
					liveUserInfoService.checkIsLiveUser(Integer.parseInt(list.get(i).getUserId())));
		}
		if (pb.getConditions().get("type") != null && pb.getConditions().get("type").equals("1")) {
			// 运营账户 查询 关注数 粉丝数
			list.stream().peek(s -> {
				UserCount userCount = userCountService.selectUserCountByUserId(s.getUserId());
				s.setUserFans(userCount.getUserFollowersCount());
				s.setUserInterestCount(userCount.getUserInterestCount());
			});
		}
		return list;
	}

	@Override
	public Integer selectNewUserCount() {
		// TODO Auto-generated method stub
		return userMapper.selectNewUserCount();
	}

	@Override
	public Integer selectOnlineUserCount() {
		// TODO Auto-generated method stub
		return userMapper.selectOnlineUserCount();
	}

	@Override
	public Integer getUserCountByParams(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return userMapper.getUserCountByParams(paramMap);
	}

	@Override
	public Integer addUser(User userCondition) {
		// TODO Auto-generated method stub

		return userMapper.insertSelective(userCondition);
	}

	@Override
	public Integer updateByPrimaryKeySelective(User userCondition) {
		Integer userId = userCondition.getUserId();
		String deviceId = userMapper.selectDeviceIdByUserId(userCondition.getUserId());
		userCondition.setDeviceId(deviceId);
		UserInfo userInfo = userInfoMapper.queryUserNickName(userId);// 获取用户昵称
		String pushChatId = userInfo.getPushChatId();
		userCondition.setUpdateTime(new Date());
		if (userMapper.updateByPrimaryKeySelective(userCondition) != 1)// 修改是否禁用状态
		{
			return RESPONSE.ERROR;
		}
		if (userCondition.getState() == User.STATE_YES) {
			List<UserInfo> userInfoList = userInfoMapper.selectAllUserByDeviceId(deviceId);
			if (StringUtil.isNotEmpty(deviceId) && userInfoList != null && userInfoList.size() > 0) {
				userInfoList.stream().forEach(arg -> {
					easeMobService.activateIMUser(arg.getPushChatId());
				});
				userMapper.updateByDeviceId(userCondition);
			} else {
				easeMobService.activateIMUser(pushChatId);
			}
		} else {
			userInfoMapper.updateUserAvatar(new UserInfo(userId, CONSTANT.LIMIT_USER_AVATAR));
			List<UserInfo> userInfoList = userInfoMapper.selectAllUserByDeviceId(deviceId);
			if (StringUtil.isNotEmpty(deviceId) && userInfoList != null && userInfoList.size() > 0) {
				userInfoList.stream().forEach(
						arg -> {
							arg.setAvatar(CONSTANT.LIMIT_USER_AVATAR);
							User user = new User();
							user.setUserId(arg.getUserId());
							user.setState(User.STATE_NO);
							userMapper.updateByPrimaryKeySelective(user);
							userInfoMapper.updateUserAvatar(arg);
							userRecommendService.updateRecommedStatusByUserId(new UserRecommend(arg
									.getUserId(), UserRecommend.STATE_NO));
							easeMobService.deactivateIMUser(arg.getPushChatId());
						});
			} else {
				easeMobService.deactivateIMUser(pushChatId);
			}
		}
		return RESPONSE.SUCCESS;
	}

	public int getUserIdByToken(String token) {
		String tokenValue = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNotEmpty(tokenValue)) {
			String tokenValueArr[] = tokenValue.split("_");
			int userId = StringUtil.getInt(tokenValueArr[0]);
			return userId;
		}
		return 0;
	}

	/**
	 * 刷新到缓存
	 * 
	 * @param userCacheInfo
	 * @param token
	 */
	@SuppressWarnings("unused")
	private void refreshToCache(UserCacheInfo userCacheInfo, String token) {
		refreshInfoIntoCache(userCacheInfo);
		refreshTokenIntoCache(token, userCacheInfo.getUserId());
	}

	/**
	 * 刷新用户信息
	 * 
	 * @param userCacheInfo
	 */
	private void refreshInfoIntoCache(UserCacheInfo userCacheInfo) {
		redisTemplate.opsForValue().set(userCacheInfo.getUserId(), JsonUtil.toJson(userCacheInfo));
	}

	/**
	 * 刷新登录令牌
	 * 
	 * @param token
	 * @param userId
	 */
	private void refreshTokenIntoCache(String token, String userId) {
		redisTemplate.opsForValue().set(token, userId, 7, TimeUnit.DAYS);
	}

	/**
	 * 查询假用户，用于群运营，选择群主
	 */
	@Override
	public List<User> queryFakeUser() {
		return userMapper.queryFakeUser();
	}

	@Override
	public List<User> queryFakeUserByCity(String city) {
		return userMapper.queryFakeUserByCity(city);
	}

	@Override
	public List<UserReturnBaseInfo> addUsers(List<UserRegCondition> userConditionList,
			String accountId, int type) {
		List<UserReturnBaseInfo> resultList = new ArrayList<UserReturnBaseInfo>();
		if (userConditionList != null && userConditionList.size() > 0) {
			for (UserRegCondition u : userConditionList) {
				PageBean pageBean = new PageBean();
				pageBean.setPageNumber(1);
				pageBean.setPageSize(20);
				Map<String, Object> map = new HashMap<>();
				map.put("nickname", u.getNickname());
				map.put("sex", u.getSex());
				map.put("birthday", DateTimeUtil.formatDate(u.getBirthday()));
				map.put("address", u.getAddress());
				map.put("type", User.ADMIN_CREATE);
				pageBean.setConditions(map);
				List<UserList> results = userListMapper.selectUserListALL(pageBean);
				if (results.size() > 0) {// 存在相似数据过滤该条数据
					continue;
				}
				resultList.add(addUser(u));
			}
		}
		return resultList;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @param userInfo
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public UserReturnBaseInfo addUser(UserRegCondition regCondition) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setFundState(User.FUND_STATE_YES);
		user.setState(User.STATE_YES);
		user.setAccount(UUIDUtil.getUUID());
		user.setPassword(Md5Util.getMd5("123456"));
		user.setType(User.UNREALY_USER);
		user.setAccountType(regCondition.getAcountType());
		String huanxinId = UUIDUtil.getUUID();
		Date now = user.getCreateTime();

		userMapper.insertSelective(user);// 添加user 并获取userid
		String userId = user.getUserId().toString();
		/**
		 * start userInfo
		 */
		UserInfo userInfo = UserInfo.instanceOfUserInfo(regCondition, userId, now, initMoyouId()
				.toString());
		userInfo.setPushChatId(huanxinId);
		SysConf appConf = SysConf.getInstance();
		String avatar = userInfo.getAvatar();
		if (StringUtil.isEmpty(avatar)) {
			avatar = appConf.getValueByKey("user.default.avatar");
		}
		userInfo.setAvatar(avatar);

		/**
		 * end userInfo userCount Start
		 */
		UserCount userCount = UserCount.instanceOfUserCount(userId, now);
		userCount.setUserFollowersCount(0);
		userCount.setUserFriendsCount(0);
		userCount.setUserInterestCount(0);
		userCount.setUserSerialCheckInCount(0);
		userCount.setUserStoryFolderCount(0);
		userCount.setUserTalkCount(0);
		userCount.setUserVisitCount(0);
		/**
		 * end userCount
		 */
		/**
		 * 用户活跃表信息 start
		 */
		UserActiveInfo activeInfo = new UserActiveInfo();
		activeInfo.setCreateTime(new Date());
		activeInfo.setLatitude(regCondition.getLatitude());
		activeInfo.setLongitude(regCondition.getLongitude());
		activeInfo.setMapFunction(1);// 地图模式 1开启0关闭
		activeInfo.setOnlineState(1);// 1在线0离线
		activeInfo.setUpdateTime(new Date());
		activeInfo.setUserId(user.getUserId());
		activeInfoMapper.insert(activeInfo);
		/**
		 * 用户活跃表信息 end
		 */
		// 注册环信账号
		EaseMobUserInfo em = easeMobService.getUsernamePassword(huanxinId, huanxinId);
		boolean isSuccess = easeMobService.addUser(em.getUsername(), em.getPassword(),
				user.getNickname());
		UserReturnBaseInfo returnBaseInfo = null;
		if (isSuccess) {
			userInfoMapper.insertSelective(userInfo);
			// 使用一个陌友id则陌友id生成区间表计数器累加1
			GenerateNumberRecord numberRecord = numberRecordMapper.queryRangeOfMoyouId();
			GenerateNumberRecord updateGenerateNumberRecord = new GenerateNumberRecord();
			updateGenerateNumberRecord.setId(numberRecord.getId());
			updateGenerateNumberRecord.setRegisterCount(numberRecord.getRegisterCount() + 1);
			numberRecordMapper.updateByPrimaryKeySelective(updateGenerateNumberRecord);

			userCountMapper.insertSelective(userCount);

			user = userMapper.selectByPrimaryKey(user.getUserId());
			userInfo.setUserId(user.getUserId());
			userInfo = userInfoMapper.selectUserInfoByUserId(userInfo);
			setUserLatLan(userInfo, user);
			return returnBaseInfo = new UserReturnBaseInfo();
		}
		return null;
	}

	/**
	 * 用户相关数据持久化操作
	 * 
	 * @param user
	 * @param loginLog
	 * @param userInfo
	 * @param userAvatar
	 * @param userSetting
	 */
	/*
	 * private void insertUserRelatedOption(User user, UserInfo userInfo,
	 * UserCount userCount) { // userMapper.insert(user); //
	 * loginLogMapper.insert(loginLog); userInfoMapper.insert(userInfo); //
	 * userAvatarMapper.insert(userAvatar); //
	 * userSettingMapper.insert(userSetting); userCountMapper.insert(userCount);
	 * }
	 */

	/**
	 * 注册时 user 对象初始化
	 * 
	 * @param
	 * 
	 * @param regCondition
	 * @return
	 */
	@SuppressWarnings("unused")
	private User instanceOfUser(Object object) {
		User user = JsonUtil.toObject(object, User.class);
		//
		/*
		 * Random ran = new Random(); user.setMoyouId(ran.nextLong());
		 */
		return user;
	}

	/**
	 * 陌友 id
	 * 
	 * @return
	 */
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
		boolean isExist = isExistMoyouId(moyouId + "");
		if (isExist) {// 存在，则再次回调方法获取id
			return initMoyouId();
		}
		return moyouId;
	}

	@Override
	public Map<String, String> queryUserDetailedData(int userId) {
		// TODO Auto-generated method stub
		return userMapper.queryUserDetailDate(userId);
	}

	@Override
	public int addUserActiveInfo(UserActiveInfo activeInfo) {
		return activeInfoMapper.insert(activeInfo);
	}

	@SuppressWarnings("unused")
	@Override
	public User queryUserId(int sex, String redisKey, String repeatUserRedisKey) {
		List<User> userIds = new ArrayList<User>();
		boolean bl = true;
		int index = 0;
		User user = null;
		while (index < 20) {
			user = queryUserId(sex, redisKey, repeatUserRedisKey, bl);
			if (user != null) {
				user.setCity(userInfoMapper.queryUserCity(user.getUserId()).getCity());
				return user;
			}
			index++;
		}
		return user;
	}

	@SuppressWarnings("unused")
	private User queryUserId(int sex, String redisKey, String repeatUserRedisKey, boolean bl) {
		List<User> userIds;
		User user = JsonUtil.toObject(redisTemplate.opsForList().leftPop(redisKey), User.class);
		if (user == null) {// 如果redis里面不够了。就从数据库重新拉取
			userIds = userMapper.selectAllFakeUserForPush();
			userIds.remove(filterRepeatUser(repeatUserRedisKey));// 去除防重复保护的用户
			if (userIds == null) {
				return null;
			} else {
				{// 重新把随机出来的user集合丢进redis
					Collections.shuffle(userIds);
					userIds.forEach(s -> {// 理论上每次都从redis里面取人是不会有重复，当redis取完了的人
											// 才开始有重复，所以把去重复加在数据库取用户的时候
						redisTemplate.opsForList().rightPush(redisKey, JsonUtil.toJson(s));
					});
				}
				user = JsonUtil.toObject(redisTemplate.opsForList().leftPop(redisKey), User.class);
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(user.getUserId());
				userInfo = userInfoMapper.selectUserInfoByUserId(userInfo);
				if (userInfo != null && userInfo.getGender() == sex) {
					bl = false;
					return user;
				} else {// 性别不符合 丢回redis
					redisTemplate.opsForList().rightPush(redisKey, JsonUtil.toJson(user));
				}

			}
			// return queryUserId(sex, redisKey, repeatUserRedisKey);
		} else {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(user.getUserId());
			userInfo = userInfoMapper.selectUserInfoByUserId(userInfo);
			if (userInfo != null && userInfo.getGender() == sex) {
				bl = false;
				return user;
			} else {// 性别不符合 丢回redis
				redisTemplate.opsForList().rightPush(redisKey, JsonUtil.toJson(user));
			}
		}
		return null;
	}

	/**
	 * 不能重复使用的用户
	 * 
	 * @return
	 */
	@SuppressWarnings("all")
	private List<User> filterRepeatUser(String repeatUserKey) {
		StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringBeanUtils
				.getFirstBeanOfType(StringRedisTemplate.class);
		RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getFirstBeanOfType(RedisTemplate.class);
		Set<String> keys = stringRedisTemplate.keys(repeatUserKey + ":*");
		List<User> userIdList = new ArrayList<>();
		if (keys != null) {
			keys.forEach(key -> {
				String usersJson = redisTemplate.opsForValue().get(key);
				List users = JsonUtil.toObject(usersJson, List.class);
				users.forEach(u -> {
					userIdList.add(JsonUtil.toObject(u, User.class));
				});
			});
		}
		return userIdList;
	}

	@Override
	public List<UserList> queryUserRecommend(PageBean pb) {
		// TODO Auto-generated method stub
		List<UserList> list = userListMapper.queryUserRecommend(pb);
		// SimpleDateFormat sDateFormat = new SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss");
		for (UserList s : list) {
			String date2 = s.getCreateTime().split("\\.")[0];
			s.setCreateTime(date2);
		}
		return userListMapper.queryUserRecommend(pb);
	}

	@Override
	public int updateUserRecommend(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendService.updateByParam(record);
	}

	/**
	 * 查询假用户，用于群运营，选择群主分页
	 */
	@Override
	public List<FakeUser> queryFakeUserList(Page page) {
		PageBean pagebean = new PageBean();
		pagebean.setPageNumber(page.getPageNumber());
		pagebean.setPageSize(page.getPageSize());
		List<FakeUser> results = userMapper.queryFakeUserList(pagebean);
		page.setTotal(pagebean.getTotalRecord());
		page.setResults(results);
		return results;
	}

	@Override
	public boolean isExistMoyouId(String moyouId) {
		String id = userInfoMapper.queryContaninsMoyouId(moyouId);
		if (id != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getUserCountByQQWEIXIN() {
		// TODO Auto-generated method stub
		return userMapper.getUserCountByQQWEIXIN();
	}

	public void setUserLatLan(UserInfo userInfo, User s) {
		try {
			String str = CMyFileUtils.readFile("E:\\坐标\\" + userInfo.getCity() + ".txt");
			String strArr[] = str.split("\n");
			if (strArr != null && strArr.length > 0) {
				int len = strArr.length;
				Random ran = new Random();
				int index = ran.nextInt(len);
				// System.out.println(strArr[index]);
				// System.out.println(strArr[index].split(",")[0]);
				// System.out.println(strArr[index].split(",")[1]);
				UserActiveInfo activeInfo = new UserActiveInfo();
				;
				Integer userId = activeInfoMapper.selectActiveInfoUserIdByUserId(s.getUserId());
				if (userId == null) {
					activeInfo.setUserId(s.getUserId());
					activeInfo.setLongitude(Double.valueOf(strArr[index].split(",")[0]));
					activeInfo.setLatitude(Double.valueOf(strArr[index].split(",")[1]));
					activeInfo.setMapFunction(1);
					activeInfo.setCreateTime(new Date());
					activeInfo.setOnlineState(1);
					activeInfoMapper.insertSelective(activeInfo);
				} else {

					activeInfo.setUserId(s.getUserId());
					activeInfo.setLongitude(Double.valueOf(strArr[index].split(",")[0]));
					activeInfo.setLatitude(Double.valueOf(strArr[index].split(",")[1]));
					activeInfo.setMapFunction(1);
					activeInfo.setCreateTime(new Date());
					activeInfo.setOnlineState(1);
					activeInfoMapper.updateUserActiveInfo(activeInfo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public User queryFakeUserList() {
		// TODO Auto-generated method stub
		return userMapper.queryFakeUserLimit1();
	}

	@Override
	public List<User> queryAllFakeUser() {
		// TODO Auto-generated method stub
		return userMapper.queryAllFakeUser();
	}

	@Override
	public User queryFakeUserLimit1() {
		// TODO Auto-generated method stub
		return userMapper.queryFakeUserLimit1();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<NewUserResult> queryUserCountList(NewUser record) throws ParseException {
		// '%y-%m-%d %H'
		StringBuilder sb = new StringBuilder("%y-%m");
		if (record.getMonth() != null && record.getMonth() != 0) {
			sb.append("-%d");
			if (record.getDay() != null && record.getDay() != 0) {
				sb.append(" %H");
			}
		}
		record.setDateFormart(sb.toString());
		List<NewUserResult> list = userMapper.queryUserCountList(record);
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
		if (record.getMonth() == null || record.getMonth() == 0) {
			try {
				if (list == null || list.size() == 0) {
					list = new ArrayList<>();
					for (int i = 0; i < 13; i++) {
						list.add(new NewUserResult(null, 0));
					}
					return list;
				}
				Date s = sdf.parse(list.get(0).getDays());
				Integer year = s.getYear() - 100;
				for (int i = s.getMonth(); i > 0; i--) {
					StringBuilder strb = new StringBuilder(year.toString());
					strb.append("-");
					if (i < 10) {
						strb.append("0");
					}
					strb.append(Integer.valueOf(i));
					NewUserResult newUserResult = new NewUserResult();
					newUserResult.setDays(strb.toString());
					newUserResult.setCounts(0);
					list.add(0, newUserResult);
				}
				s = sdf.parse(list.get(list.size() - 1).getDays());
				if (s.getMonth() < 11) {
					for (int i = 2; i < s.getMonth() + 1; i++) {
						StringBuilder strb = new StringBuilder(year.toString());
						strb.append("-");
						if (i + s.getMonth() < 10) {
							strb.append("0");
						}
						strb.append(Integer.valueOf(i + s.getMonth()));
						NewUserResult newUserResult = new NewUserResult();
						newUserResult.setDays(strb.toString());
						newUserResult.setCounts(0);
						list.add(list.size(), newUserResult);
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return list;
		}
		if (record.getDay() == null || record.getDay() == 0) {
			sdf = new SimpleDateFormat("yy-MM-dd");
			Date s = null;
			if (list == null || list.size() == 0) {
				list = new ArrayList<>();
				for (int i = 0; i < 13; i++) {
					list.add(new NewUserResult(null, 0));
				}
				return list;
			} else {
				s = sdf.parse(list.get(0).getDays());
			}
			Integer dayCount = getCurrentMonthLastDay(s);
			for (int i = 1; i < dayCount; i++) {

				Date date = sdf.parse(list.get(i - 1).getDays());
				Integer year = date.getYear() - 100;
				StringBuilder strb = new StringBuilder(year.toString());
				strb.append("-");
				if (s.getMonth() < 9) {
					strb.append("0");
				}
				strb.append(Integer.valueOf(s.getMonth() + 1));
				strb.append("-");
				if (i <= 9) {
					strb.append("0");
				}
				String tempstr = strb.toString();
				// 判断第一天是不是1号
				if ((!list.get(i - 1).getDays().equals(tempstr + i))) {// 如果不是
																		// 就添加进去
																		// 以此类推
					strb.append((Integer.valueOf(i)));
					list.add(i - 1, new NewUserResult(strb.toString(), 0));
					continue;
				} else if (i >= list.size()) {
					strb.append((Integer.valueOf(i + 1)));
					list.add(i, new NewUserResult(strb.toString(), 0));
					continue;
				}
				// if (i >= list.size())
				// {
				// list.add(i, new NewUserResult(strb.toString(), 0));
				// continue;
				// }
			}
		} else if (record.getDay() != null && record.getDay() != 0) {
			// 精确到时间的查询
			sdf = new SimpleDateFormat("yy-MM-dd HH");
			String str = "";
			if (list == null || list.size() == 0) {
				str = record.getYear().toString() + "-";
				if (record.getMonth() < 10) {
					str += "0";
				}
				str += record.getMonth() + " 00";
			} else {
				str = list.get(0).getDays();
			}
			String formart = str.substring(0, str.length() - 2);
			int hour = 24;
			for (int i = 0; i <= hour; i++) {
				StringBuilder sbb = new StringBuilder(formart);
				if (i < 10) {
					sbb.append("0");
				}
				sbb.append(i);
				if (i >= list.size() || (!list.get(i).getDays().equals(sbb.toString()))) {// 如果不是
																							// 就添加进去
																							// 以此类推
					list.add(i, new NewUserResult(sbb.toString(), 0));
				}
			}
		}
		return list;
	}

	public static int getCurrentMonthLastDay(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	@Override
	public ApiResult updateUserGold(Map<String, Object> map, HttpServletRequest httpServletRequest,
			Account account) {
		UserGold userGold = new UserGold();
		Integer userGoldNumber = Integer.valueOf(map.get("userGold").toString());
		userGold.setUserId(Integer.valueOf(map.get("userId").toString()));
		/**
		 * 用户修改金币理由
		 */
		String msg = map.get("msg").toString();
		if (StringUtil.isEmpty(msg)) {
			return new ApiResult(ResponseEnum.ERROR, "失败,修改理由为空");
		}
		userGold.setUserGold(userGoldNumber);
		UserGold oldUserGold = userGoldService.selectByUserId(userGold.getUserId().toString());
		Integer newGold = null;
		if (oldUserGold != null) {
			newGold = oldUserGold.getUserGold() + userGold.getUserGold();
			userGold.setId(oldUserGold.getId());
			userGold.setUserGold(newGold);
			userGoldService.addOrUpdateGold(userGold.getUserId(), userGoldNumber);
		} else {
			userGold.setUpdateTime(new Date());
			userGoldService.insertSelective(userGold);
		}
		/**
		 * 插入修改日志
		 */
		UserFundLog userFundLog = new UserFundLog();
		userFundLog.setAudit(UserFundLog.AUDIT_SUCCESS);
		userFundLog.setClientIp(StringUtil.getIpAddr(httpServletRequest));
		userFundLog.setCreateTime(new Date());
		userFundLog.setPayUserId(StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
				CONSTANT.SYS_CONF_PATH)));// 默认系统ID
		userFundLog.setPingxxState(UserFundLog.AUDIT_SUCCESS);
		String adminName = account == null ? "未登录管理员" : account.getName();
		userFundLog.setPayUserName(adminName);
		userFundLog.setModeType(UserFundModeEnum.SYSTEM_UPDATE.getValue());
		userFundLog.setReceiveUserId(userGold.getUserId());
		userFundLog.setSearchCategory(UserFundSrarchCategoryEnum.GOLD.getValue());
		userFundLog.setSearchUserid(userGold.getUserId());
		userFundLog.setUserFund(userGoldNumber);
		userFundLog.setReceiveUserName(userInfoMapper.queryUserNickName(userGold.getUserId())
				.getNickname());
		userFundLog.setTradeNumber(TradeNumberUtil.getTradeNumber());
		userFundLog.setBalance(userGold.getUserGold());
		userFundLog.setExtnd("{'oldGold':" + oldUserGold.getUserGold().toString()
				+ ",'updateAdmin':'" + adminName + "','newGold':" + (userGold.getUserGold())
				+ ",'msg':'" + msg + "'}");
		userFundLogService.insert(userFundLog);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	@Override
	public List<UserInfo> selectFakeUserForBiuBiuBiu() {
		// TODO Auto-generated method stub
		return userInfoMapper.selectFakeUserForBiuBiuBiu();
	}

	@Override
	public User queryFakeUserBySex(Integer sex) {
		// TODO Auto-generated method stub
		return userMapper.queryFakeUserBySex(sex);
	}

	/**
	 * 修改用户金币
	 * 
	 * @param map
	 */
	// private ApiResult updateUserGold(Map<String, Object> map,
	// HttpServletRequest httpServletRequest, Account account) {
	// UserGold userGold = new UserGold();
	// Integer userGoldNumber = Integer
	// .valueOf(map.get("userGold").toString());
	// userGold.setUserId(Integer.valueOf(map.get("userId").toString()));
	// /**
	// * 用户修改金币理由
	// */
	// String msg = map.get("msg").toString();
	// if (StringUtil.isEmpty(msg)) {
	// return new ApiResult(ResponseEnum.ERROR, "失败,修改理由为空");
	// }
	// userGold.setUserGold(userGoldNumber);
	// UserGold oldUserGold = userGoldService.selectByUserId(userGold
	// .getUserId().toString());
	// Integer newGold = null;
	// if (oldUserGold != null) {
	// newGold = oldUserGold.getUserGold() + userGold.getUserGold();
	// userGold.setId(oldUserGold.getId());
	// userGold.setUserGold(newGold);
	// userGoldService.addOrUpdateGold(userGold.getUserId(),
	// userGoldNumber);
	// } else {
	// userGold.setUpdateTime(new Date());
	// userGoldService.insertSelective(userGold);
	// }
	// /**
	// * 插入修改日志
	// */
	// UserFundLog userFundLog = new UserFundLog();
	// userFundLog.setAudit(UserFundLog.AUDIT_SUCCESS);
	// userFundLog.setClientIp(StringUtil.getIpAddr(httpServletRequest));
	// userFundLog.setCreateTime(new Date());
	// userFundLog.setPayUserId(StringUtil.getInt(PropertiesUtil
	// .getValueByKey("system_user_id", CONSTANT.SYS_CONF_PATH)));// 默认系统ID
	// userFundLog.setPingxxState(UserFundLog.AUDIT_SUCCESS);
	// String adminName = account == null ? "未登录管理员" : account.getName();
	// userFundLog.setPayUserName(adminName);
	// userFundLog.setModeType(UserFundModeEnum.SYSTEM_UPDATE.getValue());
	// userFundLog.setReceiveUserId(userGold.getUserId());
	// userFundLog.setSearchCategory(UserFundSrarchCategoryEnum.GOLD
	// .getValue());
	// userFundLog.setSearchUserid(userGold.getUserId());
	// userFundLog.setUserFund(userGoldNumber);
	// userFundLog.setReceiveUserName(userInfoMapper.queryUserNickName(
	// userGold.getUserId()).getNickname());
	// userFundLog.setTradeNumber(TradeNumberUtil.getTradeNumber());
	// userFundLog.setExtnd("{'oldGold':"
	// + oldUserGold.getUserGold().toString() + ",'updateAdmin':'"
	// + adminName + "','newGold':" + (userGold.getUserGold())
	// + ",'msg':'" + msg + "'}");
	// userFundLogService.insert(userFundLog);
	// }
}
