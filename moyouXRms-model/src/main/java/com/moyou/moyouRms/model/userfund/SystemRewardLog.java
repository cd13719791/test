package com.moyou.moyouRms.model.userfund;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:sys_reward_log表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-06-08
 */
public class SystemRewardLog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1771241838173858120L;
	/**
     * 
     */
	private Integer id;
	private Integer rewardId;// 操作记录id
	private Date pushTime;
	private Short state;// 发布状态 0未发布 1已发布
	public static final Short STATE_OK = 1;
	private String moyouId;
	private String moyouIdGet;
	private Short lastData;// 最后一条数据 0=不是最后一条； 1=是最后一条
	public static final Short LAST_YES = 1;
	public static final Short LAST_NO = 0;
	public static final Short STATE_NO = 0;

	public Short getLastData() {
		return lastData;
	}

	public void setLastData(Short lastData) {
		this.lastData = lastData;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getMoyouIdGet() {
		return moyouIdGet;
	}

	public void setMoyouIdGet(String moyouIdGet) {
		this.moyouIdGet = moyouIdGet;
	}

	public Integer getRewardId() {
		return rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	/**
	 * 打赏假用户的陌友Id
	 */
	private String userId;

	/**
     * 
     */
	private String userIdGet;

	/**
	 * 打赏分类1=说说 0 =故事
	 */
	private Integer type;
	public static final int TALK_TYPE = 1;

	/**
	 * 打赏金额
	 */
	private Integer fund;

	/**
	 * 用户余额
	 */
	private Integer userFund;

	/**
     * 
     */
	private Date createTime;

	/**
	 * 打赏说说 故事的id
	 */
	private Integer modeId;

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 打赏假用户的陌友Id
	 * 
	 * @return user_id 打赏假用户的陌友Id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 打赏假用户的陌友Id
	 * 
	 * @param userId
	 *            打赏假用户的陌友Id
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * 
	 * @return user_id_get
	 */
	public String getUserIdGet() {
		return userIdGet;
	}

	/**
	 * 
	 * @param userIdGet
	 */
	public void setUserIdGet(String userIdGet) {
		this.userIdGet = userIdGet == null ? null : userIdGet.trim();
	}

	/**
	 * 打赏分类1=说说 0 =故事
	 * 
	 * @return type 打赏分类1=说说 0 =故事
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 打赏分类1=说说 0 =故事
	 * 
	 * @param type
	 *            打赏分类1=说说 0 =故事
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 打赏金额
	 * 
	 * @return fund 打赏金额
	 */
	public Integer getFund() {
		return fund;
	}

	/**
	 * 打赏金额
	 * 
	 * @param fund
	 *            打赏金额
	 */
	public void setFund(Integer fund) {
		this.fund = fund;
	}

	/**
	 * 用户余额
	 * 
	 * @return user_fund 用户余额
	 */
	public Integer getUserFund() {
		return userFund;
	}

	/**
	 * 用户余额
	 * 
	 * @param userFund
	 *            用户余额
	 */
	public void setUserFund(Integer userFund) {
		this.userFund = userFund;
	}

	/**
	 * 
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 打赏说说 故事的id
	 * 
	 * @return mode_id 打赏说说 故事的id
	 */
	public Integer getModeId() {
		return modeId;
	}

	/**
	 * 打赏说说 故事的id
	 * 
	 * @param modeId
	 *            打赏说说 故事的id
	 */
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
}