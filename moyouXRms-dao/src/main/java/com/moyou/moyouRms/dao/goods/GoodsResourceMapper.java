package com.moyou.moyouRms.dao.goods;

import java.util.List;

import com.moyou.moyouRms.model.goods.GoodsResource;

public interface GoodsResourceMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(GoodsResource record);

	int insertSelective(GoodsResource record);

	GoodsResource selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GoodsResource record);

	int updateByPrimaryKey(GoodsResource record);
/**
 * 根据商品ID查询商品图片
 * @param goodsId
 * @return
 */
	List<GoodsResource> queryGoodsPic(Integer goodsId);
}