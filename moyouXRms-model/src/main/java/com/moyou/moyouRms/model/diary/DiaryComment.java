package com.moyou.moyouRms.model.diary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:t_diary_comment表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-02-28
 */
public class DiaryComment {
	/**
	 * 评论 状态 0删除 1正常
	 */
	public static final short DIARY_COMMENT_DELETE_NUMBER = 0;
	public static final short DIARY_COMMENT_NORMAL_NUMBER = 1;
	private Integer sex;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
     * 
     */
	private Integer id;

	/**
	 * 日记 id
	 */
	private Integer diaryId;

	/**
	 * 评论者用户id
	 */
	private Integer userId;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 父节点Id
	 */
	private Integer parentId;

	/**
	 * 0 已删除 1未删除
	 */
	private Integer state;

	/**
     * 
     */
	private Date createTime;

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 日记 id
	 * 
	 * @return diary_id 日记 id
	 */
	public Integer getDiaryId() {
		return diaryId;
	}

	/**
	 * 日记 id
	 * 
	 * @param diaryId
	 *            日记 id
	 */
	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	/**
	 * 评论者用户id
	 * 
	 * @return user_id 评论者用户id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 评论者用户id
	 * 
	 * @param userId
	 *            评论者用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 评论内容
	 * 
	 * @return content 评论内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 评论内容
	 * 
	 * @param content
	 *            评论内容
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	/**
	 * 父节点Id
	 * 
	 * @return parent_id 父节点Id
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 父节点Id
	 * 
	 * @param parentId
	 *            父节点Id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 0 已删除 1未删除
	 * 
	 * @return state 0 已删除 1未删除
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 0 已删除 1未删除
	 * 
	 * @param state
	 *            0 已删除 1未删除
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 子评论
	 */
	private List<DiaryComment> childComment = new ArrayList<DiaryComment>();

	public List<DiaryComment> getChildComment() {
		return childComment;
	}

	public void setChildComment(List<DiaryComment> childComment) {
		this.childComment = childComment;
	}

	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String avatar;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}