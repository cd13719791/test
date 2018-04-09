package com.moyou.moyouRms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.easemob.server.comm.body.ChatGroupBody;
import com.moyou.moyouRms.constants.enums.EasemobLimitNumberEnum;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMembersMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdMembers;

public class AddCrowd extends BaseJunit4 {

	private static final int ONE_THOUSAND = 10000;
//	private static final int TWO_HUNDRED = 200;
	private static final Integer CROWD_QUN_ZHU_ID_MOYOU_ID = 10022112;
	private static final Integer[] MOYOU_IDS = { 10043723, 10014337, 10046656,
			10031752, 10026255, 10047717, 10010923, 10017496, 10044429,
			10014465, 10047261, 10024773, 10010926, 10015056, 10032591,10042370,10024550,10033094,10002818,10023293,
	10045007,10020530,10040467,10043012,10013255,10020727};
//	private static final int[] MOYOU_IDS = { 10041820, 10012605, 10004185,//测试
//			10021585, 10048972, 10039409, 10048783, 10044206, 10022255,
//			10029036, 10024406, 10029879, 10003137, 10003684, 10025625 };
	@Resource
	private UserCrowdMapper userCrowdMapper;
	@Resource
	private UserCrowdMembersMapper userCrowdMembersMapper;
	@Resource
	private EaseMobService easeMobService;
	@Resource
	private UserInfoMapper userInfoMapper;

	@SuppressWarnings("unused")
	@Test
	@Rollback(false)
	public void addCrowd() {
		UserCrowd userCrowd =new UserCrowd();
		List<UserInfo> userIdList;
		Integer crowdId = userCrowdMapper.queryMaxCrowdId();
		if (crowdId == null) {
			userCrowd.setCrowdId(ONE_THOUSAND);
		} else {
			userCrowd.setCrowdId(crowdId + 1);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("crowdCreateType", UserCrowd.ADMIN_CREATE_TYPE);
		int memberCount =MOYOU_IDS.length+1;
		List<UserInfo> user = userInfoMapper
				.queryByMoyouId(CROWD_QUN_ZHU_ID_MOYOU_ID + "");
		userCrowd.setCrowdName("雨田地产");
		// userCrowd.setEasemodCroweId(user.get(0).getUserId());
		userCrowd.setCrowdDesc("雨田地产陌友官方群");
		userCrowd.setCrowdCity("");
		userCrowd.setCrowdLatitude(0.0);
		userCrowd.setCrowdLongitude(0.0);
		userCrowd.setCreateTime(new Date());
		userCrowd.setUpdateTime(new Date());
		userCrowd.setCrowdStatus(1);// 设置群状态为正常
		userCrowd.setCrowdLevel(1);// 设置群等级为1
		userCrowd.setMemberCount(memberCount);
		// 获取用户注册过的环信ID集合
		// List<String> userIdList = userMapper.selectUserIdByMap(paramMap);
		String easemodCroweUserId = userCrowd.getEasemodCroweId();// 群主对应的环信用户
		// 创建环信群
		String groupName = userCrowd.getCrowdName();
		String crowdDesc = userCrowd.getCrowdDesc();
		userCrowd.setEasemodCroweId(easemodCroweUserId);
		ChatGroupBody chatGroupBody = new ChatGroupBody(groupName, crowdDesc,
				true, (long)EasemobLimitNumberEnum.CROWD_MEMBER__NUMBER_DEFAULT.getValue(), false, easemodCroweUserId,
				null);
		String easemodCroweId = easeMobService.createChatGroup(chatGroupBody);
		easemodCroweId="15288733073409";
		if(easemodCroweId==null){
			return;
		}
		userCrowd.setEasemodCroweId(easemodCroweId);
		userCrowdMapper.insert(userCrowd);// 创建群
		// 将群主添加到成员表
		addCrowdMember(userCrowd.getId(), userInfoMapper.queryByMoyouId(CROWD_QUN_ZHU_ID_MOYOU_ID.toString()).get(0).getUserId(), 1);
		for (int i = 0; i < MOYOU_IDS.length; i++) {
			if(CROWD_QUN_ZHU_ID_MOYOU_ID.equals(MOYOU_IDS[i])){
				continue;
			}
			addCrowdMember(userCrowd.getId(),
					userInfoMapper.queryByMoyouId(MOYOU_IDS[i] + "").get(0)
							.getUserId(), 3);
		}
	}
	
	// 将用户添加到群中
	private void addCrowdMember(int crowdId, int memberId, int roleType) {
		UserCrowdMembers record = new UserCrowdMembers();
		record.setCrowdPkid(crowdId);
		record.setCrowdMemberId(memberId);
		record.setRoleType(roleType);// 1群主2管理员3普通成员
		userCrowdMembersMapper.insert(record);
	}
}
