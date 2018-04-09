package com.moyou.moyouRms.service.usercrowd.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.easemob.server.comm.body.ChatGroupBody;
import com.google.common.collect.Lists;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.EasemobLimitNumberEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.generateCrowdNumber.GenerateCrowdNumberMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMembersMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdRecommendMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.generateCrowdNumber.GenerateCrowdNumber;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdInfoResult;
import com.moyou.moyouRms.model.usercrowd.UserCrowdMembers;
import com.moyou.moyouRms.model.usercrowd.UserCrowdPage;
import com.moyou.moyouRms.model.usercrowd.UserCrowdRecommend;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.usercrowd.UserCrowdService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.UUIDUtil;

@Service
public class UserCrowdServiceImpl implements UserCrowdService {

	// private static final int ONE_THOUSAND = 10000;
	@Resource
	private UserCrowdMapper userCrowdMapper;
	@Resource
	EaseMobService easeMobService;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserCrowdMembersMapper userCrowdMembersMapper;
	@Resource
	UserCrowdRecommendMapper userCrowdRecommendMapper;
	@Resource
	GenerateCrowdNumberMapper generateCrowdNumberMapper;
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserCrowd queryCountUserCrowd() {
		UserCrowd userCrowd = new UserCrowd();
		userCrowd.setCrowdMemberId(userCrowdMembersMapper.queryUserCrowdCount());
		userCrowd.setCountCrowd(userCrowdMapper.queryNewUserCrowd());
		userCrowd.setNewCountCrowd(userCrowdMapper.queryCountUserCrowd());
		userCrowd.setRecommedCount(userCrowdMapper.queryRecommedCount());
		return userCrowd;
	}

