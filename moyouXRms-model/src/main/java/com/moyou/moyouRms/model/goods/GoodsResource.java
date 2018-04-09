package com.moyou.moyouRms.model.goods;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class GoodsResource extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7001970962861025689L;

	private Integer id;

	private Integer goodsId;

	private Short mediaType;

	private String mediaUrl;

	private Short resourceSort;

	private Date updateTime;
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Short getMediaType() {
		return mediaType;
	}

	public void setMediaType(Short mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
	}

	public Short getResourceSort() {
		return resourceSort;
	}

	public void setResourceSort(Short resourceSort) {
		this.resourceSort = resourceSort;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}