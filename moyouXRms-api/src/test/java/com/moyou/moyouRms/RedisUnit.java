/**
 */
package com.moyou.moyouRms;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.service.BaseJunit4;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年1月19日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class RedisUnit extends BaseJunit4 {
	@Resource
	private JpushService jpushService;

	@Test
	@Rollback(false)
	public void unit() {
		jpushService.sendBarPush(new String[] { "f94c924a30fe44f79bb987f628519268",
				"6355a63941984b4dac68fd03aac6e2a5", "2db12a58aa354abd9c15fa5ec65656d3" },
				PushAlertEnum.JPUSH_OFFLINE_USER_5_DAY, CONSTANT.JPUSH_OFFLINE_USER_5_DAY_TEXT);
		// JPUSH_CLIENT.
	}
}
