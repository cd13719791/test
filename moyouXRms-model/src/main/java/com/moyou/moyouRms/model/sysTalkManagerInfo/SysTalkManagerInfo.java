package com.moyou.moyouRms.model.sysTalkManagerInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:sys_talk_manager_info表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-05-10
 */
public class SysTalkManagerInfo implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String loginName;
    private Integer accountCount;
    private Integer userId;// 聊天好友的userId
    private String name;
    private Integer sumTime;// 工作时间
    private Integer sumUser;// 总用户数
    private Integer sumTalk;// 总聊天数
    private Integer thisDayUser;// 本日总用户
    private Integer thisDayTalk;// 本日总聊天
    private Integer offLineChatCount;// 离线消息数
    private String extendData;// 扩展字段 查询 账号分配范围 xxx_xxx
    private Integer startNumber;
    private Integer endNumber;

    public Integer getStartNumber()
    {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber)
    {
        this.startNumber = startNumber;
    }

    public Integer getEndNumber()
    {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber)
    {
        this.endNumber = endNumber;
    }

    public String getExtendData()
    {
        return extendData;
    }

    public void setExtendData(String extendData)
    {
        this.startNumber = Integer.valueOf(extendData.split("_")[0]);
        this.endNumber = Integer.valueOf(extendData.split("_")[1]);
        this.extendData = extendData;
    }

    public Integer getOffLineChatCount()
    {
        return offLineChatCount;
    }

    public void setOffLineChatCount(Integer offLineChatCount)
    {
        this.offLineChatCount = offLineChatCount;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getThisDayUser()
    {
        return thisDayUser;
    }

    public Integer getAccountCount()
    {
        return accountCount;
    }

    public void setAccountCount(Integer accountCount)
    {
        this.accountCount = accountCount;
    }

    public void setThisDayUser(Integer thisDayUser)
    {
        this.thisDayUser = thisDayUser;
    }

    public Integer getThisDayTalk()
    {
        return thisDayTalk;
    }

    public void setThisDayTalk(Integer thisDayTalk)
    {
        this.thisDayTalk = thisDayTalk;
    }

    private Integer onlineType;

    public Integer getOnlineType()
    {
        return onlineType;
    }

    public void setOnlineType(Integer onlineType)
    {
        this.onlineType = onlineType;
    }

    /**
     * 
     */
    private Integer id;

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getSumTime()
    {
        return sumTime;
    }

    public void setSumTime(Integer sumTime)
    {
        this.sumTime = sumTime;
    }

    public Integer getSumUser()
    {
        return sumUser;
    }

    public void setSumUser(Integer sumUser)
    {
        this.sumUser = sumUser;
    }

    public Integer getSumTalk()
    {
        return sumTalk;
    }

    public void setSumTalk(Integer sumTalk)
    {
        this.sumTalk = sumTalk;
    }

    /**
     * l聊天数量
     */
    private Integer talkNumber;

    /**
     * 聊天用户数量
     */
    private Integer userNumber;

    /**
     * 总工作时间（分钟）
     */
    private Integer timeSum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 管理员账号
     */
    private String accountId;

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
     * l聊天数量
     * 
     * @return talk_number l聊天数量
     */
    public Integer getTalkNumber()
    {
        return talkNumber;
    }

    /**
     * l聊天数量
     * 
     * @param talkNumber l聊天数量
     */
    public void setTalkNumber(Integer talkNumber)
    {
        this.talkNumber = talkNumber;
    }

    /**
     * 聊天用户数量
     * 
     * @return user_number 聊天用户数量
     */
    public Integer getUserNumber()
    {
        return userNumber;
    }

    /**
     * 聊天用户数量
     * 
     * @param userNumber 聊天用户数量
     */
    public void setUserNumber(Integer userNumber)
    {
        this.userNumber = userNumber;
    }

    /**
     * 总工作时间（分钟）
     * 
     * @return time_sum 总工作时间（分钟）
     */
    public Integer getTimeSum()
    {
        return timeSum;
    }

    /**
     * 总工作时间（分钟）
     * 
     * @param timeSum 总工作时间（分钟）
     */
    public void setTimeSum(Integer timeSum)
    {
        this.timeSum = timeSum;
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
     * 管理员账号
     * 
     * @return account_id 管理员账号
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
     * 管理员账号
     * 
     * @param accountId 管理员账号
     */
    public void setAccountId(String accountId)
    {
        this.accountId = accountId == null ? null : accountId.trim();
    }

}
