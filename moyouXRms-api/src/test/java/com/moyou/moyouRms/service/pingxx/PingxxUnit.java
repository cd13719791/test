package com.moyou.moyouRms.service.pingxx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.pingxx.service.ChargeService;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

/**
 * pingxx
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class PingxxUnit extends BaseJunit4 {
	@Autowired
	private ChargeService chargeService;
	@Test
	@Rollback(false)
	public void unit() {
		try {
			Msg isSuccess = chargeService.remitFundToWx(TradeNumberUtil.getTradeNumber(),"oiEelwueVXXV7U5iJBv9TIr4Px54", 100, UserFundModeEnum.WITHDRAW_DEPOSIT.getValue());
			System.out.println(JsonUtil.toJson(isSuccess));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
