package com.moyou.moyouRms.model.redpacket;

import java.util.Date;

/**
 * 描述:t_red_packet_receive表的实体类
 * @version
 * @author:  liuxinyi
 * @创建时间: 2017-03-31
 */
public class RedPacketReceive {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 关联发送红包数据id
     */
    private Integer redPacketSendId;

    /**
     * 接收用户id
     */
    private Integer receiveUserid;
    /**
     * 发送用户id
     */
    private Integer sendUserid;
    /**
     * 接收金额，单位分
     */
    private Integer receiveFund;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态0未领取1领取2已退还
     */
    private Integer state;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联发送红包数据id
     * @return red_packet_send_id 关联发送红包数据id
     */
    public Integer getRedPacketSendId() {
        return redPacketSendId;
    }

    /**
     * 关联发送红包数据id
     * @param redPacketSendId 关联发送红包数据id
     */
    public void setRedPacketSendId(Integer redPacketSendId) {
        this.redPacketSendId = redPacketSendId;
    }

    /**
     * 接收用户id
     * @return receive_userid 接收用户id
     */
    public Integer getReceiveUserid() {
        return receiveUserid;
    }

    /**
     * 接收用户id
     * @param receiveUserid 接收用户id
     */
    public void setReceiveUserid(Integer receiveUserid) {
        this.receiveUserid = receiveUserid;
    }

    /**
     * 接收金额，单位分
     * @return receive_fund 接收金额，单位分
     */
    public Integer getReceiveFund() {
        return receiveFund;
    }

    /**
     * 接收金额，单位分
     * @param receiveFund 接收金额，单位分
     */
    public void setReceiveFund(Integer receiveFund) {
        this.receiveFund = receiveFund;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 状态0未领取1领取2已退还
     * @return state 状态0未领取1领取2已退还
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态0未领取1领取2已退还
     * @param state 状态0未领取1领取2已退还
     */
    public void setState(Integer state) {
        this.state = state;
    }

	/**
	 * @return the sendUserid
	 */
	public Integer getSendUserid() {
		return sendUserid;
	}

	/**
	 * @param sendUserid the sendUserid to set
	 */
	public void setSendUserid(Integer sendUserid) {
		this.sendUserid = sendUserid;
	}
    
}