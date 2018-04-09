package com.moyou.moyouRms.dao.msg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.msg.MsgUserContact;

public interface MsgUserContactMapper {
    int deleteByPrimaryKey(String id);

    int insert(MsgUserContact record);

    int insertSelective(MsgUserContact record);

    MsgUserContact selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgUserContact record);

    int updateByPrimaryKey(MsgUserContact record);
    
    /**
     * 查询极光推送回调 id , 用于判断是否需要覆盖推送
     * 
     * @param msgUserContact
     * @param aliases
     * @return
     */
    List<Map<String, Object>> queryMsgIdAndUpdateTime(@Param("msgUserContact") MsgUserContact msgUserContact,
			@Param("aliases") List<String> aliases);
    
	/**
	 * 覆盖推送
	 * 
	 * @param msgUserConcern
	 * @return
	 */
	int updateByJpushMsgId(MsgUserContact msgUserContact);
	
	/**
	 * list 插入
	 * 
	 * @param records
	 * @return
	 */
	int insertList(List<MsgUserContact> records);
}