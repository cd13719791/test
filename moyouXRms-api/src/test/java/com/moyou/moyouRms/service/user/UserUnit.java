package com.moyou.moyouRms.service.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.EasemobSingleChatMsgTypeEnum;
import com.moyou.moyouRms.dao.user.GenerateNumberRecordMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.easemob.entity.SingleChatMsg;
import com.moyou.moyouRms.easemob.entity.SingleChatMsgExtend;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;

/**
 * 修改用户活跃时间
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserUnit extends BaseJunit4 {
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
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate_KVString;
	@Resource
	GenerateNumberRecordMapper numberRecordMapper;

	@Test
	public void unit() {
		try {
			SingleChatMsg chatMsg = new SingleChatMsg(EasemobSingleChatMsgTypeEnum.CMD, "limit",
					CONSTANT.LIMIT_LIVE_USER);
			SingleChatMsgExtend singleChatMsgExtend = new SingleChatMsgExtend();
			System.out.println(easeMobService.sendSingleChatMsg("admin", chatMsg,
					singleChatMsgExtend, "2db12a58aa354abd9c15fa5ec65656d3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @SuppressWarnings("unused") private Integer initMoyouId() { int moyouId =
	 * 0; String moyouIdString =
	 * redisTemplate_KVString.opsForList().rightPop(CONSTANT.MOYOUID_LIST); if
	 * (StringUtils.isEmpty(moyouIdString)) { // moyouId取值区间
	 * GenerateNumberRecord numberRecord =
	 * numberRecordMapper.queryRangeOfMoyouId(); int startNum =
	 * numberRecord.getStartNumber(), endNum = numberRecord.getEndNumber(); int
	 * surplus = endNum - startNum;// 区间值 if (numberRecord.getRegisterCount() >=
	 * surplus) {// 用户注册超过区间，需要新生成一条区间值记录 startNum = endNum; endNum = startNum +
	 * surplus; GenerateNumberRecord newGenerateNumberRecord = new
	 * GenerateNumberRecord(); newGenerateNumberRecord.setCreateTime(new
	 * Date()); newGenerateNumberRecord.setStartNumber((startNum));
	 * newGenerateNumberRecord.setEndNumber((endNum));
	 * newGenerateNumberRecord.setRegisterCount(0);
	 * newGenerateNumberRecord.setId(UUIDUtil.getUUID());
	 * numberRecordMapper.insert(newGenerateNumberRecord); } List<String>
	 * moyouIdList = Lists.newArrayList(); for (; startNum < endNum; startNum++)
	 * { moyouIdList.add(startNum + ""); } Collections.shuffle(moyouIdList);
	 * redisTemplate_KVString.opsForList().leftPushAll(CONSTANT.MOYOUID_LIST,
	 * moyouIdList); moyouId =
	 * Integer.parseInt(redisTemplate_KVString.opsForList().rightPop(
	 * CONSTANT.MOYOUID_LIST)); } else { moyouId =
	 * Integer.parseInt(moyouIdString); } boolean isExist =
	 * isExistMoyouId(moyouId + ""); if (isExist) {// 存在，则再次回调方法获取id return
	 * initMoyouId(); } return moyouId; }
	 * 
	 * public boolean isExistMoyouId(String moyouId) { String id =
	 * userInfoMapper.queryContaninsMoyouId(moyouId); if (id != null) { return
	 * true; } return false; }
	 * 
	 * @Test
	 * 
	 * @Rollback(false) public void unit1() { try {
	 * easeMobService.addUser("BF6064E3EAB883D6E03C023814235E63", "321", "哈哈");
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * @Test
	 * 
	 * @Rollback(false) public void unit3() { try { List<UserInfo> userInfo =
	 * userInfoMapper.queryUserPushChatId(); for (UserInfo userInfo2 : userInfo)
	 * { easeMobService.disconnectIMUser(userInfo2.getPushChatId()); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

}
