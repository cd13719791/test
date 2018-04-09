package com.moyou.moyouRms.model.usercrowd;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class UserCrowdMembers extends BaseModel
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 3176285378846467496L;

    private Integer id;

    private Integer crowdMemberId;

    private Integer crowdPkid;

    private Date updateTime = new Date();

    private Short messageReminding = 1;
    private int roleType;// 1群主2管理员3普通成员
    // 群主type
    public static Integer GROUP_HOLDER = 1;

    public int getRoleType()
    {
        return roleType;
    }

    public void setRoleType(int roleType)
    {
        this.roleType = roleType;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCrowdMemberId()
    {
        return crowdMemberId;
    }

    public void setCrowdMemberId(Integer crowdMemberId)
    {
        this.crowdMemberId = crowdMemberId;
    }

    public Integer getCrowdPkid()
    {
        return crowdPkid;
    }

    public void setCrowdPkid(Integer crowdPkid)
    {
        this.crowdPkid = crowdPkid;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Short getMessageReminding()
    {
        return messageReminding;
    }

    public void setMessageReminding(Short messageReminding)
    {
        this.messageReminding = messageReminding;
    }
}
