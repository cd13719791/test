package com.moyou.moyouRms.service.liveshow.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jiguang.common.utils.StringUtils;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.liveshow.LiveRoomDao;
import com.moyou.moyouRms.dao.userfund.UserFundLogMapper;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveRecommend;
import com.moyou.moyouRms.model.liveshow.LiveRecordInfo;
import com.moyou.moyouRms.model.liveshow.LiveRoom;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveRoomService;
import com.moyou.moyouRms.util.DateTimeUtil;

@Service
public class LiveRoomServiceImpl implements LiveRoomService {
	@Autowired
	private LiveRoomDao liveRoomDao;
	@Resource
	private UserFundLogMapper userFundLogMapper;
	public static final Integer RECOMMEND_LIVE_ROOM_MAX_NUMBER = 20; // 最大推荐数量

	@Override
	public long getLiveRoomRowCount(Assist assist) {
		return liveRoomDao.getLiveRoomRowCount(assist);
	}

	@Override
	public List<LiveRoom> selectLiveRoom(Assist assist) {
		return liveRoomDao.selectLiveRoom(assist);
	}

	@Override
	public LiveRoom selectLiveRoomByObj(LiveRoom obj) {
		return liveRoomDao.selectLiveRoomByObj(obj);
	}

	@Override
	public LiveRoom selectLiveRoomById(Integer id) {
		return liveRoomDao.selectLiveRoomById(id);
	}

	@Override
	public int insertLiveRoom(LiveRoom value) {
		return liveRoomDao.insertLiveRoom(value);
	}

	@Override
	public int insertNonEmptyLiveRoom(LiveRoom value) {
		return liveRoomDao.insertNonEmptyLiveRoom(value);
	}

	@Override
	public int deleteLiveRoomById(Integer id) {
		return liveRoomDao.deleteLiveRoomById(id);
	}

	@Override
	public int deleteLiveRoom(Assist assist) {
		return liveRoomDao.deleteLiveRoom(assist);
	}

	@Override
	public int updateLiveRoomById(LiveRoom enti) {
		return liveRoomDao.updateLiveRoomById(enti);
	}

	@Override
	public int updateLiveRoom(LiveRoom value, Assist assist) {
		return liveRoomDao.updateLiveRoom(value, assist);
	}

	@Override
	public int updateNonEmptyLiveRoomById(LiveRoom enti) {
		return liveRoomDao.updateNonEmptyLiveRoomById(enti);
	}

	@Override
	public int updateNonEmptyLiveRoom(LiveRoom value, Assist assist) {
		return liveRoomDao.updateNonEmptyLiveRoom(value, assist);
	}

	@Override
	public List<LiveRecordInfo> selectLiveRecordList(PageBean pageBean) {
		return liveRoomDao
				.selectLiveRecordInfoList(pageBean)
				.stream()
				.peek(s -> {
					s.setUserRecordList(userFundLogMapper
							.selectUserFundLogServiceByLiveModeId(s.getId())
							.stream()
							.peek(a -> {
								a.setWatchTime(DateTimeUtil.formartSeccentToDateTime(a
										.getWatchLiveSeconds()));
								Long lon = (a.getCreateTime().getTime() + (a.getWatchLiveSeconds() * 1000));
								a.setEndTime(new Date(lon));
							}).limit(1).collect(Collectors.toList()));
					s.setOnlineTime(DateTimeUtil.formartSeccentToDateTime(s.getLiveSeconds()));
					Long lon = (s.getStartTime().getTime() + (s.getLiveSeconds() * 1000));
					s.setEndTime(new Date(lon));
				}).collect(Collectors.toList());
	}

	@Override
	public Map<String, Object> selectLiveRecordCount() {
		// TODO Auto-generated method stub
		return liveRoomDao.selectLiveRecordCount();
	}

	@Override
	public List<LiveRoom> selectLiveRoomList(PageBean pageBean) {
		// TODO Auto-generated method stub
		return liveRoomDao.selectLiveRoomList(pageBean);
	}

	@Override
	public Map<String, Object> selectLiveRoomCount() {
		// TODO Auto-generated method stub
		return liveRoomDao.selectLiveRoomCount();
	}

	@Override
	public List<Map<String, Integer>> selectLiveRoomRecommentNumber() {
		// TODO Auto-generated method stub
		List<Integer> list = liveRoomDao.selectLiveRoomRecommentNumber();
		List<Integer> tempList = new ArrayList<Integer>(LiveRoom.RECOMMENT_NUMBER);
		for (int i = 1; i <= LiveRoom.RECOMMENT_NUMBER; i++) {
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
	public ApiResult updateliveroomrecomment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer state = Integer.valueOf(map.get("state").toString());
		Integer id = Integer.valueOf(map.get("id").toString());
		if (state == LiveRoom.STATE_RECOMMENT_YES) {
			if (this.selectLiveRoomRecommentCount(LiveRoom.STATE_RECOMMENT_YES) < RECOMMEND_LIVE_ROOM_MAX_NUMBER) {
				Integer sort = Integer.valueOf(map.get("sort").toString());
				if (liveRoomDao.selectByRecommentSort(sort) == null) {
					liveRoomDao.updateNonEmptyLiveRoomById(new LiveRoom(id,
							LiveRoom.STATE_RECOMMENT_YES, sort, new Date()));
					return new ApiResult(RESPONSE.SUCCESS, "修改成功");
				} else {
					return new ApiResult(RESPONSE.ERROR, "已经有这个排序了");
				}
			} else {
				return new ApiResult(RESPONSE.ERROR, "超过数量了：[" + RECOMMEND_LIVE_ROOM_MAX_NUMBER
						+ "]");
			}
		} else if (state == LiveRoom.STATE_RECOMMENT_NO) {
			liveRoomDao.updateNonEmptyLiveRoomById(new LiveRoom(id, LiveRoom.STATE_RECOMMENT_NO,
					LiveRoom.DEFAULT_SORT, new Date()));
			return new ApiResult(RESPONSE.SUCCESS, "修改成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "参数异常");
		}
	}

	@Override
	public List<LiveRecommend> selectLiveRecommendList(PageBean pageBean) {
		// TODO Auto-generated meth od stub
		return liveRoomDao.selectLiveRecommendList(pageBean).stream().peek(s -> {
			s.setOnlineTime((s.getLiveSeconds() / 60) + "分钟");
			if (s.getBirthday() != null) {
				s.setAge(Integer.parseInt(DateTimeUtil.getPersonAgeByBirthDate(s.getBirthday())));
			}
			if (StringUtils.isNotEmpty(s.getRealPhone())) {
				s.setRealPhone(s.getRealPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
			}
		}).collect(Collectors.toList());
	}

	@Override
	public Integer selectLiveRoomRecommentCount(Integer recommendState) {
		// TODO Auto-generated method stub
		return liveRoomDao.selectLiveRoomRecommentCount(recommendState);
	}

}