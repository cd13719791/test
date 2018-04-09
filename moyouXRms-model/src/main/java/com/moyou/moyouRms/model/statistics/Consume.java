package com.moyou.moyouRms.model.statistics;

import java.io.Serializable;

public class Consume implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3755271646176285389L;
	private int countToDayWithdraw;// 今日提现申请数
	private int counTtoDayWithdrawMoney;// 总金额
	private int countAuditWithdraw;// 提现审核中申请总数
	private int countMoney;// 提现审核中总金额
	private int countSucceedWithdraw;// 提现成功申请总数
	private int countSucceedMoney;// 提现成功总金额
	private int countHb;// 红包总数
	private int countHbMoney;// 红包总金额
	private int countYesterDayHb;//昨日红包总数
	private int countYesterDayHbMoney;//昨日红包总金额
	private int countToDayHb;//今日红包总数
	private int countToDayHbMoney;//今日红包总金额
  private int countGive;//打赏总次数
  private int countGiveMoney;//总打赏金额
  private int countFund;//总金币数
  private int talkGiveMoney;//说说打赏金额
  private int countTalkGive;//说说打赏次数
  private int diaryGiveMoney;//故事打赏金额
  private int countDiaryGvie;//故事打赏次数
  private int countUseFund;//总消耗金币
  private int countAdminFund;//管理员赠送金币
	public int getCountToDayHb() {
	return countToDayHb;
}

public void setCountToDayHb(int countToDayHb) {
	this.countToDayHb = countToDayHb;
}

public int getCountToDayHbMoney() {
	return countToDayHbMoney;
}

public void setCountToDayHbMoney(int countToDayHbMoney) {
	this.countToDayHbMoney = countToDayHbMoney;
}

	public int getTalkGiveMoney() {
	return talkGiveMoney;
}

public void setTalkGiveMoney(int talkGiveMoney) {
	this.talkGiveMoney = talkGiveMoney;
}

public int getCountTalkGive() {
	return countTalkGive;
}

public void setCountTalkGive(int countTalkGive) {
	this.countTalkGive = countTalkGive;
}

public int getDiaryGiveMoney() {
	return diaryGiveMoney;
}

public void setDiaryGiveMoney(int diaryGiveMoney) {
	this.diaryGiveMoney = diaryGiveMoney;
}

public int getCountDiaryGvie() {
	return countDiaryGvie;
}

public void setCountDiaryGvie(int countDiaryGvie) {
	this.countDiaryGvie = countDiaryGvie;
}

	public int getCountYesterDayHb() {
	return countYesterDayHb;
}

public void setCountYesterDayHb(int countYesterDayHb) {
	this.countYesterDayHb = countYesterDayHb;
}

public int getCountYesterDayHbMoney() {
	return countYesterDayHbMoney;
}

public void setCountYesterDayHbMoney(int countYesterDayHbMoney) {
	this.countYesterDayHbMoney = countYesterDayHbMoney;
}

	public int getCountFund() {
	return countFund;
}

public void setCountFund(int countFund) {
	this.countFund = countFund;
}

public int getCountUseFund() {
	return countUseFund;
}

public void setCountUseFund(int countUseFund) {
	this.countUseFund = countUseFund;
}

public int getCountAdminFund() {
	return countAdminFund;
}

public void setCountAdminFund(int countAdminFund) {
	this.countAdminFund = countAdminFund;
}

	public int getCountGive() {
	return countGive;
}

public void setCountGive(int countGive) {
	this.countGive = countGive;
}

public int getCountGiveMoney() {
	return countGiveMoney;
}

public void setCountGiveMoney(int countGiveMoney) {
	this.countGiveMoney = countGiveMoney;
}

	public int getCountToDayWithdraw() {
		return countToDayWithdraw;
	}

	public void setCountToDayWithdraw(int countToDayWithdraw) {
		this.countToDayWithdraw = countToDayWithdraw;
	}

	public int getCounTtoDayWithdrawMoney() {
		return counTtoDayWithdrawMoney;
	}

	public void setCounTtoDayWithdrawMoney(int counTtoDayWithdrawMoney) {
		this.counTtoDayWithdrawMoney = counTtoDayWithdrawMoney;
	}

	public int getCountAuditWithdraw() {
		return countAuditWithdraw;
	}

	public void setCountAuditWithdraw(int countAuditWithdraw) {
		this.countAuditWithdraw = countAuditWithdraw;
	}

	public int getCountMoney() {
		return countMoney;
	}

	public void setCountMoney(int countMoney) {
		this.countMoney = countMoney;
	}

	public int getCountSucceedWithdraw() {
		return countSucceedWithdraw;
	}

	public void setCountSucceedWithdraw(int countSucceedWithdraw) {
		this.countSucceedWithdraw = countSucceedWithdraw;
	}

	public int getCountSucceedMoney() {
		return countSucceedMoney;
	}

	public void setCountSucceedMoney(int countSucceedMoney) {
		this.countSucceedMoney = countSucceedMoney;
	}

	public int getCountHb() {
		return countHb;
	}

	public void setCountHb(int countHb) {
		this.countHb = countHb;
	}

	public int getCountHbMoney() {
		return countHbMoney;
	}

	public void setCountHbMoney(int countHbMoney) {
		this.countHbMoney = countHbMoney;
	}
}
