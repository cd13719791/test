package com.moyou.moyouRms.model.banner;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class Banner extends BaseModel
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1965742045678431632L;
    // `banner_type` smallint(2) DEFAULT NULL COMMENT '类型：1 发现页-说说 2.H5 3.发现页-故事 4供求页h5地址5供求页广告个人详情6供求页广告商家详情',
    // public static final Integer ADSUPPLY = 4;
    public static final Integer ADSUPPLY_H5 = 4;
    public static final Integer ADSUPPLY_INDIVIDUAL = 5;
    public static final Integer ADSUPPLY_BUSINESSES = 6;
    public static final Integer ADSUPPLY_LIVE =7;
    private int id;
    private String bannerName;
    private String bannerPicture;
    private Integer bannerType;
    private String eventSourceUrl;
    private Integer eventSourceId;
    private Integer state;// banner状态 0.停用未推荐1.启用推荐
    private Integer orderNo;// 排序字段正序
    private Integer bannerDel;// 0删除1正常
    private Date updateTime;
    private Date createTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public Integer getState()
    {
        return state;
    }

    public Integer getEventSourceId()
    {
        return eventSourceId;
    }

    public void setEventSourceId(Integer eventSourceId)
    {
        this.eventSourceId = eventSourceId;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public Integer getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo)
    {
        this.orderNo = orderNo;
    }

    public Integer getBannerDel()
    {
        return bannerDel;
    }

    public void setBannerDel(Integer bannerDel)
    {
        this.bannerDel = bannerDel;
    }

    public String getBannerName()
    {
        return bannerName;
    }

    public void setBannerName(String bannerName)
    {
        this.bannerName = bannerName;
    }

    public String getBannerPicture()
    {
        return bannerPicture;
    }

    public void setBannerPicture(String bannerPicture)
    {
        this.bannerPicture = bannerPicture;
    }

    public Integer getBannerType()
    {
        return bannerType;
    }

    public void setBannerType(Integer bannerType)
    {
        this.bannerType = bannerType;
    }

    public String getEventSourceUrl()
    {
        return eventSourceUrl;
    }

    public void setEventSourceUrl(String eventSourceUrl)
    {
        this.eventSourceUrl = eventSourceUrl;
    }

}
