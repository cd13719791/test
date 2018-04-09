package com.moyou.moyouRms.service.liveshow.Impl;
import java.util.List;

import com.moyou.moyouRms.dao.liveshow.LiveUserLevelSetDao;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.liveshow.LiveUserLevelSet;
import com.moyou.moyouRms.service.liveshow.LiveUserLevelSetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LiveUserLevelSetServiceImpl implements LiveUserLevelSetService{
    @Autowired
    private LiveUserLevelSetDao liveUserLevelSetDao;
    @Override
    public long getLiveUserLevelSetRowCount(Assist assist){
        return liveUserLevelSetDao.getLiveUserLevelSetRowCount(assist);
    }
    @Override
    public List<LiveUserLevelSet> selectLiveUserLevelSet(Assist assist){
        return liveUserLevelSetDao.selectLiveUserLevelSet(assist);
    }
    @Override
    public LiveUserLevelSet selectLiveUserLevelSetByObj(LiveUserLevelSet obj){
        return liveUserLevelSetDao.selectLiveUserLevelSetByObj(obj);
    }
    @Override
    public LiveUserLevelSet selectLiveUserLevelSetById(Integer id){
        return liveUserLevelSetDao.selectLiveUserLevelSetById(id);
    }
    @Override
    public int insertLiveUserLevelSet(LiveUserLevelSet value){
        return liveUserLevelSetDao.insertLiveUserLevelSet(value);
    }
    @Override
    public int insertNonEmptyLiveUserLevelSet(LiveUserLevelSet value){
        return liveUserLevelSetDao.insertNonEmptyLiveUserLevelSet(value);
    }
    @Override
    public int deleteLiveUserLevelSetById(Integer id){
        return liveUserLevelSetDao.deleteLiveUserLevelSetById(id);
    }
    @Override
    public int deleteLiveUserLevelSet(Assist assist){
        return liveUserLevelSetDao.deleteLiveUserLevelSet(assist);
    }
    @Override
    public int updateLiveUserLevelSetById(LiveUserLevelSet enti){
        return liveUserLevelSetDao.updateLiveUserLevelSetById(enti);
    }
    @Override
    public int updateLiveUserLevelSet(LiveUserLevelSet value, Assist assist){
        return liveUserLevelSetDao.updateLiveUserLevelSet(value,assist);
    }
    @Override
    public int updateNonEmptyLiveUserLevelSetById(LiveUserLevelSet enti){
        return liveUserLevelSetDao.updateNonEmptyLiveUserLevelSetById(enti);
    }
    @Override
    public int updateNonEmptyLiveUserLevelSet(LiveUserLevelSet value, Assist assist){
        return liveUserLevelSetDao.updateNonEmptyLiveUserLevelSet(value,assist);
    }

    public LiveUserLevelSetDao getLiveUserLevelSetDao() {
        return this.liveUserLevelSetDao;
    }

    public void setLiveUserLevelSetDao(LiveUserLevelSetDao liveUserLevelSetDao) {
        this.liveUserLevelSetDao = liveUserLevelSetDao;
    }

}