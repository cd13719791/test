package com.moyou.moyouRms.controller.h5;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.article.ArticleService;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年7月12日 下午4:06:41 
 * 类说明 
 */
@Controller
@RequestMapping("h5/article")
@ResponseBody
public class H5ArticleController extends BaseController{

	@Resource
	private ArticleService articleService;
	/**
	 * 推送文章详情接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectH5ArticleByArticleId", method = RequestMethod.POST)
	public ApiResult selectH5DiaryByDiaryId(@RequestBody Map<String,Integer> record) {
		Assert.notNull(record.get("articleId"), "参数不能为空");
		Integer id=Integer.valueOf(record.get("articleId").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功", articleService.selectByPrimaryKey(id));
	}
	
}
