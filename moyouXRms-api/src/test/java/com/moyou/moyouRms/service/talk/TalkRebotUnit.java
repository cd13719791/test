package com.moyou.moyouRms.service.talk;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 修改用户活跃时间
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class TalkRebotUnit extends BaseJunit4 {
	@Resource
	private TalkRobotService talkRobotService;
	@Test
	@Rollback(false)
	public void unit() {
		try {
			TalkRobot t = talkRobotService.selectByPrimaryKey(804);
			System.out.println(JsonUtil.toJson(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
