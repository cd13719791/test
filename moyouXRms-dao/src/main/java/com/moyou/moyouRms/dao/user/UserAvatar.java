package com.moyou.moyouRms.dao.user;

import java.io.Serializable;
import java.util.Date;

import com.moyou.moyouRms.util.UUIDUtil;

public class UserAvatar implements Serializable, Comparable<UserAvatar> {
	private static final long serialVersionUID = -820519179146317130L;
	// 主键id
	private String id = UUIDUtil.getUUID();
	// 用户id
	private String userId;
	// 头像地址
	private String url;
	// 头像排序
	private Integer orderno;
	private Date createTime;
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int compareTo(UserAvatar o) {
		return Integer.compare(this.orderno, o.orderno);
	}
	
	/**
	 * UserAvatar 对象初始化
	 * 
	 * @param userId
	 * @param url
	 * @param orderNo
	 * @return
	 */
	public static UserAvatar instanceOfUserAvatar(String userId, String url, Integer orderNo, Date now) {
		UserAvatar userAvatar = new UserAvatar();
		userAvatar.setUserId(userId);
		userAvatar.setUrl(url);
		userAvatar.setOrderno(orderNo);
		userAvatar.setCreateTime(now);
		userAvatar.setUpdateTime(now);
		return userAvatar;
	}
}