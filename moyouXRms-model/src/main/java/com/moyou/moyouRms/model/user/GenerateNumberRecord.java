package com.moyou.moyouRms.model.user;

import java.util.Date;

public class GenerateNumberRecord {
    private String id;

    private Integer startNumber;

    private Integer endNumber;

    private Date createTime;
    /**
     * 用户注册数,注册一个用户，该字段累加1
     */
    private Integer registerCount;
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * @return the registerCount
	 */
	public Integer getRegisterCount() {
		return registerCount;
	}

	/**
	 * @param registerCount the registerCount to set
	 */
	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}
    
}