	/**
	 * 群運營初始化
	 * 
	 * @param pagebean
	 * @return
	 */
	@Override
	public List<UserCrowd> queryUserCrowdList(UserCrowdPage userCrowdPage) {
		PageBean pagebean = new PageBean();
		pagebean.setPageNumber(userCrowdPage.getPageNumber());
		pagebean.setPageSize(userCrowdPage.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("crowdName", userCrowdPage.getCrowdName());
		map.put("crowdId", userCrowdPage.getCrowdId());
		map.put("crowdUserNickName", userCrowdPage.getCrowdUserNickName());
		map.put("starttime", userCrowdPage.getStarttime());
		map.put("endtime", userCrowdPage.getEndtime());
		pagebean.setConditions(map);
		List<UserCrowd> results = new ArrayList<UserCrowd>();
		results = userCrowdMapper.queryUserCrowdList(pagebean);
		userCrowdPage.setTotal(pagebean.getTotalRecord());
		userCrowdPage.setResults(results);
		return results;
	}

	/**
	 * 创建群
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public int addCrowd(UserCrowd userCrowd) {
		List<UserInfo> userIdList;
		/*
		 * Integer crowdId = userCrowdMapper.queryMaxCrowdId(); if (crowdId ==
		 * null) { userCrowd.setCrowdId(ONE_THOUSAND); } else {
		 * userCrowd.setCrowdId(crowdId + 1); }
		 */
		// 使用一个陌友id则陌友id生成区间表计数器累加1
		userCrowd.setCrowdId(initCrowdId());
		GenerateCrowdNumber numberRecord = generateCrowdNumberMapper.queryRangeOfCrowdId();
		GenerateCrowdNumber updateGenerateNumberRecord = new GenerateCrowdNumber();
		updateGenerateNumberRecord.setId(numberRecord.getId());
		updateGenerateNumberRecord.setRegisterCount(numberRecord.getRegisterCount() + 1);
		generateCrowdNumberMapper.updateByPrimaryKeySelective(updateGenerateNumberRecord);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crowdCreateType", UserCrowd.ADMIN_CREATE_TYPE);
		int memberCount = userCrowd.getMemberCount();
		userCrowd.setCrowdCity("");
		userCrowd.setCrowdLatitude(0.0);
		userCrowd.setCrowdLongitude(0.0);
		userCrowd.setCreateTime(new Date());
		userCrowd.setUpdateTime(new Date());
		userCrowd.setCrowdStatus(1);// 设置群状态为正常
		userCrowd.setCrowdLevel(1);// 设置群等级为1
		userCrowd.setMemberCount(memberCount + 1);
		// 获取用户注册过的环信ID集合
		// List<String> userIdList = userMapper.selectUserIdByMap(paramMap);
		if (memberCount == 0) {
			String easemodCroweUserId = userCrowd.getEasemodCroweId();// 群主对应的环信用户
			// 创建环信群
			String groupName = userCrowd.getCrowdName();
			String crowdDesc = userCrowd.getCrowdDesc();
			ChatGroupBody chatGroupBody = new ChatGroupBody(groupName, crowdDesc, true,
					(long) EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT.getValue(), false,
					easemodCroweUserId, null);
			String easemodCroweId = easeMobService.createChatGroup(chatGroupBody);
			userCrowd.setEasemodCroweId(easemodCroweId);
			userCrowdMapper.insert(userCrowd);// 创建群
			// 将群主添加到成员表
			addCrowdMember(userCrowd.getId(), userCrowd.getUserId(), 1);
		} else {
			userIdList = userInfoMapper.queryUserIdAndPushChatId(userCrowd.getUserId());
			if (userIdList == null) {
				userIdList = new ArrayList<UserInfo>();
			}
			Collections.shuffle(userIdList);// 随机集合
			List<UserInfo> memberList = new ArrayList<UserInfo>();
			if (memberCount > userIdList.size()) {
				memberCount = userIdList.size();
				userCrowd.setMemberCount(memberCount);
				memberList = userIdList;
			} else {
				memberList = userIdList.subList(0, memberCount);
			}
			List<String> addMemberEasemobIdList = memberList.stream().map(UserInfo::getPushChatId)
					.collect(Collectors.toList());// 获取查询出来的假用户环信ID
			String easemodCroweUserId = userCrowd.getEasemodCroweId();// 群主对应的环信用户
			if (memberList.size() > 0) {
				// 创建环信群
				String groupName = userCrowd.getCrowdName();
				String crowdDesc = userCrowd.getCrowdDesc();
				ChatGroupBody chatGroupBody = new ChatGroupBody(groupName, crowdDesc, true,
						(long) EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT.getValue(),
						false, easemodCroweUserId, null);
				String easemodCroweId = easeMobService.createChatGroup(chatGroupBody);
				userCrowd.setEasemodCroweId(easemodCroweId);
				userCrowdMapper.insert(userCrowd);// 创建群
				// 将群主添加到成员表
				addCrowdMember(userCrowd.getId(), userCrowd.getUserId(), 1);
				// 将成员添加到环信群中
				for (int i = 0; i < memberList.size(); i++) {
					UserInfo ui = memberList.get(i);
					boolean userAddEasemobGroup = easeMobService.addUserToChatGroup(easemodCroweId,
							addMemberEasemobIdList.get(i));
					if (userAddEasemobGroup) {
						addCrowdMember(userCrowd.getId(), ui.getUserId(), 3);//
					}
				}
			}
		}
		return userCrowd.getCrowdId();
	}

	// 将用户添加到群中
	private void addCrowdMember(int crowdId, int memberId, int roleType) {
		UserCrowdMembers record = new UserCrowdMembers();
		record.setCrowdPkid(crowdId);
		record.setCrowdMemberId(memberId);
		record.setRoleType(roleType);// 1群主2管理员3普通成员
		userCrowdMembersMapper.insert(record);
	}

