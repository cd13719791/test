package com.moyou.moyouRms.dao.user;

import java.io.Serializable;

public class UserAvatarReturn implements Serializable{
	private static final long serialVersionUID = 9065944150830000449L;
	// 头像地址
	private String url;
	// 头像排序
	private Integer orderno;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

}
