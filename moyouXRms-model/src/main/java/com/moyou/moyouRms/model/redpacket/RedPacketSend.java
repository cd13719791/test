package com.moyou.moyouRms.model.redpacket;

import java.util.Date;

/**
 * 描述:t_red_packet_send表的实体类
 * @version
 * @author:  liuxinyi
 * @创建时间: 2017-03-31
 */
public class RedPacketSend {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 发送用户id
     */
    private Integer sendUserid;
    /**
     * 接收用户id
     */
    private Integer reciveUserid;

    /**
     * 红包单个金额，单位分
     */
    private Integer sendTotalFund;

    /**
     * 留言
     */
    private String sendText;

    /**
     * 红包个数
     */
    private Integer redPacketNumber;

    /**
     * 红包种类1平均红包2拼手气红包
     */
    private Integer redPacketKind;
    /**
     * 1单对单，2群红包
     */
    private Integer redPacketType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 过期时间
     */
    private Date pastTime;

    /**
     * 红包单个金额，单位分
     */
    private Integer sendSingleFund;

    /**
     * 0生成为支付2pingxx回调支付成功
     */
    private Integer pingxxState;
    /**
     * 交易类型 注释见：PayTypeEnum
     */
    private Integer payType;

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
     * 发送用户id
     * @return send_userid 发送用户id
     */
    public Integer getSendUserid() {
        return sendUserid;
    }

    /**
     * 发送用户id
     * @param sendUserid 发送用户id
     */
    public void setSendUserid(Integer sendUserid) {
        this.sendUserid = sendUserid;
    }

    /**
     * 红包单个金额，单位分
     * @return send_total_fund 红包单个金额，单位分
     */
    public Integer getSendTotalFund() {
        return sendTotalFund;
    }

    /**
     * 红包单个金额，单位分
     * @param sendTotalFund 红包单个金额，单位分
     */
    public void setSendTotalFund(Integer sendTotalFund) {
        this.sendTotalFund = sendTotalFund;
    }

    /**
     * 留言
     * @return send_text 留言
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * 留言
     * @param sendText 留言
     */
    public void setSendText(String sendText) {
        this.sendText = sendText == null ? null : sendText.trim();
    }

    /**
     * 红包个数
     * @return red_packet_number 红包个数
     */
    public Integer getRedPacketNumber() {
        return redPacketNumber;
    }

    /**
     * 红包个数
     * @param redPacketNumber 红包个数
     */
    public void setRedPacketNumber(Integer redPacketNumber) {
        this.redPacketNumber = redPacketNumber;
    }

    /**
	 * @return the redPacketKind
	 */
	public Integer getRedPacketKind() {
		return redPacketKind;
	}

	/**
	 * @param redPacketKind the redPacketKind to set
	 */
	public void setRedPacketKind(Integer redPacketKind) {
		this.redPacketKind = redPacketKind;
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
     * 红包单个金额，单位分
     * @return send_single_fund 红包单个金额，单位分
     */
    public Integer getSendSingleFund() {
        return sendSingleFund;
    }

    /**
     * 红包单个金额，单位分
     * @param sendSingleFund 红包单个金额，单位分
     */
    public void setSendSingleFund(Integer sendSingleFund) {
        this.sendSingleFund = sendSingleFund;
    }

    /**
     * 0生成为支付2pingxx回调支付成功
     * @return pingxx_state 0生成为支付2pingxx回调支付成功
     */
    public Integer getPingxxState() {
        return pingxxState;
    }

    /**
     * 0生成为支付2pingxx回调支付成功
     * @param pingxxState 0生成为支付2pingxx回调支付成功
     */
    public void setPingxxState(Integer pingxxState) {
        this.pingxxState = pingxxState;
    }

	/**
	 * @return the payType
	 */
	public Integer getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * @return the pastTime
	 */
	public Date getPastTime() {
		return pastTime;
	}

	/**
	 * @param pastTime the pastTime to set
	 */
	public void setPastTime(Date pastTime) {
		this.pastTime = pastTime;
	}

	/**
	 * @return the reciveUserid
	 */
	public Integer getReciveUserid() {
		return reciveUserid;
	}

	/**
	 * @param reciveUserid the reciveUserid to set
	 */
	public void setReciveUserid(Integer reciveUserid) {
		this.reciveUserid = reciveUserid;
	}

	/**
	 * @return the redPacketType
	 */
	public Integer getRedPacketType() {
		return redPacketType;
	}

	/**
	 * @param redPacketType the redPacketType to set
	 */
	public void setRedPacketType(Integer redPacketType) {
		this.redPacketType = redPacketType;
	}
}