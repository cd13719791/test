package com.moyou.moyouRms.controller.statistics;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.statistics.NewUser;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.statistics.StatisticsService;
import com.moyou.moyouRms.service.statistics.UserFundRegulationService;

@Controller
@RequestMapping("statistics")
public class StatisticsController extends BaseController {
	@Resource
	StatisticsService statisticsService;
	@Resource
	private UserFundRegulationService userFundRegulationService;

	/**
	 * 资金池
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserFundList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundList(@RequestBody UserFund record) {
		try {
			return new ApiResult(RESPONSE.SUCCESS, "成功",
					statisticsService.queryUserFundList(record));
		} catch (Exception e) {
			return new ApiResult(RESPONSE.ERROR, e.getMessage());
		}
	}

	/**
	 * 资金池 V2
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryuserfundregulationlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundRegulationList(
			@RequestBody Map<String, Object> record) {
		try {
			return new ApiResult(RESPONSE.SUCCESS, "成功",
					userFundRegulationService.queryUserFundRegulationList(record));
		} catch (Exception e) {
			return new ApiResult(RESPONSE.ERROR, e.getMessage());
		}
	}

	/**
	 * 新增人数统计池
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryusercountlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserCountList(@RequestBody NewUser record) {
		try {
			return new ApiResult(RESPONSE.SUCCESS, "成功",
					statisticsService.queryUserCountList(record));
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(RESPONSE.ERROR, e.getMessage());
		}
	}

	/**
	 * 消费
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryConsumeList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryConsumeList() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", statisticsService.queryConsumeList());
	}

	/**
	 * 小程序
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryConMiniApps", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryConMiniApps() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", statisticsService.queryConMiniApps());
	}

	/**
	 * 发布
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryIssue", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryIssue() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", statisticsService.queryIssue());
	}

	/**
	 * 用户
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryUserStatistics", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserStatistics() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", statisticsService.queryUserStatistics());
	}

	/**
	 * 机器人设置
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkRobot", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkRobot() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", statisticsService.queryTalkRobot());
	}

	public static void main(String[] args) {

	}
}
