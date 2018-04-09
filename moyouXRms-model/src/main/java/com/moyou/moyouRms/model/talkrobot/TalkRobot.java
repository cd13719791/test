package com.moyou.moyouRms.model.talkrobot;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.report.CommonResource;

/**
 * 描述:t_talk_robot表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-03-06
 */
public class TalkRobot {
	public static final Integer PUBLISH=1;//'发布状态 0=未发布，1=发布',
	public static final Integer UNPUBLISH=0;//'发布状态 0=未发布，1=发布',
			
	private Integer mediaType;//资源文件类型
	private Integer yesterday;//昨天发布的数量
	public Integer getYesterday() {
		return yesterday;
	}

	public void setYesterday(Integer yesterday) {
		this.yesterday = yesterday;
	}

	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * 性别   1=男  0=女
	 */
	private int sex;
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * 发布时间
	 */
	private Date publish;

	public Date getPublish() {
		return publish;
	}

	public void setPublish(Date publish) {
		this.publish = publish;
	}

	/**
	 * 发布状态
	 */
	private int releaseStatus;
	public int getReleaseStatus() {
		return releaseStatus;
	}
	
	public void setReleaseStatus(int releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 昵称
	 */
	private String nickname;
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

	/**
     * 主键自增
     */
    private Integer id;

    /**
     * 正文
     */
    private String content;

    /**
     * 地址
     */
    private String address;

    /**
     * 发布位置经度
     */
    private Double longitude;

    /**
     * 发布位置纬度
     */
    private Double latitude;

    /**
     * 创建者Id
     */
    private Integer creatorId;

    /**
     * 阅读量统计
     */
    private Integer readTotal;

    /**
     * 评论总数
     */
    private Integer commentTotal;

    /**
     * 点赞统计统计
     */
    private Integer supportTotal;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 0正常1删除
     */
    private Integer state;

    /**
     * 打赏次数统计
     */
    private Integer rewardTotal;

    /**
     * 转发说说的说说 id
     */
    private Integer relateTalkId;

    /**
     * 转发说说的状态 0正常 1删除
     */
    private Integer relateState;

    /**
     * 城市，如成都
     */
    private String city;

    /**
     * 主键自增
     * @return id 主键自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键自增
     * @param id 主键自增
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 正文
     * @return content 正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 正文
     * @param content 正文
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 发布位置经度
     * @return longitude 发布位置经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 发布位置经度
     * @param longitude 发布位置经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 发布位置纬度
     * @return latitude 发布位置纬度
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 发布位置纬度
     * @param latitude 发布位置纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 创建者Id
     * @return creator_id 创建者Id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 创建者Id
     * @param creatorId 创建者Id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 阅读量统计
     * @return read_total 阅读量统计
     */
    public Integer getReadTotal() {
        return readTotal;
    }

    /**
     * 阅读量统计
     * @param readTotal 阅读量统计
     */
    public void setReadTotal(Integer readTotal) {
        this.readTotal = readTotal;
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
     * 点赞统计统计
     * @return support_total 点赞统计统计
     */
    public Integer getSupportTotal() {
        return supportTotal;
    }

    /**
     * 点赞统计统计
     * @param supportTotal 点赞统计统计
     */
    public void setSupportTotal(Integer supportTotal) {
        this.supportTotal = supportTotal;
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
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 0正常1删除
     * @return state 0正常1删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0正常1删除
     * @param state 0正常1删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 打赏次数统计
     * @return reward_total 打赏次数统计
     */
    public Integer getRewardTotal() {
        return rewardTotal;
    }

    /**
     * 打赏次数统计
     * @param rewardTotal 打赏次数统计
     */
    public void setRewardTotal(Integer rewardTotal) {
        this.rewardTotal = rewardTotal;
    }

    /**
     * 转发说说的说说 id
     * @return relate_talk_id 转发说说的说说 id
     */
    public Integer getRelateTalkId() {
        return relateTalkId;
    }

    /**
     * 转发说说的说说 id
     * @param relateTalkId 转发说说的说说 id
     */
    public void setRelateTalkId(Integer relateTalkId) {
        this.relateTalkId = relateTalkId;
    }

    /**
     * 转发说说的状态 0正常 1删除
     * @return relate_state 转发说说的状态 0正常 1删除
     */
    public Integer getRelateState() {
        return relateState;
    }

    /**
     * 转发说说的状态 0正常 1删除
     * @param relateState 转发说说的状态 0正常 1删除
     */
    public void setRelateState(Integer relateState) {
        this.relateState = relateState;
    }

    /**
     * 城市，如成都
     * @return city 城市，如成都
     */
    public String getCity() {
        return city;
    }

    /**
     * 城市，如成都
     * @param city 城市，如成都
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
    /**
     * 图片资源
     */
    private List<CommonResource> resources;
	public List<CommonResource> getResources() {
		return resources;
	}

	public void setResources(List<CommonResource> resources) {
		this.resources = resources;
	}
}