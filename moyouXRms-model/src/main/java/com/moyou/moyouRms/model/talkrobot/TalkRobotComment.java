package com.moyou.moyouRms.model.talkrobot;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class TalkRobotComment extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2592235328408780329L;
	public static final String TALK_ROBOT_COMMENT_IN_REDIS_REPEAT="TALK_ROBOT_COMMENT_IN_REDIS_REPEAT";
	public static final Integer TALK_ROBOT_COMMENT_24_HOURS=24;
	private Integer id;

	private Integer talkId;

	private Integer userId;

	private String content;

	private Integer parentId;

	private Short state;

	private Date createTime;
	private int commentcount;//使用次数
	private int countcomment;//评论总数
	
	public int getCountcomment() {
		return countcomment;
	}

	public void setCountcomment(int countcomment) {
		this.countcomment = countcomment;
	}

	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}