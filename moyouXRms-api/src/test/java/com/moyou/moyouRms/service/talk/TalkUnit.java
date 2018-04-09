package com.moyou.moyouRms.service.talk;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.service.BaseJunit4;

/**
 * 修改用户活跃时间
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class TalkUnit extends BaseJunit4 {
	@Autowired
	EaseMobService easeMobService;

	@Test
	@Rollback(false)
	public void unit() {
		try {
			// System.out.println("================"+easeMobService.isExistUser("73eb0edee98d46a59a803c5d9142d4fc"));
			// System.out.println("================"+easeMobService.isExistUser("864af482eab74c57b8fe973a9fe7fcb5"));
			/*
			 * System.out.println(easeMobService.sendSingleChatMsg(
			 * "73eb0edee98d46a59a803c5d9142d4fc", new SingleChatMsg(
			 * EasemobSingleChatMsgTypeEnum.TXT, "msgmsgmsg"), new
			 * SingleChatMsgExtend( "?阁下何不乘风",
			 * "http://myimagetest.immouo.com/user/avatar/486F215F-A600-49CC-9087-DE02827E9AC21495442824649_1.jpg"
			 * , 10749, 10934,
			 * "http://myimageest.immouo.com/user/avatar/486F215F-A600-49CC-9087-DE02827E9AC21495442824649_1.jpg"
			 * , "shift",true) ,"493219c54ad1414085dd9ade65e83d27") );
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
