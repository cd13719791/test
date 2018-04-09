package com.moyou.moyouRms.service.liveshow.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.liveshow.LiveFakeRoomDao;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoom;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoomEdit;
import com.moyou.moyouRms.model.po.task.TaskSeting;
import com.moyou.moyouRms.service.liveshow.LiveFakeRoomService;

@Service
public class LiveFakeRoomServiceImpl implements LiveFakeRoomService {
	@Autowired
	private LiveFakeRoomDao liveFakeRoomDao;

	@Override
	public long getLiveFakeRoomRowCount(Assist assist) {
		return liveFakeRoomDao.getLiveFakeRoomRowCount(assist);
	}

	@Override
	public List<LiveFakeRoom> selectLiveFakeRoom(Assist assist) {
		return liveFakeRoomDao.selectLiveFakeRoom(assist);
	}

	@Override
	public LiveFakeRoom selectLiveFakeRoomByObj(LiveFakeRoom obj) {
		return liveFakeRoomDao.selectLiveFakeRoomByObj(obj);
	}

	@Override
	public LiveFakeRoom selectLiveFakeRoomById(Integer id) {
		return liveFakeRoomDao.selectLiveFakeRoomById(id);
	}

	@Override
	public int insertLiveFakeRoom(LiveFakeRoom value) {
		return liveFakeRoomDao.insertLiveFakeRoom(value);
	}

	@Override
	public int insertNonEmptyLiveFakeRoom(LiveFakeRoom value) {
		return liveFakeRoomDao.insertNonEmptyLiveFakeRoom(value);
	}

	@Override
	public int deleteLiveFakeRoomById(Integer id) {
		return liveFakeRoomDao.deleteLiveFakeRoomById(id);
	}

	@Override
	public int deleteLiveFakeRoom(Assist assist) {
		return liveFakeRoomDao.deleteLiveFakeRoom(assist);
	}

	@Override
	public int updateLiveFakeRoomById(LiveFakeRoom enti) {
		return liveFakeRoomDao.updateLiveFakeRoomById(enti);
	}

	@Override
	public int updateLiveFakeRoom(LiveFakeRoom value, Assist assist) {
		return liveFakeRoomDao.updateLiveFakeRoom(value, assist);
	}

	@Override
	public int updateNonEmptyLiveFakeRoomById(LiveFakeRoom enti) {
		return liveFakeRoomDao.updateNonEmptyLiveFakeRoomById(enti);
	}

	@Override
	public int updateNonEmptyLiveFakeRoom(LiveFakeRoom value, Assist assist) {
		return liveFakeRoomDao.updateNonEmptyLiveFakeRoom(value, assist);
	}

	public LiveFakeRoomDao getLiveFakeRoomDao() {
		return this.liveFakeRoomDao;
	}

	public void setLiveFakeRoomDao(LiveFakeRoomDao liveFakeRoomDao) {
		this.liveFakeRoomDao = liveFakeRoomDao;
	}

	@Override
	public List<LiveFakeRoom> selectLiveFakeRoomList(PageBean pageBean) {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.selectLiveFakeRoomList(pageBean);
	}

	@Override
	public LiveFakeRoomEdit selectLiveFakeRoomEditById(Integer id) {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.selectLiveFakeRoomEditById(id);
	}

	@Override
	public Map<String, Object> selectLiveUserCount() {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.selectLiveUserCount();
	}

	@Override
	public TaskSeting selectLiveFakeRoomSet() {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.selectLiveFakeRoomSet();
	}

	@Override
	public int updateLiveFakeNoRecomment() {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.updateLiveFakeNoRecomment();
	}

	@Override
	public List<LiveFakeRoom> selectLiveFakeRoomLimit(int executeDataCount) {
		// TODO Auto-generated method stub
		return liveFakeRoomDao.selectLiveFakeRoomLimit(executeDataCount);
	}

}