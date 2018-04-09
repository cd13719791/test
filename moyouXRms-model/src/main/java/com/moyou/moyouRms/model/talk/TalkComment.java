package com.moyou.moyouRms.model.talk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class TalkComment extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer talkId;
	private Integer userId;
	private String content;
	private Integer parentId;
	private int state;// 0 不可见 1可见
	private Date createTime;
	private String avatar;
	private String nickname;
	private Integer sex;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	private List<TalkComment> talkCommentChildList = new ArrayList<TalkComment>();// 说说评论子评论

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<TalkComment> getTalkCommentChildList() {
		return talkCommentChildList;
	}

	public void setTalkCommentChildList(List<TalkComment> talkCommentChildList) {
		this.talkCommentChildList = talkCommentChildList;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
		this.content = content;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
