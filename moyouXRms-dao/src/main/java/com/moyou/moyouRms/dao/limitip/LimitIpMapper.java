package com.moyou.moyouRms.dao.limitip;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.limitip.LimitIp;

public interface LimitIpMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(LimitIp record);

    int insertSelective(LimitIp record);

    LimitIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LimitIp record);

    int updateByPrimaryKey(LimitIp record);

    /**
     * 初始化IP限制
     */
    List<LimitIp> queryLimitIpList(PageBean PageBean);

    /**
     * 查询该Ip是否已被限制
     * 
     * @param ipAddress
     * @return
     */
    LimitIp queryByIpAddress(String ipAddress);

    List<LimitIp> queryLimitIpListByType(PageBean page);
}
