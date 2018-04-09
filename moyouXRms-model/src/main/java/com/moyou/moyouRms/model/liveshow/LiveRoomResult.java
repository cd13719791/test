package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.util.DateTimeUtil;

public class LiveRoomResult extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7775792653449978328L;

	public LiveRoomResult(String date, String time, String onlineTime, Integer sumEarnings) {
		super();
		this.date = date;
		this.time = time;
		this.onlineTime = onlineTime;
		this.sumEarnings = sumEarnings;
	}

	public LiveRoomResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LiveRoomResult(LiveRoom liveRoom) {
		super();
		this.date = DateTimeUtil.formatDate(liveRoom.getCreateTime(), "yyyy-MM-dd");
		this.time = DateTimeUtil.formatDate(liveRoom.getCreateTime(), "HH:mm:ss");
		this.onlineTime = liveRoom.getOnlineTime();
	}

	private String date;// 日期
	private String time;// 日期
	private String onlineTime;// 在线时长
	private Integer sumEarnings;// 总获得金币数量
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getSumEarnings() {
		return sumEarnings;
	}

	public void setSumEarnings(Integer sumEarnings) {
		this.sumEarnings = sumEarnings;
	}
}

/**
 * @author created by Chenxv
 * @date 2017年9月6日 下午4:01:57
 */
