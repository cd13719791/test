/**
 */
package com.moyou.moyouRms;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.userfund.UserFundMapper;
import com.moyou.moyouRms.service.BaseJunit4;
import com.moyou.moyouRms.util.PropertiesUtil;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年2月21日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class Testss extends BaseJunit4
{

    @Resource
    private UserFundMapper userFundMapper;

    @Test
    @Rollback(false)
    public void insert()
    {
        // EasemobRestAPIFactory factory =
        // ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        // IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        // ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
        // FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
        // SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
        // ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
        // ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
        // String SYSTEM_USER_ID = PropertiesUtil.getValueByKey("system_user_id", CONSTANT.SYS_CONF_PATH);
        // System.out.println(SYSTEM_USER_ID);
        BigDecimal toDayWithdrawSum = userFundMapper.selcetToDayWithdrawSum(12038);
        BigDecimal newBig = new BigDecimal(100);
        System.out.println((newBig.multiply(new BigDecimal(100 + "")).add(toDayWithdrawSum)).compareTo(new BigDecimal(200)) < 1);
    }

    public static void main(String[] args)
    {
        String SYSTEM_USER_ID = PropertiesUtil.getValueByKey("system_user_id", CONSTANT.SYS_CONF_PATH);
        System.out.println(SYSTEM_USER_ID);
    }
}
