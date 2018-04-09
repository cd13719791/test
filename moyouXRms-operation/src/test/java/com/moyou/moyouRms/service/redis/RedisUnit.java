/**
 */
package com.moyou.moyouRms.service.redis;

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
		jpushService.sendBarPush(new String[] { "ec38327403a04f96a5d0c46546b743b9",
				"4fdcb0e16d7f43fab7daf9a666959eb5", "5a44ccbdfe5a4ca4b45fd69fdd5cf45e",
				"35f934602761495abb869f5783dd6f4f", "6355a63941984b4dac68fd03aac6e2a5",
				"2db12a58aa354abd9c15fa5ec65656d3", "000b7307ae014a3a981374129c3552af", },
				PushAlertEnum.JPUSH_OFFLINE_USER_5_DAY, CONSTANT.JPUSH_OFFLINE_USER_5_DAY_TEXT);
		// JPUSH_CLIENT.
	}
}
