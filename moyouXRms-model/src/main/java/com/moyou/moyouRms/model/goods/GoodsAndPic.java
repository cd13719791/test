package com.moyou.moyouRms.model.goods;

import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class GoodsAndPic extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8180614986797207414L;

	private Goods goods;
	private List<GoodsResource> goodsResource;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<GoodsResource> getGoodsResource() {
		return goodsResource;
	}

	public void setGoodsResource(List<GoodsResource> goodsResource) {
		this.goodsResource = goodsResource;
	}

}
