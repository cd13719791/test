package com.moyou.moyouRms.service.diary.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.PushAlertEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.TalkPraiseSateEnum;
import com.moyou.moyouRms.constants.enums.UserDeviceClientTypeEnum;
import com.moyou.moyouRms.dao.diary.DiaryCommentMapper;
import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.dao.diary.DiaryPraiseMapper;
import com.moyou.moyouRms.dao.diary.resultmodel.DirayH5ResultModel;
import com.moyou.moyouRms.dao.diaryContentRobot.DiaryContentRobotMapper;
import com.moyou.moyouRms.dao.diaryRecommend.DiaryRecommendMapper;
import com.moyou.moyouRms.dao.diaryRobot.DiaryRobotMapper;
import com.moyou.moyouRms.dao.notification.UserNotificationMapper;
import com.moyou.moyouRms.dao.talk.TalkMapper;
import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.jpush.JpushService;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryComment;
import com.moyou.moyouRms.model.diary.DiaryContentInsertDomain;
import com.moyou.moyouRms.model.diary.DiaryInsertDomain;
import com.moyou.moyouRms.model.diary.DiaryPraise;
import com.moyou.moyouRms.model.diary.UserReceivceParam;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.h5shareset.H5ShareSet;
import com.moyou.moyouRms.model.notification.UserNotification;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.response.Msg;
import com.moyou.moyouRms.service.H5shareset.H5ShareSetService;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userreceiveshare.UserReceiveShareService;
import com.moyou.moyouRms.urlshareanalysis.entity.UserReceiveDataAnalysisResult;
import com.moyou.moyouRms.urlshareanalysis.manager.UserReceiveShareFactory;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class DiaryServiceImpl implements DiaryService {
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DiaryServiceImpl.class);
	@Resource
	private DiaryMapper diaryMapper;
	@Resource
	private DiaryCommentMapper diaryCommentMapper;
	@Resource
	private DiaryContentService diaryContentService;
	@Resource
	private DiaryPraiseMapper diaryPraiseMapper;
	@Resource
	private UserCountMapper userCountMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private DiaryRobotMapper diaryRobotMapper;
	@Resource
	private UserService userService;
	@Resource
	private DiaryContentRobotMapper diaryContentRobotMapper;
	@Resource
	private DiaryRecommendMapper diaryRecommendMapper;
	@Resource
	private TalkMapper talkMapper;
	@Resource
	UserReceiveShareService userReceiveShareService;
	@Resource
	UserNotificationMapper userNotificationMapper;
	@Resource
	JpushService jpushService;
	@Resource
	UserReceiveShareFactory userReceiveShareFactory;
	@Resource
	H5ShareSetService h5ShareSetService;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Diary record) {
		// TODO Auto-generated method stub
		return diaryMapper.insert(record);
	}

	@Override
	public int insertSelective(Diary record) {
		// TODO Auto-generated method stub
		return diaryMapper.insertSelective(record);
	}

	@Override
	public Diary selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Diary record) {
		// TODO Auto-generated method stub
		return diaryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Diary record) {
		// TODO Auto-generated method stub
		return diaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Diary> selectDiaryListBySelective(PageBean record) {
		// TODO Auto-generated method stub
		return diaryMapper.selectDiaryListBySelective(record);
	}

	@Override
	public Map<String, Integer> selectDiaryCount() {
		// TODO Auto-generated method stub
		return diaryMapper.selectDiaryCount();
	}

	@Override
	public List<Diary> selectDiaryListByDiaryFolderId(Integer id) {
		// TODO Auto-generated method stub
		List<Diary> list = diaryMapper.selectDiaryListByDiaryFolderId(id);
		list.forEach(diary -> {
			diary.setFirstTextAndPic(diaryContentService.selectFirstDiaryContentListByDiaryId(diary
					.getId()));
		});
		return list;
	}

	@Override
	public List<Diary> selectDiaryListByUserId(PageBean record) {
		// TODO Auto-generated method stub
		List<Diary> diaryList = diaryMapper.selectDiaryListByUserId(record);
		diaryList.forEach(dairy -> {
			dairy.setFirstTextAndPic(diaryContentService.selectFirstDiaryContentListByDiaryId(dairy
					.getId()));
		});
		return diaryList;
	}

	/**
	 * 初始化h5分享故事接口
	 * 
	 * @param list
	 * @return
	 */
	@Override
	public Diary selectH5DiaryByDiaryId(Integer id) {
		Diary diary = diaryMapper.selectByPrimaryKey(id);
		if (diary == null) {
			return null;
		}
		diary.setDiaryContents(diaryContentService.selectDiaryContentListByDiaryId(diary.getId()));
		List<DiaryComment> list = new CopyOnWriteArrayList<DiaryComment>();
		list = diaryCommentMapper.selectDiaryCommentListByDiaryId(diary.getId());
		list = this.setChildComment(list);
		diary.setDiaryComments(list);
		List<DiaryPraise> diaryPraiseList = diaryPraiseMapper.selectDiaryPraiseListByDiaryId(id);
		diary.setDiaryPraiseList(diaryPraiseList);
		return diary;
	}

	/**
	 * 初始化子评论
	 * 
	 * @param list
	 * @return
	 */
	private List<DiaryComment> setChildComment(List<DiaryComment> list) {
		/**
		 * 给评论初始化 子级评论
		 */
		List<DiaryComment> diaryList = new ArrayList<DiaryComment>();
		list.forEach(diaryComment -> {
			list.forEach(child -> {
				if (child.getParentId() == diaryComment.getId()) {
					List<DiaryComment> newList = diaryComment.getChildComment();
					newList.add(child);
					diaryComment.setChildComment(newList);
					diaryList.add(child);
				}
			});
		});
		list.removeAll(diaryList);
		diaryList.clear();
		/**
		 * 删除所有没有父级的评论 ------有可能查询出来的子评论父级已经被删除
		 */
		// list.forEach(diaryComment ->{
		// if(0!=diaryComment.getParentId()){
		// list.remove(diaryComment);
		// }
		// });
		return list;
	}

	@Override
	public Integer updateUserCountDiaryJian1(Integer userId) {
		return userCountMapper.updateUserCountDiaryJian1(userId);
	}

	/**
	 * 发布日记
	 * 
	 * @param domain
	 * @return
	 */
	@Override
	public int insertDiary(DiaryInsertDomain diaryInsertDomain) {
		DiaryRobot dr = new DiaryRobot();
		dr.setCreateTime(new Date());
		dr.setDiaryTitle(diaryInsertDomain.getDiaryTitle());
		dr.setPushState(SecretRobot.UNPUBLISH_STATE);
		dr.setState(SecretRobot.PUBLISH_STATE);
		dr.setPushTime(diaryInsertDomain.getPushTime());
		dr.setSex(diaryInsertDomain.getGender());
		/*
		 * DataframeInDC dc=new DataframeInDC(); Map<String, ArrayList<String>>
		 * dataMap = dc.getColValues(); List number = dataMap.get("sorting");
		 * List text1 = dataMap.get("textOrPicture"); int index=number.size();
		 * for (int i = 0; i < index-1; i++) {
		 * dr.setSearchContent(text1.get(i)); }
		 */
		char a = (char) 10;
		dr.setSearchContent(diaryInsertDomain.getContents().parallelStream()
				.filter(item -> item.getContenType().intValue() == 0)
				.map(DiaryContentInsertDomain::getTextOrPicture).collect(Collectors.joining())
				.replaceAll("\n", a + ""));

		diaryRobotMapper.insertSelective(dr);
		Integer diaryId = dr.getId();
		diaryInsertDomain.getContents().parallelStream().forEach(content -> {
			content.setDiaryId(diaryId);
			if (content.getContenType().intValue() == 1) {
				content.setTextOrPicture(content.getTextOrPicture());
			}
		});
		return diaryContentRobotMapper.insertDiaryContents(diaryInsertDomain.getContents());
	}

	@Override
	public int insertComment(DiaryComment diary) {
		// TODO Auto-generated method stub
		diary.setCreateTime(new Date());
		User user = userService.queryFakeUserBySex(diary.getSex());
		diary.setUserId(user.getUserId());
		diary.setState(Integer.valueOf(DiaryComment.DIARY_COMMENT_NORMAL_NUMBER));
		diaryCommentMapper.insertSelective(diary);
		// try {
		// UserNotification userNotification = userNotificationMapper
		// .queryUserNotificationByDiaryId(diary.getId());
		// userNotification.setActionType(1);
		// userNotification.setBusinessId(diary.getDiaryId());
		// userNotification.setBusinessType(2);
		// userNotification.setCommentRelateId(diary.getId());
		// userNotification.setCommentTextContent(diary.getContent());
		// userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_COMMENT_DIARY);
		// userNotification.setSendUserId(diary.getUserId());
		// userNotification.setBusinessTextContent(diary.getContent());
		// userNotification.setPic(diaryContentService
		// .selectFirstDiaryContentListByDiaryId(diary.getDiaryId()).get("picture")
		// .toString());
		// jpushService.sendMessgePushCustomMsgToDB(userNotification);
		// // result = 1;
		// } catch (Exception e) {
		// // System.out.println("说说评论推送失败！！！！评论======>"+talkComment.getId());
		// }
		UserNotification userNotification = null;
		if (diary.getParentId() == null || diary.getParentId().intValue() == 0) {
			userNotification = userNotificationMapper.queryUserNotificationByDiaryId(diary.getId());
			userNotification.setActionType(1);
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_COMMENT_DIARY);
		} else {
			userNotification = userNotificationMapper.queryUserNotificationByDiaryId(diary.getId());
			userNotification.setActionType(5);
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_RECOMMENT);
		}

		userNotification.setBusinessId(diary.getDiaryId());
		userNotification.setCommentRelateId(diary.getId());
		userNotification.setCommentTextContent(diary.getContent());
		userNotification.setSendUserId(diary.getUserId());
		userNotification.setBusinessType(2);
		String titleOrContent = diaryMapper.queryDiaryTitleOrContent(diary.getDiaryId());
		userNotification.setBusinessTextContent(titleOrContent);
		jpushService.sendNotificationPushCustomMsgToDB(userNotification);

		return diaryMapper.updateDiaryCommentTotalJIA1(diary.getDiaryId());
	}

	@Override
	public int updateDiaryReadCount(Diary diary) {
		// TODO Auto-generated method stub
		return diaryMapper.updateDiaryReadCount(diary);
	}

	@Override
	public TalkPraiseSateEnum queryPraiseState(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return TalkPraiseSateEnum.getByValue(diaryMapper.queryPraiseState(params));
	}

	@Override
	public int insertPraise(Map<String, Object> params) {
		// TODO Auto-generated method stub
		DiaryPraise diaryPraise = new DiaryPraise();
		diaryPraise.setDiaryId(Integer.valueOf(params.get("diaryId") + ""));
		diaryPraise.setUserId(Integer.valueOf(params.get("userId") + ""));
		diaryPraise.setState(DiaryPraise.PRAISE);
		List<DiaryPraise> list = diaryPraiseMapper
				.selectDiaryPraiseListByDiaryIdAndUserId(diaryPraise);
		if (list == null || list.size() == 0) {
			diaryPraiseMapper.insertByParam(diaryPraise);
		} else {
			for (int i = 0; i < list.size() - 1; i++) {
				diaryPraiseMapper.deleteByPrimaryKey(list.get(i).getId());
			}
			diaryPraiseMapper.updateByPrimaryKeySelective(list.get(list.size() - 1));
		}
		try {
			UserNotification userNotification = userNotificationMapper
					.queryUserNotificationByDiaryId(diaryPraise.getDiaryId());
			userNotification.setActionType(2);
			userNotification.setBusinessId(diaryPraise.getDiaryId());
			userNotification.setBusinessType(2);
			userNotification.setPushAlertEnum(PushAlertEnum.JPUSH_PRAISE_DIARY);
			userNotification.setSendUserId(diaryPraise.getUserId());
			jpushService.sendMessgePushCustomMsgToDB(userNotification);
		} catch (Exception e) {
			// System.out.println("故事点赞推送失败！！！！故事Id======>"+diaryPraise.getDiaryId());
		}
		return diaryMapper.updateDiaryPraisejia1(Integer.valueOf(params.get("diaryId") + ""));

	}

	@Override
	public int updateh5UrlById(Diary t) {
		// TODO Auto-generated method stub
		return diaryMapper.updateh5UrlById(t);
	}

	@Override
	public int insertReferenceDiary(Integer diaryId) {
		// TODO Auto-generated method stub
		Talk talk = new Talk();
		Diary diary = diaryMapper.selectByPrimaryKey(diaryId);
		if (diary == null) {
			return RESPONSE.ERROR;
		}
		talk.setCreatorId(diary.getCreatorId());
		talk.setCreateTime(new Date());
		talk.setReferenceId(diary.getId());
		talk.setReferenceType(Talk.REFERENCE_TYPE_DIARY);
		return talkMapper.insertReferenceDiary(talk);
	}

	@Override
	public int deleteReferenceDiary(Integer valueOf) {
		Talk talk = new Talk();
		talk.setReferenceId(valueOf);
		talk.setReferenceType(Talk.REFERENCE_TYPE_DIARY);
		return talkMapper.deleteReferenceDiary(talk);
	}

	@Override
	public List<Diary> selectReferenceDiary(PageBean pb) {
		// TODO Auto-generated method stub
		return diaryMapper.selectReferenceDiary(pb);
	}

	@Override
	public Integer selectDiaryIsExistByDiaryId(Integer diaryId) {
		// TODO Auto-generated method stub
		return diaryMapper.selectDiaryIsExistByDiaryId(diaryId);
	}

	@Override
	public ApiResult createUserReceive(UserReceivceParam userReceivceShareParam) {
		ApiResult apiR = new ApiResult(ResponseEnum.SUCCESS);
		if (StringUtil.isNotEmpty(userReceivceShareParam.getContent())) {
			String str = userReceivceShareParam.getContent();
			UserDeviceClientTypeEnum userDeviceClientTypeEnum = UserDeviceClientTypeEnum
					.getByValue(userReceivceShareParam.getType());

			Msg msg = new Msg();

			List<H5ShareSet> h5ShareSetlist = h5ShareSetService.selectH5ShareSetList().stream()
					.filter(s -> str.indexOf(s.getUrl()) > 0).collect(Collectors.toList());
			if (h5ShareSetlist == null || h5ShareSetlist.size() == 0) {
				msg = userReceiveShareFactory.isSupportWeb(str, userDeviceClientTypeEnum);
			} else {
				msg.setSuccess(true);
				msg.setData(h5ShareSetlist.get(0).getUrl());
			}
			if (!msg.isSuccess()) {// 不支持此平台
				apiR.setCode(RESPONSE.ERROR);
				apiR.setMessage(msg.getMsg());
				return apiR;
			}
			String rawUrl = userReceiveShareFactory.getUrl(str, userDeviceClientTypeEnum);
			if (h5ShareSetlist != null && h5ShareSetlist.size() > 0
					&& userDeviceClientTypeEnum.getValue() != 1) {
				rawUrl = userReceiveShareFactory.getUrl(str, userDeviceClientTypeEnum.getValue(),
						h5ShareSetlist.get(0).getType());
			}
			// 分析页面
			UserReceiveDataAnalysisResult ob = null;
			if (h5ShareSetlist != null && h5ShareSetlist.size() > 0
					&& userDeviceClientTypeEnum.getValue() != 1) {
				ob = userReceiveShareFactory.analysisHtml(h5ShareSetlist.get(0).getType(), rawUrl,
						userDeviceClientTypeEnum);
			} else {
				ob = userReceiveShareFactory.analysisHtml(str, userDeviceClientTypeEnum);
			}
			if (UserReceiveDataAnalysisResult.ANALYSIS_STATE_FAIL == ob.getAnalysisState()) {
				apiR.setCode(RESPONSE.ERROR);
				apiR.setMessage("不支持此内容分享");
				return apiR;
			}
			ob.setRawUrl(rawUrl);
			apiR.setData(ob);
		}
		return apiR;
	}

	@Override
	public DirayH5ResultModel selectExtendDataById(Integer id) {
		// TODO Auto-generated method stub
		return diaryMapper.selectExtendDataById(id);
	}

	@Override
	public List<Diary> selectUserReceiveDataAnalysis() {
		// TODO Auto-generated method stub
		return diaryMapper.selectUserReceiveDataAnalysis();
	}

}
