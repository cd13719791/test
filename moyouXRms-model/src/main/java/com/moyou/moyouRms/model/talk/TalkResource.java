package com.moyou.moyouRms.model.talk;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class TalkResource extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer talkId;//说说的id
	private Integer mediaType;//资源文件格式：1.音频2.视频3.图片
	private String url;
	private Integer userId;
	private Integer picOrder;//图片的排序
	private Date createTime;//创建的时间
	private String coutUrl;//说说的总url数
private Integer mediaTypes;
	public Integer getMediaTypes() {
	return mediaTypes;
}
public void setMediaTypes(Integer mediaTypes) {
	this.mediaTypes = mediaTypes;
}
	public String getCoutUrl() {
		return coutUrl;
	}
	public void setCoutUrl(String coutUrl) {
		this.coutUrl = coutUrl;
	}
	public Integer getTalkId() {
		return talkId;
	}
	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPicOrder() {
		return picOrder;
	}
	public void setPicOrder(Integer picOrder) {
		this.picOrder = picOrder;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
