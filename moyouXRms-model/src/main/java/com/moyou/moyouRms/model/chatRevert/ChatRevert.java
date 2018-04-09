package com.moyou.moyouRms.model.chatRevert;

import java.io.Serializable;
import java.util.Date;

public class ChatRevert implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3847245547222140859L;

	public static final Integer ACCOST = 2;
	public static final Integer CHAT = 1;
	private Integer id;

    private String content;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer useCount;
    private Integer dataType;//'1聊天常用语2搭讪语',
    
    public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }
}