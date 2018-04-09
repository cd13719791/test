package com.moyou.moyouRms.service.chatrevert.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.chatRevert.ChatRevertMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.chatRevert.ChatRevert;
import com.moyou.moyouRms.service.chatrevert.ChatRevertService;

@Service
public class ChatRevertServiceImpl implements ChatRevertService {
	@Resource
	ChatRevertMapper chatRevertMapper;

	@Override
	public int insert(ChatRevert record) {
		return chatRevertMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ChatRevert record) {
		record.setUpdateTime(new Date());
		return chatRevertMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ChatRevert> queryListChatRevert() {
		// TODO Auto-generated method stub
		return chatRevertMapper.queryListChatRevert();
	}

	@Override
	public List<ChatRevert> queryListAccostRevert(PageBean pb) {
		// TODO Auto-generated method stub
		return chatRevertMapper.queryListAccostRevert(pb);
	}

}
