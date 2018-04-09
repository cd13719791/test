package com.moyou.moyouRms.model.liveshow;
public class LiveUserConvert {
    private Integer id;
    private Integer userId;//主播用户id
    private String nickname;//用户昵称
    private String moyouId;//陌友id
    private String weixinId;//微信账号
    private Integer goldNum;//兑换前主播金币余额
    private Integer money;//提现金额(分)
    private java.util.Date createTime;
    private Integer state;//操作状态 0未处理 1已处理 2 完成
    private String operationUser;//操作人
    private java.util.Date operationTime;//操作时间
    private String operationRemark;//操作备注
    public LiveUserConvert() {
        super();
    }
    public LiveUserConvert(Integer id,Integer userId,String nickname,String moyouId,String weixinId,Integer goldNum,Integer money,java.util.Date createTime,Integer state,String operationUser,java.util.Date operationTime,String operationRemark) {
        super();
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.moyouId = moyouId;
        this.weixinId = weixinId;
        this.goldNum = goldNum;
        this.money = money;
        this.createTime = createTime;
        this.state = state;
        this.operationUser = operationUser;
        this.operationTime = operationTime;
        this.operationRemark = operationRemark;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMoyouId() {
        return this.moyouId;
    }

    public void setMoyouId(String moyouId) {
        this.moyouId = moyouId;
    }

    public String getWeixinId() {
        return this.weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public Integer getGoldNum() {
        return this.goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOperationUser() {
        return this.operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public java.util.Date getOperationTime() {
        return this.operationTime;
    }

    public void setOperationTime(java.util.Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationRemark() {
        return this.operationRemark;
    }

    public void setOperationRemark(String operationRemark) {
        this.operationRemark = operationRemark;
    }

}
