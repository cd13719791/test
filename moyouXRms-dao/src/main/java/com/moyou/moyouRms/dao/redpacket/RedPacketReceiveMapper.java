package com.moyou.moyouRms.dao.redpacket;

import java.util.List;

import com.moyou.moyouRms.model.redpacket.RedPacketReceive;

public interface RedPacketReceiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedPacketReceive record);

    int insertSelective(RedPacketReceive record);

    RedPacketReceive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedPacketReceive record);

    int updateByPrimaryKey(RedPacketReceive record);
    /**
     * 根据红包id获取改红包生成的接收数据
     * @param sendRedpacketId
     * @return
     */
    List<RedPacketReceive> selectBySendRedpacketId(Integer sendRedpacketId);
    /**
     * 根据状态获取改红包生成的接收数据
     * @param sendRedpacketId
     * @return
     */
    List<RedPacketReceive> queryRedPacketReceive(RedPacketReceive redPacketReceive);
    /**
     * 获取红包到期应该退还的数据
     * @return
     */
    List<RedPacketReceive> selectBackRedPacketData();
}