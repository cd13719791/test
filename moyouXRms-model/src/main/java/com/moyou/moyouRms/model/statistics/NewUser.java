package com.moyou.moyouRms.model.statistics;

import com.moyou.moyouRms.model.BaseModel;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年7月28日 上午9:28:53 类说明
 */
public class NewUser extends BaseModel
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer year;// 年
    private Integer month;// 月
    private Integer day;// 日
    private String dateFormart;// '%y-%m-%d %H'
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

    public String getDateFormart()
    {
        return dateFormart;
    }

    public void setDateFormart(String dateFormart)
    {
        this.dateFormart = dateFormart;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getDay()
    {
        return day;
    }

    public void setDay(Integer day)
    {
        this.day = day;
    }
}
