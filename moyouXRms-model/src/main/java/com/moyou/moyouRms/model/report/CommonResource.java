package com.moyou.moyouRms.model.report;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:t_common_resource表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-01-18
 */
public class CommonResource extends BaseModel
{
    private Integer extendInt;

    public Integer getExtendInt()
    {
        return extendInt;
    }

    public void setExtendInt(Integer extendInt)
    {
        this.extendInt = extendInt;
    }

    public CommonResource()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommonResource(int picCount, Integer id, Short objectType, Integer objectId, Short mediaType,
        String url, Short picType, Integer userId, Short picOrder, Date createTime, Short state,
        String extendData, int noDispose)
    {
        super();
        this.picCount = picCount;
        this.id = id;
        this.objectType = objectType;
        this.objectId = objectId;
        this.mediaType = mediaType;
        this.url = url;
        this.picType = picType;
        this.userId = userId;
        this.picOrder = picOrder;
        this.createTime = createTime;
        this.state = state;
        this.extendData = extendData;
        this.noDispose = noDispose;
    }

    public CommonResource(Short objectType, String extendData)
    {
        super();
        this.objectType = objectType;
        this.extendData = extendData;
    }

    /**
     * 举报类型 = 1；
     */
    public static final short OBJECT_REPORT = 1;
    /**
     * 机器人说说类型 = 2；
     */
    public static final short OBJECT_ROBOT_TALK = 2;
    /**
     * 4发布说说文案
     */
    public static final short OBJECT_TYPE_TEXT = 4;
    /**
     * 5发布单聊文案
     */
    public static final short OBJECT_TYPE_CHAT = 5;
    /**
     * adsupply flash 广告刷新
     */
    public static final Short OBJECT_TYPE_ADSUPPLY_FLASH = 6;
    /**
     * 广告编辑
     */
    public static final Short OBJECT_TYPE_ADSUPPLY_UPDATE = 7;
    /**
     * 广告发布
     */
    public static final Short OBJECT_TYPE_ADSUPPLY_PUSH = 8;

    /**
     * 资源文件格式： 1.音频 2.视频 3.图片
     */
    public static final short MEDIATYPE_AUDIO = 1;
    public static final short MEDIATYPE_VIDEO = 2;
    public static final short MEDIATYPE_PICTURE = 3;
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public static final Short OBJECT_TYPE_CONSUMER_COIN = 6;
    /**
     * 图片数量
     */
    private int picCount;

    public int getPicCount()
    {
        return picCount;
    }

    public void setPicCount(int picCount)
    {
        this.picCount = picCount;
    }

    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 对象类型1举报
     */
    private Short objectType = 1;

    /**
     * 对象id
     */
    private Integer objectId;

    /**
     * 资源文件格式： 1.音频 2.视频 3.图片
     */
    private Short mediaType = 3;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 高清或者普通的图片 1.高清 2.普通
     */
    private Short picType;

    /**
     * 资源创建者用户Id
     */
    private Integer userId;

    /**
     * 图片排序 1 2 3 4 5
     */
    private Short picOrder;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 主键自增
     * 
     * @return id 主键自增
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 主键自增
     * 
     * @param id 主键自增
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 对象类型1举报
     * 
     * @return object_type 对象类型1举报
     */
    public Short getObjectType()
    {
        return objectType;
    }

    /**
     * 对象类型1举报
     * 
     * @param objectType 对象类型1举报
     */
    public void setObjectType(Short objectType)
    {
        this.objectType = objectType;
    }

    /**
     * 对象id
     * 
     * @return object_id 对象id
     */
    public Integer getObjectId()
    {
        return objectId;
    }

    /**
     * 对象id
     * 
     * @param objectId 对象id
     */
    public void setObjectId(Integer objectId)
    {
        this.objectId = objectId;
    }

    /**
     * 资源文件格式： 1.音频 2.视频 3.图片
     * 
     * @return media_type 资源文件格式： 1.音频 2.视频 3.图片
     */
    public Short getMediaType()
    {
        return mediaType;
    }

    /**
     * 资源文件格式： 1.音频 2.视频 3.图片
     * 
     * @param mediaType 资源文件格式： 1.音频 2.视频 3.图片
     */
    public void setMediaType(Short mediaType)
    {
        this.mediaType = mediaType;
    }

    /**
     * 资源路径
     * 
     * @return url 资源路径
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * 资源路径
     * 
     * @param url 资源路径
     */
    public void setUrl(String url)
    {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 高清或者普通的图片 1.高清 2.普通
     * 
     * @return pic_type 高清或者普通的图片 1.高清 2.普通
     */
    public Short getPicType()
    {
        return picType;
    }

    /**
     * 高清或者普通的图片 1.高清 2.普通
     * 
     * @param picType 高清或者普通的图片 1.高清 2.普通
     */
    public void setPicType(Short picType)
    {
        this.picType = picType;
    }

    /**
     * 资源创建者用户Id
     * 
     * @return user_id 资源创建者用户Id
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * 资源创建者用户Id
     * 
     * @param userId 资源创建者用户Id
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     * 图片排序 1 2 3 4 5
     * 
     * @return pic_order 图片排序 1 2 3 4 5
     */
    public Short getPicOrder()
    {
        return picOrder;
    }

    /**
     * 图片排序 1 2 3 4 5
     * 
     * @param picOrder 图片排序 1 2 3 4 5
     */
    public void setPicOrder(Short picOrder)
    {
        this.picOrder = picOrder;
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

    /**
     * 举报状态： 0.未处理1.限制 2.警告 3.删除
     */
    private Short state;
    private String extendData;// 扩展文案字段

    public String getExtendData()
    {
        return extendData;
    }

    public void setExtendData(String extendData)
    {
        this.extendData = extendData;
    }

    private int noDispose;// 未处理

    public Short getState()
    {
        return state;
    }

    public void setState(Short state)
    {
        this.state = state;
    }

    public int getNoDispose()
    {
        return noDispose;
    }

    public void setNoDispose(int noDispose)
    {
        this.noDispose = noDispose;
    }
}
