package com.moyou.moyouRms.model.feedback;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;

public class Feedback extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer userId;

	private String content;

	private Short formApp;

	private String phone;
	private List<MsgSystemX> msgSystemXList;
	public static Integer DOING = 2;// 已处理

	public List<MsgSystemX> getMsgSystemXList() {
		return msgSystemXList;
	}

	public void setMsgSystemXList(List<MsgSystemX> msgSystemXList) {
		this.msgSystemXList = msgSystemXList;
	}

	private String msgContent;

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	private String email;
	private int sendUserId;

	public int getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(int sendUserId) {
		this.sendUserId = sendUserId;
	}

	private String moyouVersion;
	private String account;
	private String avatar;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private int state;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private Date createTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Short getFormApp() {
		return formApp;
	}

	public void setFormApp(Short formApp) {
		this.formApp = formApp;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getMoyouVersion() {
		return moyouVersion;
	}

	public void setMoyouVersion(String moyouVersion) {
		this.moyouVersion = moyouVersion == null ? null : moyouVersion.trim();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}