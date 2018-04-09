package com.moyou.moyouRms.model.msg;

import java.util.Date;

/**
 * MsgItemSqlResult
 * 
 * @author PzC.
 * @time 2016年12月7日 下午2:48:47
 * 
 */
public class MsgItemReturn {
	private String shortMsgContent;
	private Integer state;
	private Date updateTime;
	private String fromNow;

	public String getShortMsgContent() {
		return shortMsgContent;
	}

	public void setShortMsgContent(String shortMsgContent) {
		this.shortMsgContent = shortMsgContent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFromNow() {
		return fromNow;
	}

	public void setFromNow(String fromNow) {
		this.fromNow = fromNow;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
