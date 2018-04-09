package com.moyou.moyouRms.controller.goods;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.goods.Goods;
import com.moyou.moyouRms.model.goods.GoodsPage;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.goods.GoodsService;
import com.moyou.moyouRms.util.JsonUtil;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController extends BaseController {

	@Resource
	private GoodsService goodsService;

	/**
	 * 商品列表初始化
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryGoodsList", method = RequestMethod.POST)
	public ApiResult queryGoodsList(@RequestBody GoodsPage goodsPage) {
		goodsService.queryGoodsList(goodsPage);
		return new ApiResult(RESPONSE.SUCCESS, "成功", goodsPage);
	}

	/**
	 * 修改商品状态
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateGoodsState", method = RequestMethod.POST)
	public ApiResult updateGoodsState(@RequestBody Goods record) {
		goodsService.updateGoodsState(record);
		return new ApiResult(RESPONSE.SUCCESS, "成功", record);
	}

	/**
	 * 发表商品
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addGoods", method = RequestMethod.POST)
	public ApiResult addGoods(@RequestBody Goods goods) {
		goodsService.addGoods(goods);
		return new ApiResult(RESPONSE.SUCCESS, "请求成功");
	}

	/**
	 * 商品详情（用于修改商品信息）
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryGoodsInfo", method = RequestMethod.POST)
	public ApiResult queryGoodsInfo(@RequestBody String id) {
		String processId = JsonUtil.toObject(id, HashMap.class).get("id")
				.toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "请求成功",
				goodsService.queryGoodsInfo(it));
	}
	/**
	 * 根据商品的ID修改商品的价格和描述
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateGoodsInfo", method = RequestMethod.POST)
	public ApiResult updateGoodsInfo(@RequestBody Goods goods) {
		goodsService.updateGoodsInfo(goods);
		return new ApiResult(RESPONSE.SUCCESS, "请求成功");
	}
}
