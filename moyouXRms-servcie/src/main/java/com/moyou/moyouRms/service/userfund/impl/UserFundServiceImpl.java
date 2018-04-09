package com.moyou.moyouRms.service.userfund.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.SystemRewardModeType;
import com.moyou.moyouRms.constants.enums.EasemobSingleChatMsgTypeEnum;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.constants.enums.RechargeExceptionEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.UserFundLogModePlusType;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.constants.enums.UserFundSrarchCategoryEnum;
import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.dao.systemFund.SystemFundMapper;
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.dao.talk.TalkResourceMapper;
import com.moyou.moyouRms.dao.user.BindDataMapper;
import com.moyou.moyouRms.dao.user.UserMapper;
import com.moyou.moyouRms.dao.userGold.UserGoldMapper;
import com.moyou.moyouRms.dao.userfund.SystemRewardLogMapper;
import com.moyou.moyouRms.dao.userfund.SystemRewardModelMapper;
import com.moyou.moyouRms.dao.userfund.UserFundLogMapper;
import com.moyou.moyouRms.dao.userfund.UserFundMapper;
import com.moyou.moyouRms.easemob.entity.SingleChatMsg;
import com.moyou.moyouRms.easemob.entity.SingleChatMsgExtend;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.exception.MouoException;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.liveshow.LiveUserConvert;
import com.moyou.moyouRms.model.liveshow.UserGoldConvertModel;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.systemFund.SystemFund;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkResource;
import com.moyou.moyouRms.model.user.BindData;
import com.moyou.moyouRms.model.user.UserMsgInfo;
import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.model.userfund.SystemRewardLog;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.pingxx.entity.ChargeEntity;
import com.moyou.moyouRms.pingxx.service.ChargeService;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.liveshow.LiveUserConvertService;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.redpacket.RedPacketService;
import com.moyou.moyouRms.service.user.BindDataService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.userGoldRule.UserGoldRuleService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.RandomStringUtil;
import com.moyou.moyouRms.util.SmsSendUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;
import com.taobao.api.ApiException;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class UserFundServiceImpl implements UserFundService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserFundMapper userFundMapper;
	@Resource
	private UserFundLogMapper userFundLogMapper;
	@Resource
	private TalkMapper talkMapper;
	@Resource
	private TalkResourceMapper talkResourceMapper;
	@Resource
	private ChargeService chargeService;
	@Resource
	private DiaryMapper diaryMapper;
	@Resource
	private BindDataMapper bindDataMapper;
	@Resource
	private UserNotificationMapper notificationMapper;
	@Resource
	private JpushService jpushService;
	@Resource
	UserGoldMapper userGoldMapper;
	@Resource
	UserGoldService userGoldService;
	@Resource
	DiaryContentService diaryContentService;
	@Resource
	RedPacketService redPacketService;
	@Resource
	BindDataService bindDataService;
	@Resource
	EaseMobService easeMobService;
	@Resource
	SystemFundMapper systemFundMapper;
	@Resource
	UserInfoService userInfoService;
	@Resource
	UserGoldRuleService userGoldRuleService;
	@Resource
	private LiveUserInfoService liveUserInfoService;
	@Resource
	private LiveUserConvertService liveUserConvertService;
	/**
	 * modelType =0 故事打赏 ；=1 说说打赏
	 */
	static final Integer TALK = 1;
	static final Integer DIARY = 0;
	/**
	 * 验证码 区间范围
	 */
	static final Integer MAX = 99999;
	static final Integer MIN = 10000;
	static final Integer DO_TIME = 60 * 60;
	static final Integer GOLD_MONEY_CONVERT = 10;// 转换为分
	@Resource
	SystemRewardModelMapper systemRewardModelMapper;
	@Resource
	SystemRewardLogMapper systemRewardLogMapper;

	@Autowired
	// private RedisTemplate<String, Integer> redisTemplate;
	// @Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, Integer> redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void createWebhooksPaySuccessV2(Map<String, Object> callbackData) {
		// TODO Auto-generated method stub
		UserFundLog ufl = new UserFundLog();
		MouoException exception = null;
		try {
			// 获取pingxx支付成功后回调的信息
			Map<String, Object> dataMap = (Map<String, Object>) callbackData.get("data");
			Map<String, Object> objectMap = (Map<String, Object>) dataMap.get("object");
			Map<String, Object> metadataMap = (Map<String, Object>) objectMap.get("metadata");
			int payUserId = StringUtil.getInt(metadataMap.get("payUserId"));
			int modeId = StringUtil.getInt(metadataMap.get("modeId"));
			short modeType = Short.valueOf(StringUtil.getStr(metadataMap.get("modeType")));
			int receiveUserId = StringUtil.getInt(metadataMap.get("receiveUserId"));
			Integer rewardId = StringUtil.getInt(metadataMap.get("rewardId"));
			String tradeNumber = StringUtil.getStr(metadataMap.get("tradeNumber"));
			String app_flag = StringUtil.getStr(metadataMap.get("app_flag"));
			String APP_FLAG = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
			Integer userId = StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
					CONSTANT.SYS_CONF_PATH));
			if (!APP_FLAG.equals(app_flag)) {// 回调地址，区分正式服和测试服
				return;
			}
			int amount = StringUtil.getInt(objectMap.get("amount"));
			String eventId = StringUtil.getStr(callbackData.get("id"));

			ufl.setPingxxId(eventId);
			ufl.setPingxxState(2);
			ufl.setUserFund(amount);
			ufl.setPayUserId(payUserId);
			ufl.setModeId(modeId);
			ufl.setModeType(modeType);
			ufl.setReceiveUserId(receiveUserId);
			ufl.setTradeNumber(tradeNumber);

			int penny = ufl.getUserFund();
			UserFundModeEnum modeEnum = UserFundModeEnum.getByValue(ufl.getModeType());
			UserNotification userNotification = new UserNotification();
			UserFund newUserFund = null;
			switch (modeEnum) {

			// 系统打赏说说
			case SYSTEM_TALK_REWARD:
				// 先模拟app 打赏操作
				if (ufl.getPayUserId() == null || ufl.getReceiveUserId() == null
						|| ufl.getPayUserId() == 0 || ufl.getReceiveUserId() == 0) {
					return;
				}
				if (talkMapper.selectTalkIsExistByTalkId(modeId) == 0) {
					return;
				}
				ufl.setSearchUserid(ufl.getPayUserId());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				ufl.setUserFund(ufl.getUserFund() * -1);
				// 生成 日志
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(ufl.getUserFund());
				}
				/**
				 * 修改系统金额 start
				 */
				userFundMapper.updateSystemFundJIAN(new UserFund(userId, (new BigDecimal(ufl
						.getUserFund()).divide(new BigDecimal(-100)))));
				Long time = redisTemplate.getExpire(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX
						+ rewardId, TimeUnit.SECONDS);
				Integer value = redisTemplate.opsForValue().get(
						CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId);
				try {

					redisTemplate.opsForValue().set(
							CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId,
							value + ufl.getUserFund(), time, TimeUnit.SECONDS);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage());
					logger.error(this.toString() + "redisTemplate=" + redisTemplate.toString()
							+ "rewardId=[" + rewardId + "]" + "value1=[" + value + "]" + "ufl=["
							+ JsonUtil.toJson(ufl) + "]");
				}
				/**
				 * 修改系统金额 end
				 */
				ufl.setUserFund(ufl.getUserFund() * -1);
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);
				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改说说打赏成功的统计数
				talkMapper.updateTalkRewardTotalIncrease(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.SYSTEM_TALK_REWARD_GAIN.getValue());
				ufl.setUserFund(-1 * ufl.getUserFund());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				ufl.setUserFund((-1) * ufl.getUserFund());
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 插入 系统打赏日志

				// systemRewardLogMapper.insert(initSystemRewardLog(ufl, TALK,
				// rewardId));
				// 发送单聊信息给用户
				UserMsgInfo sendUser = userInfoService.selectUsreDetailInfo(ufl.getPayUserId());
				UserMsgInfo getUser = userInfoService.selectUsreDetailInfo(ufl.getReceiveUserId());
				String msg = CONSTANT.I_GIVE_YOU_TALK;
				try {

					easeMobService.sendSingleChatMsg(sendUser.getPushChatId(), new SingleChatMsg(
							EasemobSingleChatMsgTypeEnum.TXT, msg), new SingleChatMsgExtend(
							sendUser.getNickname(), sendUser.getAvatar(), sendUser.getUserId(),
							getUser.getUserId(), getUser.getAvatar(), getUser.getNickname(), true

					), getUser.getPushChatId());

				} catch (Exception e) {
					// TODO: handle exception
					logger.error("ufl.getPayUserId()=[" + ufl.getPayUserId() + "]"
							+ "ufl.getReceiveUserId()=[" + ufl.getReceiveUserId() + "]");
					e.printStackTrace();
				}
				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Talk talk1 = talkMapper.queryTalkInfo(modeId);
				userNotification.setBusinessTextContent(talk1.getContent());
				userNotification.setBusinessType(1);// 业务分类 1说说相关 2专辑故事相关
				List<TalkResource> talkResourceList1 = talkResourceMapper.queryTalkResource(modeId);
				if (talkResourceList1.size() > 0) {
					userNotification.setPic(talkResourceList1.stream().findFirst().get().getUrl());
				}
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_TALK);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);

				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				// easeMobService.
				break;
			case SYSTEM_DIARY_REWARD:
				// 模拟系统给用户打赏 支出
				if (ufl.getPayUserId() == null || ufl.getReceiveUserId() == null
						|| ufl.getPayUserId() == 0 || ufl.getReceiveUserId() == 0) {
					return;
				}
				if (diaryMapper.selectByPrimaryKey(modeId).getState().intValue() == Diary.DIARY_DELETE_NUMBER
						.intValue()) {
					return;
				}
				// 修改系统金额
				ufl.setSearchUserid(ufl.getPayUserId());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				ufl.setUserFund(ufl.getUserFund() * -1);
				// 生成 日志
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(ufl.getUserFund());
				}
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改日记打赏成功的统计数
				diaryMapper.updateDiaryRewardTotal(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.SYSTEM_DIARY_REWARD_GAIN.getValue());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				/**
				 * 修改系统金额 start
				 */

				userFundMapper.updateSystemFundJIAN(new UserFund(userId, (new BigDecimal(ufl
						.getUserFund()).divide(new BigDecimal(-100)))));
				Long time1 = redisTemplate.getExpire(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX
						+ rewardId, TimeUnit.SECONDS);
				Integer value1 = redisTemplate.opsForValue().get(
						CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId);
				try {

					redisTemplate.opsForValue().set(
							CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId,
							value1 + ufl.getUserFund(), time1, TimeUnit.SECONDS);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage());
					logger.error(this.toString() + " rewardId=[" + rewardId + "]" + "value1=["
							+ value1 + "]" + "ufl=[" + ufl + "]" + "ufl.getUserFund()=["
							+ ufl.getUserFund() + "]");
				}
				/**
				 * 修改系统金额 end
				 */
				ufl.setUserFund((-1) * ufl.getUserFund());
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 插入 系统打赏日志
				// systemRewardLogMapper.insert(initSystemRewardLog(ufl, DIARY,
				// rewardId));
				UserMsgInfo sendUser1 = userInfoService.selectUsreDetailInfo(ufl.getPayUserId());
				UserMsgInfo getUser1 = userInfoService.selectUsreDetailInfo(ufl.getReceiveUserId());
				String msg1 = CONSTANT.I_GIVE_YOU_DIARY;
				easeMobService.sendSingleChatMsg(sendUser1.getPushChatId(), new SingleChatMsg(
						EasemobSingleChatMsgTypeEnum.TXT, msg1),
						new SingleChatMsgExtend(sendUser1.getNickname(), sendUser1.getAvatar(),
								sendUser1.getUserId(), getUser1.getUserId(), getUser1.getAvatar(),
								getUser1.getNickname(), true), getUser1.getPushChatId());
				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Map<String, String> diaryContentMap1 = diaryContentService
						.selectFirstDiaryContentListByDiaryId(modeId);
				userNotification.setBusinessTextContent(diaryContentMap1.get("text"));
				userNotification.setBusinessType(2);// 业务分类 1说说相关
													// 2专辑故事日记，叫法不一样，内容是一样的
				userNotification.setPic(diaryContentMap1.get("picture"));
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_DIARY);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);
				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				break;
			default:
				break;
			}
		} catch (MouoException e) {
			exception = e;
			throw e;
		} finally {
			if (exception == null) {
				// logger.info(StringUtil.getTraceInfo() + ":[" +
				// JsonUtil.toJson(callbackData) + "]");
			} else {
				logger.error(
						StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(callbackData) + "]",
						exception);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createWebhooksPaySuccess(Map<String, Object> callbackData) {
		/**
		 * 事例 { "id":"evt_ugB6x3K43D16wXCcqbplWAJo", "created":1440407501,
		 * "livemode":true, "type":"charge.succeeded", "data":{ "object":{
		 * "amount":100, "amount_refunded":0, "amount_settle":0,
		 * "app":"app_urj1WLzvzfTK0OuL", "body":"Your Body", "channel":"upacp",
		 * "client_ip":"127.0.0.1", "created":1440407501, "credential":{ },
		 * "currency":"cny", "description":null, "extra":{ },
		 * "failure_code":null, "failure_msg":null,
		 * "id":"ch_Xsr7u35O3m1Gw4ed2ODmi4Lw", "livemode":true, "metadata":{ },
		 * "object":"charge", "order_no":"123456789", "paid":true,
		 * "refunded":false, "refunds":{ "data":[ ], "has_more":false,
		 * "object":"list",
		 * "url":"/v1/charges/ch_Xsr7u35O3m1Gw4ed2ODmi4Lw/refunds" },
		 * "subject":"Your Subject", "time_expire":1440407501,
		 * "time_paid":1440407501, "time_settle":null,
		 * "transaction_no":"1224524301201505066067849274" } },
		 * "object":"event", "request":"iar_qH4y1KbTy5eLGm1uHSTS00s",
		 * "pending_webhooks":0 }
		 */
		UserFundLog ufl = new UserFundLog();
		MouoException exception = null;
		try {
			// 获取pingxx支付成功后回调的信息
			Map<String, Object> dataMap = (Map<String, Object>) callbackData.get("data");
			Map<String, Object> objectMap = (Map<String, Object>) dataMap.get("object");
			Map<String, Object> metadataMap = (Map<String, Object>) objectMap.get("metadata");

			int payUserId = StringUtil.getInt(metadataMap.get("payUserId"));
			int modeId = StringUtil.getInt(metadataMap.get("modeId"));
			short modeType = Short.valueOf(StringUtil.getStr(metadataMap.get("modeType")));
			int receiveUserId = StringUtil.getInt(metadataMap.get("receiveUserId"));
			Integer rewardId = StringUtil.getInt(metadataMap.get("rewardId"));
			String tradeNumber = StringUtil.getStr(metadataMap.get("tradeNumber"));
			String app_flag = StringUtil.getStr(metadataMap.get("app_flag"));
			String APP_FLAG = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
			Integer userId = StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
					CONSTANT.SYS_CONF_PATH));
			if (!APP_FLAG.equals(app_flag)) {// 回调地址，区分正式服和测试服
				return;
			}
			int amount = StringUtil.getInt(objectMap.get("amount"));
			String eventId = StringUtil.getStr(callbackData.get("id"));

			ufl.setPingxxId(eventId);
			ufl.setPingxxState(2);
			ufl.setUserFund(amount);
			ufl.setPayUserId(payUserId);
			ufl.setModeId(modeId);
			ufl.setModeType(modeType);
			ufl.setReceiveUserId(receiveUserId);
			ufl.setTradeNumber(tradeNumber);

			int penny = ufl.getUserFund();
			UserFundModeEnum modeEnum = UserFundModeEnum.getByValue(ufl.getModeType());
			UserNotification userNotification = new UserNotification();
			UserFund newUserFund = null;
			switch (modeEnum) {
			case TALK_REWARD:
				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改说说打赏成功的统计数
				talkMapper.updateTalkRewardTotalIncrease(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.TALK_REWARD_GAIN.getValue());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				userFundLogMapper.insert(ufl);
				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Talk talk = talkMapper.queryTalkInfo(modeId);

				userNotification.setBusinessTextContent(StringUtil.getStr(talk.getContent()));
				userNotification.setBusinessType(1);// 业务分类 1说说相关 2专辑故事相关
				List<TalkResource> talkResourceList = talkResourceMapper.queryTalkResource(modeId);
				if (talkResourceList.size() > 0) {
					userNotification.setPic(talkResourceList.stream().findFirst().get().getUrl());
				}
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_TALK);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);
				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				// easeMobService.
				break;
			case DIARY_REWARD:
				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改日记打赏成功的统计数
				diaryMapper.updateDiaryRewardTotal(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.DIARY_REWARD_GAIN.getValue());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				userFundLogMapper.insert(ufl);
				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Map<String, String> diaryContentMap = diaryContentService
						.selectFirstDiaryContentListByDiaryId(modeId);
				String text = StringUtil.getStr(diaryContentMap.get("text"));
				if (StringUtil.isEmpty(text)) {
					text = StringUtil.getStr(diaryContentMap.get("diary_title"));
				}

				userNotification.setBusinessTextContent(text);
				userNotification.setBusinessType(2);// 业务分类 1说说相关
													// 2专辑故事日记，叫法不一样，内容是一样的
				userNotification.setPic(diaryContentMap.get("picture"));
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_DIARY);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);
				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				break;
			case TOP_UP:
				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 每天的累计充值金额
				ValueOperations<String, Integer> opsForValue = redisTemplate.opsForValue();
				String charge_total_today_key = CONSTANT.CHARGE_TOTAL_TODAY_PREFIX
						+ ufl.getPayUserId();
				Integer chargeTotalToday = opsForValue.get(charge_total_today_key);
				chargeTotalToday = chargeTotalToday == null ? 0 : chargeTotalToday;
				long secondsLeftToday = 86400 - DateUtils.getFragmentInSeconds(
						Calendar.getInstance(), Calendar.DATE);
				opsForValue.set(charge_total_today_key, chargeTotalToday + penny, secondsLeftToday,
						TimeUnit.SECONDS);
				break;
			case BUY_GOLD:
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 接收金币
				UserGoldRule userGoldRule = userGoldRuleService.getUserGoldRule(modeId);
				int gold = userGoldRule.getGoldNum();
				int give = userGoldRule.getGive();
				BigDecimal goldBD = new BigDecimal(gold + "");// 防止精度问题，用字符串
				BigDecimal giveBD = new BigDecimal(give + "");// 防止精度问题，用字符串
				BigDecimal divisor = new BigDecimal(100 + "");
				int addGold = goldBD.multiply(giveBD).divide(divisor, 2, BigDecimal.ROUND_DOWN)
						.intValue()
						+ gold;
				ufl.setUserFund(addGold);
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.BUY_GOLD_ADD_GOLD.getValue());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.GOLD.getValue());
				UserGold userGold = userGoldService.selectByUserId(ufl.getSearchUserid() + "");
				ufl.setBalance(userGold.getUserGold());
				userGoldService.addOrUpdateGold(ufl.getReceiveUserId(), addGold);
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);
				break;
			// 系统打赏说说
			case SYSTEM_TALK_REWARD:
				// 先模拟app 打赏操作
				if (ufl.getPayUserId() == null || ufl.getReceiveUserId() == null
						|| ufl.getPayUserId() == 0 || ufl.getReceiveUserId() == 0) {
					return;
				}
				if (talkMapper.selectTalkIsExistByTalkId(modeId) == 0) {
					return;
				}
				ufl.setSearchUserid(ufl.getPayUserId());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				ufl.setUserFund(ufl.getUserFund() * -1);
				// 生成 日志
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(ufl.getUserFund());
				}
				/**
				 * 修改系统金额 start
				 */
				userFundMapper.updateSystemFundJIAN(new UserFund(userId, (new BigDecimal(ufl
						.getUserFund()).divide(new BigDecimal(-100)))));
				Long time = redisTemplate.getExpire(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX
						+ rewardId, TimeUnit.SECONDS);
				Integer value = redisTemplate.opsForValue().get(
						CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId);
				try {

					redisTemplate.opsForValue().set(
							CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId,
							value + ufl.getUserFund(), time, TimeUnit.SECONDS);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage());
					logger.error(this.toString() + "redisTemplate=" + redisTemplate.toString()
							+ "rewardId=[" + rewardId + "]" + "value1=[" + value + "]" + "ufl=["
							+ JsonUtil.toJson(ufl) + "]");
				}
				/**
				 * 修改系统金额 end
				 */
				ufl.setUserFund(ufl.getUserFund() * -1);
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);
				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改说说打赏成功的统计数
				talkMapper.updateTalkRewardTotalIncrease(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.SYSTEM_TALK_REWARD_GAIN.getValue());
				ufl.setUserFund(-1 * ufl.getUserFund());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				ufl.setUserFund((-1) * ufl.getUserFund());
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 插入 系统打赏日志

				systemRewardLogMapper.insert(initSystemRewardLog(ufl, TALK, rewardId));
				// 发送单聊信息给用户
				UserMsgInfo sendUser = userInfoService.selectUsreDetailInfo(ufl.getPayUserId());
				UserMsgInfo getUser = userInfoService.selectUsreDetailInfo(ufl.getReceiveUserId());
				String msg = CONSTANT.I_GIVE_YOU_TALK;
				try {

					easeMobService.sendSingleChatMsg(sendUser.getPushChatId(), new SingleChatMsg(
							EasemobSingleChatMsgTypeEnum.TXT, msg), new SingleChatMsgExtend(
							sendUser.getNickname(), sendUser.getAvatar(), sendUser.getUserId(),
							getUser.getUserId(), getUser.getAvatar(), getUser.getNickname(), true

					), getUser.getPushChatId());

				} catch (Exception e) {
					// TODO: handle exception
					logger.error("ufl.getPayUserId()=[" + ufl.getPayUserId() + "]"
							+ "ufl.getReceiveUserId()=[" + ufl.getReceiveUserId() + "]");
					e.printStackTrace();
				}

				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Talk talk1 = talkMapper.queryTalkInfo(modeId);
				userNotification.setBusinessTextContent(talk1.getContent());
				userNotification.setBusinessType(1);// 业务分类 1说说相关 2专辑故事相关
				List<TalkResource> talkResourceList1 = talkResourceMapper.queryTalkResource(modeId);
				if (talkResourceList1.size() > 0) {
					userNotification.setPic(talkResourceList1.stream().findFirst().get().getUrl());
				}
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_TALK);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);

				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				// easeMobService.
				break;
			case SYSTEM_DIARY_REWARD:

				// 模拟系统给用户打赏 支出
				if (ufl.getPayUserId() == null || ufl.getReceiveUserId() == null
						|| ufl.getPayUserId() == 0 || ufl.getReceiveUserId() == 0) {
					return;
				}
				if (diaryMapper.selectByPrimaryKey(modeId).getState().intValue() == Diary.DIARY_DELETE_NUMBER
						.intValue()) {
					return;
				}
				// 修改系统金额
				ufl.setSearchUserid(ufl.getPayUserId());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				ufl.setUserFund(ufl.getUserFund() * -1);
				// 生成 日志
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(ufl.getUserFund());
				}
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 收款者收入金额更新
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改日记打赏成功的统计数
				diaryMapper.updateDiaryRewardTotal(modeId);
				// 接收日志
				ufl.setTradeNumber(TradeNumberUtil.getTradeNumber());
				ufl.setSearchUserid(receiveUserId);
				ufl.setModeType(UserFundModeEnum.SYSTEM_DIARY_REWARD_GAIN.getValue());
				ufl.setSearchCategory(UserFundSrarchCategoryEnum.REWARD.getValue());
				newUserFund = getUserFundByUserId(ufl.getSearchUserid());
				if (newUserFund != null) {
					ufl.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100 + ""))
							.intValue());
				} else {
					ufl.setBalance(penny);
				}
				/**
				 * 修改系统金额 start
				 */

				userFundMapper.updateSystemFundJIAN(new UserFund(userId, (new BigDecimal(ufl
						.getUserFund()).divide(new BigDecimal(-100)))));
				Long time1 = redisTemplate.getExpire(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX
						+ rewardId, TimeUnit.SECONDS);
				Integer value1 = redisTemplate.opsForValue().get(
						CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId);
				try {

					redisTemplate.opsForValue().set(
							CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + rewardId,
							value1 + ufl.getUserFund(), time1, TimeUnit.SECONDS);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage());
					logger.error(this.toString() + " rewardId=[" + rewardId + "]" + "value1=["
							+ value1 + "]" + "ufl=[" + ufl + "]" + "ufl.getUserFund()=["
							+ ufl.getUserFund() + "]");
				}
				/**
				 * 修改系统金额 end
				 */
				ufl.setUserFund((-1) * ufl.getUserFund());
				ufl.setPingxxId(null);
				userFundLogMapper.insert(ufl);

				// 插入 系统打赏日志
				systemRewardLogMapper.insert(initSystemRewardLog(ufl, DIARY, rewardId));
				UserMsgInfo sendUser1 = userInfoService.selectUsreDetailInfo(ufl.getPayUserId());
				UserMsgInfo getUser1 = userInfoService.selectUsreDetailInfo(ufl.getReceiveUserId());
				String msg1 = CONSTANT.I_GIVE_YOU_DIARY;
				easeMobService.sendSingleChatMsg(sendUser1.getPushChatId(), new SingleChatMsg(
						EasemobSingleChatMsgTypeEnum.TXT, msg1),
						new SingleChatMsgExtend(sendUser1.getNickname(), sendUser1.getAvatar(),
								sendUser1.getUserId(), getUser1.getUserId(), getUser1.getAvatar(),
								getUser1.getNickname(), true), getUser1.getPushChatId());
				// 发送一条推送通知给用户
				userNotification.setActionType(3);// 操作分类 1评论说说或专辑故事 2点赞 3打赏 4转发
													// 5回复评论
				userNotification.setBusinessId(modeId);
				Map<String, String> diaryContentMap1 = diaryContentService
						.selectFirstDiaryContentListByDiaryId(modeId);
				userNotification.setBusinessTextContent(diaryContentMap1.get("text"));
				userNotification.setBusinessType(2);// 业务分类 1说说相关
													// 2专辑故事日记，叫法不一样，内容是一样的
				userNotification.setPic(diaryContentMap1.get("picture"));
				userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_REWARD_DIARY);
				userNotification.setReceiveUserId(receiveUserId);
				userNotification.setSendUserId(payUserId);
				jpushService.sendMessgePushCustomMsgToDB(userNotification);
				break;
			case SEND_RED_ENVELOPE:
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改红包支付状态 state 0生成为支付2pingxx回调支付成功
				redPacketService.updateRedPacketPingxxState(modeId, 2);
				break;
			case SEND_RED_ENVELOPE_CROWD:
				// 订单状态修改
				ufl.setPingxxState(2);// ping++支付状态0发起订单1手机端支付成功，不一定真成功2第三方异步通知成功,真正支付成功
				userFundLogMapper.updateTradeState(ufl);
				// 修改红包支付状态 state 0生成为支付2pingxx回调支付成功
				redPacketService.updateRedPacketPingxxState(modeId, 2);
				break;
			default:
				break;
			}
		} catch (MouoException e) {
			exception = e;
			throw e;
		} finally {
			if (exception == null) {
				logger.info(StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(callbackData) + "]");
			} else {
				logger.error(
						StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(callbackData) + "]",
						exception);
			}
		}
	}

	@Override
	public UserFund getUserFundById(int id) {
		return userFundMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserFund getUserFundByUserIdLock(int userId) {
		return userFundMapper.selectUserFundByXLock(userId);
	}

	@Override
	public UserFund getUserFundByUserId(int userId) {
		return userFundMapper.selectUserFundByUserId(userId);
	}

	@Override
	public boolean addAndUpdateUserFund(int userId, int penny) {
		BigDecimal moneyBD = new BigDecimal(penny + "");// 防止精度问题，用字符串
		BigDecimal divisor = new BigDecimal(100 + "");
		return addAndUpdateUserFund(userId, moneyBD.divide(divisor, 2, BigDecimal.ROUND_DOWN));// 小数后2位，多余位数直接截断
	}

	@Override
	public boolean addAndUpdateUserFund(int userId, BigDecimal yuan) {
		boolean flag = true;
		try {
			/**
			 * 添加收支记录
			 */
			// userFundRegulationService.insertNonEmptyUserFundRegulation(new
			// UserFundRegulation(null,
			// 0, 0, yuan.multiply(new BigDecimal("100")).intValue(), new
			// Date()));
			UserFund uf = userFundMapper.selectUserFundByXLock(userId);
			if (uf != null) {// 存在就修改
				BigDecimal oldFund = uf.getUserFund();
				BigDecimal userFund = oldFund.add(yuan);
				uf.setUserFund(userFund);
				uf.setUpdateTime(new Date());
				userFundMapper.updateUserFundByUserId(uf);
			} else {// 不存在则添加
				uf = new UserFund();
				uf.setUserFund(yuan);
				uf.setUserId(userId);
				uf.setUpdateTime(new Date());
				userFundMapper.insert(uf);
			}
		} catch (RuntimeException e) {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId);
			paramsMap.put("money", yuan);
			logger.error(StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(paramsMap) + "]", e);
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public ChargeEntity createCharge(UserFundLog ufl) {
		Map<String, Object> metadata = new HashMap<String, Object>();
		String tradeNumber = TradeNumberUtil.getTradeNumber();// 订单号
		metadata.put("tradeNumber", tradeNumber);
		metadata.put("payUserId", ufl.getPayUserId());
		metadata.put("modeId", ufl.getModeId());
		metadata.put("modeType", ufl.getModeType());
		metadata.put("receiveUserId", ufl.getReceiveUserId());

		ChargeEntity charge = null;
		try {
			charge = chargeService.createWxCharge(tradeNumber, ufl.getModeType(),
					ufl.getClientIp(), ufl.getUserFund(), metadata);
			// logger.info(":["+JsonUtil.toJson(charge)+"]");
			ufl.setPingxxId(charge.getId());
			UserFundModeEnum modeEnum = UserFundModeEnum.getByValue(ufl.getModeType());
			switch (modeEnum) {
			case TALK_REWARD:
				// 生成 日志
				ufl.setTradeNumber(tradeNumber);
				userFundLogMapper.insert(ufl);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error(StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(ufl) + "]", e);
		}
		return charge;
	}

	@Override
	public boolean addFundLog(UserFundLog ufl) {
		/*
		 * logger.debug("This is debug message.");
		 * logger.info("This is a info message.");
		 * logger.warn("This is a warn message.");
		 * logger.error("This is a error message.............");
		 */
		boolean flag = true;
		try {
			int penny = ufl.getUserFund();
			// 付款者支出
			UserFundModeEnum modeEnum = UserFundModeEnum.getByValue(ufl.getModeType());
			switch (modeEnum) {
			case WITHDRAW_DEPOSIT:
				// 收款者收入
				addAndUpdateUserFund(ufl.getReceiveUserId(), penny);
				// 收款者收入订单状态修改
				ufl.setPingxxState(0);
				userFundLogMapper.updateTradeState(ufl);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			flag = false;
			logger.error(StringUtil.getTraceInfo() + ":[" + JsonUtil.toJson(ufl) + "]", e);
		}
		return flag;
	}

	/**
	 * (根据用户Id获取用户的金币和余额)
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserFund queryUserFundByUserId(Integer userId) {
		UserFund userFund = new UserFund();
		int userGold = userGoldMapper.queryUserGoldByUserId(userId);
		BigDecimal userGolds = userFundMapper.queryUserFundByUserId(userId);
		BindData bindData = bindDataMapper.queryBindDataInfoByUserIdForWEIXIN(userId);
		userFund.setBindData(bindData);
		userFund.setUserGold(userGold);
		userFund.setUserFund(userGolds);
		userFund.setUserType(userMapper.checkIsLiveShower(userId));
		return userFund;
	}

	@Override
	public ApiResult insertWithdrawDeposit(UserFundLog userFundlog, HttpServletRequest request) {
		Integer userId = userFundlog.getSearchUserid();
		UserFund uf = userFundMapper.selectUserFundByXLock(userId);
		BigDecimal toDayWithdrawSum = userFundMapper.selcetToDayWithdrawSum(userId);// 当日提现不得超过200
		BigDecimal oldMoney = uf.getUserFund();
		if (userFundlog.getUserFund() < CONSTANT.SYSTEM_WITHDRAW_DEPOSIT) {
			logger.error("提现金额不足10元 userid=[" + userId + "]");
			return new ApiResult(RESPONSE.ERROR, "提现金额不足10元");
		}
		if (uf.getUserFund().multiply(new BigDecimal(100 + "")).intValue() < (userFundlog
				.getUserFund())) {// 判断余额是否足够
			logger.error("余额不足的提现 userId=" + "[" + userId + "]");
			return new ApiResult(RESPONSE.ERROR, "余额不足的提现");
		}
		BigDecimal now = (new BigDecimal(userFundlog.getUserFund())
				.divide(new BigDecimal(100 + "")).add(toDayWithdrawSum));
		if (now.compareTo(CONSTANT.ONE_DAY_WITHDRAW_SUM) > 0) {
			logger.error("提现额度[" + now + "]超过" + CONSTANT.ONE_DAY_WITHDRAW_SUM + " userId=" + "["
					+ userId + "]");
			return new ApiResult(RESPONSE.ERROR, "提现额度[" + now + "]超过当日提现总额"
					+ CONSTANT.ONE_DAY_WITHDRAW_SUM + "元");
		}
		String orderNo = TradeNumberUtil.getTradeNumber();
		userFundlog.setReceiveUserId(userId);
		userFundlog.setClientIp(StringUtil.getIpAddr(request));
		userFundlog.setCreateTime(new Date());
		userFundlog.setTradeNumber(orderNo);
		{// 金额操作
			double money = new BigDecimal(userFundlog.getUserFund()).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_DOWN).doubleValue();
			if (!this.addAndUpdateUserFund(
					userId,
					new BigDecimal(userFundlog.getUserFund()).divide(new BigDecimal(100 + ""), 2,
							BigDecimal.ROUND_DOWN).multiply(new BigDecimal(-1)))) {// 如果修改金额不成功
																					// 返回空值
				return new ApiResult(RESPONSE.ERROR, "提现发生了错误");
			}
			userFundlog.setAudit(UserFundLog.AUDIT_ASK_FOR);
			userFundlog.setPingxxState(UserFundLog.AUDIT_ASK_FOR);
			userFundlog.setSearchUserid(userId);
			userFundlog.setModeType((UserFundModeEnum.WITHDRAW_DEPOSIT.getValue()));
			userFundlog.setBalance((BigDecimal.valueOf(money * (-1)).add(oldMoney).intValue()));
			userFundlog.setSearchCategory(1);
			// this.addAndUpdateUserFund(userId,BigDecimal.valueOf(money*(-1)).add(uf.getUserFund()));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("oldMoney", oldMoney);
			map.put("userFund", money);
			map.put("newMoney", BigDecimal.valueOf(money * (-1)).add(oldMoney));
			userFundlog.setExtnd(JsonUtil.toJson(map));
			userFundLogMapper.insertSelective(userFundlog);
		}
		return new ApiResult(RESPONSE.SUCCESS, "提现发生了错误", userFundlog.getId());
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ApiResult insertSystemReward(SystemRewardModel systemRewardModel) throws Exception {
		// systemRewardModel.setPrice(systemRewardModel.getPrice()*100);
		// systemRewardModel.setOnePrice(systemRewardModel.getOnePrice()*100);//单位处理
		// 变动参数 放在配置文件中
		ApiResult ar = new ApiResult(ResponseEnum.ERROR);
		String SYSTEM_USER_ID = PropertiesUtil.getValueByKey("system_user_id",
				CONSTANT.SYS_CONF_PATH);
		UserFund uf = userFundMapper.selectUserFundByUserId(Integer.valueOf(SYSTEM_USER_ID));
		int systemFund = uf.getUserFund().intValue();
		if (systemRewardModel.getPrice() > (systemFund * 100) || systemFund == 0) {
			return ar;
		}
		// uf.setUserFund(new BigDecimal((systemRewardModel.getPrice()/100)));
		// userFundMapper.updateSystemFundJIAN(uf);

		switch (systemRewardModel.getModelType()) {
		case SystemRewardModeType.TALK: {
			List<Talk> talks = talkMapper.queryTalkGroupByUser(systemRewardModel.getRangeDay() - 1);
			if (talks.isEmpty()) {
				return ar;
			}
			Collections.shuffle(talks);
			Integer count = systemRewardModel.getRewardNumber();
			List<Talk> newtalks = talks.subList(0, (int) (double) talks.size() * count / 100);
			systemRewardModel.setRewardNumber(newtalks.size());
			count = newtalks.size();// 修改了代码 本来count是执行次数的 现在改了代码 吧count从百分比
									// 又换成执行次数
			Long price = Long.valueOf(systemRewardModel.getPrice());
			if (count > ((price * 100)) || (count * systemRewardModel.getOnePrice()) < price) {// 用户数量
																								// 小于等于
																								// 总金额/100
																								// （每个用户打赏1分都不够）
				ar.setCode(ResponseEnum.ERROR.getValue());
				ar.setMessage("数量不够");
				return ar;
			}
			// 随机总金额

			List<Long> longs = Arrays.asList(RandomStringUtil.generateLong(price, // 总金额
																					// 单位都是
																					// 分
					count, // 打赏次数
					systemRewardModel.getOnePrice().longValue(), // 单次最高金额
					1 // 最低金额
					));
			List<Long> times = Arrays.asList(RandomStringUtil.generateLong(
					systemRewardModel.getRange() * DO_TIME, count));
			for (int i = 0; i < times.size(); i++) {
				Long newTime = times.get(i);
				if (i > 0) {
					newTime = times.get(i) + times.get(i - 1);
				}
				times.set(i, newTime);
			}
			systemRewardModel.setState(SystemRewardModel.STATE_DOING);
			systemRewardModel.setCreateTime(new Date());
			systemRewardModelMapper.insertSelective(systemRewardModel);
			// range是执行时间 单位小时 这里做转换成分钟 并增加点时间 作为预留
			redisTemplate.opsForValue().set(
					CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + systemRewardModel.getId(),
					systemRewardModel.getPrice(), systemRewardModel.getRange() * 70,
					TimeUnit.MINUTES);
			try {
				times.forEach(time -> {
					Integer index = times.indexOf(time);
					String APP_FLAG = PropertiesUtil.getValueByKey("app_flag",
							CONSTANT.SYS_CONF_PATH);
					Map<String, Object> map = getCallbackDataMap(newtalks.get(index).getId(),
							UserFundModeEnum.SYSTEM_TALK_REWARD.getValue(), newtalks.get(index)
									.getCreatorId(), TradeNumberUtil.getTradeNumber(), APP_FLAG,
							longs.get(index), systemRewardModel.getId());
					Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
					Map<String, Object> objectMap = (Map<String, Object>) dataMap.get("object");
					Map<String, Object> metadataMap = (Map<String, Object>) objectMap
							.get("metadata");
					/**
					 * 封装打赏model 供opretion项目调度数据
					 */
					SystemRewardLog systemRewardLog = new SystemRewardLog();
					systemRewardLog.setRewardId(systemRewardModel.getId());
					systemRewardLog.setUserId((metadataMap.get("payUserId")).toString());
					systemRewardLog.setUserIdGet((metadataMap.get("receiveUserId")).toString());
					systemRewardLog.setType(SystemRewardModeType.TALK);
					systemRewardLog.setFund(longs.get(index).intValue());
					systemRewardLog.setUserFund(StringUtil.getInt(objectMap.get("amount")));
					systemRewardLog.setCreateTime(new Date());
					systemRewardLog.setModeId(newtalks.get(index).getId());
					systemRewardLog.setPushTime(new Date((time * 1000) + getNextHour().getTime()));
					systemRewardLog.setState(SystemRewardLog.STATE_NO);
					systemRewardLog.setLastData(SystemRewardLog.LAST_NO);
					if (Integer.compare(index, newtalks.size() - 1) == 0) {// 执行最后一次任务的时候
						// 把执行的任务修改为完成
						systemRewardLog.setLastData(SystemRewardLog.LAST_YES);
						systemRewardLogMapper.insertSelective(systemRewardLog);
					} else {
						systemRewardLogMapper.insertSelective(systemRewardLog);
					}
					// createWebhooksPaySuccess(map);
				});
			} catch (Exception e) {
				logger.error("任务[ 系统自动打赏说说 ]异常", e);
				systemRewardModel.setState(SystemRewardModel.STATE_ERRO);
				systemRewardModelMapper.updateByPrimaryKey(systemRewardModel);
				redisTemplate.delete(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX
						+ systemRewardModel.getId());
				// e.printStackTrace();
			}
			return new ApiResult(ResponseEnum.SUCCESS.getValue());

		}
		case SystemRewardModeType.DIARY: {
			List<Diary> diarys = diaryMapper
					.selectDiaryGroupByUser(systemRewardModel.getRangeDay() - 1);
			Integer count = systemRewardModel.getRewardNumber();
			// if (count > diarys.size())
			// {
			// return new ApiResult(ResponseEnum.ERROR.getValue(), "数量不够");
			// }
			Collections.shuffle(diarys);
			if (count == 0 || diarys.isEmpty()) {
				return new ApiResult(ResponseEnum.ERROR.getValue());
			}
			List<Diary> newDiary = diarys.subList(0, (int) (double) diarys.size() * count / 100);
			count = newDiary.size();// 修改了代码 本来count是执行次数的 现在改了代码 吧count从百分比
									// 又换成执行次数
			systemRewardModel.setRewardNumber(count);
			Long price = Long.valueOf(systemRewardModel.getPrice());
			if (count > ((price * 100)) || (count * systemRewardModel.getOnePrice()) < price) {// 用户数量
																								// 小于等于
																								// 总金额/100
																								// （每个用户打赏1分都不够）
				ar.setCode(ResponseEnum.ERROR.getValue());
				ar.setMessage("数量不够");
				return ar;
			}
			List<Long> longs = Arrays.asList(RandomStringUtil.generateLong(price, // 总金额
																					// 单位都是
																					// 分
					count, // 打赏次数
					systemRewardModel.getOnePrice().longValue(), // 单次最高金额
					1 // 最低金额
					));
			List<Long> times = Arrays.asList(RandomStringUtil.generateLong(
					systemRewardModel.getRange() * DO_TIME, count));
			for (int i = 0; i < times.size(); i++) {
				Long newTime = times.get(i);
				if (i > 0) {
					newTime = times.get(i) + times.get(i - 1);
				}
				times.set(i, newTime);
			}
			systemRewardModel.setState(SystemRewardModel.STATE_DOING);
			systemRewardModel.setCreateTime(new Date());
			systemRewardModelMapper.insertSelective(systemRewardModel);
			redisTemplate.opsForValue().set(
					CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + systemRewardModel.getId(),
					systemRewardModel.getPrice(), systemRewardModel.getRange(), TimeUnit.HOURS);
			times.forEach(time -> {
				Integer index = times.indexOf(time);
				String APP_FLAG = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
				Map<String, Object> map = getCallbackDataMap(newDiary.get(index).getId(),
						UserFundModeEnum.SYSTEM_TALK_REWARD.getValue(), newDiary.get(index)
								.getCreatorId(), TradeNumberUtil.getTradeNumber(), APP_FLAG, longs
								.get(index), systemRewardModel.getId());
				Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
				Map<String, Object> objectMap = (Map<String, Object>) dataMap.get("object");
				Map<String, Object> metadataMap = (Map<String, Object>) objectMap.get("metadata");
				/**
				 * 封装打赏model 供opretion项目调度数据
				 */
				SystemRewardLog systemRewardLog = new SystemRewardLog();
				systemRewardLog.setRewardId(systemRewardModel.getId());
				systemRewardLog.setUserId((metadataMap.get("payUserId")).toString());
				systemRewardLog.setUserIdGet((metadataMap.get("receiveUserId")).toString());
				systemRewardLog.setType(SystemRewardModeType.DIARY);
				systemRewardLog.setFund(longs.get(index).intValue());
				systemRewardLog.setUserFund(StringUtil.getInt(objectMap.get("amount")));
				systemRewardLog.setCreateTime(new Date());
				systemRewardLog.setModeId(newDiary.get(index).getId());
				systemRewardLog.setPushTime(new Date((time * 1000) + getNextHour().getTime()));
				systemRewardLog.setState(SystemRewardLog.STATE_NO);
				systemRewardLog.setLastData(SystemRewardLog.LAST_NO);
				if (Integer.compare(index, newDiary.size() - 1) == 0) {// 执行最后一次任务的时候
					// 把执行的任务修改为完成
					systemRewardLog.setLastData(SystemRewardLog.LAST_YES);
					systemRewardLogMapper.insertSelective(systemRewardLog);
				} else {
					systemRewardLogMapper.insertSelective(systemRewardLog);
				}
			});
			return new ApiResult(ResponseEnum.SUCCESS.getValue());
		}
		default:
			return new ApiResult(ResponseEnum.ERROR.getValue(), "modeType错误");
		}
	}

	// 封装 打赏参数
	private Map<String, Object> getCallbackDataMap(int modeId, short modeType, int receiveUserId,
			String tradeNumber, String app_flag, Long amount, Integer rewardId) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		int payUserId = userMapper.queryFakeUserLimit1().getUserId();
		map4.put("payUserId", payUserId);
		map4.put("modeId", modeId);
		map4.put("modeType", modeType);
		map4.put("receiveUserId", receiveUserId);
		map4.put("tradeNumber", tradeNumber);
		map4.put("rewardId", rewardId);
		map4.put("app_flag", app_flag);
		map3.put("amount", amount);
		map3.put("metadata", map4);
		map2.put("object", map3);
		map1.put("data", map2);
		map1.put("id", null);
		return map1;
	}

	private SystemRewardLog initSystemRewardLog(UserFundLog ufl, Integer type, Integer rewardId) {
		SystemRewardLog systemRewardLog = new SystemRewardLog();
		systemRewardLog.setFund(ufl.getUserFund());
		systemRewardLog.setModeId(ufl.getModeId());
		systemRewardLog.setUserId(ufl.getPayUserId().toString());
		systemRewardLog.setUserIdGet(ufl.getReceiveUserId().toString());
		systemRewardLog.setUserFund(ufl.getBalance());
		systemRewardLog.setType(type);
		systemRewardLog.setCreateTime(new Date());
		systemRewardLog.setRewardId(rewardId);
		return systemRewardLog;

	}

	@Override
	public Map<String, Object> selectRewardSystemInfoCount() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("systemFund", this.getCompanySysFund()); // 单位元
		map.putAll(systemRewardModelMapper.selectRewardSystemInfoCount()); // 昨天
																			// 打赏
																			// 今天打赏
																			// 累计打赏
																			// 单位分
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getIdentifyingCode(String phone, String accountName, Integer money) {
		// TODO Auto-generated method stub
		Integer randNum = MIN + (int) (Math.random() * ((MAX - MIN) + 1));// 随机验证码
		RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getFirstBeanOfType(RedisTemplate.class);
		Map<String, Object> map = Maps.newConcurrentMap();
		try {
			// String appName = PropertiesUtil.getValueByKey("app_flag",
			// CONSTANT.SYS_CONF_PATH);
			if (SmsSendUtil.sendForIdentifyingCode(phone, accountName, "陌友",
					CONSTANT.REG_IDENTIFYING_CODE, randNum, money.toString()) != null) {
				map.put("code", ResponseEnum.SUCCESS.getValue());
				SystemFund systemFund = new SystemFund();
				systemFund.setCode(randNum);
				systemFund.setFund(new BigDecimal(money));
				redisTemplate.opsForValue().set(CONSTANT.REDIS_IDENTIFYING_CODE_KEY + ":" + phone,
						JsonUtil.toJson(systemFund), 10L, TimeUnit.MINUTES);
				return map;
			}
		} catch (ApiException e) {
			map.put("code", ResponseEnum.ERROR.getValue());
			e.printStackTrace();
		}
		map.put("code", ResponseEnum.ERROR.getValue());
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insertSystemFund(Map<String, Object> param, HttpServletRequest req, String loginName) {
		// TODO Auto-generated method stub
		RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getFirstBeanOfType(RedisTemplate.class);
		SystemFund systemFundRedis = JsonUtil.toObject(
				redisTemplate.opsForValue().get(
						CONSTANT.REDIS_IDENTIFYING_CODE_KEY + ":" + param.get("phone")),
				SystemFund.class);
		String userCode = param.get("code").toString();// 前端输入的code
		if (systemFundRedis == null) {
			return ResponseEnum.ERROR.getValue();
		}
		if (userCode.equals(systemFundRedis.getCode().toString())) {
			UserFund userFund = new UserFund();
			userFund.setUserFund(systemFundRedis.getFund());
			String SYSTEM_USER_ID = PropertiesUtil.getValueByKey("system_user_id",
					CONSTANT.SYS_CONF_PATH);
			userFund.setUserId(Integer.valueOf(SYSTEM_USER_ID));
			userFundMapper.updateSystemFund(userFund);
			SystemFund systemFund = new SystemFund();
			systemFund.setBalance((userFundMapper.selectUserFundByUserId(userFund.getUserId())
					.getUserFund()));
			systemFund.setCreateTime(new Date());
			systemFund.setFund(userFund.getUserFund());
			systemFund.setLoginName(loginName);
			systemFund.setTradeNo(TradeNumberUtil.getTradeNumber());
			systemFundMapper.insert(systemFund);
			redisTemplate.delete(CONSTANT.REDIS_IDENTIFYING_CODE_KEY + ":" + param.get("phone"));// 清空redis
																									// 数据
			// 充值成功回执
			SmsSendUtil.sendSuccess(param.get("phone").toString(), "陌友",
					CONSTANT.REG_IDENTIFYING_CODE_SUCCESS, systemFundRedis.getFund(), loginName);
			return ResponseEnum.SUCCESS.getValue();
		}
		return ResponseEnum.ERROR.getValue();
	}

	@Override
	public List<SystemFund> selectSystemFundLog(PageBean pb) {
		// TODO Auto-generated method stub
		return systemFundMapper.selectSystemFundLog(pb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.moyou.moyouRms.service.userfund.UserFundService#getCompanySysFund()
	 */
	@Override
	public BigDecimal getCompanySysFund() {
		int userId = StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
				CONSTANT.SYS_CONF_PATH));
		BigDecimal userFund = getUserFundByUserId(userId).getUserFund();
		Set<String> keys = redisTemplate.keys(CONSTANT.SYS_REWARD_DEDUCT_MONEY_PREFIX + "*");
		int sysRewardDeductMoneySum = 0;// 单位分
		if (keys != null) {
			for (String key : keys) {
				sysRewardDeductMoneySum += redisTemplate.opsForValue().get(key);
			}
		}
		// 单位分转换为元
		BigDecimal moneyBD = new BigDecimal(sysRewardDeductMoneySum + "");// 防止精度问题，用字符串
		BigDecimal divisor = new BigDecimal(100 + "");
		BigDecimal tmpFund = userFund.subtract(moneyBD.divide(divisor, 2, BigDecimal.ROUND_DOWN));
		if (tmpFund.intValue() < 0) {
			tmpFund = new BigDecimal(0);
		}
		return tmpFund;
	}

	@Override
	public int updateUserGold(UserFund userFund, short type, Integer id, HttpServletRequest req) {
		// 修改金币
		userGoldService.addOrUpdateGold(userFund.getUserId(), userFund.getUserGold());
		// 插入日志
		UserFundLog record = new UserFundLog();
		record.setPayUserId(StringUtil.getInt(PropertiesUtil.getValueByKey("system_user_id",
				CONSTANT.SYS_CONF_PATH)));
		record.setReceiveUserId(userFund.getUserId());
		record.setUserFund(userFund.getUserGold());
		record.setModeType(UserFundModeEnum.SYSTEM_GOLD_BACK.getValue());
		record.setModeId(id);
		record.setTradeNumber(TradeNumberUtil.getTradeNumber());
		record.setClientIp(StringUtil.getIpAddr(req));
		record.setCreateTime(new Date());
		record.setPingxxState(UserFundLog.PINGXX_SUCCESS);
		record.setAudit(UserFundLog.AUDIT_SUCCESS);
		record.setAuditTime(new Date());
		record.setSearchUserid(userFund.getUserId());
		record.setSearchCategory(UserFundSrarchCategoryEnum.GOLD.getValue());
		Account a = (Account) req.getSession().getAttribute(Const.SESSION_USER);
		Integer oldGold = userGoldMapper.selectByUserIdLock(userFund.getUserId().toString())
				.getUserGold();
		record.setBalance(oldGold + userFund.getUserGold());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldGold", oldGold);
		if (a == null) {
			map.put("auditAdmin", "未登录");
		} else {
			map.put("auditAdmin", a.getLoginName());
		}
		map.put("newGold", oldGold + userFund.getUserGold());
		record.setExtnd(JsonUtil.toJson(map));
		userFundLogMapper.insertSelective(record);
		return RESPONSE.SUCCESS;
	}

	/**
	 * 消耗余额 充值金币
	 */
	@Override
	public ApiResult inserUserGold(Integer userId, Integer modeId) {
		// TODO 半成品 不能用
		if (StringUtil.isNotEmpty(userId) || StringUtil.isNotEmpty(modeId)) {
			UserFund userFund = userFundMapper.selectUserFundByXLock(userId);
			BigDecimal userBigDecimal = userFund.getUserFund();// 用户余额
			UserGoldRule userGoldRule = userGoldRuleService.selectByPrimaryKey(modeId);
			if (userGoldRule.getNeedRmb().compareTo(userBigDecimal) >= 0)// 判断用户余额是否够支付该规则的余额消耗
			{
				BigDecimal needRmb = userGoldRule.getNeedRmb();// rmb消耗的数量
				@SuppressWarnings("unused")
				Integer goldNum = userGoldRule.getGoldNum()
						+ (userGoldRule.getGoldNum() * userGoldRule.getGive() / 100);// 金币增加的数量
				boolean index = addAndUpdateUserFund(userId, needRmb.multiply(new BigDecimal("-1")));
				if (!index) {
					return new ApiResult(
							RESPONSE.ERROR,// 余额不足
							RechargeExceptionEnum.CHARGEBACK_FAILURE.getDesc(),
							RechargeExceptionEnum.CHARGEBACK_FAILURE.getValue());
				} else {
					// 扣款成功 增加扣款日志 增加金币 增加金币日志

				}
			} else {
				return new ApiResult(RESPONSE.ERROR,
						RechargeExceptionEnum.HAVE_NOT_ENOUGH_FUND.getDesc(),
						RechargeExceptionEnum.HAVE_NOT_ENOUGH_FUND.getValue());
			}
		} else {
			// 用户请求非法
			return new ApiResult(RESPONSE.ERROR,
					RechargeExceptionEnum.REQUEST_IS_ILLEGAL.getDesc(),
					RechargeExceptionEnum.REQUEST_IS_ILLEGAL.getValue());
		}

		return null;
	}

	@Override
	public int insertSystemRewardV2ForSchedule() throws Exception {
		List<SystemRewardLog> systemRewardLogList = systemRewardLogMapper
				.selectOneHourNeedDoingData();
		String APP_FLAG = PropertiesUtil.getValueByKey("app_flag", CONSTANT.SYS_CONF_PATH);
		Timer timer = new Timer();
		try {
			for (SystemRewardLog systemRewardLog : systemRewardLogList) {
				timer.schedule(new TimerTask() {
					public void run() {
						Map<String, Object> map = getCallbackDataMap(
								systemRewardLog.getModeId().intValue(),
								systemRewardLog.getType() == SystemRewardLog.TALK_TYPE ? UserFundModeEnum.SYSTEM_TALK_REWARD
										.getValue() : UserFundModeEnum.SYSTEM_DIARY_REWARD
										.getValue(), Integer
										.valueOf(systemRewardLog.getUserIdGet()).intValue(),
								TradeNumberUtil.getTradeNumber(), APP_FLAG, Long
										.valueOf(systemRewardLog.getFund().toString()),
								systemRewardLog.getRewardId());
						createWebhooksPaySuccessV2(map);
						systemRewardLog.setState(SystemRewardLog.STATE_OK);
						systemRewardLogMapper.updateByPrimaryKeySelective(systemRewardLog);
						if (systemRewardLog.getLastData() == 1) {// 执行最后一次任务的时候
							// 把执行的任务修改为完成
							SystemRewardModel systemRewardModel = new SystemRewardModel();
							systemRewardModel.setId(systemRewardLog.getRewardId());
							systemRewardModel.setState(SystemRewardModel.STATE_OK);
							systemRewardModelMapper.updateByPrimaryKeySelective(systemRewardModel);
						}
					};
				}, systemRewardLog.getPushTime());
			}
		} catch (Exception e) {
			timer.cancel();
			logger.error("insertSystemReward 发生错误 任务停止" + e.getMessage());
		}
		return RESPONSE.SUCCESS;
	}

	private Date getNextHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
		return calendar.getTime();
	}

	@Override
	public ApiResult updateGoldToMoney(Integer userId, Integer gold, HttpServletRequest req) {
		UserGoldConvertModel userInfo = userInfoService.selectGoldConvertModel(userId);
		Integer money = gold / GOLD_MONEY_CONVERT; // 单位分
		if (userInfo == null) {
			return new ApiResult(RESPONSE.ERROR, "绑定数据有误");
		} else if (gold < 0 || userInfo.getGoldNum() < gold) {
			return new ApiResult(RESPONSE.ERROR, "数据有误");
		} else if ((userInfo.getToDayGet() + gold) > 200 * 1000) {
			return new ApiResult(RESPONSE.USER_LIVE_CONVERT, "领取额度达到上限200元");
		} else if (StringUtil.isEmpty(userInfo.getWeixin())) {
			return new ApiResult(RESPONSE.ERROR, "尚未提交转账微信");
		}
		userGoldService.addOrUpdateGoldForLive(userId, gold * -1);
		int modeId = liveUserConvertService.insertLiveUserConvert(new LiveUserConvert(null, userId,
				userInfo.getNickname(), userInfo.getMoyouId(), userInfo.getWeixin(), gold, money,
				new Date(), UserGoldConvertModel.DEFAULT_STATE, null, null, null));
		/**
		 * 添加userfundlog数据
		 */
		UserFundLog u = new UserFundLog();
		String SYSTEM_USER_ID = PropertiesUtil.getValueByKey("system_user_id",
				CONSTANT.SYS_CONF_PATH);
		u.setPayUserId(Integer.valueOf(SYSTEM_USER_ID));
		u.setReceiveUserId(userId);
		u.setUserFund(gold);
		u.setUserFund(gold);
		u.setModeType(UserFundModeEnum.LIVESHOW_GOLD_CONVERT_MONEY.getValue());
		u.setModeId(modeId);
		u.setTradeNumber(TradeNumberUtil.getTradeNumber());
		u.setClientIp(StringUtil.getIpAddr(req));
		u.setCreateTime(new Date());
		u.setPingxxState(UserFundLog.PINGXX_SUCCESS);
		u.setAudit(UserFundLog.AUDIT_DEFAULT);
		u.setPayType(0);
		u.setSearchUserid(userId);
		u.setSearchCategory(UserFundSrarchCategoryEnum.WITHDRAW_DEPOSIT.getValue());
		u.setBalance(userInfo.getGoldNum() - gold);
		u.setModePlusType(UserFundLogModePlusType.LIVE_SHOW.getValue());
		userFundLogMapper.insertSelective(u);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	@Override
	public Map<String, Object> selectSystemFundSum() {
		// TODO Auto-generated method stub
		return systemFundMapper.selectSystemFundSum();
	}
}
