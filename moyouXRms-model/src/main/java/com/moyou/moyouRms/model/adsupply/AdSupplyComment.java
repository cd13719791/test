package com.moyou.moyouRms.model.adsupply;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:ad_supply_comment表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-07-18
 */
public class AdSupplyComment extends BaseModel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // '0 已删除 1未删除'
    public static final Short STATE_OK = 1;
    public static final Short STATE_DELETE = 0;
    private String avatar;
    private String nickName;

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 供求 id
     */
    private Integer supplyId;

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
    private Short state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     * @return id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 供求 id
     * 
     * @return supply_id 供求 id
     */
    public Integer getSupplyId()
    {
        return supplyId;
    }

    /**
     * 供求 id
     * 
     * @param supplyId 供求 id
     */
    public void setSupplyId(Integer supplyId)
    {
        this.supplyId = supplyId;
    }

    /**
     * 评论者用户id
     * 
     * @return user_id 评论者用户id
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * 评论者用户id
     * 
     * @param userId 评论者用户id
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     * 评论内容
     * 
     * @return content 评论内容
     */
    public String getContent()
    {
        return content;
    }

    /**
     * 评论内容
     * 
     * @param content 评论内容
     */
    public void setContent(String content)
    {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 父节点Id
     * 
     * @return parent_id 父节点Id
     */
    public Integer getParentId()
    {
        return parentId;
    }

    /**
     * 父节点Id
     * 
     * @param parentId 父节点Id
     */
    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    /**
     * 0 已删除 1未删除
     * 
     * @return state 0 已删除 1未删除
     */
    public Short getState()
    {
        return state;
    }

    /**
     * 0 已删除 1未删除
     * 
     * @param state 0 已删除 1未删除
     */
    public void setState(Short state)
    {
        this.state = state;
    }

    /**
     * 创建时间
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 创建时间
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
