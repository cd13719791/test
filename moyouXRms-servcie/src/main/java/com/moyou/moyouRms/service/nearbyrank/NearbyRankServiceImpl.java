package com.moyou.moyouRms.service.nearbyrank;
import java.util.List;
import com.moyou.moyouRms.dao.nearbyrank.NearbyRankDao;
import com.moyou.moyouRms.model.nearbyrank.NearbyRank;
import com.moyou.moyouRms.interceptor.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NearbyRankServiceImpl implements NearbyRankService{
    @Autowired
    private NearbyRankDao nearbyRankDao;
    @Override
    public long getNearbyRankRowCount(Assist assist){
        return nearbyRankDao.getNearbyRankRowCount(assist);
    }
    @Override
    public List<NearbyRank> selectNearbyRank(Assist assist){
        return nearbyRankDao.selectNearbyRank(assist);
    }
    @Override
    public NearbyRank selectNearbyRankByObj(NearbyRank obj){
        return nearbyRankDao.selectNearbyRankByObj(obj);
    }
    @Override
    public NearbyRank selectNearbyRankById(String id){
        return nearbyRankDao.selectNearbyRankById(id);
    }
    @Override
    public int insertNearbyRank(NearbyRank value){
        return nearbyRankDao.insertNearbyRank(value);
    }
    @Override
    public int insertNonEmptyNearbyRank(NearbyRank value){
        return nearbyRankDao.insertNonEmptyNearbyRank(value);
    }
    @Override
    public int deleteNearbyRankById(String id){
        return nearbyRankDao.deleteNearbyRankById(id);
    }
    @Override
    public int deleteNearbyRank(Assist assist){
        return nearbyRankDao.deleteNearbyRank(assist);
    }
    @Override
    public int updateNearbyRankById(NearbyRank enti){
        return nearbyRankDao.updateNearbyRankById(enti);
    }
    @Override
    public int updateNearbyRank(NearbyRank value, Assist assist){
        return nearbyRankDao.updateNearbyRank(value,assist);
    }
    @Override
    public int updateNonEmptyNearbyRankById(NearbyRank enti){
        return nearbyRankDao.updateNonEmptyNearbyRankById(enti);
    }
    @Override
    public int updateNonEmptyNearbyRank(NearbyRank value, Assist assist){
        return nearbyRankDao.updateNonEmptyNearbyRank(value,assist);
    }

    public NearbyRankDao getNearbyRankDao() {
        return this.nearbyRankDao;
    }

    public void setNearbyRankDao(NearbyRankDao nearbyRankDao) {
        this.nearbyRankDao = nearbyRankDao;
    }

}