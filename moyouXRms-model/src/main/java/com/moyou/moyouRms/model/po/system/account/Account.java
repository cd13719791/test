package com.moyou.moyouRms.model.po.system.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.po.system.loginlog.LoginLog;
import com.moyou.moyouRms.model.po.system.organ.Position;
import com.moyou.moyouRms.model.po.system.organ.Role;
import com.moyou.moyouRms.model.sysBaseAccountRole.SysBaseAccountRole;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;

/**
 * 用户帐号表
 */
@Alias("BaseAccount")
public class Account extends BaseModel
{

    private static final long serialVersionUID = 1L;

    public static final Integer TALK_MANAGER = 3;
    private Date loginTime;
    private String loginIP;
    private String accountId;
    private String loginName;
    private String juese;
    private String password;
    private String id;
    private String sessionId;
    private String extendData;// 扩展字段

    public Account(Integer type)
    {
        super();
        this.type = type;
    }

    public Account(String accountId, String extendData)
    {
        super();
        this.accountId = accountId;
        this.extendData = extendData;
    }

    public Account()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getExtendData()
    {
        return extendData;
    }

    public void setExtendData(String extendData)
    {
        this.extendData = extendData;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    // 部门
    private String department;
    private String opwd;// 旧密码
    private String npwd;// 新秘密
    private String qpwd;// 确认密码
    private String position;
    private List<SysBaseAccountRole> sysBaseRoleList;
    private String salt;
    private String phone;
    private String countUser;
    private String roleId;
    private SysTalkManagerInfo sysTalkManagerInfo;

    public SysTalkManagerInfo getSysTalkManagerInfo()
    {
        return sysTalkManagerInfo;
    }

    public void setSysTalkManagerInfo(SysTalkManagerInfo sysTalkManagerInfo)
    {
        this.sysTalkManagerInfo = sysTalkManagerInfo;
    }

    public List<SysBaseAccountRole> getSysBaseRoleList()
    {
        return sysBaseRoleList;
    }

    public void setSysBaseRoleList(List<SysBaseAccountRole> sysBaseRoleList)
    {
        this.sysBaseRoleList = sysBaseRoleList;
    }

    public String getOpwd()
    {
        return opwd;
    }

    public void setOpwd(String opwd)
    {
        this.opwd = opwd;
    }

    public String getNpwd()
    {
        return npwd;
    }

    public void setNpwd(String npwd)
    {
        this.npwd = npwd;
    }

    public String getQpwd()
    {
        return qpwd;
    }

    public void setQpwd(String qpwd)
    {
        this.qpwd = qpwd;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getLoginIP()
    {
        return loginIP;
    }

    public void setLoginIP(String loginIP)
    {
        this.loginIP = loginIP;
    }

    public String getJuese()
    {
        return juese;
    }

    public void setJuese(String juese)
    {
        this.juese = juese;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String getCountUser()
    {
        return countUser;
    }

    public void setCountUser(String countUser)
    {
        this.countUser = countUser;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    private String name;

    private String picUrl;

    private String skin;

    private String userId;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    private String roleIdStr;
    private List<Role> roleList = new ArrayList<Role>();

    private Integer type;

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    private String email;

    private String description;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;

    private LoginLog loginLog = new LoginLog();

    private String keyWord;

    private List<Position> poss = new ArrayList<Position>();

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getIsValid()
    {
        return isValid;
    }

    public void setIsValid(Integer isValid)
    {
        this.isValid = isValid;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getSkin()
    {
        return skin;
    }

    public void setSkin(String skin)
    {
        this.skin = skin;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getPicUrl()
    {
        return picUrl;
    }

    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Account [accountId=" + accountId + ", loginName=" + loginName + ", password=" + password
            + ", name=" + name + ", skin=" + skin + ", roleId " + ", email=" + email + ", isValid=" + isValid
            + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

    public String getKeyWord()
    {
        return keyWord;
    }

    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }

    public LoginLog getLoginLog()
    {
        return loginLog;
    }

    public void setLoginLog(LoginLog loginLog)
    {
        this.loginLog = loginLog;
    }

    public List<Position> getPoss()
    {
        return poss;
    }

    public void setPoss(List<Position> poss)
    {
        this.poss = poss;
    }

    public String getRoleIdStr()
    {
        return roleIdStr;
    }

    public void setRoleIdStr(String roleIdStr)
    {
        this.roleIdStr = roleIdStr;
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

}
