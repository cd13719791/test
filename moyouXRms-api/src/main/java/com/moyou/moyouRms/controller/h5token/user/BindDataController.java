package com.moyou.moyouRms.controller.h5token.user;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.user.BindData;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.BindDataService;

@Controller
@RequestMapping("/h5token/bindData")
public class BindDataController extends BaseController {

	@Resource
	private BindDataService bindDataService;

	/**
	 * 绑定微信opendId
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody ApiResult insert(@RequestBody Map<String, Object> param) {
		Integer userId = getUserIdByToken(param.get("token") + "");
		BindData record = new BindData();
		record.setUserId(userId);
		record.setBindData1(param.get("bindData1") + "");
		record.setBindData2(param.get("bindData2") + "");
		record.setBindData3(Integer.valueOf(param.get("bindData3") + ""));
		record.setBindType(Short.valueOf((param.get("bindType") + "")));
		record.setCreateTime(new Date());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				bindDataService.insert(record));
	}

}