	/**
	 * 群 id
	 * 
	 * @return
	 */
	private Integer initCrowdId() {
		int crowdId = 0;
		String crowdIdString = redisTemplate.opsForList().rightPop(CONSTANT.CROWDID_LIST);
		if (StringUtils.isEmpty(crowdIdString)) {
			// 群Id取值区间
			GenerateCrowdNumber numberRecord = generateCrowdNumberMapper.queryRangeOfCrowdId();
			int startNum = numberRecord.getStartNumber(), endNum = numberRecord.getEndNumber();
			int surplus = endNum - startNum;// 区间值
			if (numberRecord.getRegisterCount() >= surplus) {// 用户注册超过区间，需要新生成一条区间值记录
				startNum = endNum;
				endNum = startNum + surplus;
				GenerateCrowdNumber newGenerateNumberRecord = new GenerateCrowdNumber();
				newGenerateNumberRecord.setCreateTime(new Date());
				newGenerateNumberRecord.setStartNumber((startNum));
				newGenerateNumberRecord.setEndNumber((endNum));
				newGenerateNumberRecord.setRegisterCount(0);
				newGenerateNumberRecord.setId(UUIDUtil.getUUID());
				generateCrowdNumberMapper.insert(newGenerateNumberRecord);
			}
			List<String> crowdList = Lists.newArrayList();
			for (; startNum < endNum; startNum++) {
				crowdList.add(startNum + "");
			}
			Collections.shuffle(crowdList);
			redisTemplate.opsForList().leftPushAll(CONSTANT.CROWDID_LIST, crowdList);
			crowdId = Integer.parseInt(redisTemplate.opsForList().rightPop(CONSTANT.CROWDID_LIST));
		} else {
			crowdId = Integer.parseInt(crowdIdString);
		}
		boolean isExist = isExistCrowdId(crowdId + "");
		if (isExist) {// 存在，则再次回调方法获取id
			return initCrowdId();
		}
		return crowdId;
	}

	/**
	 * 根据群编号查询群信息
	 * 
	 * @param crowdId
	 * @return
	 */
	@Override
	public UserCrowd queryUserCrowdInfo(Integer crowdId) {
		return userCrowdMapper.queryUserCrowdInfo(crowdId);
	}

