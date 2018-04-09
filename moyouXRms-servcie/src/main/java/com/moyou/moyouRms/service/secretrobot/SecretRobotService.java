package com.moyou.moyouRms.service.secretrobot;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobotDetailResult;

public interface SecretRobotService
{
    int deleteByPrimaryKey(Integer id);

    int insert(SecretRobot record);

    int insertSelective(SecretRobot record);

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
     * 初始化秘密库 不带内容
     * 
     * @param pb state=0未发布 state=1已发布
     * @return
     */
    List<SecretRobot> selectSecretRobotListExtendsContent(PageBean pb);

    /**
     * 查询昨日发布统计数据
     * 
     * @return
     */
    int selectSecretRobotYesterdayCount();

    /**
     * 修改发布状态
     * 
     * @param dRobot
     * @return
     */
    int updatePushStateByPrimaryKey(SecretRobot dRobot);

    /**
     * 获取机器人统计信息
     * 
     * @return
     */
    Map<String, Integer> selectSecretRobotCount();

    SecretRobotDetailResult selectSecretRobotDetailBySecretId(Integer valueOf);
}
