package com.moyou.moyouRms.service.everyactivityreceive;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivityreceive.EveryActivityReceive;

public interface EveryActivityReceiveService {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivityReceive record);

	int insertSelective(EveryActivityReceive record);

	EveryActivityReceive selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivityReceive record);

	int updateByPrimaryKey(EveryActivityReceive record);

	List<EveryActivityReceive> selectActivityReceiveList(PageBean pb);

	/**
	 * 查询执行了的条数
	 * 
	 * @param integer
	 * @return
	 */
	Integer selectActivityDoingCount(Integer integer);

	/**
	 * 查询没执行的钱总额
	 * 
	 * @param id
	 * @return
	 */
	Integer selectUnDoingFundSum(Integer id);

	/**
	 * 测试单元临时用的方法
	 * 
	 * @return
	 */
	@Deprecated
	List<EveryActivityReceive> selectActivityReceiveForTestUnit();
}