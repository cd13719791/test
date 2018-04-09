package com.moyou.moyouRms.service.syssessionmanager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.Const;
import com.moyou.moyouRms.dao.sysSessionManager.SysSessionManagerMapper;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.sysSessionManager.SysSessionManager;
import com.moyou.moyouRms.service.syssessionmanager.SysSessionManagerService;

@Service
public class SysSessionManagerSreviceImpl implements SysSessionManagerService
{
    @Resource
    SysSessionManagerMapper sysSessionManagerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(SysSessionManager record)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertSelective(SysSessionManager record)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public SysSessionManager selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysSessionManager record)
    {
        // TODO Auto-generated method stub
        return sysSessionManagerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysSessionManager record)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateByLoginName(Session session)
    {
        // TODO Auto-generated method stub
        Account account = ((Account) session.getAttribute(Const.SESSION_USER));
        if (account == null)
        {
            return 0;
        }
        SysSessionManager sysSessionManager = new SysSessionManager();
        sysSessionManager.setLoginName(account.getLoginName());
        sysSessionManager.setOnlineTime(Integer.valueOf(session.getLastAccessTime().getTime()
            - (session.getStartTimestamp().getTime()) + "") / 1000 / 60);
        sysSessionManager.setOnlineType(SysSessionManager.OFFLINE);
        sysSessionManager.setSessionId(session.getId().toString());
        // System.out.println(sysSessionManager.getOnlineTime());
        // System.out.println(sysSessionManager.getSessionId());
        List<SysSessionManager> list =
            sysSessionManagerMapper.selectToDayDataByLoginName(account.getLoginName());
        if (list == null || list.size() == 0)
        {
            sysSessionManager.setCreateTime(new Date());
            return sysSessionManagerMapper.insertSelective(sysSessionManager);
        }
        return sysSessionManagerMapper.updateByLoginName(sysSessionManager);
    }

    @Override
    public int updateOnlineTypeByLoginName(SysSessionManager record)
    {
        record.setSetTime(new Date());
        List<SysSessionManager> list =
            sysSessionManagerMapper.selectToDayDataByLoginName(record.getLoginName());
        if (list == null || list.size() == 0)
        {
            return sysSessionManagerMapper.insertSelective(record);
        }

        return sysSessionManagerMapper.updateOnlineTypeByLoginName(record);
    }

    @Override
    public int updateByLoginName(SysSessionManager sysSessionManager)
    {
        // TODO Auto-generated method stub
        // Account account = ((Account) session.getAttribute(Const.SESSION_USER));
        // if (account == null) {
        // return 0;
        // }
        // SysSessionManager sysSessionManager = new SysSessionManager();
        // sysSessionManager.setLoginName(sysSessionManager.getLoginName());
        // sysSessionManager.setOnlineTime(Integer.valueOf((new Date().getTime()-sysSessionManager.getLoginTime()
        // .getTime() ) + "")/1000/60);
        // sysSessionManager.setOnlineType(SysSessionManager.OFFLINE);
        if (sysSessionManagerMapper.selectToDayDataByLoginName(sysSessionManager.getLoginName()) == null)
        {
            sysSessionManager.setCreateTime(new Date());
            return sysSessionManagerMapper.insertSelective(sysSessionManager);
        }
        return sysSessionManagerMapper.updateByLoginName(sysSessionManager);
    }

    public static void main(String[] args)
    {
        System.out.println(new Date().getTime() - (new Date().getTime() + 1l));

    }

    @Override
    public SysSessionManager selectByLoginName(String loginName)
    {
        // TODO Auto-generated method stub
        return sysSessionManagerMapper.selectByLoginName(loginName);
    }

    @Override
    public int updateAllUserOnlineType()
    {
        // TODO Auto-generated method stub
        return sysSessionManagerMapper.updateAllUserOnlineType();
    }

    @Override
    public SysSessionManager selectToDayDataByLoginName(String id)
    {
        // TODO Auto-generated method stub
        List<SysSessionManager> list = sysSessionManagerMapper.selectToDayDataByLoginName(id);
        if (list != null && list.size() != 0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public int updateSessionSetTime(String longinName)
    {
        // TODO Auto-generated method stub
        return sysSessionManagerMapper.updateSessionSetTime(longinName);
    }

    @Override
    public List<SysSessionManager> selectSessionNeedBalance()
    {
        // TODO Auto-generated method stub
        return sysSessionManagerMapper.selectSessionNeedBalance();
    }
}
