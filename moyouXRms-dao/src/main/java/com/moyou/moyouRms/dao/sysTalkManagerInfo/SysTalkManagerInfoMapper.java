package com.moyou.moyouRms.dao.sysTalkManagerInfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysTalkManagerInfo.SysTalkManagerInfo;

public interface SysTalkManagerInfoMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(SysTalkManagerInfo record);

    int insertSelective(SysTalkManagerInfo record);

    SysTalkManagerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTalkManagerInfo record);

    int updateByPrimaryKey(SysTalkManagerInfo record);

    List<SysTalkManagerInfo> selecTalkAccountInfoByAccount(PageBean pb);

    /**
     * 获取聊天数据
     * 
     * @param accountId
     * @return
     */
    Map<String, Object> selectAccountCount(String accountId);

    /**
     * 修改聊天数据加一
     * 
     * @param userId
     * @param account
     * @return
     */
    int updateTalkCountJIA1(SysTalkManagerInfo s);

    /**
     * 查找聊天数据
     * 
     * @param userId
     * @param account
     * @return
     */
    SysTalkManagerInfo selectSysTalkManagerInfoByUserAndAccountId(SysTalkManagerInfo s);

    Map<String, Object> selectAccountTalkInfo(Account loginName);

    /**
     * 优化的批量查询聊天数据 现在是一个id 一个id的查
     * 
     * @param loginName
     * @param accountId
     * @return
     */
    SysTalkManagerInfo querySysTalkManagerInfoByAccountNameAndId(@Param("loginName") String loginName,
        @Param("accountId") String accountId);

}
