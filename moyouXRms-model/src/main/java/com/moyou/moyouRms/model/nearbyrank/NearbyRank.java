package com.moyou.moyouRms.model.nearbyrank;
public class NearbyRank {
    private String id;
    private Integer userId;
    private Integer gold;//占位时消费的金币
    private java.util.Date updateTime;
    private String city;
    public NearbyRank() {
        super();
    }
    public NearbyRank(String id,Integer userId,Integer gold,java.util.Date updateTime,String city) {
        super();
        this.id = id;
        this.userId = userId;
        this.gold = gold;
        this.updateTime = updateTime;
        this.city = city;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGold() {
        return this.gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
