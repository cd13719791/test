package com.moyou.moyouRms.service.liveshow.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.liveshow.LiveUserConvertDao;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.liveshow.LiveUserConvert;
import com.moyou.moyouRms.service.liveshow.LiveUserConvertService;

@Service
public class LiveUserConvertServiceImpl implements LiveUserConvertService {
	@Autowired
	private LiveUserConvertDao liveUserConvertDao;

	@Override
	public long getLiveUserConvertRowCount(Assist assist) {
		return liveUserConvertDao.getLiveUserConvertRowCount(assist);
	}

	@Override
	public List<LiveUserConvert> selectLiveUserConvert(Assist assist) {
		return liveUserConvertDao.selectLiveUserConvert(assist);
	}

	@Override
	public LiveUserConvert selectLiveUserConvertByObj(LiveUserConvert obj) {
		return liveUserConvertDao.selectLiveUserConvertByObj(obj);
	}

	@Override
	public LiveUserConvert selectLiveUserConvertById(Integer id) {
		return liveUserConvertDao.selectLiveUserConvertById(id);
	}

	@Override
	public int insertLiveUserConvert(LiveUserConvert value) {
		return liveUserConvertDao.insertLiveUserConvert(value);
	}

	@Override
	public int insertNonEmptyLiveUserConvert(LiveUserConvert value) {
		return liveUserConvertDao.insertNonEmptyLiveUserConvert(value);
	}

	@Override
	public int deleteLiveUserConvertById(Integer id) {
		return liveUserConvertDao.deleteLiveUserConvertById(id);
	}

	@Override
	public int deleteLiveUserConvert(Assist assist) {
		return liveUserConvertDao.deleteLiveUserConvert(assist);
	}

	@Override
	public int updateLiveUserConvertById(LiveUserConvert enti) {
		return liveUserConvertDao.updateLiveUserConvertById(enti);
	}

	@Override
	public int updateLiveUserConvert(LiveUserConvert value, Assist assist) {
		return liveUserConvertDao.updateLiveUserConvert(value, assist);
	}

	@Override
	public int updateNonEmptyLiveUserConvertById(LiveUserConvert enti) {
		return liveUserConvertDao.updateNonEmptyLiveUserConvertById(enti);
	}

	@Override
	public int updateNonEmptyLiveUserConvert(LiveUserConvert value, Assist assist) {
		return liveUserConvertDao.updateNonEmptyLiveUserConvert(value, assist);
	}

	public LiveUserConvertDao getLiveUserConvertDao() {
		return this.liveUserConvertDao;
	}

	public void setLiveUserConvertDao(LiveUserConvertDao liveUserConvertDao) {
		this.liveUserConvertDao = liveUserConvertDao;
	}

	@Override
	public Map<String, Object> selectUserConvertCount() {
		// TODO Auto-generated method stub
		return liveUserConvertDao.selectUserConvertCount();
	}

}