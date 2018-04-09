package com.moyou.moyouRms.model.systemFund;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:sys_fund表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-06-20
 */
public class SystemFund {
	private Integer code;
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	/**
     * 
     */
    private Integer id;

    /**
     * 充值金额
     */
    private BigDecimal fund;

    /**
     * 
     */
    private Date createTime;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 充值后余额
     */
    private BigDecimal balance;

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
     * 充值金额
     * @return fund 充值金额
     */
    public BigDecimal getFund() {
        return fund;
    }

    /**
     * 充值金额
     * @param fund 充值金额
     */
    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 登录账号
     * @return login_name 登录账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录账号
     * @param loginName 登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 流水号
     * @return trade_no 流水号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 流水号
     * @param tradeNo 流水号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * 充值后余额
     * @return balance 充值后余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 充值后余额
     * @param balance 充值后余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}