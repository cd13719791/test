package com.moyou.moyouRms.model.liveshow;
public class LiveUserLevelSet {
    private Integer id;
    private Integer levelNum;//等级
    private Integer needGold;//需要多少金币
    private Integer needMin;//需要多少分钟  -单位：（分钟）
    private Integer needFans;//需要多少粉丝
    public LiveUserLevelSet() {
        super();
    }
    public LiveUserLevelSet(Integer id,Integer levelNum,Integer needGold,Integer needMin,Integer needFans) {
        super();
        this.id = id;
        this.levelNum = levelNum;
        this.needGold = needGold;
        this.needMin = needMin;
        this.needFans = needFans;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelNum() {
        return this.levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public Integer getNeedGold() {
        return this.needGold;
    }

    public void setNeedGold(Integer needGold) {
        this.needGold = needGold;
    }

    public Integer getNeedMin() {
        return this.needMin;
    }

    public void setNeedMin(Integer needMin) {
        this.needMin = needMin;
    }

    public Integer getNeedFans() {
        return this.needFans;
    }

    public void setNeedFans(Integer needFans) {
        this.needFans = needFans;
    }

}
