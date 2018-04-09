package com.moyou.moyouRms.model.talk;

import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class TalkAndUserInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 当前页 */
	private int pageNumber;
	/* 每页显示条目数 */
	private int pageSize;
	/**
	 * 总条数
	 */
	private int total;
	private List<?> results;
	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	// 昵称
	private String nickname;
	// 发布时间 开始时间
	private String starttime;
	// 结束时间
	private String endtime;
	private int relateTalkId;//转发说说的说说 id;
	public int getRelateTalkId() {
		return relateTalkId;
	}

	public void setRelateTalkId(int relateTalkId) {
		this.relateTalkId = relateTalkId;
	}

	/*
	 * 资源文件格式： 1.音频 2.视频 3.文件
	 */
	private Integer mediaType;
	// 点赞量开始
	private Integer startSupport;
	// 点赞量结束
	private Integer endSupport;
private String city;
	public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

	private String address;// 发布说说的地址

	private Integer startcomment;// 评论开始

	private Integer endcomment;// 评论结束
	private String content;// 说说正文
    private Integer type;//用户类型
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getStartSupport() {
		return startSupport;
	}

	public void setStartSupport(Integer startSupport) {
		this.startSupport = startSupport;
	}

	public Integer getEndSupport() {
		return endSupport;
	}

	public void setEndSupport(Integer endSupport) {
		this.endSupport = endSupport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStartcomment() {
		return startcomment;
	}

	public void setStartcomment(Integer startcomment) {
		this.startcomment = startcomment;
	}

	public Integer getEndcomment() {
		return endcomment;
	}

	public void setEndcomment(Integer endcomment) {
		this.endcomment = endcomment;
	}

}
