package com.moyou.moyouRms.controller.h5;

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
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.secret.SecretCommentService;
import com.moyou.moyouRms.service.secret.SecretService;

@Controller
@RequestMapping("h5/secret")
@ResponseBody
public class H5SecretController extends BaseController {

	@Resource
	private SecretService secretService;
	@Resource
	private SecretCommentService secretCommentService;
	/**
	 * 秘密详情接口
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectSecretDetail", method = RequestMethod.POST)
	public ApiResult selectSecretDetail(@RequestBody Map<String,Object> record) {
		Assert.notNull(record, "参数不能为空");
		Secret secret=	new Secret();
		PageBean pb=this.getJsonWrapPageBean(record);
		secret.setId(Integer.valueOf(record.get("secretId").toString()));
		secret=secretService.selectH5SecretDetail(pb);
		return new ApiResult(RESPONSE.SUCCESS, "成功",secret);
	}
	/**
	 * 秘密评论接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectSecretCommentListBySecretId", method = RequestMethod.POST)
	public ApiResult selectSecretCommentListBySecretId(@RequestBody Map<String,Object> record) {
		Assert.notNull(record, "参数不能为空");
	PageBean pb=this.getJsonWrapPageBean(record);
	pb.setResults(secretCommentService.selectSecretCommentBySecretId(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功",pb);
	}

}
