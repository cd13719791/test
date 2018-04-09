package com.moyou.moyouRms.controller.secret;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.secret.SecretComment;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.secret.SecretCommentService;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.user.UserService;

@Controller
@RequestMapping("secret")
@ResponseBody
public class SecretController extends BaseController {
	@Resource
	private SecretService secretService;
	@Resource
	private SecretCommentService secretCommentService;
	@Resource
	UserService userService;

	/**
	 * 初始化接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/selectSecretListBySelctive", method = RequestMethod.POST)
	public ApiResult selectSecretListBySelctive(@RequestBody Map record) {
		Assert.notNull(record, "参数不能为空");
		PageBean pb = this.getJsonWrapPageBean(record);
		pb.setResults(secretService.selectSecretByPageBeanParam(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 统计接口 今日秘密总数 所有秘密总数
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/selectSecretCount", method = RequestMethod.POST)
	public ApiResult selectSecretCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				secretService.selectSecretCount());
	}

	/**
	 * 修改狀態为删除
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateSecretBySelctive", method = RequestMethod.POST)
	public ApiResult updateSecretBySelctive(@RequestBody Secret record) {
		Assert.notNull(record.getId(), "参数不能为空");
		record = secretService.selectByPrimaryKey(record.getId());
		record.setState(Secret.DELETE);
		secretService.updateByPrimaryKeySelective(record);
		secretService.updateUserCountSecretJian1(record.getUserId());
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 秘密详情接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectSecretDetail", method = RequestMethod.POST)
	public ApiResult selectSecretDetail(@RequestBody Map<String, Object> record) {
		Assert.notNull(record, "参数不能为空");
		Secret secret = new Secret();
		PageBean pb = this.getJsonWrapPageBean(record);
		secret.setId(Integer.valueOf(record.get("secretId").toString()));
		secret = secretService.selectSecretDetail(pb);
		return new ApiResult(RESPONSE.SUCCESS, "成功", secret);
	}

	/**
	 * 秘密评论接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectSecretCommentListBySecretId", method = RequestMethod.POST)
	public ApiResult selectSecretCommentListBySecretId(
			@RequestBody Map<String, Object> record) {
		Assert.notNull(record, "参数不能为空");
		PageBean pb = this.getJsonWrapPageBean(record);
		pb.setResults(secretCommentService.selectSecretCommentBySecretId(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 修改狀態为删除
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateSecretCommentBySelctive", method = RequestMethod.POST)
	public ApiResult updateSecretCommentBySelctive(
			@RequestBody SecretComment record) {
		Assert.notNull(record.getId(), "参数不能为空");
		secretCommentService.updateByPrimaryKeySelective(record);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

//	/**
//	 * insert Secret
//	 * 
//	 * @param insertModel
//	 * @return
//	 */
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//	public ApiResult insertSecret(@RequestBody SecretInsertModel insertModel) {
//		Integer secretId = secretService.insertSecret(insertModel);
//		Map<String, Object> map = Maps.newHashMap();
//		map.put("secretId", secretId);
//		return new ApiResult(map);
//	}

	/**
	 * 评论回复
	 * 
	 * @param talkComment
	 * @return
	 */
	@RequestMapping(value = "/insertCommentForFakeUser", method = RequestMethod.POST)
	public ApiResult insertCommentForFakeUser(
			@RequestBody SecretComment secretComment) {
		Assert.notNull(secretComment, "参数不能为空");
		secretComment.setCreateTime(new Date());
		secretService.insertComment(secretComment);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

}
