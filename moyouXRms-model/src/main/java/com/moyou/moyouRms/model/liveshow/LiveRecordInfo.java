package com.moyou.moyouRms.model.liveshow;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;
/**
 * 
 * @author Administrator
 *  开播记录
 */
public class LiveRecordInfo extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; 			//主键
	private String roomName; 		//直播间昵称
	private String bizRoomId; 		//第三方视频号
	private String anchiorName;		//主播昵称  nickname
	private Integer anchorId;		//主播id=moyouid
    private List<UserRecordListResult> userRecordList;//观众记录
    private Integer liveEarnings;	//直播收益金币
    private String onlineTime;		//直播时长
    private Date startTime;			//开播时间
    private Date endTime;			//结束时间
    private Integer liveSeconds;
    private Integer watchLiveSeconds;//观看时长
    /**
	 * @return the liveSeconds  直播时长
	 */
	public Integer getLiveSeconds() {
		return liveSeconds;
	}
	/**
	 * @param liveSeconds the liveSeconds to set
	 */
	public void setLiveSeconds(Integer liveSeconds) {
		this.liveSeconds = liveSeconds;
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
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	/**
	 * @return the anchiorName
	 */
	public String getAnchiorName() {
		return anchiorName;
	}
	/**
	 * @param anchiorName the anchiorName to set
	 */
	public void setAnchiorName(String anchiorName) {
		this.anchiorName = anchiorName;
	}
	/**
	 * @return the anchorId
	 */
	public Integer getAnchorId() {
		return anchorId;
	}
	/**
	 * @param anchorId the anchorId to set
	 */
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}

	/**
	 * @return the liveEarnings
	 */

	/**
	 * @return the userRecordList
	 */
	public List<UserRecordListResult> getUserRecordList() {
		return userRecordList;
	}
	/**
	 * @param userRecordList the userRecordList to set
	 */
	public void setUserRecordList(List<UserRecordListResult> userRecordList) {
		this.userRecordList = userRecordList;
	}
	/**
	 * @return the onlineTime
	 */
	public String getOnlineTime() {
		return onlineTime;
	}
	/**
	 * @return the liveEarnings
	 */
	public Integer getLiveEarnings() {
		return liveEarnings;
	}
	/**
	 * @param liveEarnings the liveEarnings to set
	 */
	public void setLiveEarnings(Integer liveEarnings) {
		this.liveEarnings = liveEarnings;
	}
	/**
	 * @param onlineTime the onlineTime to set
	 */
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	public LiveRecordInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the bizRoomId
	 */
	public String getBizRoomId() {
		return bizRoomId;
	}
	/**
	 * @param bizRoomId the bizRoomId to set
	 */
	public void setBizRoomId(String bizRoomId) {
		this.bizRoomId = bizRoomId;
	}
	public LiveRecordInfo(Integer id, String roomName, String bizRoomId, String anchiorName,
			Integer anchorId, List<UserRecordListResult> userRecordList, Integer liveEarnings,
			String onlineTime, Date startTime, Date endTime, Integer liveSeconds,
			Integer watchLiveSeconds) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.bizRoomId = bizRoomId;
		this.anchiorName = anchiorName;
		this.anchorId = anchorId;
		this.userRecordList = userRecordList;
		this.liveEarnings = liveEarnings;
		this.onlineTime = onlineTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.liveSeconds = liveSeconds;
		this.watchLiveSeconds = watchLiveSeconds;
	}


	

	
    
}
