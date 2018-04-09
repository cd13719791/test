package com.moyou.moyouRms.dao.commonReason;

import com.moyou.moyouRms.model.commonReason.CommonReason;

public interface CommonReasonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonReason record);

    int insertSelective(CommonReason record);

    CommonReason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonReason record);

    int updateByPrimaryKeyWithBLOBs(CommonReason record);

    int updateByPrimaryKey(CommonReason record);

	CommonReason selectCommonReasonByObjectId(CommonReason commonReason);

}