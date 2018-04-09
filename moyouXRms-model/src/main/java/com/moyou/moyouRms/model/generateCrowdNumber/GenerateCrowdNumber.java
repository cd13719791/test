package com.moyou.moyouRms.model.generateCrowdNumber;

import java.util.Date;

public class GenerateCrowdNumber {
	 private String id;

	    private Integer startNumber;

	    private Integer endNumber;

	    private Date createTime;

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

	    public Integer getRegisterCount() {
	        return registerCount;
	    }

	    public void setRegisterCount(Integer registerCount) {
	        this.registerCount = registerCount;
	    }
	}

