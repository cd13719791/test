package com.moyou.moyouRms.model.adsupply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述:ad_supply表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-07-18
 */
public class AdSupply implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    public static final Short DELETE = 0;
    // '0等待审核1通过2不通过'
    public static final Short AUDIT_NO = 2;
    public static final Short AUDIT_YES = 1;
    public static final Short AUDIT_DEFAULT = 0;
    private String avatar;
    private String nickname;
    private String sig;
    private Date auditTime;// 审核时间
    private Integer picCount;// 图片数量
    private String msg;
    private String auditer;// 审核人
    private String moyouId;

    public String getMoyouId()
    {
        return moyouId;
    }

    public void setMoyouId(String moyouId)
    {
        this.moyouId = moyouId;
    }

    public String getAuditer()
    {
        return auditer;
    }

    public void setAuditer(String auditer)
    {
        this.auditer = auditer;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Integer getPicCount()
    {
        return picCount;
    }

    public void setPicCount(Integer picCount)
    {
        this.picCount = picCount;
    }

    public Date getAuditTime()
    {
        return auditTime;
    }

    public void setAuditTime(Date auditTime)
    {
        this.auditTime = auditTime;
    }

    private List<AdSupplyComment> adSupplyCommentList;

    public List<AdSupplyComment> getAdSupplyCommentList()
    {
        return adSupplyCommentList;
    }

    public void setAdSupplyCommentList(List<AdSupplyComment> adSupplyCommentList)
    {
        this.adSupplyCommentList = adSupplyCommentList;
    }

    public String getSig()
    {
        return sig;
    }

    public void setSig(String sig)
    {
        this.sig = sig;
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

    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String supplyDesc;

    /**
     * 活动信息
     */
    private String activityInfo;

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
     * 允许私信
     */
    private Byte allowLetter;

    /**
     * 允许手机
     */
    private Byte allowPhone;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 地区，如四川 成都 金牛
     */
    private String region;

    /**
     * 类目，美食
     */
    private String category;

    /**
     * 图片多张用逗号分隔
     */
    private String pic;

    /**
     * 封面
     */
    private String cover;

    /**
     * 0等待审核1通过2不通过
     */
    private Short audit;

    /**
     * 1个人2商家
     */
    private Short supplyType;

    /**
     * 0删除1正常
     */
    private Short state;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 扩展字段
     */
    private String extendData;

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
     * 标题
     * 
     * @return title 标题
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * 标题
     * 
     * @param title 标题
     */
    public void setTitle(String title)
    {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 描述
     * 
     * @return supply_desc 描述
     */
    public String getSupplyDesc()
    {
        return supplyDesc;
    }

    /**
     * 描述
     * 
     * @param supplyDesc 描述
     */
    public void setSupplyDesc(String supplyDesc)
    {
        this.supplyDesc = supplyDesc == null ? null : supplyDesc.trim();
    }

    /**
     * 活动信息
     * 
     * @return activity_info 活动信息
     */
    public String getActivityInfo()
    {
        return activityInfo;
    }

    /**
     * 活动信息
     * 
     * @param activityInfo 活动信息
     */
    public void setActivityInfo(String activityInfo)
    {
        this.activityInfo = activityInfo == null ? null : activityInfo.trim();
    }

    /**
     * 地址
     * 
     * @return address 地址
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * 地址
     * 
     * @param address 地址
     */
    public void setAddress(String address)
    {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 发布位置经度
     * 
     * @return longitude 发布位置经度
     */
    public Double getLongitude()
    {
        return longitude;
    }

    /**
     * 发布位置经度
     * 
     * @param longitude 发布位置经度
     */
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * 发布位置纬度
     * 
     * @return latitude 发布位置纬度
     */
    public Double getLatitude()
    {
        return latitude;
    }

    /**
     * 发布位置纬度
     * 
     * @param latitude 发布位置纬度
     */
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * 创建者Id
     * 
     * @return creator_id 创建者Id
     */
    public Integer getCreatorId()
    {
        return creatorId;
    }

    /**
     * 创建者Id
     * 
     * @param creatorId 创建者Id
     */
    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }

    /**
     * 阅读量统计
     * 
     * @return read_total 阅读量统计
     */
    public Integer getReadTotal()
    {
        return readTotal;
    }

    /**
     * 阅读量统计
     * 
     * @param readTotal 阅读量统计
     */
    public void setReadTotal(Integer readTotal)
    {
        this.readTotal = readTotal;
    }

    /**
     * 评论总数
     * 
     * @return comment_total 评论总数
     */
    public Integer getCommentTotal()
    {
        return commentTotal;
    }

    /**
     * 评论总数
     * 
     * @param commentTotal 评论总数
     */
    public void setCommentTotal(Integer commentTotal)
    {
        this.commentTotal = commentTotal;
    }

    /**
     * 允许私信
     * 
     * @return allow_letter 允许私信
     */
    public Byte getAllowLetter()
    {
        return allowLetter;
    }

    /**
     * 允许私信
     * 
     * @param allowLetter 允许私信
     */
    public void setAllowLetter(Byte allowLetter)
    {
        this.allowLetter = allowLetter;
    }

    /**
     * 允许手机
     * 
     * @return allow_phone 允许手机
     */
    public Byte getAllowPhone()
    {
        return allowPhone;
    }

    /**
     * 允许手机
     * 
     * @param allowPhone 允许手机
     */
    public void setAllowPhone(Byte allowPhone)
    {
        this.allowPhone = allowPhone;
    }

    /**
     * 联系电话
     * 
     * @return phone 联系电话
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * 联系电话
     * 
     * @param phone 联系电话
     */
    public void setPhone(String phone)
    {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 地区，如四川 成都 金牛
     * 
     * @return region 地区，如四川 成都 金牛
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * 地区，如四川 成都 金牛
     * 
     * @param region 地区，如四川 成都 金牛
     */
    public void setRegion(String region)
    {
        this.region = region == null ? null : region.trim();
    }

    /**
     * 类目，美食
     * 
     * @return category 类目，美食
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * 类目，美食
     * 
     * @param category 类目，美食
     */
    public void setCategory(String category)
    {
        this.category = category == null ? null : category.trim();
    }

    /**
     * 图片多张用逗号分隔
     * 
     * @return pic 图片多张用逗号分隔
     */
    public String getPic()
    {
        return pic;
    }

    /**
     * 图片多张用逗号分隔
     * 
     * @param pic 图片多张用逗号分隔
     */
    public void setPic(String pic)
    {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * 封面
     * 
     * @return cover 封面
     */
    public String getCover()
    {
        return cover;
    }

    /**
     * 封面
     * 
     * @param cover 封面
     */
    public void setCover(String cover)
    {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * 0等待审核1通过2不通过
     * 
     * @return audit 0等待审核1通过2不通过
     */
    public Short getAudit()
    {
        return audit;
    }

    /**
     * 0等待审核1通过2不通过
     * 
     * @param audit 0等待审核1通过2不通过
     */
    public void setAudit(Short audit)
    {
        this.audit = audit;
    }

    /**
     * 1个人2商家
     * 
     * @return supply_type 1个人2商家
     */
    public Short getSupplyType()
    {
        return supplyType;
    }

    /**
     * 1个人2商家
     * 
     * @param supplyType 1个人2商家
     */
    public void setSupplyType(Short supplyType)
    {
        this.supplyType = supplyType;
    }

    /**
     * 0删除1正常
     * 
     * @return state 0删除1正常
     */
    public Short getState()
    {
        return state;
    }

    /**
     * 0删除1正常
     * 
     * @param state 0删除1正常
     */
    public void setState(Short state)
    {
        this.state = state;
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
     * 
     * @return update_time
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    /**
     * 扩展字段
     * 
     * @return extend_data 扩展字段
     */
    public String getExtendData()
    {
        return extendData;
    }

    /**
     * 扩展字段
     * 
     * @param extendData 扩展字段
     */
    public void setExtendData(String extendData)
    {
        this.extendData = extendData == null ? null : extendData.trim();
    }
}
