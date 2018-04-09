package com.moyou.moyouRms.dao.talk;

import java.util.List;

import com.moyou.moyouRms.model.talk.TalkResource;

public interface TalkResourceMapper {
	/**
	 * 根据说说ID查询说说的资源
	 * 
	 * @param talkId
	 * @return
	 */
	List<TalkResource> queryTalkResource(Integer talkId);
}
