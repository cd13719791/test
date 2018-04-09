package com.moyou.moyouRms.model.sysSessionManager;

import java.io.Serializable;
import java.util.Date;

import org.apache.shiro.session.mgt.SessionKey;

/**
 * 描述:sys_session_manager表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-05-11
 */
public class SysSessionManager implements Serializable,SessionKey{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int ONLINE=1;
	public static final int OFFLINE=0;
	/**
	 * 操作时间
	 */
	private Date setTime;
    public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	/**
     * 
     */
    private Integer id;

    /**
     * session id 会话id
     */
    private String sessionId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 
     */
    private Integer onlineTime;

    /**
     * 在线状态 0不在线  1在线
     */
    private Integer onlineType;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * session id 会话id
     * @return session_id session id 会话id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * session id 会话id
     * @param sessionId session id 会话id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 登录账号
     * @return login_name 登录账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录账号
     * @param loginName 登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}

	/**
     * 在线状态 0不在线  1在线
     * @return online_type 在线状态 0不在线  1在线
     */
    public Integer getOnlineType() {
        return onlineType;
    }

    /**
     * 在线状态 0不在线  1在线
     * @param onlineType 在线状态 0不在线  1在线
     */
    public void setOnlineType(Integer onlineType) {
        this.onlineType = onlineType;
    }
}