package com.moyou.moyouRms.model.shareDetails;

import java.io.Serializable;

public class ResourcePic implements Serializable {
	private static final long serialVersionUID = 5504745429984016272L;
	private Integer picOrder;//排序
	private String url;
	private Integer mediaType;//资源文件格式：1.音频2.视频3.图片
	
	public Integer getMediaType() {
		return mediaType;
	}
	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}
	public Integer getPicOrder() {
		return picOrder;
	}
	public void setPicOrder(Integer picOrder) {
		this.picOrder = picOrder;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
