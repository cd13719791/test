package com.moyou.moyouRms.service.goods.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.goods.GoodsMapper;
import com.moyou.moyouRms.dao.goods.GoodsResourceMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.goods.Goods;
import com.moyou.moyouRms.model.goods.GoodsPage;
import com.moyou.moyouRms.model.goods.GoodsResource;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.goods.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private GoodsResourceMapper goodsResourceMapper;

	/**
	 * 商品列表初始化
	 * 
	 * @return
	 */
	@Override
	public List<Goods> queryGoodsList(GoodsPage goodsPage) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(goodsPage.getPageNumber());
		pageBean.setPageSize(goodsPage.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("startGoodsPrice", goodsPage.getStartGoodsPrice());
		map.put("endGoodsPrice", goodsPage.getEndGoodsPrice());
		map.put("state", goodsPage.getState());
		map.put("goodsName", goodsPage.getGoodsName());
		map.put("sellAmount", goodsPage.getSellAmount());
		map.put("goodsPrice", goodsPage.getGoodsPrice());
		map.put("sortRule", goodsPage.getSortRule());
		pageBean.setConditions(map);
		List<Goods> results = new ArrayList<Goods>();
		results = goodsMapper.queryGoodsList(pageBean);
		goodsPage.setTotal(pageBean.getTotalRecord());
		goodsPage.setResults(results);
		return results;
	}

	/**
	 * 修改商品状态
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public ApiResult updateGoodsState(Goods record) {
		goodsMapper.updateGoodsState(record);
		return new ApiResult(RESPONSE.SUCCESS, "修改成功");
	}

	/**
	 * 添加商品
	 * 
	 * @param goodsAndPic
	 * @return
	 */
	@Override
	public ApiResult addGoods(Goods goods) {
		goods.setCreateTime(new Date());
		goods.setUpdateTime(new Date());
		goodsMapper.insert(goods);
		List<GoodsResource> goodsResource = goods.getGoodsResource();
		for (GoodsResource goodsResource2 : goodsResource) {
			goodsResource2.setUpdateTime(new Date());
			goodsResource2.setCreateTime(new Date());
			goodsResource2.setGoodsId(goods.getId());
			goodsResource2.setMediaType((short) 3);
		}
		goodsMapper.insertListGoodsPic(goodsResource);
		return new ApiResult(RESPONSE.SUCCESS, "添加成功");
	}

	/**
	 * 商品详情（用于修改商品信息）
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Goods queryGoodsInfo(Integer id) {
		Goods good = goodsMapper.queryGoodsInfo(id);
		List<GoodsResource> goodsResource = goodsResourceMapper
				.queryGoodsPic(id);
		good.setGoodsResource(goodsResource);
		return good;
	}

	/**
	 * 根据商品的ID修改商品的价格和描述
	 * @param id
	 * @return
	 */
	@Override
	public ApiResult updateGoodsInfo(Goods record) {
		 goodsMapper.updateGoodsInfo(record);
 return new ApiResult(RESPONSE.SUCCESS, "修改成功");
	}
	
}
