package com.moyou.moyouRms.service.diaryrobot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.dao.diary.DiaryContentMapper;
import com.moyou.moyouRms.dao.diary.DiaryMapper;
import com.moyou.moyouRms.dao.diaryContentRobot.DiaryContentRobotMapper;
import com.moyou.moyouRms.dao.diaryRobot.DiaryRobotMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryContent;
import com.moyou.moyouRms.model.diary.DiaryH5Insert;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobotDetailResult;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.PropertiesUtil;

@Service
public class DiaryRobotServiceImpl implements DiaryRobotService
{
    private static final Logger LOG = LoggerFactory.getLogger(DiaryRobotServiceImpl.class);
    @Resource
    private DiaryRobotMapper diaryRobotMapper;
    @Resource
    private DiaryContentMapper diaryContentMapper;
    @Resource
    private DiaryMapper diaryMapper;
    @Resource
    private DiaryContentRobotMapper diaryContentRobotMapper;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserService userService;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DiaryRobot record)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.insert(record);
    }

    @Override
    public int insertSelective(DiaryRobot record)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.insertSelective(record);
    }

    @Override
    public DiaryRobot selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DiaryRobot record)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(DiaryRobot record)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(DiaryRobot record)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DiaryRobot> queryDiaryRobotList(PageBean pb)
    {
        // TODO Auto-generated method stub
        List<DiaryRobot> list = diaryRobotMapper.queryDiaryRobotList(pb);
        for (DiaryRobot diaryRobot : list)
        {
            diaryRobot.setDiaryContentRobot(diaryContentRobotMapper.selectByDiaryRobotId(diaryRobot.getId()));
        }
        return list;
    }

    @Override
    public int updatePushStateByPrimaryKey(DiaryRobot dRobot)
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.updatePushStateByPrimaryKey(dRobot);
    }

    @Override
    public List<DiaryRobot> queryDiaryRobotFirstResourceList(PageBean pb)
    {
        // TODO Auto-generated method stub
        List<DiaryRobot> list = diaryRobotMapper.queryDiaryRobotList(pb);
        for (DiaryRobot diaryRobot : list)
        {
            diaryRobot.setSearchContent("");
            Map<String, Object> map = new HashMap<String, Object>();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(diaryRobot.getCreatorId());
            userInfo = userInfoService.selectUsreNickNameAndAvatar(diaryRobot.getCreatorId());
            try
            {
                map = JsonUtil.toMap(JsonUtil.toJson(userInfo));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            diaryRobot.setParam(map);
            diaryRobot.setDiaryContentRobot(diaryContentRobotMapper.selectByDiaryRobotIdFirstResource(diaryRobot.getId()));
        }
        return list;
    }

    @Override
    public Map<String, Object> selectDiaryCount()
    {
        // TODO Auto-generated method stub
        return diaryRobotMapper.selectDiaryCount();
    }

    private static String AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX =
        "AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX";// 发布专辑的user
                                                       // redis
                                                       // key
    private static String AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX =
        "AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX";// 防重复userRedisKey

    @Override
    public int insertDiarybyH5(DiaryH5Insert record)
    {
        LOG.info(record.getSearchContent());
        if (record.getPushTime() == null)
        {// 即时发布
            Diary diary = new Diary();
            User u =
                userService.queryUserId(record.getSex(),
                    AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX,
                    AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX);
            if (u == null)
            {
                return 3;
            }
            diary.setDiaryTitle(record.getDiaryTitle());
            diary.setCity(u.getCity());
            diary.setSearchContent(record.getSearchContent());
            diary.setCreatorId(u.getUserId());
            diary.setPraiseTotal(0);
            diary.setCommentTotal(0);
            diary.setRewardTotal(0);
            diary.setPicTotal(record.getPirTotal());
            diary.setState((int) Diary.DIARY_NORMAL_NUMBER);
            diary.setReadTotal(0);
            diary.setSourceType(Diary.SOURCE_TYPE_HTML);
            diary.setUrlAnalysisSuccess(Diary.URL_ANALYS_SUCCESS);
            diary.setCreateTime(new Date());
            diaryMapper.insertSelective(diary);
            // 添加后 修改url参数
            diary.setH5Url(PropertiesUtil.getValueByKey(CONSTANT.SYSTEM_H5_DIARY_PATH, CONSTANT.SYS_CONF_PATH)
                + diary.getId());
            diaryMapper.updateh5UrlById(diary);
            /**
             * 添加content
             */
            // 添加第一张图片
            DiaryContent diaryContent = new DiaryContent();
            diaryContent.setContenType(DiaryContentRobot.PIC);
            diaryContent.setCreateTime(new Date());
            diaryContent.setDiaryId(diary.getId());
            diaryContent.setTextOrPicture(record.getFirstPicUrl());
            diaryContent.setExtendData(record.getExtendData());
            diaryContent.setSorting(2);
            diaryContentMapper.insertSelective(diaryContent);
            // 添加第一段文本
            diaryContent = new DiaryContent();
            diaryContent.setContenType(DiaryContentRobot.TEXT);
            diaryContent.setCreateTime(new Date());
            diaryContent.setDiaryId(diary.getId());
            diaryContent.setTextOrPicture(record.getFirstContent());
            diaryContent.setSorting(1);
            diaryContentMapper.insertSelective(diaryContent);
            return ResponseEnum.SUCCESS.getValue();
        }
        else
        {// 机器人 调度发布
         // User u = userService.queryUserId(record.getSex(),
         // AUTO_PUBLISH_DIARY_JOB_USER_REDIS_KEY_PREFIX,
         // AUTO_PUBLISH_REPEAT_USER_REDIS_KEY_PREFIX);
         // if(u==null) return 0;
            DiaryRobot diaryRobot = new DiaryRobot();
            diaryRobot.setDiaryTitle(record.getDiaryTitle());
            diaryRobot.setSearchContent(record.getSearchContent());
            diaryRobot.setPicTotal(record.getPirTotal());
            diaryRobot.setState((int) Diary.DIARY_NORMAL_NUMBER);
            diaryRobot.setSourceType(Diary.SOURCE_TYPE_HTML);
            diaryRobot.setUrlAnalysisSuccess(Diary.URL_ANALYS_SUCCESS);
            diaryRobot.setCreateTime(new Date());
            diaryRobot.setPushTime(record.getPushTime());
            diaryRobot.setPushState(DiaryRobot.UNPUBLISH_STATE);
            diaryRobotMapper.insertSelective(diaryRobot);
            /**
             * 添加content
             */
            // 添加第一张图片
            DiaryContentRobot diaryContentRobot = new DiaryContentRobot();
            diaryContentRobot.setContenType(DiaryContentRobot.PIC);
            diaryContentRobot.setCreateTime(new Date());
            diaryContentRobot.setDiaryRobotId(diaryRobot.getId());
            diaryContentRobot.setTextOrPicture(record.getFirstPicUrl());
            diaryContentRobot.setExtendData(record.getExtendData());
            diaryContentRobot.setSorting(2);
            diaryContentRobotMapper.insertSelective(diaryContentRobot);
            // 添加第一段文本
            diaryContentRobot = new DiaryContentRobot();
            diaryContentRobot.setContenType(DiaryContentRobot.TEXT);
            diaryContentRobot.setCreateTime(new Date());
            diaryContentRobot.setDiaryRobotId(diaryRobot.getId());
            diaryContentRobot.setTextOrPicture(record.getFirstContent());
            diaryContentRobot.setSorting(1);
            diaryContentRobotMapper.insertSelective(diaryContentRobot);
            return ResponseEnum.SUCCESS.getValue();
        }
    }

    @Override
    public DiaryRobotDetailResult queryDiaryByDiaryId(Integer valueOf)
    {
        DiaryRobotDetailResult diaryRobot = diaryRobotMapper.queryDiaryByDiaryId(valueOf);
        diaryRobot.setList(diaryContentRobotMapper.selectByDiaryRobotId(valueOf));
        return diaryRobot;
    }
}
