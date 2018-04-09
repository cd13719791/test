package com.moyou.moyouRms.model.limitip;

import java.io.Serializable;
import java.util.Date;

public class LimitIp implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 7908429248797201055L;
    /**
     * 前台创建
     */
    public static final Integer MODE_TYPE_PHONE = 2;
    /**
     * 后台创建
     */
    public static final Integer MODE_TYPE_PC = 1;
    public static final Integer STATE_OK = 1;// '1正常2删除',
    private Integer id;

    private String ipAddress;

    private Integer state;
    private Date createTime;
    private Date updateTime;
    private Integer modeType;// '1后台2前台',
    private String extendData;// '扩展字段',

    public Integer getModeType()
    {
        return modeType;
    }

    public void setModeType(Integer modeType)
    {
        this.modeType = modeType;
    }

    public String getExtendData()
    {
        return extendData;
    }

    public void setExtendData(String extendData)
    {
        this.extendData = extendData;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }
}
