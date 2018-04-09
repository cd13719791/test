package com.moyou.moyouRms.model.statistics;

import java.io.Serializable;

public class Issue implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 802789459273235747L;
    private int toDayCountTalk;// 真实用户今日说说总数
    private int countTalk;// 真实用户说说总数
    private int countPicTalk;// 真实用户图片说说
    private int countVidTalk;// 真实用户视频说说
    private int toDayStory;// 真实用户今日故事总数
    private int countStory;// 真实用户故事总数
    private int countSecret;// 真实用户秘密总数
    private int toDaySecret;// 真实用户今日秘密总数
    private int allToDayCountTalk;// 所有的 今日说说总数
    private int allCountTalk;// 所有的说说总数
    private int allCountPicTalk;// 所有的图片说说
    private int allCountVidTalk;// 所有的视频说说
    private int allToDayStory;// 所有的今日故事总数
    private int allCountStory;// 所有的故事总数
    private int allCountSecret;// 所有的秘密总数
    private int allToDaySecret;// 所有的今日秘密总数
    private int yesterDayCountTalk;// 昨天真用户发布的说说
    private int allYesterDayCountTalk;// 昨天所有发布的说说

    public int getYesterDayCountTalk()
    {
        return yesterDayCountTalk;
    }

    public void setYesterDayCountTalk(int yesterDayCountTalk)
    {
        this.yesterDayCountTalk = yesterDayCountTalk;
    }

    public int getAllYesterDayCountTalk()
    {
        return allYesterDayCountTalk;
    }

    public void setAllYesterDayCountTalk(int allYesterDayCountTalk)
    {
        this.allYesterDayCountTalk = allYesterDayCountTalk;
    }

    public int getAllToDayCountTalk()
    {
        return allToDayCountTalk;
    }

    public void setAllToDayCountTalk(int allToDayCountTalk)
    {
        this.allToDayCountTalk = allToDayCountTalk;
    }

    public int getAllCountTalk()
    {
        return allCountTalk;
    }

    public void setAllCountTalk(int allCountTalk)
    {
        this.allCountTalk = allCountTalk;
    }

    public int getAllCountPicTalk()
    {
        return allCountPicTalk;
    }

    public void setAllCountPicTalk(int allCountPicTalk)
    {
        this.allCountPicTalk = allCountPicTalk;
    }

    public int getAllCountVidTalk()
    {
        return allCountVidTalk;
    }

    public void setAllCountVidTalk(int allCountVidTalk)
    {
        this.allCountVidTalk = allCountVidTalk;
    }

    public int getAllToDayStory()
    {
        return allToDayStory;
    }

    public void setAllToDayStory(int allToDayStory)
    {
        this.allToDayStory = allToDayStory;
    }

    public int getAllCountStory()
    {
        return allCountStory;
    }

    public void setAllCountStory(int allCountStory)
    {
        this.allCountStory = allCountStory;
    }

    public int getAllCountSecret()
    {
        return allCountSecret;
    }

    public void setAllCountSecret(int allCountSecret)
    {
        this.allCountSecret = allCountSecret;
    }

    public int getAllToDaySecret()
    {
        return allToDaySecret;
    }

    public void setAllToDaySecret(int allToDaySecret)
    {
        this.allToDaySecret = allToDaySecret;
    }

    public int getToDayCountTalk()
    {
        return toDayCountTalk;
    }

    public void setToDayCountTalk(int toDayCountTalk)
    {
        this.toDayCountTalk = toDayCountTalk;
    }

    public int getCountTalk()
    {
        return countTalk;
    }

    public void setCountTalk(int countTalk)
    {
        this.countTalk = countTalk;
    }

    public int getCountPicTalk()
    {
        return countPicTalk;
    }

    public void setCountPicTalk(int countPicTalk)
    {
        this.countPicTalk = countPicTalk;
    }

    public int getCountVidTalk()
    {
        return countVidTalk;
    }

    public void setCountVidTalk(int countVidTalk)
    {
        this.countVidTalk = countVidTalk;
    }

    public int getToDayStory()
    {
        return toDayStory;
    }

    public void setToDayStory(int toDayStory)
    {
        this.toDayStory = toDayStory;
    }

    public int getCountStory()
    {
        return countStory;
    }

    public void setCountStory(int countStory)
    {
        this.countStory = countStory;
    }

    public int getCountSecret()
    {
        return countSecret;
    }

    public void setCountSecret(int countSecret)
    {
        this.countSecret = countSecret;
    }

    public int getToDaySecret()
    {
        return toDaySecret;
    }

    public void setToDaySecret(int toDaySecret)
    {
        this.toDaySecret = toDaySecret;
    }

}
