package com.moyou.moyouRms.controller.h5;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.gerrting.GreetingAbstract;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.gerrting.GerrtingService;

@Controller
@RequestMapping("/h5/Gerrting")
public class H5GerrtingController {

	@Resource
	private GerrtingService gerrtingService;

	/**
	 * 查询一条祝福语句
	 * 
	 * @param blessingId
	 * @return
	 */
	@RequestMapping(value = "/queryBlessingDetails", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryBlessingDetails(@RequestBody GreetingAbstract greetingAbstract) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",gerrtingService.queryBlessingDetails(greetingAbstract));
	}
}
