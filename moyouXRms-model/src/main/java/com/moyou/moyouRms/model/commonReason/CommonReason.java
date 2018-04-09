package com.moyou.moyouRms.model.commonReason;

import java.io.Serializable;

/**
 * 描述:sys_common_reason表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2017-03-15
 */
public class CommonReason implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1761368966251573809L;

	public static final Integer USER_FUND_TAKE=1;//用户提现申请
	
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1提现拒绝
     */
    private Integer modeType;

    /**
     * 对应数据表主键id
     */
    private Integer modeId;

    /**
     * 原因
     */
    private String reasonText;

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
     * 1提现拒绝
     * @return mode_type 1提现拒绝
     */
    public Integer getModeType() {
        return modeType;
    }

    /**
     * 1提现拒绝
     * @param modeType 1提现拒绝
     */
    public void setModeType(Integer modeType) {
        this.modeType = modeType;
    }

    /**
     * 对应数据表主键id
     * @return mode_id 对应数据表主键id
     */
    public Integer getModeId() {
        return modeId;
    }

    /**
     * 对应数据表主键id
     * @param modeId 对应数据表主键id
     */
    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    /**
     * 原因
     * @return reason_text 原因
     */
    public String getReasonText() {
        return reasonText;
    }

    /**
     * 原因
     * @param reasonText 原因
     */
    public void setReasonText(String reasonText) {
        this.reasonText = reasonText == null ? null : reasonText.trim();
    }
}