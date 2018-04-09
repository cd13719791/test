package com.moyou.moyouRms.model.gerrting;

import java.util.Date;
import java.util.List;

public class GreetingUserSendLog {
	private Integer id;
	private Integer userId;
	private String greetingAppellation;// 称呼
	private String greetingContent;// 内容
	private String greetingBackground;// 背景
	private String greetingInscribe;// 落款
	private Date createTime;
	private Date updateTime;
	private int state;
	private int greetingAbstractId;
	private String avatar;
	private Integer BlessingId;
   public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

private List<PicList>  greetingAbstract;
	public Date getUpdateTime() {
		return updateTime;
	}


	public List<PicList> getGreetingAbstract() {
		return greetingAbstract;
	}


	public void setGreetingAbstract(List<PicList> greetingAbstract) {
		this.greetingAbstract = greetingAbstract;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getGreetingAbstractId() {
		return greetingAbstractId;
	}

	public void setGreetingAbstractId(int greetingAbstractId) {
		this.greetingAbstractId = greetingAbstractId;
	}



	public Integer getBlessingId() {
		return BlessingId;
	}


	public void setBlessingId(Integer blessingId) {
		BlessingId = blessingId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGreetingBackground() {
		return greetingBackground;
	}

	public void setGreetingBackground(String greetingBackground) {
		this.greetingBackground = greetingBackground;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGreetingAppellation() {
		return greetingAppellation;
	}

	public void setGreetingAppellation(String greetingAppellation) {
		this.greetingAppellation = greetingAppellation;
	}

	public String getGreetingContent() {
		return greetingContent;
	}

	public void setGreetingContent(String greetingContent) {
		this.greetingContent = greetingContent;
	}

	public String getGreetingInscribe() {
		return greetingInscribe;
	}

	public void setGreetingInscribe(String greetingInscribe) {
		this.greetingInscribe = greetingInscribe;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
