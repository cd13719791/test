package com.moyou.moyouRms.service.liveshow.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.EasemobSingleChatMsgTypeEnum;
import com.moyou.moyouRms.dao.liveshow.LiveUserInfoDao;
import com.moyou.moyouRms.easemob.entity.SingleChatMsg;
import com.moyou.moyouRms.easemob.entity.SingleChatMsgExtend;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.H5LiveShow;
import com.moyou.moyouRms.model.liveshow.LiveUserInfo;
import com.moyou.moyouRms.model.liveshow.LiveUserListResult;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;

@Service
public class LiveUserInfoServiceImpl implements LiveUserInfoService {
	public static final Integer RECOMMEND_USER_MAX_NUM = 50;
	@Autowired
	private LiveUserInfoDao liveUserInfoDao;
	@Resource
	private UserService userService;
	@Resource
	UserInfoService userInfoServicel;
	@Resource
	EaseMobService easeMobService;

	@Override
	public long getLiveUserInfoRowCount(Assist assist) {
		return liveUserInfoDao.getLiveUserInfoRowCount(assist);
	}

	@Override
	public List<LiveUserInfo> selectLiveUserInfo(Assist assist) {
		return liveUserInfoDao.selectLiveUserInfo(assist);
	}

	@Override
	public LiveUserInfo selectLiveUserInfoByObj(LiveUserInfo obj) {
		return liveUserInfoDao.selectLiveUserInfoByObj(obj);
	}

	@Override
	public LiveUserInfo selectLiveUserInfoById(Integer id) {
		return liveUserInfoDao.selectLiveUserInfoById(id);
	}

	@Override
	public int insertLiveUserInfo(LiveUserInfo value) {
		return liveUserInfoDao.insertLiveUserInfo(value);
	}

	@Override
	public int insertNonEmptyLiveUserInfo(LiveUserInfo value) {
		return liveUserInfoDao.insertNonEmptyLiveUserInfo(value);
	}

	@Override
	public int deleteLiveUserInfoById(Integer id) {
		return liveUserInfoDao.deleteLiveUserInfoById(id);
	}

	@Override
	public int deleteLiveUserInfo(Assist assist) {
		return liveUserInfoDao.deleteLiveUserInfo(assist);
	}

	@Override
	public int updateLiveUserInfoById(LiveUserInfo enti) {

		return liveUserInfoDao.updateLiveUserInfoById(enti);
	}

	@Override
	public int updateLiveUserInfo(LiveUserInfo value, Assist assist) {
		return liveUserInfoDao.updateLiveUserInfo(value, assist);
	}

	@Override
	public int updateNonEmptyLiveUserInfoById(LiveUserInfo enti) {
		int index = 0;
		// if (StringUtil.isNotEmpty(enti.getUserState().toString())) {// 封号与解封
		// 暂时用的是限制User
		if (enti.getRecommendState() == LiveUserInfo.RECOMMEND_STATE_YES) {// 推荐
			if (liveUserInfoDao.selectRecommentCount() >= RECOMMEND_USER_MAX_NUM) {// 数量大于最大数
				return index;
			} else {
				index = liveUserInfoDao.updateNonEmptyLiveUserInfoById(enti);
			}
		} else if (enti.getRecommendState() == LiveUserInfo.RECOMMEND_STATE_NO) {// 解除推荐
			index = liveUserInfoDao.updateNonEmptyLiveUserInfoById(enti);
		}
		// }
		return index;
	}

	@Override
	public int updateNonEmptyLiveUserInfo(LiveUserInfo value, Assist assist) {
		return liveUserInfoDao.updateNonEmptyLiveUserInfo(value, assist);
	}

	public LiveUserInfoDao getLiveUserInfoDao() {
		return this.liveUserInfoDao;
	}

	public void setLiveUserInfoDao(LiveUserInfoDao liveUserInfoDao) {
		this.liveUserInfoDao = liveUserInfoDao;
	}

	@Override
	public List<LiveUserListResult> selectLiveUserList(PageBean pageBean) {
		// TODO Auto-generated method stub
		return liveUserInfoDao.selectLiveUserList(pageBean);
	}

	@Override
	public Map<String, Object> selectLiveUserCount() {
		// TODO Auto-generated method stub
		return liveUserInfoDao.selectLiveUserCount();
	}

	@Override
	public LiveUserListResult selectliveUserInfo(Integer userId) {
		// TODO Auto-generated method stub
		return liveUserInfoDao.selectliveUserInfo(userId);
	}

	@Override
	public Integer checkIsLiveUser(Integer userId) {
		// TODO Auto-generated method stub
		return liveUserInfoDao.checkIsLiveUser(userId);
	}

	@Override
	public List<Map<String, Integer>> selectLiveUserRecommentNumber() {
		// TODO Auto-generated method stub
		List<Integer> list = liveUserInfoDao.selectLiveUserRecommentNumber();
		List<Integer> tempList = new ArrayList<Integer>(RECOMMEND_USER_MAX_NUM);
		for (int i = 1; i <= RECOMMEND_USER_MAX_NUM; i++) {
			tempList.add(i);
		}
		tempList.removeAll(list);
		List<Map<String, Integer>> listMap = new ArrayList<Map<String, Integer>>();
		tempList.forEach(s -> {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("order", s);
			listMap.add(map);
		});
		return listMap;
	}

	@Override
	public Integer updateLimitLiveUser(LiveUserInfo enti) {
		// 发送透传消息
		SingleChatMsg chatMsg = new SingleChatMsg(EasemobSingleChatMsgTypeEnum.CMD,
				CONSTANT.LIMIT_LIVE_USER, CONSTANT.LIMIT_LIVE_USER);
		SingleChatMsgExtend singleChatMsgExtend = new SingleChatMsgExtend();
		// singleChatMsgExtend.setBizRoomId(enti.get);
		singleChatMsgExtend.setUserId(enti.getUserId());
		if (easeMobService.sendSingleChatMsg("admin", chatMsg, singleChatMsgExtend,
				userInfoServicel.selectUsreDetailInfo(enti.getUserId()).getPushChatId())) {
			return RESPONSE.SUCCESS;
		}
		return RESPONSE.ERROR;
	}

	@Override
	public Integer updateLiveUserRemarkById(LiveUserInfo enti) {
		return liveUserInfoDao.updateNonEmptyLiveUserInfoById(new LiveUserInfo(enti.getUserId(),
				enti.getOperationRemark(), new Date(), enti.getOperationUser()));
	}

	@Override
	public Integer updateStopLiveUser(LiveUserInfo enti) {
		// TODO Auto-generated method stub
		SingleChatMsg chatMsg = new SingleChatMsg(EasemobSingleChatMsgTypeEnum.CMD,
				CONSTANT.LIMIT_LIVE_USER, CONSTANT.STOP_LIVE_USER);
		SingleChatMsgExtend singleChatMsgExtend = new SingleChatMsgExtend();
		// singleChatMsgExtend.setBizRoomId(enti.get);
		singleChatMsgExtend.setUserId(enti.getUserId());
		if (easeMobService.sendSingleChatMsg("admin", chatMsg, singleChatMsgExtend,
				userInfoServicel.selectUsreDetailInfo(enti.getUserId()).getPushChatId())) {
			return RESPONSE.SUCCESS;
		}
		return RESPONSE.ERROR;
	}

	@Override
	public H5LiveShow queryH5ShareLiveShow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer id = (Integer) map.get("id");
		return liveUserInfoDao.queryH5ShareLiveShow(id);
	}

}