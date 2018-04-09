package com.moyou.moyouRms.model.liveshow;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.util.DateTimeUtil;

public class LiveRoom extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int RECOMMENT_NUMBER = 20;   //最大推荐数
	public static final int DEFAULT_SORT = 99;       //默认推荐排序
	public static final int STATE_RECOMMENT_YES = 1; //推荐
	public static final Integer STATE_RECOMMENT_NO = 0; //未推荐
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
	private Integer recommendState;// 0未推荐1推荐
	private Integer recommendSort;// 推荐排序
	private String bizRoomId;// 关联第三方房间id
	private String bizRoomKey;// 关联第三方房间key',
	private String date;
	private String time;
	private String onlineTime;
	private Date recommendTime;// 推荐时间
	private String moyouId;// moyouid
	private String nickname;// 主播昵称
	private Integer liveGrade;// 等级
	private Integer allEarnings;// 总消费

	public Integer getAllEarnings() {
		return allEarnings;
	}

	public void setAllEarnings(Integer allEarnings) {
		this.allEarnings = allEarnings;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getLiveGrade() {
		return liveGrade;
	}

	public void setLiveGrade(Integer liveGrade) {
		this.liveGrade = liveGrade;
	}

	public String getBizRoomKey() {
		return bizRoomKey;
	}

	public void setBizRoomKey(String bizRoomKey) {
		this.bizRoomKey = bizRoomKey;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LiveRoom() {
		super();
	}

	public LiveRoom(Integer id, Integer recommendState, Integer recommendSort, Date time) {
		super();
		this.id = id;
		this.recommendState = recommendState;
		this.recommendSort = recommendSort;
		this.recommendTime = time;
	}

	public LiveRoom(Integer id, Integer userId, String roomId, String roomName, Integer roomCount,
			String roomDesc, Integer consume, String cover, Integer liveSeconds, String city,
			String password, Integer liveState, Integer roomState, java.util.Date updateTime,
			java.util.Date createTime, Integer recommendState, Integer recommendSort,
			String bizRoomId) {
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

	public static LiveRoomResult instansToResult(LiveRoom liveRoom) {
		LiveRoomResult liveRoomResult = new LiveRoomResult();
		liveRoomResult.setDate(DateTimeUtil.formatDate(liveRoom.getCreateTime(), "yyyy-MM-dd"));
		liveRoomResult.setTime(DateTimeUtil.formatDate(liveRoom.getCreateTime(), "HH:mm:ss"));
		liveRoomResult.setOnlineTime(DateTimeUtil.formartSeccentToDateTime(liveRoom
				.getLiveSeconds()));
		liveRoomResult.setId(liveRoom.getId());
		return liveRoomResult;
	}
}
