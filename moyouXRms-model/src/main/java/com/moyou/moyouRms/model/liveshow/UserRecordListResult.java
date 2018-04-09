package com.moyou.moyouRms.model.liveshow;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;
/**
 * 
 * @author Administrator
 *  观众观看详情
 */
public class UserRecordListResult extends BaseModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;					   //主键
	private String moyouId;				   //陌友ID
	private String watchTime;			   //观看时长/分钟
	private Integer watchLiveExpense;      //看直播扣费
	private Integer giftExpense;           //刷礼物扣费
	private Integer talkExpense;           //连麦扣费
	private Integer videoExpense;          //连视频扣费
	private Integer danMuExpense;          //发弹幕扣费
	private Integer allExpense;  		   //总消费
	private Integer allEarnings;		   //总收入	
	private Integer userId;				   //用户Id
	private String nickname;			   //观众名称
	private Date createTime;			   //开始观看
	private Date endTime;  				   //结束观看
	private Integer watchLiveSeconds;	   //观看时长
	private Integer modeId;				   //t_user_fund_log,对应房间ID
	
	/**
	 * @return the modeId
	 */
	public Integer getModeId() {
		return modeId;
	}
	/**
	 * @param modeId the modeId to set
	 */
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
	/**
	 * @return the watchLiveExpense
	 */
	public Integer getWatchLiveExpense() {
		return watchLiveExpense;
	}
	/**
	 * @param watchLiveExpense the watchLiveExpense to set
	 */
	public void setWatchLiveExpense(Integer watchLiveExpense) {
		this.watchLiveExpense = watchLiveExpense;
	}
	/**
	 * @return the giftExpense
	 */
	public Integer getGiftExpense() {
		return giftExpense;
	}
	/**
	 * @param giftExpense the giftExpense to set
	 */
	public void setGiftExpense(Integer giftExpense) {
		this.giftExpense = giftExpense;
	}
	/**
	 * @return the talkExpense
	 */
	public Integer getTalkExpense() {
		return talkExpense;
	}
	/**
	 * @param talkExpense the talkExpense to set
	 */
	public void setTalkExpense(Integer talkExpense) {
		this.talkExpense = talkExpense;
	}
	/**
	 * @return the videoExpense
	 */
	public Integer getVideoExpense() {
		return videoExpense;
	}
	/**
	 * @param videoExpense the videoExpense to set
	 */
	public void setVideoExpense(Integer videoExpense) {
		this.videoExpense = videoExpense;
	}
	/**
	 * @return the danMuExpense
	 */
	public Integer getDanMuExpense() {
		return danMuExpense;
	}
	/**
	 * @param danMuExpense the danMuExpense to set
	 */
	public void setDanMuExpense(Integer danMuExpense) {
		this.danMuExpense = danMuExpense;
	}

	

	/**
	 * @return the allExpense
	 */
	public Integer getAllExpense() {
		return allExpense;
	}
	/**
	 * @param allExpense the allExpense to set
	 */
	public void setAllExpense(Integer allExpense) {
		this.allExpense = allExpense;
	}
	/**
	 * @return the allEarnings
	 */
	public Integer getAllEarnings() {
		return allEarnings;
	}
	/**
	 * @param allEarnings the allEarnings to set
	 */
	public void setAllEarnings(Integer allEarnings) {
		this.allEarnings = allEarnings;
	}

	/**
	 * @return the watchTime
	 */
	public String getWatchTime() {
		return watchTime;
	}
	/**
	 * @param watchTime the watchTime to set
	 */
	public void setWatchTime(String watchTime) {
		this.watchTime = watchTime;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the watchLiveSeconds
	 */
	public Integer getWatchLiveSeconds() {
		return watchLiveSeconds;
	}
	/**
	 * @param watchLiveSeconds the watchLiveSeconds to set
	 */
	public void setWatchLiveSeconds(Integer watchLiveSeconds) {
		this.watchLiveSeconds = watchLiveSeconds;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the moyouId
	 */
	public String getMoyouId() {
		return moyouId;
	}
	/**
	 * @param moyouId the moyouId to set
	 */
	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
