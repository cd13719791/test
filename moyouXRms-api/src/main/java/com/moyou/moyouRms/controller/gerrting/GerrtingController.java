package com.moyou.moyouRms.controller.gerrting;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.annotation.vaild.Validation;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.gerrting.GreetingAbstract;
import com.moyou.moyouRms.model.gerrting.GreetingUserSendLog;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.gerrting.GerrtingService;
import com.moyou.moyouRms.util.JsonUtil;

@RestController
@RequestMapping(value = "/gerrting")
public class GerrtingController extends BaseController {
	@Resource
	private GerrtingService gerrtingService;

	/**
	 * 查询最近的节日祝福卡片和内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryBlessing", method = RequestMethod.GET)
	public ApiResult queryBlessing() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.queryBlessing());
	}

	/**
	 * 插入一条祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@RequestMapping(value = "/insertBlessing", method = RequestMethod.POST)
	public ApiResult insertBlessing(
			@RequestBody GreetingUserSendLog greetingUserSendLog) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.insertBlessingLog(greetingUserSendLog));
	}

	/**
	 * 查询一条祝福语句
	 * 
	 * @param blessingId
	 * @return
	 */
	@RequestMapping(value = "/queryBlessingById", method = RequestMethod.POST)
	@Validation(notNull = { "blessingId" })
	public ApiResult queryBlessingDetails(
			@RequestBody Map<String, Object> params) {
		Integer blessingId = (Integer.valueOf(params.get("blessingId")
				.toString()));
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.queryBlessingById(blessingId));
	}

	/**
	 * 卡片id查询图片集合
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/queryBlessingPicList", method = RequestMethod.POST)
	@Validation(notNull = { "blessingId" })
	public ApiResult queryBlessingPicList(
			@RequestBody Map<String, Object> params) {
		Integer blessingId = (Integer.valueOf(params.get("blessingId")
				.toString()));
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.queryBlessingPicList(blessingId));
	}

	/**
	 * 初始化祝福语管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryGreetingAbstractList", method = RequestMethod.POST)
	public ApiResult queryGreetingAbstractList(@RequestBody Page page) {
		gerrtingService.queryGreetingAbstractList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 根据节日名查询节日封面图片和节日内容
	 * 
	 * @param festivalTitle
	 * @return
	 */
	@RequestMapping(value = "/queryGreetingAbstractInfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryGreetingAbstractInfo(
			@RequestBody Page page) {
		gerrtingService.queryGreetingAbstractInfo(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 添加祝福语
	 * 
	 * @param gerrting
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/AddGreeting", method = RequestMethod.POST)
	public @ResponseBody ApiResult AddGreeting(
			@RequestBody GreetingUserSendLog greetingUserSendLog) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.AddGreeting(greetingUserSendLog));
	}

	/**
	 * 根据节日ID删除封面图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteGreeting", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteGreeting(
			@RequestBody GreetingAbstract greetingAbstract) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.deleteGreeting(greetingAbstract));
	}

	/**
	 * 根据内容ID删除内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteGreetingContent", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteGreetingContent(
			@RequestBody GreetingAbstract greetingAbstract) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.deleteGreetingContent(greetingAbstract));
	}

	/**
	 * 根据状态禁用或者启用
	 * 
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/updateGreetingState", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateGreetingState(@RequestBody String state) {
		String processId = JsonUtil.toObject(state, HashMap.class).get("state")
				.toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.updateGreetingState(it));
	}

	/**
	 * 初始化祝福语管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryGreetingAbstract", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryGreetingAbstract(@RequestBody String id) {
		String processId = JsonUtil.toObject(id, HashMap.class).get("id")
				.toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.queryGreetingAbstract(it));
	}

	/**
	 * 插入一条祝福语
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@RequestMapping(value = "/insertGreetingContent", method = RequestMethod.POST)
	public ApiResult insertGreetingContent(
			@RequestBody GreetingUserSendLog greetingUserSendLog) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.insertBlessing(greetingUserSendLog));
	}

	/**
	 * 根据状态禁用或者启用
	 * 
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/updateGreetingContent", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateGreetingContent(
			@RequestBody GreetingAbstract greetingAbstract) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				gerrtingService.updateGreetingContent(greetingAbstract));
	}

}
