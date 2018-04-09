package com.moyou.moyouRms.service.shareDetails.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.shareDetails.ShareDetailsMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.shareDetails.FollowerOrInterest;
import com.moyou.moyouRms.model.shareDetails.PersonalInfoTalk;
import com.moyou.moyouRms.service.shareDetails.ShareDetailsService;

@Service
public class ShareDetailsServiceImpl implements ShareDetailsService{
	@Resource
	private ShareDetailsMapper shareDetailsMapper;
	/**
	 * 故事列表
	 */
	@Override
	public List<PersonalInfoTalk> queryUserTalk(Map<String, Object> params) {
		List<PersonalInfoTalk> talkList = shareDetailsMapper.queryUserTalk(params);
		Integer myUserId = 0;
		Integer userId = (Integer) params.get("userId");
		if (!myUserId.equals(userId)) { // 查看别人的说说，过滤掉转发已经删除的
			talkList = talkList.stream().filter(
					talk -> talk.getRelateState() == 0).collect(Collectors.toList());
		}
		talkList.forEach(talk -> {
			if (talk.getRelateState() == 0) { // 原说说未删除
				talk.setResourcePics(shareDetailsMapper.queryPicbyTalkId(talk.getTalkId()));
				talk.setSupportState(false);//未点赞
			} else { // 原说说已删除
				talk.setResourcePics(null);
				talk.setSupportState(null);
				talk.setContent(null);
			}
			if (talk.getRelateTalkId() == 0) {
				talk.setRelateState(-1); // -1不是转发的说说
			}

			if (talk.getRelateUserId() == null) {
				talk.setRelateUserId(-1);
			}
		});
		return talkList;
	}
	
	/**
	 * 关注列表
	 */
	@Override
	public List<FollowerOrInterest> queryOtherInterestList(Map<String, Object> params) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(Integer.valueOf(params.get("pageNumber").toString()));
		pageBean.setPageSize(Integer.valueOf(params.get("pageSize").toString()));
		Map<String, Object> map = new HashMap<>();
		map.put("userId", Integer.valueOf(params.get("userId").toString()));
		map.put("myUserId", 0);
		pageBean.setConditions(map);
		List<FollowerOrInterest> followerOrInterest = shareDetailsMapper.queryOtherInterestList(pageBean);
		return followerOrInterest;
	}
}