	/**
	 * 根据加入群的编号查询群成员
	 * 
	 * @param crowdId
	 * @return
	 */
	@Override
	public List<UserInfo> queryUserCrowdMembersList(Page page) {
		PageBean pagebean = new PageBean();
		pagebean.setPageNumber(page.getPageNumber());
		pagebean.setPageSize(page.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("id", page.getId());
		pagebean.setConditions(map);
		List<UserInfo> userCrowdMembers = new ArrayList<UserInfo>();
		userCrowdMembers = userCrowdMembersMapper.queryUserCrowdMembersList(pagebean);
		page.setTotal(pagebean.getTotalRecord());
		page.setResults(userCrowdMembers);
		for (UserInfo userCrowdMembers2 : userCrowdMembers) {
			userCrowdMembers2.setAge(DateTimeUtil.getPersonAgeByBirthDate(userCrowdMembers2
					.getBirthday()));
		}
		return userCrowdMembers;
	}

	/**
	 * 根据群编号解散群（假删除）
	 * 
	 * @param crowdId
	 * @return
	 */
	@Override
	public int updateUserCrowdState(Integer crowdId) {
		userCrowdRecommendMapper.updateRecomenstateToNoByCrowdId(new UserCrowdRecommend(crowdId,
				Short.valueOf(UserCrowdRecommend.STATE_NO + "")));
		List<UserCrowdMembers> userCrowdMembers = userCrowdMembersMapper.queryMemberCrowd(crowdId);
		String roomId = userCrowdMapper.queryEasemodCroweId(crowdId);
		/**
		 * 先退出群成员↓
		 */
		userCrowdMembers.stream().filter(s -> s.getRoleType() != UserCrowdMembers.GROUP_HOLDER)
				.forEach(s -> {
					String pushId = userInfoMapper.queryPushChatId(s.getCrowdMemberId());
					easeMobService.removeUserFromChatGroup(roomId, pushId);
				});
		/**
		 * 退出群主↓
		 */
		userCrowdMembers.stream().filter(s -> s.getRoleType() == UserCrowdMembers.GROUP_HOLDER)
				.forEach(s -> {
					String pushId = userInfoMapper.queryPushChatId(s.getCrowdMemberId());
					easeMobService.removeUserFromChatGroup(roomId, pushId);
				});
		return userCrowdMapper.updateUserCrowdState(crowdId);
	}

	/**
	 * 修改群资料
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ApiResult updateByPrimaryKeySelective(UserCrowd record) {
		if (record.getMemberCount() == null) {
			record.setMemberCount(0);
		}
		int memberCount = record.getMemberCount();
		int numberUser = userCrowdMapper.queryCrowdCountAllUser(record.getId());
		int newMemberCount = memberCount + numberUser;
		Integer maxCount = record.getMaxCount();
		if (newMemberCount > EasemobLimitNumberEnum.USER_CREATE_CROWD_NUMBER.getValue()) {
			return new ApiResult(RESPONSE.ERROR, "不能大于五百人");
		}
		String CROW_FADE_USER = PropertiesUtil.getValueByKey("crow_fade_user",
				CONSTANT.SYS_CONF_PATH);
		List<UserInfo> userIdList = userInfoMapper.queryUserIdAndPushChatId2(record.getUserId(),
				CROW_FADE_USER);// 获取排除了群主的所有假用户id和环信ID
		List<UserCrowdMembers> memberList = userCrowdMembersMapper.queryMemberCrowd(record.getId());// 获取现有的成员ID,已排除群主
		List<Integer> List1 = userIdList.stream().map(UserInfo::getUserId)
				.collect(Collectors.toList());// 获取所有假用户Id
		List<Integer> List2 = memberList.stream().map(UserCrowdMembers::getCrowdMemberId)
				.collect(Collectors.toList());// 获取现有成员数Id
		List<Integer> list3 = new ArrayList<Integer>();
		list3 = List1;
		list3.removeAll(List2);
		boolean me = false;
		if (list3.size() >= memberCount) {// 用户个数大于需要随机的用户个数才开始随机
			Collections.shuffle(list3);// 随机集合
			List<Integer> list4 = list3.subList(0, memberCount);
			for (int i = 0; i < list4.size(); i++) {
				me = easeMobService.addUserToChatGroup(record.getEasemodCroweId(),
						userInfoMapper.queryPushChatId(list4.get(i)));
			}
			if (me == true) {
				for (Integer integer : list4) {
					UserCrowdMembers record1 = new UserCrowdMembers();
					record1.setCrowdPkid(record.getId());
					record1.setCrowdMemberId(integer.intValue());
					record1.setRoleType(3);// 设置为群成员
					userCrowdMembersMapper.insert(record1);
				}
			}
			UserCrowd record2 = new UserCrowd();
			record2.setId(record.getId());
			record2.setMemberCount(newMemberCount);
			record2.setCrowdDesc(record.getCrowdDesc());
			record2.setCrowdName(record.getCrowdName());
			record2.setCrowdBackground(record.getCrowdBackground());
			record2.setCrowdStatus(1);
			record2.setUpdateTime(new Date());
			userCrowdMapper.updateByPrimaryKeySelective(record2);
			record = userCrowdMapper.selectByPrimaryKey(record.getId());
			String owern = userInfoMapper.queryPushChatId(record.getUserId());
			// 环信修改群资料
			if (!easeMobService.updateGroup(
					owern,
					record.getEasemodCroweId(),
					record.getCrowdName(),
					record.getCrowdDesc(),
					maxCount == null ? EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT
							.getValue() : maxCount)) {
				logger.error("环信修改失败");
				return new ApiResult(RESPONSE.ERROR, "修改失败");
			}
			;
		}
		return new ApiResult(RESPONSE.SUCCESS, "修改成功");
	}

	/**
	 * 添加群成员
	 */
	@Override
	public ApiResult addCrowdUser(UserCrowd userCrowd) {
		int memberCount = userCrowd.getMemberCount();
		List<UserCrowdMembers> memberList = userCrowdMembersMapper.queryMemberCrowd(userCrowd
				.getId());
		int numberUser = memberList.size();
		int newMemberCount = memberCount + numberUser;
		ApiResult ar = new ApiResult(ResponseEnum.SUCCESS);
		if (newMemberCount > EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT.getValue()) {
			return ar = new ApiResult(RESPONSE.ERROR, "不能大于  ["
					+ EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT.getValue() + "] 人");
		}
		String CROW_FADE_USER = PropertiesUtil.getValueByKey("crow_fade_user",
				CONSTANT.SYS_CONF_PATH);
		List<UserInfo> userIdList = userInfoMapper.queryUserIdAndPushChatId2(userCrowd.getUserId(),
				CROW_FADE_USER);// 所有假用户
		List<Integer> memberIdList = memberList.stream().map(UserCrowdMembers::getCrowdMemberId)
				.collect(Collectors.toList());
		Iterator<UserInfo> uItor = userIdList.iterator();
		while (uItor.hasNext()) {
			UserInfo user = uItor.next();
			if (memberIdList.contains(user.getUserId())) {
				uItor.remove();
			}
		}
		Collections.shuffle(userIdList);// 随机集合
		List<UserInfo> addMemberList = new ArrayList<UserInfo>();
		int userIdListSize = userIdList.size();
		if (userIdListSize < memberCount) {// 用户不足添加，则有多少个就添加多少
			addMemberList = userIdList.subList(0, userIdListSize);
			ar.setMessage("用户不足添加" + memberCount + "个，只添加[" + userIdListSize + "]个用户");
		} else {// 用户数足够
			addMemberList = userIdList.subList(0, memberCount);
		}
		List<Integer> addMemberIdList = addMemberList.stream().map(UserInfo::getUserId)
				.collect(Collectors.toList());// 获取成员数Id
		List<String> addMemberEasemobIdList = addMemberList.stream().map(UserInfo::getPushChatId)
				.collect(Collectors.toList());// 获取查询出来的假用户环信ID
		UserCrowdMembers userCrowdMembers = new UserCrowdMembers();
		boolean userAddEasemobGroup = easeMobService.bacthUserToChatGroup(
				userCrowd.getEasemodCroweId(), addMemberEasemobIdList);
		if (!userAddEasemobGroup) {
			return ar = new ApiResult(RESPONSE.ERROR, "用户添加到环信失败");
		}
		for (int i = 0; i < addMemberIdList.size(); i++) {
			Integer userId = addMemberIdList.get(i);
			userCrowdMembers.setCrowdMemberId(userId);
			userCrowdMembers.setCrowdPkid(userCrowd.getId());
			userCrowdMembers.setRoleType(3);
			userCrowdMembersMapper.insert(userCrowdMembers);
		}
		UserCrowd userCrowd1 = new UserCrowd();
		userCrowd1.setId(userCrowd.getId());
		userCrowd1.setMemberCount(newMemberCount + 1);
		userCrowdMapper.updateCrowdMember(userCrowd1);// 修改群成员统计数量
		return ar;
	}

	/**
	 * 删除群用户
	 * 
	 * @param crowdPkid
	 * @return
	 */
	@Override
	public ApiResult deleteMemberCrowd(UserCrowd userCrowd) {
		int memberCount = userCrowd.getMemberCount();
		Integer id = userCrowd.getId();
		List<UserInfo> userIdList = userCrowdMembersMapper.queryUserIdAndEasemodCroweId(userCrowd
				.getId());// 查询该群的成员ID以及环信ID,已排除了群主
		if (userIdList.size() == 0) {
			return new ApiResult(RESPONSE.ERROR, "该群已经没有成员数了，无法删除");
		}
		int numberUser = userCrowdMapper.queryCrowdCountFakeUser(userCrowd.getId());// 获取群成员表这个群对应的成员数量

		if (numberUser <= memberCount && numberUser != 0) {
			memberCount = numberUser;
		}
		// int deleteNumberUser = numberUser - memberCount;
		for (int i = 0; i < userIdList.size(); i++) {
			if ((i + 1) <= memberCount) {
				Integer userId = userIdList.get(i).getUserId();
				String pushChatId = userIdList.get(i).getPushChatId();
				boolean isSuccess = easeMobService.removeUserFromChatGroup(
						userCrowd.getEasemodCroweId(), pushChatId);// 用户退出环信群组
				if (isSuccess == false) {
					return new ApiResult(RESPONSE.ERROR, "删除环信群用户失败，无法删除");
				}
				UserCrowd userCrowd2 = new UserCrowd();
				userCrowd2.setId(userCrowd.getId());
				userCrowd2.setUserId(userId);
				userCrowdMembersMapper.deleteMemberCrowd(userCrowd2);
			}
		}
		UserCrowd userCrowd1 = new UserCrowd();
		userCrowd1.setId(userCrowd.getId());
		userCrowd1.setMemberCount(userCrowdMapper.queryCrowdCountAllUser(id));
		userCrowdMapper.updateByCrowdMember(userCrowd1);// 修改群成员统计数量
		return new ApiResult(RESPONSE.SUCCESS, "已成功删除成员");
	}

	@Override
	public List<UserCrowd> queryUserCrowdList(UserCrowd userCrowd) {
		return userCrowdMapper.queryUserCrowd(userCrowd);
	}

	@Override
	public List<UserInfo> queryCrowdMemberList(Long crowdPkId) {
		List<UserInfo> userInfo = userCrowdMapper.queryCrowdMemberList(crowdPkId);
		for (UserInfo userInfo2 : userInfo) {
			userInfo2.setAge(DateTimeUtil.getPersonAgeByBirthDate(userInfo2.getBirthday()));
		}
		return userInfo;
	}

	@Override
	public Object addCrowdUser2() {
		PageBean pb = new PageBean(1, 100);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "166");
		pb.setConditions(map);
		List<UserInfo> users = userCrowdMembersMapper.queryUserCrowdMembersList(pb);
		List<String> strs = new ArrayList<String>();
		for (int i = 0; i < users.size(); i++) {
			strs.add(users.get(i).getPushChatId());
		}
		// System.out.println(strs.toString());
		boolean userAddEasemobGroup = easeMobService.bacthUserToChatGroup("15288733073409", users
				.stream().map(UserInfo::getPushChatId).collect(Collectors.toList()));
		if (!userAddEasemobGroup) {
			return null;
		}
		return 1;
	}

