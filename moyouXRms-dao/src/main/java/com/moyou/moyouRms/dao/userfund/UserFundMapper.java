package com.moyou.moyouRms.dao.userfund;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.statistics.Consume;
import com.moyou.moyouRms.model.userfund.UserFund;

public interface UserFundMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(UserFund record);

    int insertSelective(UserFund record);

    UserFund selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFund record);

    int updateByPrimaryKey(UserFund record);

    /**
     * 修改用户资金
     * 
     * @param record
     * @return
     */
    int updateUserFundByUserId(UserFund record);

    /**
     * 根据userId获取记录
     * 
     * @param userId
     * @return
     */
    UserFund selectUserFundByUserId(Integer userId);

    /**
     * 获取一行记录并对这一行记录枷锁,一般不要使用
     * 
     * @param userId
     * @param fund
     */
    UserFund selectUserFundByXLock(Integer userId);

    /**
     * (根据用户Id获取用户的金币和余额)
     * 
     * @param userId
     * @return
     */
    BigDecimal queryUserFundByUserId(Integer userId);

    /**
     * 查询今日红包总数和红包总金额
     * 
     * @return
     */
    Consume queryCountHb();

    /**
     * 查询作日红包总数和红包总金额
     * 
     * @return
     */
    Consume queryCountYesterDayHb();

    /**
     * 查询作日红包总数和红包总金额
     * 
     * @return
     */
    Consume queryCountToDayHb();

    /**
     * 今日 提现总数 和 总金额
     * 
     * @return
     */
    Consume queryCountToDayWithdraw();

    /**
     * 打赏
     * 
     * @return
     */
    Consume queryCountGive();

    /**
     * 提现审核中申请总数
     * 
     * @return
     */
    Consume queryAuditWithdraw();

    /**
     * 提现成功申请总数
     * 
     * @return
     */
    Consume queryCountSucceedWithdraw();

    /**
     * 总金币数
     * 
     * @return
     */
    Consume queryCountFund();

    /**
     * 总消耗金币
     * 
     * @return
     */
    Consume queryCountUseFund();

    /**
     * 管理员赠送金币
     * 
     * @return
     */
    Consume queryCountAdminFund();

    /**
     * 说说打赏次数和总金额
     * 
     * @return
     */
    Consume queryTalkGiveMoney();

    /**
     * 故事打赏次数和总金额
     * 
     * @return
     */
    Consume queryDiaryGiveMoney();

    /**
     * 查询所有统计数据
     * 
     * @return
     */
    Consume queryAllStatist();

    /**
     * 资金池
     * 
     * @param date
     * @return
     */
    List<UserFund> queryUserFundList(@Param("states") String states, @Param("time") String time);

    /**
     * 修改系统资金 递增
     * 
     * @param userFund
     * @return
     */
    int updateSystemFund(UserFund userFund);

    /**
     * 修改系统资金 递减
     * 
     * @param userFund
     * @return
     */
    int updateSystemFundJIAN(UserFund userFund);

    /**
     * 查询当日提现数量
     * 
     * @param userId
     * @return
     */
    BigDecimal selcetToDayWithdrawSum(@Param("userId") Integer userId);

    int updateUserGole(UserFund userFund);

}
