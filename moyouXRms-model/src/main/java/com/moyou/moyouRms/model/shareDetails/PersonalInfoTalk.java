package com.moyou.moyouRms.model.shareDetails;

import java.util.Date;
import java.util.List;


/**
 * @author PzC.
 * @time 2017年1月13日 下午3:46:53
 * 
 */
public class PersonalInfoTalk {
	private Integer talkId;// 说说的id
	private String city;// 城市
	private String content;// 内容
	private Date createTime;// 创建的时间
	private Integer commentTotal;// 评论统计
	private Integer rewardTotal;// 打赏统计
	private Integer supportTotal;// 点赞统计
	private Double longitude;// 经度
	private Double latitude;// 纬度
	private String distance;
	private String fromNow;
	private Integer mediaType;// 媒体类型 2.视频3.图片
	private List<ResourcePic> resourcePics;// 图片
	private Boolean supportState;
	private Integer relateTalkId;// 转发说说原说说 id , 如果不是转发说说值为 -1 
	private Integer relateState;// 转发说说状态 0.正常显示 1.原内容已删除 -1.不是转发说说
	private Integer relateUserId;// 发布原说说的用户 id，如果不是转发说说值为 -1 
	private String relateNickname;// 发布原说说的用户昵称

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCommentTotal() {
		return commentTotal;
	}

	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}

	public Integer getRewardTotal() {
		return rewardTotal;
	}

	public void setRewardTotal(Integer rewardTotal) {
		this.rewardTotal = rewardTotal;
	}

	public Integer getSupportTotal() {
		return supportTotal;
	}

	public void setSupportTotal(Integer supportTotal) {
		this.supportTotal = supportTotal;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getFromNow() {
		return fromNow;
	}

	public void setFromNow(String fromNow) {
		this.fromNow = fromNow;
	}

	public Boolean getSupportState() {
		return supportState == null ? false : supportState;
	}

	public void setSupportState(Boolean supportState) {
		this.supportState = supportState;
	}

	public List<ResourcePic> getResourcePics() {
		return resourcePics;
	}

	public void setResourcePics(List<ResourcePic> resourcePics) {
		this.resourcePics = resourcePics;
	}

	public Integer getRelateTalkId() {
		return relateTalkId;
	}

	public void setRelateTalkId(Integer relateTalkId) {
		this.relateTalkId = relateTalkId;
	}

	public Integer getRelateState() {
		return relateState;
	}

	public void setRelateState(Integer relateState) {
		this.relateState = relateState;
	}

	public Integer getRelateUserId() {
		return relateUserId;
	}

	public void setRelateUserId(Integer relateUserId) {
		this.relateUserId = relateUserId;
	}

	public String getRelateNickname() {
		return relateNickname;
	}

	public void setRelateNickname(String relateNickname) {
		this.relateNickname = relateNickname;
	}

	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

}
