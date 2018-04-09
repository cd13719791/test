package com.moyou.moyouRms.model.usercrowd;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class UserCrowdRecommend extends BaseModel
{
    public UserCrowdRecommend(Integer crowedId, Short recommedStatus)
    {
        super();
        this.crowedId = crowedId;
        this.recommedStatus = recommedStatus;
    }

    public UserCrowdRecommend()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 6008189938165514546L;

    public final static Integer STATE_OK = 1;
    public final static Integer STATE_NO = 0;
    public final static Short MODE_TYPE1 = 1;// 首页推荐
    public final static Integer SORT_0 = 0;// 排序

    private Integer id;

    private Short modeType;

    private Integer crowedId;
    // 推荐状态0否1是
    private Short recommedStatus;

    private Date createTime;

    private Date updateTime;

    private Integer recommendSort;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Short getModeType()
    {
        return modeType;
    }

    public void setModeType(Short modeType)
    {
        this.modeType = modeType;
    }

    public Integer getCrowedId()
    {
        return crowedId;
    }

    public void setCrowedId(Integer crowedId)
    {
        this.crowedId = crowedId;
    }

    public Short getRecommedStatus()
    {
        return recommedStatus;
    }

    public void setRecommedStatus(Short recommedStatus)
    {
        this.recommedStatus = recommedStatus;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Integer getRecommendSort()
    {
        return recommendSort;
    }

    public void setRecommendSort(Integer recommendSort)
    {
        this.recommendSort = recommendSort;
    }
}
