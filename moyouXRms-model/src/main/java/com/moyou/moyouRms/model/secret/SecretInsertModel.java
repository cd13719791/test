package com.moyou.moyouRms.model.secret;

import java.util.ArrayList;
import java.util.Date;

/**
 * SecretInsertModel
 * 
 * @author PzC.
 * @time 2017年3月29日 上午11:41:06
 * 
 */
public class SecretInsertModel {
	private Integer secretId;// 秘密 id
	private Integer userId;// 用户 id
	private Integer anonymousAvatarId;// 匿名头像 id
	private String secretTitle;// 秘密标题
	private String firstContent;// 第一段文字
	private String firstImage;// 第一张图片
	private String location;// 位置信息，如 成都，德阳 等。不加 "市"
	private Integer imageTotal;// 图片总数
	private String extendData;// 第一张图片对应的附加数据
	private int gender;//性别
	private Date pushTime;//发送时间
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	private ArrayList<SecretContentInsertModel> contents;// 文字图片内容
		
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAnonymousAvatarId() {
		return anonymousAvatarId;
	}

	public void setAnonymousAvatarId(Integer anonymousAvatarId) {
		this.anonymousAvatarId = anonymousAvatarId;
	}

	public String getSecretTitle() {
		return secretTitle;
	}

	public void setSecretTitle(String secretTitle) {
		this.secretTitle = secretTitle;
	}

	public String getFirstContent() {
		return firstContent;
	}

	public void setFirstContent(String firstContent) {
		this.firstContent = firstContent;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getImageTotal() {
		return imageTotal;
	}

	public void setImageTotal(Integer imageTotal) {
		this.imageTotal = imageTotal;
	}

	public String getExtendData() {
		return extendData;
	}

	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	public ArrayList<SecretContentInsertModel> getContents() {
		return contents;
	}

	public void setContents(ArrayList<SecretContentInsertModel> contents) {
		this.contents = contents;
	}

	public Integer getSecretId() {
		return secretId;
	}

	public void setSecretId(Integer secretId) {
		this.secretId = secretId;
	}

}
