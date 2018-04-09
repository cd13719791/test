package com.moyou.moyouRms.model.usercrowd;

import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class UserCrowdPage extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8804568108216346301L;
	/** 当前页 */
	private int pageNumber;
	/* 每页显示条目数 */
	private int pageSize;
	/**
	 * 总条数
	 */
	private int total;
	private List<?> results;
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	private Integer crowdId;// 群号
	private String crowdName;// 群名
	private String starttime;
	private String endtime;
	private String crowdUserNickName;//群主昵称
	public String getCrowdUserNickName() {
		return crowdUserNickName;
	}

	public void setCrowdUserNickName(String crowdUserNickName) {
		this.crowdUserNickName = crowdUserNickName;
	}

	public Integer getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(Integer crowdId) {
		this.crowdId = crowdId;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}


	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
