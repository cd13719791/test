package com.moyou.moyouRms.dao.redpacket;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.redpacket.RedPacketSend;

public interface RedPacketSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedPacketSend record);

    int insertSelective(RedPacketSend record);

    RedPacketSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedPacketSend record);

    int updateByPrimaryKey(RedPacketSend record);
    /**
     * 查询过期的红包
     * @param pastTime
     * @return
     */
    List<RedPacketSend> queryPastRedPacketSend(@Param(value = "startTime")String startTime, @Param(value = "endTime")String endTime);
}