package com.moyou.moyouRms.controller.usercrowd;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.category.Category;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.category.CategoryService;
import com.moyou.moyouRms.util.UUIDUtil;
@Controller
@ResponseBody
@RequestMapping(value = "/category")
/**
 * 群分类接口
 * @author Administrator
 *
 */
public class CategoryController {
	@Resource
	private CategoryService categoryServiceImpl;
	/**
	 * 群分类初始化
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectCategoryList", method = RequestMethod.POST)
	public ApiResult selectCategoryList(@RequestBody PageBean pageBean) {
		Assert.notNull(pageBean, "参数不能为空");
		categoryServiceImpl.selectCategoryList(pageBean);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}
	/**
	 * 群分类修改群分类名称
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public ApiResult updateByPrimaryKeySelective(@RequestBody Category category) {
		Assert.notNull(category, "参数不能为空");
		categoryServiceImpl.updateByPrimaryKeySelective(category);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}
	
	/**
	 * 添加群分类
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
	public ApiResult insertCategory(@RequestBody Category category) {
		Assert.notNull(category, "参数不能为空");
		category.setId(UUIDUtil.getUUID());
		category.setCategoryStatus(Category.CATEGORY_STATUS_YES);
		category.setModeType(Category.MODE_TYPE_CROWD);
		category.setCreateTime(new Date());
		categoryServiceImpl.insertSelective(category);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}
}
