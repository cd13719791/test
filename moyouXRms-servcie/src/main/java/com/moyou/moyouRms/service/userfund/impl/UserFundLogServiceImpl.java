package com.moyou.moyouRms.service.userfund.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.dao.userfund.SystemRewardLogMapper;
import com.moyou.moyouRms.dao.userfund.SystemRewardModelMapper;
import com.moyou.moyouRms.dao.userfund.UserFundLogMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserRecordListResult;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.user.BindData;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.pingxx.service.ChargeService;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.BindDataService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;
import com.moyou.moyouRms.util.StringUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

@Service
public class UserFundLogServiceImpl implements UserFundLogService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserFundLogMapper userFundLogMapper;
	@Resource
	private UserFundService userFundService;
	@Resource
	private BindDataService bindDataService;
	@Resource
	private ChargeService chargeService;
	@Resource
	private TalkService talkService;
	@Resource
	private DiaryService diaryService;
	@Resource
	private SystemRewardModelMapper systemRewardModelMapper;
	@Resource
	private SystemRewardLogMapper systemRewardLogMapper;
	@Resource
	private DiaryMapper diaryMapper;
	@Resource
	MsgSystemXService msgSystemXService;
	@Resource
	UserNotificationMapper userNotificationMapper;
	private final static Integer TALK = 1;
	private final static Integer DIARY = 0;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFundLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.insertSelective(record);
	}

	@Override
	public UserFundLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFundLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateTradeState(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.updateTradeState(record);
	}

	@Override
	public int updateTradeStateyByPingId(UserFundLog record) {
		// TODO Auto-generated method stub
		return userFundLogMapper.updateTradeStateyByPingId(record);
	}

	@Override
	public List<UserFundLog> selectUserFundLogBySelective(PageBean userFundLog) {
		// TODO Auto-generated method stub
		return dateFormat(userFundLogMapper.selectUserFundLogBySelective(userFundLog));
	}

	@Override
	public List<UserFundLog> selectUserFundLogByUser(PageBean userFundLog) {
		// TODO Auto-generated method stub
		List<UserFundLog> list = userFundLogMapper.selectUserFundLogByUser(userFundLog);
		String userId = userFundLog.getConditions().get("userId").toString();
		for (int i = 0; i < list.size(); i++) {
			UserFundLog s = list.get(i);
			String receiveUserId = s.getReceiveUserId().toString();
			if (receiveUserId.equals(userId)) {
				s.setExtnd("1");// 别人给自己打赏 附带参数1
			} else {
				s.setExtnd("0");// 自己打赏给别人 附带参数0
			}
			;
		}
		return dateFormat(list);
	}

	@Override
	public List<UserFundLog> selectUserFundLogByParam(PageBean pageBean) {
		// TODO Auto-generated method stub
		List<UserFundLog> list = userFundLogMapper.selectUserFundLogByParam(pageBean);
		// String userId = pageBean.getConditions().get("userId").toString();
		// for (int i = 0; i < list.size(); i++) {
		// UserFundLog s = list.get(i);
		// String receiveUserId = "";
		// if (s.getReceiveUserId() == null) {
		// s.setReceiveUserId(0);
		// }
		// try {
		// receiveUserId = s.getReceiveUserId().toString();
		// if (receiveUserId.equals(userId)) {
		// s.setExtnd("1");// 别人给自己打赏 附带参数1
		// } else {
		// // s.setExtnd("0");// 自己打赏给别人 附带参数0
		// }
		// ;
		// } catch (Exception e) {
		//
		// // System.out.println("receiveUserId" + receiveUserId
		// // + "        userId" + userId + "    " + s.getId());
		// // TODO: handle exception
		// }
		// }
		return dateFormat(list);
	}

	private List<UserFundLog> dateFormat(List<UserFundLog> list) {
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
			SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
			list.get(i).setMonth(sdf.format(list.get(i).getCreateTime()));
			list.get(i).setMonthDay(sdf2.format(list.get(i).getCreateTime()));
			list.get(i).setHourMinSecond(sdf3.format(list.get(i).getCreateTime()));
		}
		return list;
	}

	@Override
	public List<UserFundLog> selectUserFundLogBySystemUpdate(PageBean pb) {
		// TODO Auto-generated method stub
		return userFundLogMapper.selectUserFundLogBySystemUpdate(pb);
	}

	@Override
	public List<UserFundLog> selectUserFundLogReward(PageBean pageBean) {
		List<UserFundLog> list = userFundLogMapper.selectUserFundLogReward(pageBean);
		list.forEach(s -> {
			if (pageBean.getConditions().get("userId").equals(s.getReceiveUserId().toString())) {
				s.setExtnd("get");
			} else {
				s.setExtnd("set");
			}
			;
		});
		return list;
	}

	@Override
	public List<UserFundLog> selectUserFundLogByState(PageBean pb) {
		// TODO Auto-generated method stub
		List<UserFundLog> list = userFundLogMapper.selectUserFundLogByState(pb);
		for (int i = 0; i < list.size(); i++) {
			UserFundLog s = list.get(i);
			if (s.getExtnd() != null && s.getExtnd() != "") {
				String param = s.getExtnd();
				Map<String, Object> map = new HashMap<String, Object>();
				map = JsonUtil.toCollection(param, new TypeReference<Map<String, Object>>() {
				});
				s.setParam(map);
			}
		}
		return list;
	}

	@Override
	public List<UserFundLog> queryUserFundLogByPageBean(PageBean pb) {
		// TODO Auto-generated method stub
		return userFundLogMapper.queryUserFundLogByPageBean(pb);
	}

	@Override
	public ApiResult updateUserFundState(int userFundLogId, int audit, Account account,
			String reasonText) {
		logger.info("开始提现：");
		// UserFund
		UserFundLog userFundlog = userFundLogMapper.selectByIdForLock(userFundLogId);
		// BigDecimal userfund=userFund.getUserFund();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userFundlog.setAuditTime(new Date());
		String jsonMap = userFundlog.getExtnd();
		Map<String, Object> map = null;
		if (jsonMap != null) {
			map = JsonUtil.toMap(jsonMap);
		} else {
			map = new HashMap<String, Object>();
		}
		/*
		 * account = new Account(); account.setAccountId("11111111111");
		 * account.setName("lxy");
		 */
		map.put("accountId", account.getAccountId());
		map.put("auditorName", account.getName());
		map.put("reasonText", userFundlog.getReasonText());
		String remitOrderNo = TradeNumberUtil.getTradeNumber();
		map.put("remitOrderNo", remitOrderNo);
		userFundlog.setExtnd(JsonUtil.toJson(map));
		// 审核0申请提现1审核成功2审核不通过3
		if (audit == UserFundLog.AUDIT_SUCCESS
				&& userFundlog.getAudit() == UserFundLog.AUDIT_ASK_FOR) {// 操作为通过审核并且数据的状态为审核中
																			// /**
																			// *
																			// 提现开始
																			// */
																			// /**
																			// *
																			// 每日200限额
																			// 记得加上判断
																			// */
			BindData bindData = bindDataService
					.queryBindDateByUserId(userFundlog.getSearchUserid());
			if (bindData == null) {
				logger.error("提现失败，bindData is null  userFundLogId:" + userFundLogId + "audit: "
						+ audit);
				return new ApiResult(RESPONSE.ERROR, "提现失败,尚未绑定微信");
			}
			// 开始向用户微信账户打款
			Msg msg = chargeService.remitFundToWx(remitOrderNo, bindData.getOpenId(),
					userFundlog.getUserFund(), UserFundModeEnum.WITHDRAW_DEPOSIT.getValue());
			if (!msg.isSuccess()) {// 微信提现失败 就结束
				logger.error("微信打款失败啦！！！！=============" + msg.getMsg());
				/**
				 * 推送错误信息给用户
				 */
				MsgSystemX msgSystemX = new MsgSystemX();
				msgSystemX.setMsgSendType(CONSTANT.JPUSH_AUDIT_FALSE);
				msgSystemX.setMsgTitle("审核失败");
				msgSystemX.setReceiveUserId(userFundlog.getSearchUserid());
				msgSystemX.setModeType(Short.valueOf("7"));
				msgSystemX.setMsgContent(CONSTANT.JPUSH_AUDIT_FALSE);
				msgSystemX.setSendUserId(StringUtil.getInt(PropertiesUtil.getValueByKey(
						"system_user_id", CONSTANT.SYS_CONF_PATH)));
				msgSystemXService.addSysMsgAndPushCustomMsg(msgSystemX, CONSTANT.JPUSH_AUDIT_FALSE);
				return new ApiResult(RESPONSE.ERROR, "提现失败,微信打款失败,余额不足" + msg.getMsg());
			}

			userFundlog.setAudit(UserFundLog.AUDIT_SUCCESS);
			userFundlog.setPingxxState(UserFundLog.PINGXX_SUCCESS);
			logger.info("提现成功 流水号：" + remitOrderNo);
			updateByPrimaryKeySelective(userFundlog);
		} else if (audit == UserFundLog.AUDIT_ERRO) {// 审核操作， 不通过则想金额打回用户的原始账户
			userFundlog.setAudit(UserFundLog.AUDIT_ERRO);
			userFundlog.setPingxxState(UserFundLog.PINGXX_ERRO);
			Map<String, Object> extendMap = JsonUtil.toMap(userFundlog.getExtnd());
			if (extendMap == null) {
				extendMap = new HashMap<String, Object>();
			}
			extendMap.put("reasonText", reasonText);
			userFundService.addAndUpdateUserFund(userFundlog.getSearchUserid(),
					userFundlog.getUserFund());
			userFundlog.setExtnd(JsonUtil.toJson(extendMap));
			updateByPrimaryKeySelective(userFundlog);
		} else {
			logger.error("提现失败，数据异常 userFundLogId:" + userFundLogId + "audit: " + audit);
			return new ApiResult(RESPONSE.ERROR, "提现失败，数据异常");
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功");

	}

	@Override
	public Map<String, Object> queryGoldAndFundCount() {
		// TODO Auto-generated method stub
		return userFundLogMapper.queryGoldAndFundCount();
	}

	@Override
	public int updateUserFundLogRemark(int id, String msg) {
		// TODO Auto-generated method stub
		UserFundLog record = this.selectByPrimaryKey(id);
		Map<String, Object> map = JsonUtil.toMap(record.getExtnd());
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("remark", msg);
		record.setExtnd(JsonUtil.toJson(map));
		return userFundLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Map<String, Object> queryUserFundLogCount() {
		// TODO Auto-generated method stub
		return userFundLogMapper.queryUserFundLogCount();
	}

	@Override
	public List<SystemRewardModel> selectSystemRewardLog(PageBean pb) {
		// TODO Auto-generated method stub
		return systemRewardModelMapper.selectSystemRewardLog(pb);
	}

	@Override
	public Map<String, Object> selectSystemRewardLogCount() {
		// TODO Auto-generated method stub
		return systemRewardModelMapper.selectSystemRewardLogCount();
	}

	@Override
	public SystemRewardModel selectSystemRewardLogById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer id = Integer.valueOf(map.get("id").toString());
		SystemRewardModel systemRewardModel = systemRewardModelMapper.selectByPrimaryKey(id);
		systemRewardModel.setSystemRewardLogList(systemRewardLogMapper.selectByRewardId(id));
		return systemRewardModel;
	}

	@Override
	public int updateSystemRewardLogById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		SystemRewardModel record = new SystemRewardModel();
		record.setId(Integer.valueOf(map.get("id").toString()));
		record.setDec(map.get("decs").toString());
		return systemRewardModelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Map<String, Object> selectRewardCountNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if (Integer.compare(Integer.valueOf(map.get("type").toString()), TALK) == 0) {
			map.put("count",
					talkService.queryTalkReallyUser(
							Integer.valueOf(map.get("rangeDay").toString()) - 1).size());
			map.put("userCount", talkService.queryTalkUserCount(Integer.valueOf(map.get("rangeDay")
					.toString()) - 1));
		} else if (Integer.compare(Integer.valueOf(map.get("type").toString()), DIARY) == 0) {
			map.put("count",
					diaryMapper.selectReallyUserDiary(
							Integer.valueOf(map.get("rangeDay").toString()) - 1).size());
			map.put("userCount", diaryMapper.selectDiaryUserCount(Integer.valueOf(map.get(
					"rangeDay").toString()) - 1));
		}
		return map;
	}

	@Override
	public UserFundLog selectUserFundLogByTypeAndId(short value, Integer id) {
		// TODO Auto-generated method stub
		return userFundLogMapper.selectUserFundLogByTypeAndId(value, id);
	}

	@Override
	public List<UserFundLog> selectActivityLog(PageBean pb) {
		// TODO Auto-generated method stub

		return userFundLogMapper.selectActivityLog(pb);
	}

	@Override
	public List<UserFundLog> selectUserFundLogForZhiBo(PageBean pageBean) {
		// TODO Auto-generated method stub
		pageBean.getConditions().put("modeType",
				UserFundModeEnum.LIVESHOW_GOLD_CONVERT_MONEY.getValue());
		return userFundLogMapper.selectUserFundLogForZhiBo(pageBean).stream().peek(s -> {
			s.setParam(JsonUtil.toMap(s.getExtnd()));
		}).collect(Collectors.toList());
	}

	/**
	 * 获取所有需要统计的数据 资金流水
	 */
	@Override
	public List<UserFundLog> selectUserFundLogForRegulation() {
		// TODO Auto-generated method stub
		List<UserFundLog> list1 = userFundLogMapper.selectUserFundLogForRegulationChongZhi()
				.stream().peek(s -> {
					s.setUserFund(Math.abs(s.getUserFund()));// 求绝对值
					}).collect(Collectors.toList());
		List<UserFundLog> list2 = userFundLogMapper.selectUserFundLogForRegulationTiXian().stream()
				.peek(s -> {
					s.setUserFund(Math.abs(s.getUserFund()) * -1);// 求绝对值
				}).collect(Collectors.toList());
		List<UserFundLog> list3 = userFundLogMapper.selectUserFundLogForRegulationHongBao()
				.stream().peek(s -> {
					s.setUserFund(Math.abs(s.getUserFund()));// 求绝对值
					}).collect(Collectors.toList());
		List<UserFundLog> list4 = userFundLogMapper.selectUserFundLogForRegulationDaShang()
				.stream().peek(s -> {
					s.setUserFund(Math.abs(s.getUserFund()));// 求绝对值
					}).collect(Collectors.toList());
		List<UserFundLog> list5 = userFundLogMapper.selectUserFundLogForRegulationHuodong()
				.stream().peek(s -> {
					s.setUserFund(Math.abs(s.getUserFund()));// 求绝对值
					}).collect(Collectors.toList());
		list1.addAll(list2);
		list1.addAll(list3);
		list1.addAll(list4);
		list1.addAll(list5);
		return list1;
	}

	// LIVESHOW_GET_GOLD((short) 56, "直播获得金币（送礼）"),
	// LIVESHOW_DANMU_GOLD((short) 57, "直播发弹幕支出金币"),
	// LIVESHOW_DEL_GLOD((short) 58, "观看直播得到金币"),
	// LIVESHOW_SAY_TALK_GLOD((short) 59, "连麦消耗金币"),
	// LIVESHOW_SEE_GLOD((short) 60, "连视频消耗金币"),
	@Override
	public Integer selectWacthLiveShowEarn(Integer id) {
		// TODO Auto-generated method stub
		UserFundLog ufl = userFundLogMapper.selectUserFundLogByTypeAndId(
				UserFundModeEnum.LIVESHOW_GET_GOLD.getValue(), id);
		Integer LIVESHOW_GET_GOLD = ufl == null ? 0 : ufl.getUserFund();
		ufl = userFundLogMapper.selectUserFundLogByTypeAndId(
				UserFundModeEnum.LIVESHOW_DANMU_GOLD.getValue(), id);
		Integer LIVESHOW_DANMU_GOLD = ufl == null ? 0 : ufl.getUserFund();
		ufl = userFundLogMapper.selectUserFundLogByTypeAndId(
				UserFundModeEnum.LIVESHOW_DEL_GLOD.getValue(), id);
		Integer LIVESHOW_DEL_GLOD = ufl == null ? 0 : ufl.getUserFund();
		ufl = userFundLogMapper.selectUserFundLogByTypeAndId(
				UserFundModeEnum.LIVESHOW_SAY_TALK_GLOD.getValue(), id);
		Integer LIVESHOW_SAY_TALK_GLOD = ufl == null ? 0 : ufl.getUserFund();
		ufl = userFundLogMapper.selectUserFundLogByTypeAndId(
				UserFundModeEnum.LIVESHOW_SEE_GLOD.getValue(), id);
		Integer LIVESHOW_SEE_GLOD = ufl == null ? 0 : ufl.getUserFund();
		return LIVESHOW_GET_GOLD + LIVESHOW_DANMU_GOLD + LIVESHOW_DEL_GLOD + LIVESHOW_SAY_TALK_GLOD
				+ LIVESHOW_SEE_GLOD;
	}

	// 观众支出
	// LIVESHOW_PAY_GOLD((short) 61, "直播支出金币（送礼）"),
	// WATCH_LIVESHOW_DANMU_GOLD((short) 62, "直播发弹幕支出金币"),
	// WATCH_LIVESHOW_DEL_GLOD((short) 63, "观看直播消耗金币"),
	// WATCH_LIVESHOW_SAY_TALK_GLOD((short) 64, "连麦消耗金币"),
	// WATCH_LIVESHOW_SEE_GLOD((short) 65, "连视频消耗金币"),
	/**
	 * 观众观看主播直播消费总数
	 */
	public Integer selectUserWatchLiveAllExpense(Integer id, Integer userId) {
		Integer LIVESHOW_PAY_GOLD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_PAY_GOLD.getValue(), id, userId);
		Integer WATCH_LIVESHOW_DANMU_GOLD = userFundLogMapper
				.selectUserFundLogByTypeAndIdAndUserId(
						UserFundModeEnum.WATCH_LIVESHOW_DANMU_GOLD.getValue(), id, userId);
		Integer WATCH_LIVESHOW_DEL_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.WATCH_LIVESHOW_DEL_GLOD.getValue(), id, userId);
		Integer WATCH_LIVESHOW_SAY_TALK_GLOD = userFundLogMapper
				.selectUserFundLogByTypeAndIdAndUserId(
						UserFundModeEnum.WATCH_LIVESHOW_SAY_TALK_GLOD.getValue(), id, userId);
		Integer WATCH_LIVESHOW_SEE_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.WATCH_LIVESHOW_SEE_GLOD.getValue(), id, userId);
		return LIVESHOW_PAY_GOLD + WATCH_LIVESHOW_DANMU_GOLD + WATCH_LIVESHOW_DEL_GLOD
				+ WATCH_LIVESHOW_SAY_TALK_GLOD + WATCH_LIVESHOW_SEE_GLOD;
	}

	/**
	 * 主播开播获取观众送出总额
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	public Integer selectAnchorLiveAllEarnings(Integer id, Integer userId) {
		Integer LIVESHOW_GET_GOLD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_GET_GOLD.getValue(), id, userId);
		Integer LIVESHOW_DEL_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_DEL_GLOD.getValue(), id, userId);
		Integer LIVESHOW_SAY_TALK_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_SAY_TALK_GLOD.getValue(), id, userId);
		Integer LIVESHOW_SEE_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_SEE_GLOD.getValue(), id, userId);
		return LIVESHOW_GET_GOLD + LIVESHOW_DEL_GLOD + LIVESHOW_SAY_TALK_GLOD + LIVESHOW_SEE_GLOD;
	}

	/**
	 * 观众看直播扣费
	 */
	public Integer selectUserWatchLiveGold(Integer id, Integer userId) {
		Integer WATCH_LIVESHOW_DEL_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.WATCH_LIVESHOW_DEL_GLOD.getValue(), id, userId);
		return WATCH_LIVESHOW_DEL_GLOD;
	}

	/**
	 * 观众发弹幕扣费
	 */
	public Integer selectUserSendDanMuGold(Integer id, Integer userId) {
		Integer WATCH_LIVESHOW_DANMU_GOLD = userFundLogMapper
				.selectUserFundLogByTypeAndIdAndUserId(
						UserFundModeEnum.WATCH_LIVESHOW_DANMU_GOLD.getValue(), id, userId);
		return WATCH_LIVESHOW_DANMU_GOLD;
	}

	/**
	 * 观众刷礼物扣费
	 */
	public Integer selectUserWatchLivePayGold(Integer id, Integer userId) {
		Integer LIVESHOW_PAY_GOLD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.LIVESHOW_PAY_GOLD.getValue(), id, userId);
		return LIVESHOW_PAY_GOLD;
	}

	/**
	 * 观众连麦扣费
	 */
	public Integer selectUserSayTalkGold(Integer id, Integer userId) {
		Integer WATCH_LIVESHOW_SAY_TALK_GLOD = userFundLogMapper
				.selectUserFundLogByTypeAndIdAndUserId(
						UserFundModeEnum.WATCH_LIVESHOW_SAY_TALK_GLOD.getValue(), id, userId);
		return WATCH_LIVESHOW_SAY_TALK_GLOD;
	}

	/**
	 * 观众连视频扣费
	 */
	public Integer selectUserWacthLiveAllVideoGold(Integer id, Integer userId) {
		Integer WATCH_LIVESHOW_SEE_GLOD = userFundLogMapper.selectUserFundLogByTypeAndIdAndUserId(
				UserFundModeEnum.WATCH_LIVESHOW_SEE_GLOD.getValue(), id, userId);
		return WATCH_LIVESHOW_SEE_GLOD;
	}

	@Override
	public List<UserRecordListResult> selectUserFundLogServiceByLiveModeId(Integer modeId) {
		// TODO Auto-generated method stub
		return userFundLogMapper.selectUserFundLogServiceByLiveModeId(modeId).parallelStream()
				.peek(s -> {
					s.setAllExpense(this.selectUserWatchLiveAllExpense(modeId, s.getUserId()));
					s.setAllEarnings(this.selectAnchorLiveAllEarnings(modeId, s.getUserId()));
					s.setDanMuExpense(this.selectUserSendDanMuGold(modeId, s.getUserId()));
					s.setTalkExpense(this.selectUserSayTalkGold(modeId, s.getUserId()));
					s.setVideoExpense(this.selectUserWacthLiveAllVideoGold(modeId, s.getUserId()));
					s.setGiftExpense(this.selectUserWatchLivePayGold(modeId, s.getUserId()));
					s.setWatchLiveExpense(this.selectUserWatchLiveGold(modeId, s.getUserId()));
					s.setWatchTime(s.getWatchLiveSeconds() / 60 + "分钟");
					Long lon = (s.getCreateTime().getTime() + (s.getWatchLiveSeconds() * 1000));
					if (s.getWatchLiveSeconds() != 0) {
						s.setEndTime(new Date(lon));
					}
				}).limit(1).collect(Collectors.toList());
	}
}
