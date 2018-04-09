package com.moyou.moyouRms.service.goods;

import java.util.List;

import com.moyou.moyouRms.model.goods.Goods;
import com.moyou.moyouRms.model.goods.GoodsPage;
import com.moyou.moyouRms.response.ApiResult;

public interface GoodsService {
	/**
	 * 商品列表初始化
	 * 
	 * @return
	 */
	List<Goods> queryGoodsList(GoodsPage goodsPage);

	/**
	 * 修改商品状态
	 * 
	 * @param record
	 * @return
	 */
	ApiResult updateGoodsState(Goods record);
/**
 * 添加商品
 * @param goodsAndPic
 * @return
 */
	ApiResult addGoods(Goods goods);
	/**
	 * 商品详情（用于修改商品信息）
	 * @param id
	 * @return
	 */
	Goods queryGoodsInfo(Integer id);
	/**
	 * 根据商品的ID修改商品的价格和描述
	 * @param id
	 * @return
	 */
	ApiResult updateGoodsInfo(Goods record);
}
