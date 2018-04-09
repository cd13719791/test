package com.moyou.moyouRms.dao.shareDetails;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.shareDetails.FollowerOrInterest;
import com.moyou.moyouRms.model.shareDetails.PersonalInfoTalk;
import com.moyou.moyouRms.model.shareDetails.ResourcePic;

public interface ShareDetailsMapper {
	
	/**
	 * 故事
	 * @param pageBean
	 * @return
	 */
	List<PersonalInfoTalk> queryUserTalk(Map<String, Object> params);
	
	/**
	 * set图片
	 * @param talkId
	 * @return
	 */
	List<ResourcePic> queryPicbyTalkId(Integer talkId);
	
	/**
	 * 关注列表
	 * @param pageBean
	 * @return
	 */
	List<FollowerOrInterest> queryOtherInterestList(PageBean pageBean);
}