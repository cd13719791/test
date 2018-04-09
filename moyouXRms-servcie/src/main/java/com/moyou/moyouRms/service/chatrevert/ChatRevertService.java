package com.moyou.moyouRms.service.chatrevert;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.chatRevert.ChatRevert;

public interface ChatRevertService {
	/**
	 * 添加一跳常用语（聊天）
	 * @param record
	 * @return
	 */
    int insert(ChatRevert record);
    /**
     *  假删除一条常用语
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChatRevert record);
   /**
    *  初始化常用语
    * @return
    */
    List<ChatRevert> queryListChatRevert();
List<ChatRevert> queryListAccostRevert(PageBean pb);

}
