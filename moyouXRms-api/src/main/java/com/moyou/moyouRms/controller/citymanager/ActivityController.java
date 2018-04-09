package com.moyou.moyouRms.controller.citymanager;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.everyactivity.EveryActivityService;
import com.moyou.moyouRms.service.everyactivityreceive.EveryActivityReceiveService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;

/**
 * @author created by Chenxv
 * @date 2017年8月14日 下午2:16:46
 */
@Controller
@RequestMapping("activity")
public class ActivityController extends BaseController {
	@Resource
	private EveryActivityReceiveService everyActivityReceiveService;
	@Resource
	private EveryActivityService everyActivityService;
	@Resource
	private UserFundLogService userFundLogService;

	/**
	 * 初始化活动明细
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectactivitylist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectActivityList(@RequestBody Map<String, Object> map) {
		PageBean pb = this.getJsonWrapPageBean(map);
		pb.setResults(everyActivityService.selectActivityInfoList(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 查询昨天今天和每天的活动打赏额
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectactivitysum", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectActivitySum() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", everyActivityService.selectActivitySum());
	}

	/**
	 * 修改备注
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateactivity", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateActivity(@RequestBody EveryActivity record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				everyActivityService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 查询用户领奖记录
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectactivitylog", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectActivityReceive(@RequestBody Map<String, Object> map) {
		Integer type = Integer.valueOf(map.get("type").toString());
		switch (type) {
		case 1:// 说说
			map.put("modeTypeOne", 31);// 说说获得金钱
			map.put("modeTypeTwo", 37);// 说说获得金币
			break;
		case 2:// 故事
			map.put("modeTypeOne", 32);
			map.put("modeTypeTwo", 38);
			break;
		case 3:// 秘密
			map.put("modeTypeOne", 33);
			map.put("modeTypeTwo", 39);
			break;
		case 4:// 评论
			map.put("modeTypeOne", 34);
			map.put("modeTypeTwo", 40);
			break;
		case 5:// 全局分享
			map.put("modeTypeOne", 35);
			map.put("modeTypeTwo", 41);
			break;
		case 6:// 系统分享
			map.put("modeTypeOne", 36);
			map.put("modeTypeTwo", 42);
			break;
		default:
			break;
		}
		// map.put("searchCategory",
		// UserFundSrarchCategoryEnum.ACTIVITY_EARN.getValue());
		PageBean pb = this.getJsonWrapPageBean(map);
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundLogService.selectActivityLog(pb));
	}
}