	@Override
	public boolean isExistCrowdId(String crowdId) {
		String id = userCrowdMapper.queryContaninsCrowdId(crowdId);
		if (id != null) {
			return true;
		}
		return false;
	}

	@Override
	public int updateCrowdRecommend(Map<String, Object> map) {
		// TODO Auto-generated method stub
		// userCrowdRecommendMapper.updateRecomenstateToNo();// 修改所有推荐为取消推荐
		UserCrowdRecommend userCrowdRecommend = userCrowdRecommendMapper
				.getUserCrowdRecommend(Integer.valueOf(map.get("id").toString()),
						UserCrowdRecommend.MODE_TYPE1.intValue());
		Integer sort = Integer.valueOf(map.get("sort").toString());
		if (StringUtil.isEmpty(sort)) {
			sort = 0;
		}
		Integer index = 0;
		if (null != userCrowdRecommend) {
			userCrowdRecommend.setRecommedStatus(Short
					.valueOf(map.get("recommedStatus").toString()));
			userCrowdRecommend.setRecommendSort(sort);
			index = userCrowdRecommendMapper.updateByPrimaryKeySelective(userCrowdRecommend);
		} else {
			userCrowdRecommend = new UserCrowdRecommend();
			userCrowdRecommend.setCrowedId(Integer.valueOf(map.get("id").toString()));
			userCrowdRecommend.setCreateTime(new Date());
			userCrowdRecommend.setModeType(UserCrowdRecommend.MODE_TYPE1);
			userCrowdRecommend.setRecommedStatus(Short
					.valueOf(map.get("recommedStatus").toString()));
			userCrowdRecommend.setRecommendSort(sort);// 排序
			userCrowdRecommend.setUpdateTime(userCrowdRecommend.getCreateTime());
			index = userCrowdRecommendMapper.insertSelective(userCrowdRecommend);
		}
		return index;
	}

	@Override
	public UserCrowdInfoResult selectCrowdInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer id = Integer.valueOf(map.get("id").toString());
		int crowdRecommendCount = CONSTANT.USER_CROWD_RECOMMEND_COUNT;
		UserCrowdInfoResult userCrowd = userCrowdMapper.selectCorwdInfo(id);
		List<Integer> sortList = userCrowdMapper.selectSortList();
		List<Integer> tempSort = new ArrayList<Integer>();
		List<Map<String, Integer>> resultListMap = new ArrayList<Map<String, Integer>>();
		for (Integer i = 1; i <= crowdRecommendCount; i++) {
			tempSort.add(i);
		}
		tempSort.removeAll(sortList);
		for (Integer integer : tempSort) {
			Map<String, Integer> maps = new HashMap<String, Integer>();
			maps.put("sort", integer);
			resultListMap.add(maps);
		}
		userCrowd.setSortList(resultListMap);
		return userCrowd;
	}
}
