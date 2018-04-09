package com.moyou.moyouRms.model.userfund;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:sys_reward表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-06-08
 */
public class SystemRewardModel extends BaseModel
{
    /**
	 * 
	 */
    private static final long serialVersionUID = -997768970549685917L;
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    Integer range;

    public Integer getRange()
    {
        return range;
    }

    public void setRange(Integer range)
    {
        this.range = range;
    }

    public final static Integer STATE_OK = 1;
    public final static Integer STATE_ERRO = 3;
    public final static Integer STATE_DOING = 0;
    private Integer rangeDay;// 有效范围 单位日
    private List<SystemRewardLog> SystemRewardLogList;// 日志集合

    public List<SystemRewardLog> getSystemRewardLogList()
    {
        return SystemRewardLogList;
    }

    public void setSystemRewardLogList(List<SystemRewardLog> systemRewardLogList)
    {
        SystemRewardLogList = systemRewardLogList;
    }

    public Integer getRangeDay()
    {
        return rangeDay;
    }

    public void setRangeDay(Integer rangeDay)
    {
        this.rangeDay = rangeDay;
    }

    /**
     * 打赏分类 1=系统批量打赏
     */
    private Integer type;

    /**
     * 打赏类型 0=故事 1=说说
     */
    private Integer modelType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 打赏总额
     */
    private Integer price;

    /**
     * 打赏篇数
     */
    private Integer rewardNumber;

    /**
     * 单词最高打赏额
     */
    private Integer onePrice;

    /**
     * 交易状态 0正在交易 1交易成功 3异常
     */
    private Integer state;

    /**
     * 打赏操作人员
     */
    private String admin;

    /**
     * 备注
     */
    private String dec;

    /**
     * 
     * @return id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 打赏分类 1=系统批量打赏
     * 
     * @return type 打赏分类 1=系统批量打赏
     */
    public Integer getType()
    {
        return type;
    }

    /**
     * 打赏分类 1=系统批量打赏
     * 
     * @param type 打赏分类 1=系统批量打赏
     */
    public void setType(Integer type)
    {
        this.type = type;
    }

    /**
     * 打赏类型 0=故事 1=说说
     * 
     * @return model_type 打赏类型 0=故事 1=说说
     */
    public Integer getModelType()
    {
        return modelType;
    }

    /**
     * 打赏类型 0=故事 1=说说
     * 
     * @param modelType 打赏类型 0=故事 1=说说
     */
    public void setModelType(Integer modelType)
    {
        this.modelType = modelType;
    }

    /**
     * 
     * @return create_time
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * 打赏总额
     * 
     * @return price 打赏总额
     */
    public Integer getPrice()
    {
        return price;
    }

    /**
     * 打赏总额
     * 
     * @param price 打赏总额
     */
    public void setPrice(Integer price)
    {
        this.price = price;
    }

    /**
     * 10=10% 百分比！！
     * 
     * @return reward_number 打赏篇数
     */
    public Integer getRewardNumber()
    {
        return rewardNumber;
    }

    /**
     * 打赏篇数
     * 
     * @param rewardNumber 打赏篇数
     */
    public void setRewardNumber(Integer rewardNumber)
    {
        this.rewardNumber = rewardNumber;
    }

    /**
     * 单词最高打赏额
     * 
     * @return one_price 单词最高打赏额
     */
    public Integer getOnePrice()
    {
        return onePrice;
    }

    /**
     * 单词最高打赏额
     * 
     * @param onePrice 单词最高打赏额
     */
    public void setOnePrice(Integer onePrice)
    {
        this.onePrice = onePrice;
    }

    /**
     * 交易状态 0正在交易 1交易成功 3异常
     * 
     * @return state 交易状态 0正在交易 1交易成功 3异常
     */
    public Integer getState()
    {
        return state;
    }

    /**
     * 交易状态 0正在交易 1交易成功 3异常
     * 
     * @param state 交易状态 0正在交易 1交易成功 3异常
     */
    public void setState(Integer state)
    {
        this.state = state;
    }

    /**
     * 打赏操作人员
     * 
     * @return admin 打赏操作人员
     */
    public String getAdmin()
    {
        return admin;
    }

    /**
     * 打赏操作人员
     * 
     * @param admin 打赏操作人员
     */
    public void setAdmin(String admin)
    {
        this.admin = admin == null ? null : admin.trim();
    }

    /**
     * 备注
     * 
     * @return dec 备注
     */
    public String getDec()
    {
        return dec;
    }

    /**
     * 备注
     * 
     * @param dec 备注
     */
    public void setDec(String dec)
    {
        this.dec = dec == null ? null : dec.trim();
    }
}
