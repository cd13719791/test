package com.moyou.moyouRms.model.msg;

/**
 * MsgIndexReturn
 * 
 * @author PzC.
 * @time 2016年12月7日 下午2:51:27
 * 
 */
public class MsgIndexReturn {
	private MsgItemReturn systemMsg;
	private MsgItemReturn concernMsg;
	private MsgItemReturn commentMsg;

	public MsgItemReturn getSystemMsg() {
		return systemMsg;
	}

	public void setSystemMsg(MsgItemReturn systemMsg) {
		this.systemMsg = systemMsg;
	}

	public MsgItemReturn getConcernMsg() {
		return concernMsg;
	}

	public void setConcernMsg(MsgItemReturn concernMsg) {
		this.concernMsg = concernMsg;
	}

	public MsgItemReturn getCommentMsg() {
		return commentMsg;
	}

	public void setCommentMsg(MsgItemReturn commentMsg) {
		this.commentMsg = commentMsg;
	}
}
