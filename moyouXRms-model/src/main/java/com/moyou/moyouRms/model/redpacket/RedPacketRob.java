package com.moyou.moyouRms.model.redpacket;


/**
 * 描述:抢红包实体
 * @version
 * @author:  liuxinyi
 * @创建时间: 2017-03-31
 */
public class RedPacketRob {
    /**
     * 红包发送实体id
     */
    private Integer redPacketSendId;

    /**
     * 红包初始化个数
     */
    private Integer redPacketNumber;

    /**
     * 红包剩余个数
     */
    private Integer redPacketResidueNumber;

	/**
	 * @return the redPacketSendId
	 */
	public Integer getRedPacketSendId() {
		return redPacketSendId;
	}

	/**
	 * @param redPacketSendId the redPacketSendId to set
	 */
	public void setRedPacketSendId(Integer redPacketSendId) {
		this.redPacketSendId = redPacketSendId;
	}

	/**
	 * @return the redPacketNumber
	 */
	public Integer getRedPacketNumber() {
		return redPacketNumber;
	}

	/**
	 * @param redPacketNumber the redPacketNumber to set
	 */
	public void setRedPacketNumber(Integer redPacketNumber) {
		this.redPacketNumber = redPacketNumber;
	}

	/**
	 * @return the redPacketResidueNumber
	 */
	public Integer getRedPacketResidueNumber() {
		return redPacketResidueNumber;
	}

	/**
	 * @param redPacketResidueNumber the redPacketResidueNumber to set
	 */
	public void setRedPacketResidueNumber(Integer redPacketResidueNumber) {
		this.redPacketResidueNumber = redPacketResidueNumber;
	}
}