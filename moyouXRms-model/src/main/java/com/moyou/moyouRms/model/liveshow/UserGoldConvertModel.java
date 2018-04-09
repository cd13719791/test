package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;

public class UserGoldConvertModel extends BaseModel {
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer DEFAULT_STATE = 0;
	private String moyouId;
	private Integer goldNum;
	private Integer money;
	private String weixin;
	private String nickname;
	private Integer toDayGet;// 今日已经兑换多少金币

	public Integer getToDayGet() {
		return toDayGet;
	}

	public void setToDayGet(Integer toDayGet) {
		this.toDayGet = toDayGet;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public Integer getGoldNum() {
		return goldNum;
	}

	public void setGoldNum(Integer goldNum) {
		this.goldNum = goldNum;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
}

/**
 * @author created by Chenxv
 * @date 2017年9月12日 下午4:55:58
 */
