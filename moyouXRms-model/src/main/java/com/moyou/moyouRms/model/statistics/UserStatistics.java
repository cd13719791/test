package com.moyou.moyouRms.model.statistics;

import java.io.Serializable;

public class UserStatistics implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 8558744281231874435L;
private int countYFeedBack;//已处理
private int countNFeedBack;//未处理
private int noDispose;//未处理
	private int dispose;// 已处理;

	public int getCountYFeedBack() {
		return countYFeedBack;
	}

	public void setCountYFeedBack(int countYFeedBack) {
		this.countYFeedBack = countYFeedBack;
	}

	public int getCountNFeedBack() {
		return countNFeedBack;
	}

	public void setCountNFeedBack(int countNFeedBack) {
		this.countNFeedBack = countNFeedBack;
	}

	public int getNoDispose() {
		return noDispose;
	}

	public void setNoDispose(int noDispose) {
		this.noDispose = noDispose;
	}

	public int getDispose() {
		return dispose;
	}

	public void setDispose(int dispose) {
		this.dispose = dispose;
	}
}
