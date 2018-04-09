package com.moyou.moyouRms.model.secret;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述:t_secret_comment表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-05
 */
public class SecretComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String avatar;
	private String nickname;
	public static final Integer DELETE=0;//删除
	public static final Integer NORMAL=1;//正常
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<SecretComment> getSecretCommentList() {
		return secretCommentList;
	}

	public void setSecretCommentList(List<SecretComment> secretCommentList) {
		this.secretCommentList = secretCommentList;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 子级评论集合
	 */
	private List<SecretComment> secretCommentList;
	/**
	 * 父级评论id
	 */
	private Integer parentId;
    /**
     * 
     */
    private Integer id;

    /**
     * 秘密 id
     */
    private Integer secretId;

    /**
     * 评论者用户id
     */
    private Integer userId;

    /**
     * 匿名头像 id
     */
    private Integer anonymousAvatarId;

    /**
     * 评论内容
     */
    private String content;

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
     * 秘密 id
     * @return secret_id 秘密 id
     */
    public Integer getSecretId() {
        return secretId;
    }

    /**
     * 秘密 id
     * @param secretId 秘密 id
     */
    public void setSecretId(Integer secretId) {
        this.secretId = secretId;
    }

    /**
     * 评论者用户id
     * @return user_id 评论者用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 评论者用户id
     * @param userId 评论者用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 匿名头像 id
     * @return anonymous_avatar_id 匿名头像 id
     */
    public Integer getAnonymousAvatarId() {
        return anonymousAvatarId;
    }

    /**
     * 匿名头像 id
     * @param anonymousAvatarId 匿名头像 id
     */
    public void setAnonymousAvatarId(Integer anonymousAvatarId) {
        this.anonymousAvatarId = anonymousAvatarId;
    }

    /**
     * 评论内容
     * @return content 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 0 已删除 1未删除
     * @return state 0 已删除 1未删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0 已删除 1未删除
     * @param state 0 已删除 1未删除
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
}