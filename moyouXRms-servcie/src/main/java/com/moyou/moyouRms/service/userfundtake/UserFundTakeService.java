package com.moyou.moyouRms.service.userfundtake;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.userFundTake.UserFundTake;
import com.moyou.moyouRms.response.ApiResult;

public interface UserFundTakeService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFundTake record);

    int insertSelective(UserFundTake record);

    UserFundTake selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFundTake record);

    int updateByPrimaryKey(UserFundTake record);
    
    /**
     * 初始化用户提现审核
     * @param pb state =1  已处理  state=0 未处理
     * @return
     */
    List<UserFundTake> selectUserFundTakeByState(PageBean pb);
    /**
     * 审核用户提现记录
     * @param uft
     * @return
     */
    ApiResult updateUserFundTake(UserFundTake userFundTake);
}