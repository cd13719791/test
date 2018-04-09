package com.moyou.moyouRms.model.diaryRobot;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;

/**
 * 描述:t_diary_robot表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-03-28
 */
public class DiaryRobot extends BaseModel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int sourceType;// '0App格式发布1后台html发布',
    // 'h5地址，默认空',
    private String h5Url;
    // 'url解析0未成功1成功'
    private int urlAnalysisSuccess;
    private int picTotal;// 图片数量

    public int getPicTotal()
    {
        return picTotal;
    }

    public void setPicTotal(int picTotal)
    {
        this.picTotal = picTotal;
    }

    private Map<String, Object> param;// 多余的参数 传递使用这个

    public Map<String, Object> getParam()
    {
        return param;
    }

    public void setParam(Map<String, Object> param)
    {
        this.param = param;
    }

    /**
     * 发布状态 0未发布 1已发布
     */
    public static final Integer PUBLISH_STATE = 1;
    public static final Integer UNPUBLISH_STATE = 0;
    /**
     * 0删除 1正常
     */
    public static final Integer DELETED = 0;
    public static final Integer NORMAL = 1;
    /**
     * 图片 文字 资源
     */
    private List<DiaryContentRobot> diaryContentRobot;

    public List<DiaryContentRobot> getDiaryContentRobot()
    {
        return diaryContentRobot;
    }

    public void setDiaryContentRobot(List<DiaryContentRobot> diaryContentRobot)
    {
        this.diaryContentRobot = diaryContentRobot;
    }

    public int getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(int sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getH5Url()
    {
        return h5Url;
    }

    public void setH5Url(String h5Url)
    {
        this.h5Url = h5Url;
    }

    public int getUrlAnalysisSuccess()
    {
        return urlAnalysisSuccess;
    }

    public void setUrlAnalysisSuccess(int urlAnalysisSuccess)
    {
        this.urlAnalysisSuccess = urlAnalysisSuccess;
    }

    /**
     * 
     */
    private Integer id;
    private int gender;

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    /**
     * 日记标题
     */
    private String diaryTitle;

    /**
     * 城市定位
     */
    private String city;

    /**
     * 创建用户 id
     */
    private Integer creatorId;

    /**
     * 0删除 1正常
     */
    private Integer state;

    /**
     * 0男 1女
     */
    private Integer sex;

    /**
     * 
     */
    private Date createTime;

    /**
     * 发布时间
     */
    private Date pushTime;

    /**
     * 发布状态 0未发布 1已发布
     */
    private Integer pushState;

    /**
     * 文字内容用于检索
     */
    private String searchContent;

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
     * 日记标题
     * 
     * @return diary_title 日记标题
     */
    public String getDiaryTitle()
    {
        return diaryTitle;
    }

    /**
     * 日记标题
     * 
     * @param diaryTitle 日记标题
     */
    public void setDiaryTitle(String diaryTitle)
    {
        this.diaryTitle = diaryTitle == null ? null : diaryTitle.trim();
    }

    /**
     * 城市定位
     * 
     * @return city 城市定位
     */
    public String getCity()
    {
        return city;
    }

    /**
     * 城市定位
     * 
     * @param city 城市定位
     */
    public void setCity(String city)
    {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 创建用户 id
     * 
     * @return creator_id 创建用户 id
     */
    public Integer getCreatorId()
    {
        return creatorId;
    }

    /**
     * 创建用户 id
     * 
     * @param creatorId 创建用户 id
     */
    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }

    /**
     * 0删除 1正常
     * 
     * @return state 0删除 1正常
     */
    public Integer getState()
    {
        return state;
    }

    /**
     * 0删除 1正常
     * 
     * @param state 0删除 1正常
     */
    public void setState(Integer state)
    {
        this.state = state;
    }

    /**
     * 0男 1女
     * 
     * @return sex 0男 1女
     */
    public Integer getSex()
    {
        return sex;
    }

    /**
     * 0男 1女
     * 
     * @param sex 0男 1女
     */
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    /**
     * 
     * @return create_time
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 发布时间
     * 
     * @return push_time 发布时间
     */
    public Date getPushTime()
    {
        return pushTime;
    }

    /**
     * 发布时间
     * 
     * @param pushTime 发布时间
     */
    public void setPushTime(Date pushTime)
    {
        this.pushTime = pushTime;
    }

    /**
     * 发布状态 0未发布 1已发布
     * 
     * @return push_state 发布状态 0未发布 1已发布
     */
    public Integer getPushState()
    {
        return pushState;
    }

    /**
     * 发布状态 0未发布 1已发布
     * 
     * @param pushState 发布状态 0未发布 1已发布
     */
    public void setPushState(Integer pushState)
    {
        this.pushState = pushState;
    }

    /**
     * 文字内容用于检索
     * 
     * @return search_content 文字内容用于检索
     */
    public String getSearchContent()
    {
        return searchContent;
    }

    /**
     * 文字内容用于检索
     * 
     * @param searchContent 文字内容用于检索
     */
    public void setSearchContent(String searchContent)
    {
        this.searchContent = searchContent == null ? null : searchContent.trim();
    }
}
