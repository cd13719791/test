package com.moyou.moyouRms.model.secret;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.user.UserInfo;


/**
 * 描述:t_secret表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-04-05
 */
public class Secret implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer DELETE=0;//删除
	public static final Integer NORMAL=1;//正常
	List<SecretContent> secretContentList;//内容集合
	List<SecretComment> secretCommentList;//评论集合
	private int readTotal;
	private int type;
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<SecretContent> getSecretContentList() {
		return secretContentList;
	}

	public void setSecretContentList(List<SecretContent> secretContentList) {
		this.secretContentList = secretContentList;
	}

	public List<SecretComment> getSecretCommentList() {
		return secretCommentList;
	}

	public void setSecretCommentList(List<SecretComment> secretCommentList) {
		this.secretCommentList = secretCommentList;
	}

	private String nickName;
	private String avatar;
	private UserInfo createrUser;//作者
	

	public UserInfo getCreaterUser() {
		return createrUser;
	}

	public void setCreaterUser(UserInfo createrUser) {
		this.createrUser = createrUser;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
     * 
     */
    private Integer id;

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 匿名头像 id
     */
    private Integer anonymousAvatarId;

    /**
     * 秘密标题
     */
    private String secretTitle;

    /**
     * 第一段文字
     */
    private String firstContent;

    /**
     * 第一张图片
     */
    private String firstImage;

    /**
     * 位置信息，如 成都，德阳 等。不加 "市"
     */
    private String location;

    /**
     * 图片总数
     */
    private Integer imageTotal;

    /**
     * 评论总数
     */
    private Integer commentTotal;

    /**
     * 第一张图片对应的附加数据
     */
    private String extendData;

    /**
     * 0 删除  1正常
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
     * 用户 id
     * @return user_id 用户 id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户 id
     * @param userId 用户 id
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
     * 秘密标题
     * @return secret_title 秘密标题
     */
    public String getSecretTitle() {
        return secretTitle;
    }

    /**
     * 秘密标题
     * @param secretTitle 秘密标题
     */
    public void setSecretTitle(String secretTitle) {
        this.secretTitle = secretTitle == null ? null : secretTitle.trim();
    }

    /**
     * 第一段文字
     * @return first_content 第一段文字
     */
    public String getFirstContent() {
        return firstContent;
    }

    /**
     * 第一段文字
     * @param firstContent 第一段文字
     */
    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent == null ? null : firstContent.trim();
    }

    /**
     * 第一张图片
     * @return first_image 第一张图片
     */
    public String getFirstImage() {
        return firstImage;
    }

    /**
     * 第一张图片
     * @param firstImage 第一张图片
     */
    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage == null ? null : firstImage.trim();
    }

    /**
     * 位置信息，如 成都，德阳 等。不加 "市"
     * @return location 位置信息，如 成都，德阳 等。不加 "市"
     */
    public String getLocation() {
        return location;
    }

    /**
     * 位置信息，如 成都，德阳 等。不加 "市"
     * @param location 位置信息，如 成都，德阳 等。不加 "市"
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 图片总数
     * @return image_total 图片总数
     */
    public Integer getImageTotal() {
        return imageTotal;
    }

    /**
     * 图片总数
     * @param imageTotal 图片总数
     */
    public void setImageTotal(Integer imageTotal) {
        this.imageTotal = imageTotal;
    }

    /**
     * 评论总数
     * @return comment_total 评论总数
     */
    public Integer getCommentTotal() {
        return commentTotal;
    }

    /**
     * 评论总数
     * @param commentTotal 评论总数
     */
    public void setCommentTotal(Integer commentTotal) {
        this.commentTotal = commentTotal;
    }

    /**
     * 第一张图片对应的附加数据
     * @return extend_data 第一张图片对应的附加数据
     */
    public String getExtendData() {
        return extendData;
    }

    /**
     * 第一张图片对应的附加数据
     * @param extendData 第一张图片对应的附加数据
     */
    public void setExtendData(String extendData) {
        this.extendData = extendData == null ? null : extendData.trim();
    }

    /**
     * 0 删除  1正常
     * @return state 0 删除  1正常
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0 删除  1正常
     * @param state 0 删除  1正常
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

	public int getReadTotal() {
		return readTotal;
	}

	public void setReadTotal(int readTotal) {
		this.readTotal = readTotal;
	}
}