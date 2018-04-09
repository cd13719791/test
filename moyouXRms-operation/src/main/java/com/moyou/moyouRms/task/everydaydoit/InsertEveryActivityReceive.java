package com.moyou.moyouRms.task.everydaydoit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.PHONE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.SysEveryActivityModeTypeEnum;
import com.moyou.moyouRms.model.everyActivityRule.EveryActivityRule;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;
import com.moyou.moyouRms.model.everyactivityreceive.EveryActivityReceive;
import com.moyou.moyouRms.service.everyActivityRule.EveryActivityRuleService;
import com.moyou.moyouRms.service.everyactivity.EveryActivityService;
import com.moyou.moyouRms.service.everyactivityreceive.EveryActivityReceiveService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.RandomStringUtil;
import com.moyou.moyouRms.util.SmsSendUtil;

/**
 * @author created by Chenxv
 * @date 2017年8月17日 下午4:39:26
 */
@Component
public class InsertEveryActivityReceive {
	private static final Logger LOG = LoggerFactory.getLogger(InsertEveryActivityReceive.class);
	@Resource
	private EveryActivityService everyActivityService;
	@Resource
	private EveryActivityReceiveService everyActivityReceiveService;
	@Resource
	private EveryActivityRuleService everyActivityRuleService;
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate_KVString;
	@Resource
	private UserFundService userFundService;
	String SYSTEM_USER_ID = CONSTANT.SYSTEM_USER_ID;

