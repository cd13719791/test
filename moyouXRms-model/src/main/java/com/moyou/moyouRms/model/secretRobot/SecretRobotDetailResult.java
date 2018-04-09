package com.moyou.moyouRms.model.secretRobot;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月31日 下午5:07:49 类说明
 */
public class SecretRobotDetailResult extends BaseModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String secretTitle;
    private Integer imageTotal;
    private Integer commentTotal;
    private String extend_data;
    private Date createTime;
    private String firstImage;
    private String firstContent;
    private Date pushTime;
    private String nickname;
    private String avatar;
    private List<SecretRobotContent> secretContentList;

    public List<SecretRobotContent> getSecretContentList()
    {
        return secretContentList;
    }

    public void setSecretContentList(List<SecretRobotContent> secretContentList)
    {
        this.secretContentList = secretContentList;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getSecretTitle()
    {
        return secretTitle;
    }

    public void setSecretTitle(String secretTitle)
    {
        this.secretTitle = secretTitle;
    }

    public Integer getImageTotal()
    {
        return imageTotal;
    }

    public void setImageTotal(Integer imageTotal)
    {
        this.imageTotal = imageTotal;
    }

    public Integer getCommentTotal()
    {
        return commentTotal;
    }

    public void setCommentTotal(Integer commentTotal)
    {
        this.commentTotal = commentTotal;
    }

    public String getExtend_data()
    {
        return extend_data;
    }

    public void setExtend_data(String extend_data)
    {
        this.extend_data = extend_data;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getFirstImage()
    {
        return firstImage;
    }

    public void setFirstImage(String firstImage)
    {
        this.firstImage = firstImage;
    }

    public String getFirstContent()
    {
        return firstContent;
    }

    public void setFirstContent(String firstContent)
    {
        this.firstContent = firstContent;
    }

    public Date getPushTime()
    {
        return pushTime;
    }

    public void setPushTime(Date pushTime)
    {
        this.pushTime = pushTime;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

}
