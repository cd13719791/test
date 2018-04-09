package com.moyou.moyouRms.dao.goods;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.goods.Goods;
import com.moyou.moyouRms.model.goods.GoodsResource;

public interface GoodsMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 添加商品
	 * 
	 * @param record
	 * @return
	 */
	Integer insert(Goods record);

	/**
	 * 添加多张商品图片
	 * 
	 * @param record
	 * @return
	 */
	Integer insertListGoodsPic(List<GoodsResource> list);

	int insertSelective(Goods record);

	Goods selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Goods record);

	int updateByPrimaryKeyWithBLOBs(Goods record);

	int updateByPrimaryKey(Goods record);
/**
 *  修改商品状态
 * @param record
 * @return
 */
	Integer updateGoodsState(Goods record);
	/**
	 * 商品列表初始化
	 * 
	 * @return
	 */
	List<Goods> queryGoodsList(PageBean pageBean);
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
	int updateGoodsInfo(Goods record);
	
}