package com.moyou.moyouRms.model.goods;

import java.math.BigDecimal;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class GoodsPage extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7605998872799075460L;

	/** 当前页 */
	private int pageNumber;
	/* 每页显示条目数 */
	private int pageSize;
	
	/**
	 * 总条数
	 */
	private int total;
	private List<?> results;
	// 商品价格开始
	private BigDecimal startGoodsPrice;
	// 商品价格结束
	private BigDecimal endGoodsPrice;
	private Integer state;// 上架状态
	private String goodsName;// 商品名称
	private String goodsPrice;// 商品价格
	private String sortRule;// 排序
	private String sellAmount;// 售出数量

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getSortRule() {
		return sortRule;
	}

	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}

	public String getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(String sellAmount) {
		this.sellAmount = sellAmount;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

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

	public BigDecimal getStartGoodsPrice() {
		return startGoodsPrice;
	}

	public void setStartGoodsPrice(BigDecimal startGoodsPrice) {
		this.startGoodsPrice = startGoodsPrice;
	}

	public BigDecimal getEndGoodsPrice() {
		return endGoodsPrice;
	}

	public void setEndGoodsPrice(BigDecimal endGoodsPrice) {
		this.endGoodsPrice = endGoodsPrice;
	}
}
