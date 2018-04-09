package com.moyou.moyouRms.controller.chatRevert;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.chatRevert.ChatRevert;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.chatrevert.ChatRevertService;

@Controller
@RequestMapping(value = "/chatRevert")
@ResponseBody
public class ChatRevertController extends BaseController {
	@Resource
	ChatRevertService chatRevertService;

	/**
	 * 添加一跳常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ApiResult insertDiary(@RequestBody ChatRevert record) {
		record.setDataType(ChatRevert.CHAT);
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.insert(record));
	}

	/**
	 * 删除常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public ApiResult updateByPrimaryKeySelective(@RequestBody ChatRevert record) {
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 初始化常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/queryListChatRevert", method = RequestMethod.POST)
	public ApiResult queryListChatRevert() {
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.queryListChatRevert());
	}

	/**
	 * 添加一跳常用语
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insertaccost", method = RequestMethod.POST)
	public ApiResult insertAccost(@RequestBody ChatRevert record) {
		record.setDataType(ChatRevert.ACCOST);
		return new ApiResult(ResponseEnum.SUCCESS,
				chatRevertService.insert(record));
	}

	/**
	 * 初始化搭讪语
	 * 
	 * @param domain
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/querylistaccostrevert", method = RequestMethod.POST)
	public ApiResult queryListAccostRevert(@RequestBody Map<String,Object> map) {
		PageBean pb=this.getJsonWrapPageBean(map);
		pb.setResults(chatRevertService.queryListAccostRevert(pb));
		return new ApiResult(ResponseEnum.SUCCESS,
				pb);
	}
}
