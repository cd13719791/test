package com.moyou.moyouRms.controller.citymanager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.provinceCity.ProvinceCity;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.provincecity.ProvinceCityService;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年6月8日 下午5:28:33 
 * 类说明 
 */

@Controller
@RequestMapping("cityManager")
public class CityManagerController {

	@Resource
	ProvinceCityService provinceCityService;
	
	
	
	@RequestMapping(value = "/selectCity", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectCity() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				provinceCityService.selectCity());
	}
	
	@RequestMapping(value = "/updateCity", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateCity(@RequestBody List<ProvinceCity> list) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				provinceCityService.updateCity(list));
	}
}