	@Scheduled(cron = "0 0 0 * * ?")
	// 每天18点执行
	public void start() {
		LOG.info("================开始更新活动数据=================");
		try {

			if (redisTemplate_KVString.hasKey(CONSTANT.ACTIVITY_EARN_SMALLCHANGE_LIST_FOR_USER)) {
				redisTemplate_KVString.delete(CONSTANT.ACTIVITY_EARN_SMALLCHANGE_LIST_FOR_USER);
			}
			if (redisTemplate_KVString.hasKey(CONSTANT.SYS_EVERY_ACTIVITY_RULE)) {
				redisTemplate_KVString.delete(CONSTANT.SYS_EVERY_ACTIVITY_RULE);
			}
			if (redisTemplate_KVString.hasKey(CONSTANT.SYS_EVERY_ACTIVITY)) {
				redisTemplate_KVString.delete(CONSTANT.SYS_EVERY_ACTIVITY);
			}
			EveryActivity yesterdayActivity = everyActivityService.selectYesterdayActivity();
			if (yesterdayActivity != null) {
				// 没执行的钱 退回公司 start
				if (everyActivityReceiveService.selectActivityDoingCount(yesterdayActivity.getId()) != yesterdayActivity
						.getEveryDayLimit()) {
					// 如果没执行完 把没执行的钱退回公司账户
					Integer unDoingFund = everyActivityReceiveService
							.selectUnDoingFundSum(yesterdayActivity.getId());// 查询没执行的钱

					if (unDoingFund > 0) {
						userFundService.addAndUpdateUserFund(Integer.valueOf(SYSTEM_USER_ID),
								unDoingFund);// 退回公司账户
					}
				}
				// 没执行的钱 退回公司 end
				// 修改昨天的规则
				yesterdayActivity.setIngState(EveryActivity.DO_OK);
				yesterdayActivity.setUpdateTime(new Date());
				everyActivityService.updateState();
				// everyActivityService.updateByPrimaryKey(yesterdayActivity);
			}
			// 查询最近的规则 生成新的用户获取金币
			EveryActivity everyActivity = everyActivityService.selectEveryActivityLimit();
			// 判断公司账户是否够支付活动经费
			BigDecimal companyFund = userFundService.getCompanySysFund();// 系统余额
			companyFund = companyFund.multiply(new BigDecimal("100")); // (单位元)=*100==>(单位分)
			Integer limitCount = everyActivity.getEveryDayLimit();// '单日限制次数'
			Integer moneyTotal = everyActivity.getEveryDayMoneyTotal();// '单日总金额,单位分
			Integer moneyMax = everyActivity.getEveryMoneyMax();// 单笔最高金额单位分
			if (companyFund.doubleValue() < moneyTotal) {
				// 公司钱不够
				LOG.info("公司余额不足支付活动经费");
				String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
				String appName = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
				SmsSendUtil.send(strs, appName, this.getClass() + "陌友" + appName,
						CONSTANT.REG_SCHEDULE, ResponseEnum.SCHEDULE_ERRO.getValue());
				return;
			} else {// 活动开始 扣除公司钱
				userFundService.addAndUpdateUserFund(Integer.valueOf(SYSTEM_USER_ID), moneyTotal
						* (-1));
			}
			everyActivity.setEveryDay(new Date());
			everyActivity.setUpdateTime(new Date());
			everyActivity.setCreateTime(new Date());
			everyActivity.setIngState(EveryActivity.DO_ING);
			everyActivity.setId(null);
			everyActivityService.insertSelective(everyActivity);// 将这条最新的记录更新为今天执行
			// 将每日活动总规则 存入redis
			redisTemplate_KVString.opsForValue().set(CONSTANT.SYS_EVERY_ACTIVITY,
					JsonUtil.toJson(everyActivity));

			Long[] smallMoney = RandomStringUtil.generateLong(moneyTotal, limitCount, moneyMax);// 随机出所有的小钱份额
			List<String> ids = new ArrayList<String>();
			Arrays.asList(smallMoney)
					.parallelStream()
					.forEach(
							s -> {
								EveryActivityReceive everyActivityReceive = new EveryActivityReceive();
								everyActivityReceive.setActivityId(everyActivity.getId());
								everyActivityReceive.setCreateTime(new Date());
								everyActivityReceive.setReceiveFund(Integer.valueOf(s + ""));
								everyActivityReceive.setState(EveryActivityReceive.DO_NOT_GET);
								everyActivityReceiveService.insertSelective(everyActivityReceive);
								String str = everyActivityReceive.getId() + "_"
										+ everyActivityReceive.getReceiveFund();
								if (everyActivityReceive.getId() != null
										&& everyActivityReceive.getReceiveFund() != null) {
									ids.add(str);
								} else {
									LOG.error("**********活动初始化 有参数为空**********");
									LOG.error("everyActivityReceive.getId() ["
											+ everyActivityReceive.getId() + "]");
									LOG.error("everyActivityReceive.getReceiveFund() ["
											+ everyActivityReceive.getReceiveFund() + "]");
								}
							});
			redisTemplate_KVString.opsForList().leftPushAll(
					CONSTANT.ACTIVITY_EARN_SMALLCHANGE_LIST_FOR_USER, ids);
			List<EveryActivityRule> everyActivityRuleList = everyActivityRuleService
					.selectEveryActivityRuleList();
			HashOperations<String, String, String> opsForHash = redisTemplate_KVString.opsForHash();
			everyActivityRuleList.forEach(everyActivityRule -> {
				opsForHash.put(
						CONSTANT.SYS_EVERY_ACTIVITY_RULE,
						SysEveryActivityModeTypeEnum.getByValue(everyActivityRule.getModeType())
								.getDesc(),
						everyActivityRule.getRewardType() + "_"
								+ everyActivityRule.getEveryDayUserLimit() + "_"
								+ everyActivityRule.getRewardNum());
				everyActivityRule.setActivityId(everyActivity.getId());
				everyActivityRuleService.updateByPrimaryKeySelective(everyActivityRule);
			});
			redisTemplate_KVString.expire(CONSTANT.SYS_EVERY_ACTIVITY_RULE, 1, TimeUnit.DAYS);
		} catch (Exception e) {
			String[] strs = { PHONE.CHEN_XV, PHONE.LIU_XIN_YI };
			SmsSendUtil.send(strs, "", "陌友", CONSTANT.REG_SCHEDULE,
					ResponseEnum.SCHEDULE_ERRO.getValue());
		}
	}
}
