package com.moyou.moyouRms.model.sysOperationLog;

import java.util.Date;

public class SysOperationLog {
    private Integer logid;
    	//请求方法
    private String logmethod;
    	//请求描述
    private String logdescription;
    	//请求用户名
    private String logusername;
    	//请求ip
    private String logpath;
    private String logParam;
    public String getLogParam() {
		return logParam;
	}

	public void setLogParam(String logParam) {
		this.logParam = logParam;
	}

	/**
     * 日志记录枚举类型    0 默认值  1修改金币
     */
    private int logEnumValue;

    public int getLogEnumValue() {
		return logEnumValue;
	}

	public void setLogEnumValue(int logEnumValue) {
		this.logEnumValue = logEnumValue;
	}

	private Date createTime;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getLogmethod() {
        return logmethod;
    }

    public void setLogmethod(String logmethod) {
        this.logmethod = logmethod == null ? null : logmethod.trim();
    }

    public String getLogdescription() {
        return logdescription;
    }

    public void setLogdescription(String logdescription) {
        this.logdescription = logdescription == null ? null : logdescription.trim();
    }

    public String getLogusername() {
        return logusername;
    }

    public void setLogusername(String logusername) {
        this.logusername = logusername == null ? null : logusername.trim();
    }

    public String getLogpath() {
        return logpath;
    }

    public void setLogpath(String logpath) {
        this.logpath = logpath == null ? null : logpath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}