package com.moyou.moyouRms.dao.userGoldRule;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;

public interface UserGoldRuleMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(UserGoldRule record);

    int insertSelective(UserGoldRule record);

    UserGoldRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGoldRule record);

    int updateByPrimaryKey(UserGoldRule record);

    List<Object> selectGoldRuleByModeRuleType(@Param("type") Integer intValue);

    List<Object> selectGodRuleByModeTypeAndModeRuleType(@Param("type") Integer type);
}
