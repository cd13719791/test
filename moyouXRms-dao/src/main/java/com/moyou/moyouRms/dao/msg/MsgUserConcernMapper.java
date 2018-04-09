package com.moyou.moyouRms.dao.msg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msg.MsgItemReturn;
import com.moyou.moyouRms.model.msg.MsgUserConcern;

/**
 * 关系消息
 * 
 * @author PzC.
 * @time 2016年11月30日 下午4:56:59
 * 
 */
public interface MsgUserConcernMapper {
	int deleteByPrimaryKey(String id);

	int insert(MsgUserConcern record);

	int insertSelective(MsgUserConcern record);

	MsgUserConcern selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(MsgUserConcern record);

	int updateByPrimaryKey(MsgUserConcern record);

	/**
	 * 查询极光推送回调 id , 用于判断是否需要覆盖推送
	 * 
	 * @param msgUserConcern
	 * @param aliases
	 * @return
	 */
	List<Map<String, Object>> queryMsgIdAndUpdateTime(@Param("msgUserConcern") MsgUserConcern msgUserConcern,
			@Param("aliases") List<String> aliases);

	/**
	 * 覆盖推送
	 * 
	 * @param msgUserConcern
	 * @return
	 */
	int updateByJpushMsgId(MsgUserConcern msgUserConcern);

	/**
	 * list 插入
	 * 
	 * @param records
	 * @return
	 */
	int insertList(List<MsgUserConcern> records);
	
	/**
	 * 查询一条关注消息
	 * 
	 * @param userId
	 * @return
	 */
	MsgItemReturn queryConcernMsgItem(String userId);
	
	/**
	 * 关注消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<Map<String, Object>> queryConcernMsgList(PageBean pageBean);
}