package com.moyou.moyouRms.model.statistics;

import java.io.Serializable;

public class Robot implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 3100053987135545491L;
private int talkY;//已发布
private int talkN;//未发布
private int diaryY;
private int diaryN;
private int secretY;
private int secretN;
private int commentY;
private int commentN;
public int getTalkY() {
	return talkY;
}
public void setTalkY(int talkY) {
	this.talkY = talkY;
}
public int getTalkN() {
	return talkN;
}
public void setTalkN(int talkN) {
	this.talkN = talkN;
}
public int getDiaryY() {
	return diaryY;
}
public void setDiaryY(int diaryY) {
	this.diaryY = diaryY;
}
public int getDiaryN() {
	return diaryN;
}
public void setDiaryN(int diaryN) {
	this.diaryN = diaryN;
}
public int getSecretY() {
	return secretY;
}
public void setSecretY(int secretY) {
	this.secretY = secretY;
}
public int getSecretN() {
	return secretN;
}
public void setSecretN(int secretN) {
	this.secretN = secretN;
}
public int getCommentY() {
	return commentY;
}
public void setCommentY(int commentY) {
	this.commentY = commentY;
}
public int getCommentN() {
	return commentN;
}
public void setCommentN(int commentN) {
	this.commentN = commentN;
}
}
