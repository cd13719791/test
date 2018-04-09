package com.moyou.moyouRms.model.diaryRobot;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;

/**
 * 机器人详情返回实体
 * 
 * @author 作者:陈旭
 * @version 创建时间：2017年7月31日 下午4:54:51 类说明
 */
public class DiaryRobotDetailResult extends BaseModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String diaryTitle;
    private Date createTime;
    private String searchContent;
    private String avatar;
    private String nickname;
    private List<DiaryContentRobot> diaryContentRobotList;

    public List<DiaryContentRobot> getList()
    {
        return diaryContentRobotList;
    }

    public void setList(List<DiaryContentRobot> diaryContentRobotList)
    {
        this.diaryContentRobotList = diaryContentRobotList;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDiaryTitle()
    {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle)
    {
        this.diaryTitle = diaryTitle;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getSearchContent()
    {
        return searchContent;
    }

    public void setSearchContent(String searchContent)
    {
        this.searchContent = searchContent;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
}
