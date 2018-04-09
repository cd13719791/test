package com.moyou.moyouRms.dao.userdynamic;

import com.moyou.moyouRms.model.userdynamic.UserDynamic;

public interface UserDynamicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserDynamic record);

	int insertSelective(UserDynamic record);

	UserDynamic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserDynamic record);

	int updateByPrimaryKey(UserDynamic record);

	int deleteByDataIdAnDType(UserDynamic record);
}