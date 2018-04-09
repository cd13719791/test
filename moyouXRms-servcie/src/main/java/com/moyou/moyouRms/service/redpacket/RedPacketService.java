package com.moyou.moyouRms.service.redpacket;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.redpacket.RedPacketReceive;
import com.moyou.moyouRms.model.redpacket.RedPacketSend;

/**
 * @describe 红包service
 * @author liuxinyi
 * @date 2017年3月31日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public interface RedPacketService {
	/**
	 * 红包实体
	 * @param redPacketSendId
	 * @return
	 */
	RedPacketSend getRedPacket(int redPacketSendId);
	/**
	 * 根据参数查询红包接收数据
	 * @param redPacketReceive
	 * @return
	 */
	List<RedPacketReceive> queryRedPacketReceive(RedPacketReceive redPacketReceive);
	/**
	 * 退还红包
	 */
	void createRedPacketBack();
	/**
	 * 查询时间段内过期的红包
	 * @param pastTime
	 * @return
	 */
	List<RedPacketSend> queryPastRedPacketSend(Date startTime, Date endTime);
	/**
	 * 修改红包状态
	 * @param redPacketSendId
	 * @param state 0生成为支付2pingxx回调支付成功
	 * @return
	 */
	void updateRedPacketPingxxState(int redPacketSendId, int state);
}
