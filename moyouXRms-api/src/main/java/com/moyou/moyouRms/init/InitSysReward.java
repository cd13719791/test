package com.moyou.moyouRms.init;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.userfund.UserFundMapper;
import com.moyou.moyouRms.service.userfund.SystemRewardLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月4日 上午9:53:47 类说明
 */
@Component
public class InitSysReward implements InitializingBean {
	Logger log = LoggerFactory.getLogger(this.getClass());
	static boolean index = true;
	@Resource
	private SystemRewardLogService systemRewardLogService;
	@Resource
	UserFundService userFundService;
	@Resource
	UserFundMapper userFundMapper;
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate_KVString;

	@Override
	public void afterPropertiesSet() throws Exception {
		index = false;
		if (index) {
			index = false;
			{// 系统重启后 讲redis里剩余的钱都还回去
				// int userId = StringUtil.getInt(PropertiesUtil.getValueByKey(
				// "system_user_id", CONSTANT.SYS_CONF_PATH));
				// BigDecimal userFund = userFundService.getUserFundByUserId(
				// userId).getUserFund();
				Set<String> keys = redisTemplate_KVString
						.keys(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + "*");
				// int sysRewardDeductMoneySum = 0;// 单位分
				if (keys != null) {
					for (String key : keys) {
						// sysRewardDeductMoneySum +=
						// redisTemplate.opsForValue()
						// .get(key);
						redisTemplate_KVString.delete(key);
					}
				}
				// // 单位分转换为元
				// BigDecimal moneyBD = new BigDecimal(sysRewardDeductMoneySum
				// + "");// 防止精度问题，用字符串
				// BigDecimal divisor = new BigDecimal(100 + "");
				// BigDecimal tmpFund =
				// userFund.subtract(moneyBD.divide(divisor,
				// 2, BigDecimal.ROUND_DOWN));
				// if (tmpFund.intValue() > 0) {
				// tmpFund = new BigDecimal(0);
				// }
				// userFundMapper.updateSystemFund(new UserFund(userId,
				// tmpFund));
			}
			try {
				systemRewardLogService.updateSysRewardState();
			} catch (Exception e) {
				log.error("【初始化系统打赏改为异常失败】" + e.getStackTrace());
			}
		}
	}

}
