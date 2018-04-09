package com.moyou.moyouRms.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.dao.user.UserFollowersMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.user.UserMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserFollowers;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;

/**
 * 用户下线
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserDownloadUnit extends BaseJunit4 {
	@Autowired
	private UserMapper userMapper;
	@Resource
	UserFollowersMapper userFollowersMapper;
	@Resource
	UserCountMapper userCountMapper;
	@Resource
	private EaseMobService easeMobService;
	@Resource
	private SecretRobotService secretRobotService;
	@Resource
	private UserInfoMapper userInfoMapper;

	@Test
	@Rollback(false)
	public void unit3() {
		try {
			// EasemobRestAPIFactory factory =
			// ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES)
			// .getAPIFactory();
			//
			// IMUserAPI user = (IMUserAPI)
			// factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
			// // Object u =
			// user.getIMUsersByUserName("fcafe373885b403b81037e8df040bdd4");
			// // System.out.println(u);
			// List<UserInfo> userInfo = userInfoMapper.queryUserPushChatId();
			// for (UserInfo userInfo2 : userInfo) {
			// Object ob =
			// easeMobService.disconnectIMUser(userInfo2.getPushChatId());
			// System.out.println(JsonUtil.toJson(ob));
			// } 小陌陌 26424
			List<User> list = userMapper.queryFakeUser();
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				int j = userFollowersMapper.insertSelective(new UserFollowers(26424, user
						.getUserId(), (short) 1, new Date(), new Date()));
				if (j == 1) {
					userCountMapper.updateFansCountJIA1(26424);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
