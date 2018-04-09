package com.moyou.moyouRms.model.msgBar;

import java.util.Date;

/**
 * 描述:t_msg_bar表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-08-08
 */
public class MsgBar
{

    /**
     * 
     */
    private Integer id;

    /**
     * 推送文字内容
     */
    private String content;

    /**
     * 推送内容id
     */
    private Integer modeId;

    /**
     * 推送类型 1 消息 2说说 3附近人 4地图 5发现 6供求 7秘密
     */
    private Short modeType;

    /**
     * 操作人员名字
     */
    private String account;

    /**
     * 
     */
    private Date createTime;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * 推送状态 0未推送 1推送成功
     */
    private Short state;
    public static final Short STATE_NO = 0;
    // public static final Short STATE_DO = 1;
    public static final Short STATE_OK = 1;

    /**
     * 
     */
    private String extendData;

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
     * 推送文字内容
     * 
     * @return content 推送文字内容
     */
    public String getContent()
    {
        return content;
    }

    /**
     * 推送文字内容
     * 
     * @param content 推送文字内容
     */
    public void setContent(String content)
    {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 推送内容id
     * 
     * @return mode_id 推送内容id
     */
    public Integer getModeId()
    {
        return modeId;
    }

    /**
     * 推送内容id
     * 
     * @param modeId 推送内容id
     */
    public void setModeId(Integer modeId)
    {
        this.modeId = modeId;
    }

    /**
     * 推送类型 1 消息 2说说 3附近人 4地图 5发现 6供求 7秘密
     * 
     * @return mode_type 推送类型 1 消息 2说说 3附近人 4地图 5发现 6供求 7秘密
     */
    public Short getModeType()
    {
        return modeType;
    }

    /**
     * 推送类型 1 消息 2说说 3附近人 4地图 5发现 6供求 7秘密
     * 
     * @param modeType 推送类型 1 消息 2说说 3附近人 4地图 5发现 6供求 7秘密
     */
    public void setModeType(Short modeType)
    {
        this.modeType = modeType;
    }

    /**
     * 操作人员名字
     * 
     * @return account 操作人员名字
     */
    public String getAccount()
    {
        return account;
    }

    /**
     * 操作人员名字
     * 
     * @param account 操作人员名字
     */
    public void setAccount(String account)
    {
        this.account = account == null ? null : account.trim();
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
     * 推送时间
     * 
     * @return push_time 推送时间
     */
    public Date getPushTime()
    {
        return pushTime;
    }

    /**
     * 推送时间
     * 
     * @param pushTime 推送时间
     */
    public void setPushTime(Date pushTime)
    {
        this.pushTime = pushTime;
    }

    /**
     * 推送状态 0未推送 1正在推送 2推送成功
     * 
     * @return state 推送状态 0未推送 1正在推送 2推送成功
     */
    public Short getState()
    {
        return state;
    }

    /**
     * 推送状态 0未推送 1正在推送 2推送成功
     * 
     * @param state 推送状态 0未推送 1正在推送 2推送成功
     */
    public void setState(Short state)
    {
        this.state = state;
    }

    /**
     * 
     * @return extend_data
     */
    public String getExtendData()
    {
        return extendData;
    }

    /**
     * 
     * @param extendData
     */
    public void setExtendData(String extendData)
    {
        this.extendData = extendData == null ? null : extendData.trim();
    }
}
