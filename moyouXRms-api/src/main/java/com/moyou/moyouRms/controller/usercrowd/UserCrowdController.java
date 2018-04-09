package com.moyou.moyouRms.controller.usercrowd;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.QiNiuFileEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdPage;
import com.moyou.moyouRms.qiniu.entity.QiNiuUploadFileConfing;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.usercrowd.UserCrowdService;
import com.moyou.moyouRms.util.JsonUtil;

@Controller
@RequestMapping(value = "/userCrowd")
public class UserCrowdController extends BaseController {
	@Resource
	private UserCrowdService userCrowdService;
	@Resource
	private UserService userService;
	@Resource
	private QiNiuUploadFileConfing qiNiuUploadFileConfing;

	/**
	 * 初始化群总数和今日新增群数
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryCountUserCrowd", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryCountUserCrowd() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.queryCountUserCrowd());
	}

	/**
	 * 群運營初始化
	 * 
	 * @param pagebean
	 * @return
	 */
	@RequestMapping(value = "/queryUserCrowdList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserCrowdList(@RequestBody UserCrowdPage userCrowdPage) {
		userCrowdService.queryUserCrowdList(userCrowdPage);
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdPage);
	}

	/**
	 * 创建群
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insertCrowd", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertCrowd(@RequestBody UserCrowd record) {
		record.setCrowdCreateType(UserCrowd.ADMIN_CREATE_TYPE);
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.addCrowd(record));
	}

	/**
	 * 查询假用户，用于群运营，选择群主
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryFakeUserList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryFakeUserList(@RequestBody Page page) {
		userService.queryFakeUserList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 七牛上传
	 *
	 */
	@RequestMapping(value = "/qiniuFileUpload", method = RequestMethod.GET)
	public @ResponseBody ApiResult qiniuFileUpload(@RequestParam int type) {
		if (QiNiuFileEnum.AUDIO.getValue() == type) {
			return new ApiResult(RESPONSE.SUCCESS, "成功", qiNiuUploadFileConfing.getAudioUpToken());
		}
		if (QiNiuFileEnum.VIDEO.getValue() == type) {
			return new ApiResult(RESPONSE.SUCCESS, "成功", qiNiuUploadFileConfing.getVideoUpToken());
		}
		if (QiNiuFileEnum.IMAGE.getValue() == type) {
			return new ApiResult(RESPONSE.SUCCESS, "成功", qiNiuUploadFileConfing.getImageUpToken());
		}
		return new ApiResult(RESPONSE.ERROR, "参数有误");
	}

	/**
	 * 根据群编号查询群信息
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryUserCrowdInfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserCrowdInfo(@RequestBody String crowdId) {
		String processId = JsonUtil.toObject(crowdId, HashMap.class).get("id").toString();
		Integer crowdNumber = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userCrowdService.queryUserCrowdInfo(crowdNumber));
	}

	/**
	 * 查询群成员信息
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryUserCrowdMembersList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserCrowdMembersList(@RequestBody Page page) {
		userCrowdService.queryUserCrowdMembersList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 根据群编号解散群（假删除）
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateUserCrowdState", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserCrowdState(@RequestBody String crowdId) {
		String processId = JsonUtil.toObject(crowdId, HashMap.class).get("crowdId").toString();
		Integer crowdIds = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userCrowdService.updateUserCrowdState(crowdIds));
	}

	/**
	 * 
	 * 根据群主建Id查询群成员资料
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryCrowdMemberList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryCrowdMemberList(@RequestBody String crowdPkId) {
		String processId = JsonUtil.toObject(crowdPkId, HashMap.class).get("crowdPkId").toString();
		Long crowdPkIds = Long.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userCrowdService.queryCrowdMemberList(crowdPkIds));
	}

	/**
	 * 修改群资料
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateByPrimaryKeySelective(@RequestBody UserCrowd record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userCrowdService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 添加群成员数
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addCrowdUser", method = RequestMethod.POST)
	public @ResponseBody ApiResult addCrowdUser(@RequestBody UserCrowd record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.addCrowdUser(record));
	}

	/**
	 * 删除群用户
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/deleteMemberCrowd", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteMemberCrowd(@RequestBody UserCrowd record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.deleteMemberCrowd(record));
	}

	/**
	 * 临时用来添加群用户
	 */
	@RequestMapping(value = "/addCrowdUser2", method = RequestMethod.POST)
	public @ResponseBody ApiResult addCrowdUser2() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.addCrowdUser2());
	}

	/**
	 * 推荐群 和取消推荐
	 */
	@RequestMapping(value = "/updateCrowdRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateCrowdRecommend(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.updateCrowdRecommend(map));
	}

	/**
	 * 推荐群 和取消推荐
	 */
	@RequestMapping(value = "/selectcrowdinfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectCrowdInfo(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userCrowdService.selectCrowdInfo(map));
	}

}
