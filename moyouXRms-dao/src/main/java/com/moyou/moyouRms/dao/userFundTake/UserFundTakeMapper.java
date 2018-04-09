package com.moyou.moyouRms.dao.userFundTake;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.userFundTake.UserFundTake;

public interface UserFundTakeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFundTake record);

    int insertSelective(UserFundTake record);

    UserFundTake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFundTake record);

    int updateByPrimaryKey(UserFundTake record);
    
    /**
     * 初始化用户提现审核
     * @return
     * @param pb state =1  已处理  state=0 未处理
     */
    List<UserFundTake> selectUserFundTakeByState(PageBean pb);
}