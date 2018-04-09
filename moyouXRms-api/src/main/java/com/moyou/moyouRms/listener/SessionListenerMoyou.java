package com.moyou.moyouRms.listener;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;
import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;

public class SessionListenerMoyou implements SessionListener
{
    @Resource
    private SysSessionManagerService sysSessionManagerService;

    @Override
    public void onStart(Session session)
    {// 会话创建触发 已进入shiro的过滤连就触发这个方法
    // System.out.println(session.getTimeout());
    // System.out.println("会话创建：" + session.getId());
    // System.out.println("会话生命时间: !@#$%^&&*()==>" + session.getTimeout());
    // session.setTimeout(2*60*1000L);
    }

    @Override
    public void onStop(Session session)
    {// 退出
        // TODO Auto-generated method stub
        // System.out.println("退出会话：" + session.getId());

    }

    @Override
    public void onExpiration(Session session)
    {// 会话过期时触发
        // TODO Auto-generated method stub
        // System.out.println("会话过期：" + session.getId());
        Account account = (Account) (session.getAttribute(Const.SESSION_USER));
        SysSessionManager s = new SysSessionManager();
        if (account == null)
        {
            return;
        }
        s.setLoginName(account.getLoginName());
        s.setOnlineTime(Integer.valueOf(session.getLastAccessTime().compareTo(session.getStartTimestamp())) / 1000 / 60);
        s.setSessionId(session.getId().toString());
        s.setOnlineType(SysSessionManager.OFFLINE);
        sysSessionManagerService.updateByLoginName(s);
        // SecurityUtils.getSubject().logout();
    }

}
