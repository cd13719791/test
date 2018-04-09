package com.moyou.moyouRms.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.easemob.server.api.IMUserAPI;
import com.easemob.server.comm.ClientContext;
import com.easemob.server.comm.EasemobRestAPIFactory;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 用户下线
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserDownloadUnit extends BaseJunit4 {
	@Autowired
	private UserService service;
	@Resource
	private EaseMobService easeMobService;
	@Resource
	private SecretRobotService secretRobotService;
	@Resource
	private UserInfoMapper userInfoMapper;


	@SuppressWarnings("unused")
	@Test
	@Rollback(false)
	public void unit3() {
		try {
			EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES)
					.getAPIFactory();

			IMUserAPI user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
//			Object u = user.getIMUsersByUserName("fcafe373885b403b81037e8df040bdd4");
//			System.out.println(u);
			List<UserInfo> userInfo = userInfoMapper.queryUserPushChatId();
			for (UserInfo userInfo2 : userInfo) {
				Object ob = easeMobService.disconnectIMUser(userInfo2.getPushChatId());
				System.out.println(JsonUtil.toJson(ob));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
