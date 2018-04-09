package com.moyou.moyouRms.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.UserList;
import com.moyou.moyouRms.model.userRecommend.UserRecommend;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userrecommend.UserRecommendService;
import com.moyou.moyouRms.service.usersign.UserSignService;

/**
 * 推荐用户列表
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/recommend")
public class RecommendUserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private UserCountService userCountService;
	@Resource
	private DiaryService diaryService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserRecommendService userRecommendService;
	PageBean pageBean = null;
	@Resource
	MsgSystemXService msgSystemXService;
	@Resource
	EaseMobService easeMobService;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserSignService userSignService;

	/**
	 * 查询所有用户(个人主页)
	 * 
	 * @param userId
	 * @return
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryUserLists", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserLists(@RequestBody Map map) {
		// map.put("state", "1");
		PageBean pageBean = getJsonWrapPageBean(map);
		List<UserList> list = userService.queryAllUsers(pageBean);
		for (int i = 0; i < list.size(); i++) {
			java.util.Date date = new Date();
			if (null == list.get(i).getBirthday()) {
				continue;
			}
			java.util.Date mydate = (list.get(i).getBirthday());
			long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) + 1;
			int year = (int) Double.parseDouble(new java.text.DecimalFormat("").format(day / 365f));
			list.get(i).setAge(year + "");
			list.get(i).setContinuousSignCount(
					userSignService.getUSerSignByUserId(list.get(i).getUserId())
							.getContinuousSignCount());
		}
		// pageBean = new PageBean();
		pageBean.setResults(list);
		// pageBean.setTotal(list.size());
		return new ApiResult(ResponseEnum.SUCCESS, pageBean);
	}

	/**
	 * 推荐用户
	 */
	@RequestMapping(value = "/insertUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertUserRecommend(@RequestBody Map<String, Object> user) {
		Assert.notNull(user.get("userId"), "参数为空");
		int userId = Integer.valueOf(user.get("userId").toString());
		short recommendState = Short.valueOf(user.get("recommendState").toString());
		UserRecommend userRecommend = userRecommendService.selectByUsreId(userId);
		int index = 0;
		if (userRecommend == null) {
			userRecommend = new UserRecommend();
			userRecommend.setModeType(Short.valueOf(user.get("modeType") + ""));
			userRecommend.setRecommedStatus(recommendState);
			userRecommend.setUserId(userId);
			userRecommend.setRecommendSort((short) 1);
			userRecommend.setCreateTime(new Date());
			userRecommend.setUpdateTime(new Date());
			index = userRecommendService.insert(userRecommend);
		} else {
			userRecommend.setRecommedStatus(recommendState);
			index = userRecommendService.updateByPrimaryKeySelective(userRecommend);
		}
		if (1 == index) {
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
	}

	/**
	 * 查询所有推荐用户
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserRecommend(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数为空");
		PageBean pb = super.getJsonWrapPageBean(param);
		pb.setResults(userService.queryUserRecommend(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 取消推荐用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserRecommend(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数为空");
		Integer userId = Integer.valueOf(param.get("userId").toString());
		short modeType = Short.valueOf(param.get("modeType").toString());
		UserRecommend userRecommend = userRecommendService.selectByUsreId(userId);
		userRecommend.setRecommedStatus(UserRecommend.STATE_NO);
		userRecommend.setModeType(modeType);
		userRecommend.setUpdateTime(new Date());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userRecommendService.updateByPrimaryKeySelective(userRecommend));
	}
}
