package com.moyou.moyouRms.model.version;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class Version extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 257536660307559551L;
	private Integer id;
	private String versionName;// 版本号
	private String downloadUrl;// app下载地址
	private Boolean phoneRegOn;// 0关1开，版本手机号注册开关
	private Integer forceUpdateState;// 强制更新 0 不强制更新 1 强制更新
	private String content;// 版本描述信息
	private Date createTime;
	private int publish;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublish() {
	return publish;
}

public void setPublish(int publish) {
	this.publish = publish;
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName == null ? null : versionName.trim();
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
	}

	public Boolean getPhoneRegOn() {
		return phoneRegOn;
	}

	public void setPhoneRegOn(Boolean phoneRegOn) {
		this.phoneRegOn = phoneRegOn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getForceUpdateState() {
		return forceUpdateState;
	}

	public void setForceUpdateState(Integer forceUpdateState) {
		this.forceUpdateState = forceUpdateState;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
