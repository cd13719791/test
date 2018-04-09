package com.moyou.moyouRms.dao.secretRobot;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secret.SecretInsertModel;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobotDetailResult;
import com.moyou.moyouRms.model.statistics.Robot;

public interface SecretRobotMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(SecretRobot record);

    int insertSelective(SecretRobot record);

    int insertSecret(SecretInsertModel secretInsertModel);

    SecretRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretRobot record);

    int updateByPrimaryKey(SecretRobot record);

    /**
     * 初始化秘密库
     * 
     * @param pb state=0未发布 state=1已发布
     * @return
     */
    List<SecretRobot> selectSecretRobotList(PageBean pb);

    /**
     * 查询昨日发布统计数据
     * 
     * @return
     */
    int selectSecretRobotYesterdayCount();

    int updatePushStateByPrimaryKey(SecretRobot dRobot);

    /**
     * 机器人秘密统计
     * 
     * @return
     */
    Map<String, Integer> selectSecretRobotCount();

    Robot querySecret();

    SecretRobotDetailResult selectSecretRobotDetailBySecretId(@Param("id") Integer id);
}
