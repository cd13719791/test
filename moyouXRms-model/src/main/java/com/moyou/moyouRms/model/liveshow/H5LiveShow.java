package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author created by Chenxv
 * @date 2017年9月28日 下午2:31:06
 */
public class H5LiveShow extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moyouId;
	private String avatar;
	private String roomName;
	private String cover;

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}
