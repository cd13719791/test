package com.moyou.moyouRms.controller.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseResourceControllr;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.report.Report;
import com.moyou.moyouRms.model.secret.Secret;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.report.ReportService;
import com.moyou.moyouRms.service.secret.SecretService;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.StringUtil;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("report")
public class ReprotController extends BaseResourceControllr {

	@Resource
	private ReportService reportService;
	@Resource
	private DiaryService diaryService;
	@Resource
	private UserService userService;
	@Resource
	private TalkService talkService;
	@Resource
	private CommonResourceService commonResourceService;
	@Resource
	MsgSystemXService msgSystemXService;
	@Resource
	private SecretService secretService;

	/**
	 * 举报管理初始化接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/selectReportsBySelctive", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectReportsBySelctive(@RequestBody Map<String, Object> record) {
		Assert.notNull(record, "参数不能为空");
		if (record.get("content") == null) {
			record.put("content", "");
		}
		if (StringUtil.isEmpty(record.get("orderBy"))) {
			record.put("orderBy", 0);
		}
		PageBean pb = this.getJsonWrapPageBean(record);
		List<Report> list = reportService.selectReportsBySelctive(pb);
		pb.setResults(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 举报管理 根据id获取详细举报属性
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "selectReportsById", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectReportsById(@RequestBody Map<String, Object> record) {
		Assert.notNull(record, "参数不能为空");
		PageBean pb = new PageBean();
		pb = super.getJsonWrapPageBean(record);
		pb.setPageNumber(1);
		pb.setPageSize(1);
		pb.setResults(super.setCommonResourceList(reportService.selectReportsBySelctive(pb)));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 举报管理 警告用户
	 * 
	 * @param {userId:"",id:""}
	 * @return
	 */
	@RequestMapping(value = "updateReportsOfWarning", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateReportsOfWarning(@RequestBody Report record) {
		record.setState(Report.REPORT_STATE_WARNING);
		reportService.updateReportsWithState(record);
		MsgSystemX msgSystemX = new MsgSystemX();
		msgSystemX.setModeType((short) 1);
		msgSystemX.setReceiveUserId(record.getToUserId());
		msgSystemX.setMsgContent(record.getMsg());
		msgSystemX.setSendUserId(0);// 系统发送的默认为0
		msgSystemX.setModeId(record.getId());
		msgSystemX.setMsgSendType(CONSTANT.SYS_USER_WARNING);
		msgSystemX.setMsgTitle(CONSTANT.SYS_USER_WARNING_TITLE);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				msgSystemXService.addSysMsgAndPushCustomMsg(msgSystemX));
	}

	/**
	 * 举报管理 忽略
	 * 
	 * @param {userId:"",id:""}
	 * @return
	 */
	@RequestMapping(value = "/updateReportsWithState", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateReportsWithState(@RequestBody Report record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功", reportService.updateReportsWithState(record));
	}

	/**
	 * 举报管理 删除内容
	 * 
	 * @param {userId:"",id:"",objectType:""}
	 * @return
	 */
	@RequestMapping(value = "updateReportsOfDelete", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateReportsOfDelete(@RequestBody Report record) {
		Assert.notNull(record, "参数不能为空");
		record = reportService.selectReportsById(record);
		if (record != null) {
			record.setState(3);
			reportService.updateReportsWithState(record);
		}
		switch (record.getObjectType()) {
		case Report.REPORT_OBJECT_TYPE_TALK: {// 删除说说
			Talk talk = new Talk();
			talk.setTalkId(record.getObjectId());
			talk.setCreatorId(record.getToUserId());
			talkService.deleteTalk(talk);// 假删除
			break;
		}
		case Report.REPORT_OBJECT_TYPE_DIARY: {// 删除日记
			Diary diary = new Diary();
			diary.setState(Integer.valueOf(Diary.DIARY_DELETE_NUMBER));
			diary.setId(record.getObjectId());
			diaryService.updateByPrimaryKeySelective(diary);
			diaryService.updateUserCountDiaryJian1(record.getToUserId());
			break;
		}
		case 6: {
			Secret secret = new Secret();
			secret.setState(Secret.DELETE);
			secret.setId(record.getObjectId());
			secretService.updateByPrimaryKeySelective(secret);
			secretService.updateUserCountSecretJian1(record.getToUserId());
			break;
		}
		default:
			return new ApiResult(RESPONSE.ERROR, "你以为什么都能删？搞笑？");
		}
		/**
		 * 推送给用户
		 * 
		 * int userId= record.getUserId();
		 */

		return new ApiResult(RESPONSE.SUCCESS, "成功", record);
	}

	/**
	 * 举报管理 限制用户
	 * 
	 * @param {userId:"",id:"",objectType:""}
	 * @return
	 */
	@RequestMapping(value = "updateReportsOfLimitUser", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateReportsOfLimitUser(@RequestBody Report record) {
		Assert.notNull(record, "参数不能为空");
		User user = new User();
		int userId = record.getUserId();
		user.setUserId(userId);
		user.setState(User.STATE_NO);// 修改用户状态为不可用
		userService.updateByPrimaryKeySelective(user);
		record.setState(Report.REPORT_STATE_LIMITT);
		reportService.updateReportsWithState(record);
		MsgSystemX msgSystemX = new MsgSystemX();
		msgSystemX.setReceiveUserId(userId);
		msgSystemX.setModeType((short) 1);
		msgSystemX.setMsgContent(CONSTANT.REG_LIMIT);
		msgSystemXService.insertWarn(msgSystemX);
		return new ApiResult(RESPONSE.SUCCESS, "成功", record);
	}

	/**
	 * 举报处理和未处理总数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryCountDispose", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryCountDispose() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", reportService.queryCountDispose());
	}

}
