package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;

public class LiveFakeRoom extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private Integer userId;// 用户id
	private String roomId;// 房间id
	private String roomName;// 房间名称
	private Integer roomCount;// 房间人数
	private String roomDesc;// 房间描述
	private Integer consume;// 房间消费
	private String cover;// 房间封面图
	private Integer liveSeconds;// 直播时长 单位秒
	private String city;// 创建房间的城市
	private String password;// 房间密码
	private Integer liveState;// 直播状态 0空闲1进行中
	private Integer roomState;// 房间状态0不可用 1可用
	private java.util.Date updateTime;// 修改时间
	private java.util.Date createTime;// 创建时间
	private java.util.Date prevLiveStartTime;// 上次启动时间
	private java.util.Date prevLiveEndTime;// 上次结束时间
	private java.util.Date recommendTme;// 推荐时间
	private Integer recommendState;// 0未推荐1推荐
	private Integer recommendSort;// 推荐排序
	private String bizRoomId;// 关联第三方房间id
	public static int RECOMMEND_STATE_OK = 1;
	public static int RECOMMEND_STATE_NO = 0;

	public LiveFakeRoom() {
		super();
	}

	public LiveFakeRoom(Integer id, Integer userId, String roomId, String roomName,
			Integer roomCount, String roomDesc, Integer consume, String cover, Integer liveSeconds,
			String city, String password, Integer liveState, Integer roomState,
			java.util.Date updateTime, java.util.Date createTime, java.util.Date prevLiveStartTime,
			java.util.Date prevLiveEndTime, java.util.Date recommendTme, Integer recommendState,
			Integer recommendSort, String bizRoomId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomCount = roomCount;
		this.roomDesc = roomDesc;
		this.consume = consume;
		this.cover = cover;
		this.liveSeconds = liveSeconds;
		this.city = city;
		this.password = password;
		this.liveState = liveState;
		this.roomState = roomState;
		this.updateTime = updateTime;
		this.createTime = createTime;
		this.prevLiveStartTime = prevLiveStartTime;
		this.prevLiveEndTime = prevLiveEndTime;
		this.recommendTme = recommendTme;
		this.recommendState = recommendState;
		this.recommendSort = recommendSort;
		this.bizRoomId = bizRoomId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getRoomCount() {
		return this.roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public String getRoomDesc() {
		return this.roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public Integer getConsume() {
		return this.consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getLiveSeconds() {
		return this.liveSeconds;
	}

	public void setLiveSeconds(Integer liveSeconds) {
		this.liveSeconds = liveSeconds;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLiveState() {
		return this.liveState;
	}

	public void setLiveState(Integer liveState) {
		this.liveState = liveState;
	}

	public Integer getRoomState() {
		return this.roomState;
	}

	public void setRoomState(Integer roomState) {
		this.roomState = roomState;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getPrevLiveStartTime() {
		return this.prevLiveStartTime;
	}

	public void setPrevLiveStartTime(java.util.Date prevLiveStartTime) {
		this.prevLiveStartTime = prevLiveStartTime;
	}

	public java.util.Date getPrevLiveEndTime() {
		return this.prevLiveEndTime;
	}

	public void setPrevLiveEndTime(java.util.Date prevLiveEndTime) {
		this.prevLiveEndTime = prevLiveEndTime;
	}

	public java.util.Date getRecommendTme() {
		return this.recommendTme;
	}

	public void setRecommendTme(java.util.Date recommendTme) {
		this.recommendTme = recommendTme;
	}

	public Integer getRecommendState() {
		return this.recommendState;
	}

	public void setRecommendState(Integer recommendState) {
		this.recommendState = recommendState;
	}

	public Integer getRecommendSort() {
		return this.recommendSort;
	}

	public void setRecommendSort(Integer recommendSort) {
		this.recommendSort = recommendSort;
	}

	public String getBizRoomId() {
		return this.bizRoomId;
	}

	public void setBizRoomId(String bizRoomId) {
		this.bizRoomId = bizRoomId;
	}

}
