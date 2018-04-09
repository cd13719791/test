package com.moyou.moyouRms.service.redpacket.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.constants.enums.UserFundSrarchCategoryEnum;
import com.moyou.moyouRms.dao.redpacket.RedPacketReceiveMapper;
import com.moyou.moyouRms.dao.redpacket.RedPacketSendMapper;
import com.moyou.moyouRms.dao.userfund.UserFundLogMapper;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.redpacket.RedPacketReceive;
import com.moyou.moyouRms.model.redpacket.RedPacketSend;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.redpacket.RedPacketService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.makeorder.TradeNumberUtil;

@Service
public class RedPacketServiceImpl implements RedPacketService {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedPacketSendMapper redPacketSendMapper;
	@Autowired
	private RedPacketReceiveMapper redPacketReceiveMapper;
	@Autowired
	private UserFundService userFundService;
	@Autowired
	private UserFundLogMapper userFundLogMapper;
	@Autowired
	private MsgSystemXService msgSystemXService;
	
	
	public static double getRandomMoney(RedPackage _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return (double) Math.round(_redPackage.remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = _redPackage.remainMoney / _redPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        _redPackage.remainSize--;
        _redPackage.remainMoney -= money;
        return money;
    }
	static class RedPackage {
		private int remainSize;
		private double remainMoney;
	}
	public static void main(String[] args) {
		RedPackage rp = new RedPackage();
		rp.remainMoney = 10-1.62;
		rp.remainSize = 1;
//		System.out.println(getRandomMoney(rp));
	}

	@Override
	public RedPacketSend getRedPacket(int redPacketSendId) {
		return redPacketSendMapper.selectByPrimaryKey(redPacketSendId);
	}

	@Override
	public List<RedPacketReceive> queryRedPacketReceive(RedPacketReceive redPacketReceive) {
		return redPacketReceiveMapper.queryRedPacketReceive(redPacketReceive);
	}

	@Override
	public void createRedPacketBack() {
		List<RedPacketReceive> backRedPacketReceiveList = redPacketReceiveMapper.selectBackRedPacketData();
		if (backRedPacketReceiveList != null && backRedPacketReceiveList.size() > 0) {
			Map<Integer, BackRedPacket> userBackFundMap = new HashMap<Integer, BackRedPacket>();// 将需要退还的红包金额累加起来，统一退回
			for (RedPacketReceive redPacketReceive : backRedPacketReceiveList) {
				int sendUserid = redPacketReceive.getSendUserid();
				int sendRedPacketId = redPacketReceive.getRedPacketSendId();
				if (userBackFundMap.containsKey(sendRedPacketId)) {// 有则累加
					BackRedPacket backRedPacket = userBackFundMap.get(sendRedPacketId);
					backRedPacket.setBackTotalFund(backRedPacket.getBackTotalFund()+redPacketReceive.getReceiveFund());
					userBackFundMap.put(sendRedPacketId, backRedPacket);
				} else {// 没有则新增
					BackRedPacket backRedPacket = new BackRedPacket();
					backRedPacket.setBackTotalFund(redPacketReceive.getReceiveFund());
					backRedPacket.setSendRedPacketId(sendRedPacketId);
					backRedPacket.setSendUserid(sendUserid);
					userBackFundMap.put(sendRedPacketId, backRedPacket);
				}
				RedPacketReceive updateRedPacketReceive = new RedPacketReceive();
				updateRedPacketReceive.setId(redPacketReceive.getId());
				updateRedPacketReceive.setState(2);//0未领取1领取2退还
				redPacketReceiveMapper.updateByPrimaryKeySelective(updateRedPacketReceive);// 修改数据状态变为退还
			}
			// 将金额统一退还给发红包的人
			for (Integer sendRedPacketId : userBackFundMap.keySet()) {
				BackRedPacket backRedPacket = userBackFundMap.get(sendRedPacketId);
				userFundService.addAndUpdateUserFund(backRedPacket.getSendUserid(), backRedPacket.getBackTotalFund());
				// 生成一条红包退还日志
				String tradeNumber = TradeNumberUtil.getTradeNumber();// 订单号
				UserFundLog userFundLog = new UserFundLog();
				userFundLog.setTradeNumber(tradeNumber);
				userFundLog.setClientIp("");
				userFundLog.setCreateTime(new Date());
				userFundLog.setModeId(backRedPacket.getSendRedPacketId());
				userFundLog.setModeType(UserFundModeEnum.RED_REFUND.getValue());
				userFundLog.setPayUserId(0);
				userFundLog.setReceiveUserId(backRedPacket.getSendUserid());
				userFundLog.setUserFund(backRedPacket.getBackTotalFund());
				userFundLog.setPayType(0);
				userFundLog.setPingxxState(2);// ping++支付状态0发起订单1手机或pc支付成功，不一定真成功2第三方异步通知成功,真正支付成功或本应用内支付交易成功
				userFundLog.setSearchUserid(backRedPacket.getSendUserid());
				userFundLog.setSearchCategory(UserFundSrarchCategoryEnum.RED_PACKET.getValue());
				UserFund newUserFund = userFundService.getUserFundByUserId(userFundLog.getSearchUserid());
				if (newUserFund != null) {
					userFundLog.setBalance(newUserFund.getUserFund().multiply(new BigDecimal(100+"")).intValue());
				} else {
					userFundLog.setBalance(backRedPacket.getBackTotalFund());
				}
				userFundLogMapper.insert(userFundLog);
				// 发送系统消息
				MsgSystemX msgSystemX = new MsgSystemX();
				msgSystemX.setMsgSendType(CONSTANT.SYS_MSG_RED_PACKET_BACK);
				msgSystemX.setMsgTitle(CONSTANT.SYS_MSG_RED_PACKET_BACK_TITLE);
				msgSystemX.setMsgContent(backRedPacket.getBackTotalFund()+"");
				msgSystemX.setReceiveUserId(backRedPacket.getSendUserid());
				msgSystemX.setSendUserId(0);
				msgSystemXService.addSysMsgAndPushCustomMsg(msgSystemX, "红包退还通知");
			}
		}
		
	}

	@Override
	public List<RedPacketSend> queryPastRedPacketSend(Date startDate, Date endDate) {
		String startTime = DateTimeUtil.formatDate(startDate, "yyyy-MM-dd HH");
		String endTime = DateTimeUtil.formatDate(endDate, "yyyy-MM-dd HH");
		return redPacketSendMapper.queryPastRedPacketSend(startTime, endTime);
	}
	@Override
	public void updateRedPacketPingxxState(int redPacketSendId, int state) {
		RedPacketSend record = new RedPacketSend();
		record.setId(redPacketSendId);
		record.setPingxxState(state);
		redPacketSendMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 退还红包实体
	 * @describe TODO
	 * @author liuxinyi
	 * @date 2017年4月5日
	 * @email liuxinyi@mousns.com
	 * @version 1.0.0
	 */
	class BackRedPacket {
		private int sendUserid;//发送用户id
		private int sendRedPacketId;// 发送红包id
		private int backTotalFund;// 应该退还的总金额 单位分
		/**
		 * @return the sendUserid
		 */
		public int getSendUserid() {
			return sendUserid;
		}
		/**
		 * @param sendUserid the sendUserid to set
		 */
		public void setSendUserid(int sendUserid) {
			this.sendUserid = sendUserid;
		}
		/**
		 * @return the sendRedPacketId
		 */
		public int getSendRedPacketId() {
			return sendRedPacketId;
		}
		/**
		 * @param sendRedPacketId the sendRedPacketId to set
		 */
		public void setSendRedPacketId(int sendRedPacketId) {
			this.sendRedPacketId = sendRedPacketId;
		}
		/**
		 * @return the backTotalFund
		 */
		public int getBackTotalFund() {
			return backTotalFund;
		}
		/**
		 * @param backTotalFund the backTotalFund to set
		 */
		public void setBackTotalFund(int backTotalFund) {
			this.backTotalFund = backTotalFund;
		}
	}
	
}
