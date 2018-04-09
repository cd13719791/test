package com.moyou.moyouRms.model.statistics;

import java.io.Serializable;

public class MiniApps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9220083138597543498L;
	private int yesterdaySig;// 昨日签到
	private int countSig;// 总签到
	private int yesterdayGerrting;// 祝福卡昨日使用数
	private int countGerrting;// 祝福语总使用次数

	public int getYesterdaySig() {
		return yesterdaySig;
	}

	public void setYesterdaySig(int yesterdaySig) {
		this.yesterdaySig = yesterdaySig;
	}

	public int getCountSig() {
		return countSig;
	}

	public void setCountSig(int countSig) {
		this.countSig = countSig;
	}

	public int getYesterdayGerrting() {
		return yesterdayGerrting;
	}

	public void setYesterdayGerrting(int yesterdayGerrting) {
		this.yesterdayGerrting = yesterdayGerrting;
	}

	public int getCountGerrting() {
		return countGerrting;
	}

	public void setCountGerrting(int countGerrting) {
		this.countGerrting = countGerrting;
	}
}
