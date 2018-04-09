package com.moyou.moyouRms.model.userGoldRule;

import java.math.BigDecimal;
import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:t_user_gold_rule表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-08-02
 */
public class UserGoldRule extends BaseModel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     */
    public static final Short MODETYPE_ANDROID = 1;
    public static final Short MODETYPE_IOS = 2;
    public static final Short MODE_RULE_TYPE_ANCHOR = 2;// 主播
    private Integer dataStatus;// 删除状态 0删除1正常
    public static final Short Status_DELETE = 0;
    public static final Short Status_NORMAL = 1;

    public Integer getDataStatus()
    {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus)
    {
        this.dataStatus = dataStatus;
    }

    /**
     * 主键
     */
    private Integer id;

    /**
     * 模块类型，1金币充值(金币购买)
     */
    private Short modeType;

    /**
     * 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     */
    private Short modeRuleType;

    /**
     * 规则描述(苹果产品ID)
     */
    private String ruleDesc;

    /**
     * 获取金币数量
     */
    private Integer goldNum;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 赠送
     */
    private Integer give;

    /**
     * 需要rmb 单位元
     */
    private BigDecimal needRmb;

    /**
     * 排序数越小在前面
     */
    private Integer dataSort;

    /**
     * 主键
     * 
     * @return id 主键
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 主键
     * 
     * @param id 主键
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 模块类型，1金币充值(金币购买)
     * 
     * @return mode_type 模块类型，1金币充值(金币购买)
     */
    public Short getModeType()
    {
        return modeType;
    }

    /**
     * 模块类型，1金币充值(金币购买)
     * 
     * @param modeType 模块类型，1金币充值(金币购买)
     */
    public void setModeType(Short modeType)
    {
        this.modeType = modeType;
    }

    /**
     * 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     * 
     * @return mode_rule_type 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     */
    public Short getModeRuleType()
    {
        return modeRuleType;
    }

    /**
     * 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     * 
     * @param modeRuleType 模块对应规则类型如1-1表示模块1对应的规则1 /1安卓购买金币 2ios购买金币
     */
    public void setModeRuleType(Short modeRuleType)
    {
        this.modeRuleType = modeRuleType;
    }

    /**
     * 规则描述(苹果产品ID)
     * 
     * @return rule_desc 规则描述(苹果产品ID)
     */
    public String getRuleDesc()
    {
        return ruleDesc;
    }

    /**
     * 规则描述(苹果产品ID)
     * 
     * @param ruleDesc 规则描述(苹果产品ID)
     */
    public void setRuleDesc(String ruleDesc)
    {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    /**
     * 获取金币数量
     * 
     * @return gold_num 获取金币数量
     */
    public Integer getGoldNum()
    {
        return goldNum;
    }

    /**
     * 获取金币数量
     * 
     * @param goldNum 获取金币数量
     */
    public void setGoldNum(Integer goldNum)
    {
        this.goldNum = goldNum;
    }

    /**
     * 修改时间
     * 
     * @return update_time 修改时间
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * 修改时间
     * 
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    /**
     * 创建时间
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 创建时间
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 赠送
     * 
     * @return give 赠送
     */
    public Integer getGive()
    {
        return give;
    }

    /**
     * 赠送
     * 
     * @param give 赠送
     */
    public void setGive(Integer give)
    {
        this.give = give;
    }

    /**
     * 需要rmb 单位元
     * 
     * @return need_rmb 需要rmb 单位元
     */
    public BigDecimal getNeedRmb()
    {
        return needRmb;
    }

    /**
     * 需要rmb 单位元
     * 
     * @param needRmb 需要rmb 单位元
     */
    public void setNeedRmb(BigDecimal needRmb)
    {
        this.needRmb = needRmb;
    }

    /**
     * 排序数越小在前面
     * 
     * @return data_sort 排序数越小在前面
     */
    public Integer getDataSort()
    {
        return dataSort;
    }

    /**
     * 排序数越小在前面
     * 
     * @param dataSort 排序数越小在前面
     */
    public void setDataSort(Integer dataSort)
    {
        this.dataSort = dataSort;
    }
}
