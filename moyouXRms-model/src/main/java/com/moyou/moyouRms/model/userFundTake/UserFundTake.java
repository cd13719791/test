package com.moyou.moyouRms.model.userFundTake;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:t_user_fund_take表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-03-15
 */
public class UserFundTake implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4248292254424637121L;
	private String moyouId;
	private String nickname;

	/**
	 * 微信账号
	 */
	private String weixin;
	 public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
     * 审核状态1待审核2通过3未通过
     */
	public static final int WAITTING_TAKE=1;
	public static final int AGREE=2;
	public static final int NOT_AGREE=3;
			
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 提现金额
     */
    private BigDecimal takeFund;

    /**
     * 审核状态1待审核2通过3未通过
     */
    private Integer auditStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 后台管理员id
     */
    private String auditorId;

    /**
     * 后台管理员名称
     */
    private String auditorName;

    /**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 提现金额
     * @return take_fund 提现金额
     */
    public BigDecimal getTakeFund() {
        return takeFund;
    }

    /**
     * 提现金额
     * @param takeFund 提现金额
     */
    public void setTakeFund(BigDecimal takeFund) {
        this.takeFund = takeFund;
    }
    /**
     * 审核状态1待审核2通过3未通过
     * @return audit_status 审核状态1待审核2通过3未通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 审核状态1待审核2通过3未通过
     * @param auditStatus 审核状态1待审核2通过3未通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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

    /**
     * 审核时间
     * @return audit_time 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 审核时间
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 后台管理员id
     * @return auditor_id 后台管理员id
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     * 后台管理员id
     * @param auditorId 后台管理员id
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId == null ? null : auditorId.trim();
    }

    /**
     * 后台管理员名称
     * @return auditor_name 后台管理员名称
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * 后台管理员名称
     * @param auditorName 后台管理员名称
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }
    
    /**
     * 拒绝理由
     */
    private String reasonText;
    
    public String getReasonText() {
    	return reasonText;
    }
    
    public void setReasonText(String reasonText) {
    	this.reasonText = reasonText;
    }
}