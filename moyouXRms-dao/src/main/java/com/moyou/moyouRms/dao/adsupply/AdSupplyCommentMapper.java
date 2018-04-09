package com.moyou.moyouRms.dao.adsupply;

import java.util.List;

import com.moyou.moyouRms.model.adsupply.AdSupplyComment;

public interface AdSupplyCommentMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(AdSupplyComment record);

    int insertSelective(AdSupplyComment record);

    AdSupplyComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdSupplyComment record);

    int updateByPrimaryKey(AdSupplyComment record);

    /**
     * 查询评论
     * 
     * @param id
     * @return
     */
    List<AdSupplyComment> selectByAdsupplyId(Integer id);
}
