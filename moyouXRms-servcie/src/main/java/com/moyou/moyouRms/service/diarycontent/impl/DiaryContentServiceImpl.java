package com.moyou.moyouRms.service.diarycontent.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diary.DiaryContentMapper;
import com.moyou.moyouRms.model.diary.DiaryContent;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
@Service
public class DiaryContentServiceImpl implements DiaryContentService{
@Resource
private DiaryContentMapper diaryContenMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryContenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DiaryContent record) {
		// TODO Auto-generated method stub
		return diaryContenMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryContent record) {
		// TODO Auto-generated method stub
		return diaryContenMapper.insertSelective(record);
	}

	@Override
	public DiaryContent selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryContenMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryContent record) {
		// TODO Auto-generated method stub
		return diaryContenMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryContent record) {
		// TODO Auto-generated method stub
		return diaryContenMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DiaryContent> selectDiaryContentListByDiaryId(Integer id) {
		// TODO Auto-generated method stub
		return diaryContenMapper.selectDiaryContentListByDiaryId(id);
	}
	 /**
     * 根据日记id 获取日记第一段内容图片集合
     * @param id
     * @return
     */
	@Override
	public Map<String,String> selectFirstDiaryContentListByDiaryId(Integer id) {
		// TODO Auto-generated method stub
		return diaryContenMapper.selectFirstDiaryContentListByDiaryId(id);
	}

}
