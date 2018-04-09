package com.moyou.moyouRms.service.talkcomment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserInfoService;

public class TalkComment extends BaseJunit4 {
	@Autowired
	private UserInfoService userInfoService;
	@Resource
	private MsgSystemXService msgSystemXService;
	private static final int BATCH_PUSH_TO_DB = 500;// 批量入库

	@Test
	@Rollback(false)
	public void insert() {
		List<UserInfo> users = new ArrayList<UserInfo>();
		for (int i = 0; i < 35000; i++) {
			UserInfo u = new UserInfo();
			u.setUserId(i);
			u.setPushChatId(i + "PushChatId");
			users.add(u);
		}
		List<UserInfo> tmpUserList = new ArrayList<UserInfo>(BATCH_PUSH_TO_DB);
		int i = 0, batchNumber = 0;
		String push_content = "标题";
		MsgSystemX record = new MsgSystemX();
		record.setCreateTime(new Date());
		record.setH5Url("https://www.immouo.com/app/push.html?id=55");
		record.setMsgSendType("sys_art_msg");
		record.setSendUserId(0);
		record.setMsgTitle("【点击领取】分享视频赢万元大奖啦");
		record.setModeType(Short.valueOf("6"));
		record.setMsgContent("https://myimage.immouo.com//o_1bnka2u161ga51m7u1c08bocbkn1d.png");
		record.setShortMsgContent("陌友，专注身边陌生人社交 同一座城，同样的心情  秀出你的视频，让身边的人认识你 万元大奖，等你来拿");
		for (int j = 0; j < users.size(); j++) {
			UserInfo userInfo = users.get(j);
			if (i == BATCH_PUSH_TO_DB) {// 达到入库阈值 入库
				try {
					msgSystemXService.addSysMsg(tmpUserList, record, push_content);
					batchNumber = batchNumber + BATCH_PUSH_TO_DB;
				} catch (Exception e) {
				}
				tmpUserList = new ArrayList<UserInfo>(BATCH_PUSH_TO_DB);
				i = 0;
				System.err.println("执行了[" + batchNumber + "]");
			}
			tmpUserList.add(userInfo);
			i++;
		}
		// 入库
		if (tmpUserList != null && tmpUserList.size() > 0) {
			batchNumber = batchNumber + tmpUserList.size();
			msgSystemXService.addSysMsg(tmpUserList, record, push_content);
		}
		System.out.println("执行了[" + batchNumber + "]");
	}

	// private static Connection getConn() {
	// String driver = "com.mysql.jdbc.Driver";
	// String url = "jdbc:mysql://localhost:3306/samp_db";
	// String username = "root";
	// String password = "";
	// Connection conn = null;
	// try {
	// Class.forName(driver); // classLoader,加载对应驱动
	// conn = (Connection) DriverManager.getConnection(url, username,
	// password);
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return conn;
	// }
}
