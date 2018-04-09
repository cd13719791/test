package com.moyou.moyouRms.model.goods;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class Goods extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396389275540103737L;

	private Integer id;

	private String goodsName;

	private BigDecimal goodsPrice;

	private Integer state;

	private Date createTime;

	private Date updateTime;

	private Integer goodsAmount;
	private List<GoodsResource> goodsResource;
	private String mediaUrl;//商品图片
	private short resourceSort;//商品图片排序
	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public short getResourceSort() {
		return resourceSort;
	}

	public void setResourceSort(short resourceSort) {
		this.resourceSort = resourceSort;
	}

	public List<GoodsResource> getGoodsResource() {
		return goodsResource;
	}

	public void setGoodsResource(List<GoodsResource> goodsResource) {
		this.goodsResource = goodsResource;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	private Integer sellAmount;

	public Integer getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(Integer sellAmount) {
		this.sellAmount = sellAmount;
	}

	private String goodsDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
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

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
	}
		
}