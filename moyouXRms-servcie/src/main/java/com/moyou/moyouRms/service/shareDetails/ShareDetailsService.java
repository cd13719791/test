package com.moyou.moyouRms.service.shareDetails;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.shareDetails.FollowerOrInterest;
import com.moyou.moyouRms.model.shareDetails.PersonalInfoTalk;

public interface ShareDetailsService {
	
	/**
	 * 故事
	 * @param 
	 * @return
	 */
	List<PersonalInfoTalk> queryUserTalk(Map<String, Object> params);
	
	/**
	 * 关注列表
	 * @param 
	 * @return
	 */
	List<FollowerOrInterest> queryOtherInterestList(Map<String, Object> params);
}
