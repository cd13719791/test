package com.moyou.moyouRms.model.goldSignConfigure;

import java.util.Date;

/**
 * 描述:t_gold_sign_configure表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-08-02
 */
public class GoldSignConfigure {
    /**
     * 
     */
    private Integer id;

    /**
     * 签到的天数
     */
    private Integer days;

    /**
     * 对应获取的金币
     */
    private Integer gold;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 签到的天数
     * @return days 签到的天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 签到的天数
     * @param days 签到的天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 对应获取的金币
     * @return gold 对应获取的金币
     */
    public Integer getGold() {
        return gold;
    }

    /**
     * 对应获取的金币
     * @param gold 对应获取的金币
     */
    public void setGold(Integer gold) {
        this.gold = gold;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}