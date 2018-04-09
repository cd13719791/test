package com.moyou.moyouRms.model.statistics;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 上午10:46:23 类说明
 */
public class NewUserResult extends BaseModel
{
    public NewUserResult()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public NewUserResult(String days, Integer counts)
    {
        super();
        Days = days;
        this.counts = counts;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String Days;
    private Integer counts;

    public String getDays()
    {
        return Days;
    }

    public void setDays(String days)
    {
        Days = days;
    }

    public Integer getCounts()
    {
        return counts;
    }

    public void setCounts(Integer counts)
    {
        this.counts = counts;
    }
}
