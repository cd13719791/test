package com.moyou.moyouRms.model.msg;

import java.util.Date;

import com.moyou.moyouRms.util.UUIDUtil;

/**
 * 消息联系人好友
 * 
 * @author PzC.
 * @time 2016年12月6日 下午2:39:03
 * 
 */
public class UserContact {
	// 主键 id
    private String id = UUIDUtil.getUUID();
    // 用户 id
    private String userId;
    // 联系人 id
    private String contactUserId;
    private Date updateTime = new Date();
    private Date createTime = this.updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(String contactUserId) {
        this.contactUserId = contactUserId == null ? null : contactUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}