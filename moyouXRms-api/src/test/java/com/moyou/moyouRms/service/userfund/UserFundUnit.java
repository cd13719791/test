package com.moyou.moyouRms.service.userfund;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * 修改用户活跃时间
 * 
 * @author liuxinyi
 * @date 2016年6月2日
 * @version 1.0.0
 */
public class UserFundUnit extends BaseJunit4 {
	@Autowired
	private UserFundService userFundService;

	@Test
	@Rollback(false)
	public void unit() {
		try {
			UserFund newUserFund = userFundService.getUserFundByUserId(22789);
			int n = newUserFund.getUserFund().multiply(new BigDecimal(100+"")).intValue();
			System.out.println(JsonUtil.toJson(newUserFund));
			System.out.println(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
