package com.moyou.moyouRms.dao.msgsystem;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.msgsystem.MsgSystemX;

public interface MsgSystemXMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MsgSystemX record);

	int insertList(List<MsgSystemX> record);

	int insertSelective(MsgSystemX record);

	MsgSystemX selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MsgSystemX record);

	int updateByPrimaryKey(MsgSystemX record);

	List<MsgSystemX> queryMsgSystemByModeTypeAndModeId(
			@Param("type") Integer type, @Param("id") Integer id);
}