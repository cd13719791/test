package com.moyou.moyouRms.controller.talk;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.talk.ComentTwo;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.talk.TalkAndUserInfo;
import com.moyou.moyouRms.model.talk.TalkComment;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.DistanceUtil;
import com.moyou.moyouRms.util.JsonUtil;

@RestController
@RequestMapping(value = "/talk")
public class TalkController extends BaseController {
	@Resource
	private TalkService talkService;
	@Resource
	private UserService userService;

	/**
	 * 发表说说
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insertTalk", method = RequestMethod.POST)
	public ApiResult insertTalk(@RequestBody Talk talk) {
		Assert.notNull(talk, "参数不能为空");
		talkService.addTalk(talk);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 评论回复
	 * 
	 * @param talkComment
	 * @return
	 */
	@RequestMapping(value = "/insertComment", method = RequestMethod.POST)
	public ApiResult insertComment(@RequestBody TalkComment talkComment) {
		Assert.notNull(talkComment, "参数不能为空");
		talkComment.setCreateTime(new Date());
		talkService.insertComment(talkComment);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 说说管理模块——初始化4个总量
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryNewCountTalk() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.queryNewCountTalk());
	}

	/**
	 * 说说模块初始化数据
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkList(@RequestBody TalkAndUserInfo talkAndUserInfo) {
		talkService.queryTalkList(talkAndUserInfo);
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkAndUserInfo);
	}

	/**
	 * 根据说说ID查询说说详情以及说说的评论
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkInfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkInfo(@RequestBody Map<String, Object> map) {
		String processId = map.get("talkId").toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.queryTalkInfo(it));
	}

	/**
	 * 根据说说ID删除一条说说（假删除）
	 * 
	 * @param talkId
	 * @return
	 */
	@RequestMapping(value = "/deleteTalk", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteTalk(@RequestBody Talk talk) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.deleteTalk(talk));
	}

	/**
	 * 根据说说评论Id删除一条评论(假删除)
	 * 
	 * @param talkCommentId
	 * @return
	 */
	@RequestMapping(value = "/deleteTalkComment", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteTalkComment(@RequestBody ComentTwo comentTwo) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.deleteTalkComment(comentTwo));
	}

	/**
	 * 根据userId查询所有说说 state=0
	 * 
	 * @param talkCommentId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryTalkByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkByUserId(@RequestBody Map param) {
		String creatorId = param.get("creatorId").toString();
		int pageNum = Integer.valueOf(param.get("pageNumber").toString());
		int pageSize = Integer.valueOf(param.get("pageSize").toString());
		// String processId = JsonUtil.toObject(creatorId, HashMap.class)
		// .get("creatorId").toString();
		PageBean pageBean = new PageBean();
		Map map = new HashMap();
		map.put("creatorId", creatorId);
		pageBean.setPageNumber(pageNum);
		pageBean.setPageSize(pageSize);
		pageBean.setConditions(map);
		List<Talk> list = talkService.queryTalkByUserId(pageBean);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCountCommentTotal(
						DistanceUtil.handleDataInfo(Integer
								.valueOf(list.get(i).getCommentTotal() != null ? list.get(i)
										.getCommentTotal() : 0)));
				list.get(i).setCountReadTotal(
						DistanceUtil.handleDataInfo(Integer
								.valueOf(list.get(i).getReadTotal() != null ? list.get(i)
										.getReadTotal() : 0)));
				list.get(i).setCountsupportTotal(
						DistanceUtil.handleDataInfo(Integer
								.valueOf(list.get(i).getSupportTotal() != null ? list.get(i)
										.getSupportTotal() : 0)));
				list.get(i).setCountRewardTotal(
						DistanceUtil.handleDataInfo(Integer
								.valueOf(list.get(i).getRewardTotal() != null ? list.get(i)
										.getRewardTotal() : 0)));
			}
		}
		pageBean.setResults(list);
		// pageBean.setTotal(list.size());
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	// Opretion
	/**
	 * 说说模块初始化数据
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectTalkList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkListOpretion(
			@RequestBody TalkAndUserInfo talkAndUserInfo) {
		talkService.queryTalkList(talkAndUserInfo);
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkAndUserInfo);
	}

	/**
	 * 根据说说ID查询说说详情以及说说的评论
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectTalkInfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkInfoOpretion(@RequestBody String talkId) {
		String processId = JsonUtil.toObject(talkId, HashMap.class).get("talkId").toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.queryTalkInfo(it));
	}

	/**
	 * 根据说说评论Id删除一条评论(假删除)
	 * 
	 * @param talkCommentId
	 * @return
	 */
	@RequestMapping(value = "/updateTalkComment", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteTalkCommentOpretion(@RequestBody ComentTwo comentTwo) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.deleteTalkComment(comentTwo));
	}

	/**
	 * 评论回复
	 * 
	 * @param talkComment
	 * @return
	 */
	@RequestMapping(value = "/insertCommentForFakeUser", method = RequestMethod.POST)
	public ApiResult insertCommentForFakeUser(@RequestBody TalkComment talkComment) {
		Assert.notNull(talkComment, "参数不能为空");
		talkComment.setCreateTime(new Date());
		Integer userId = userService.queryFakeUserBySex(talkComment.getSex()).getUserId();
		talkComment.setUserId(userId);
		talkService.insertComment(talkComment);
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 批量删除说说
	 * 
	 * @param talk
	 * @return
	 */
	@RequestMapping(value = "/updateTalkList", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateTalkList(@RequestBody List<Talk> talk) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.updateTalkList(talk));
	}
}
