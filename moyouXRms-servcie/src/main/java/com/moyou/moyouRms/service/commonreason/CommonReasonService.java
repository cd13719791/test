package com.moyou.moyouRms.service.commonreason;

import com.moyou.moyouRms.model.commonReason.CommonReason;

public interface CommonReasonService {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonReason record);

    int insertSelective(CommonReason record);

    CommonReason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonReason record);

    int updateByPrimaryKeyWithBLOBs(CommonReason record);

    int updateByPrimaryKey(CommonReason record);
    /**
     * 根据模块id  查询理由
     * @param id
     * @return
     */
    CommonReason selectCommonReasonByObjectId(CommonReason commonReason);
}