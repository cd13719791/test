package com.moyou.moyouRms.service.diaryrecommend.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diaryRecommend.DiaryRecommendMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diaryRecommend.DiaryRecommend;
import com.moyou.moyouRms.service.diaryrecommend.DiaryRecommendService;

@Service
public class DiaryRecommendServiceImpl implements DiaryRecommendService {

	@Resource
	private DiaryRecommendMapper diaryRecommendMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(DiaryRecommend record) {
		return diaryRecommendMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryRecommend record) {
		Integer record1 = diaryRecommendMapper.selectByDiaryId(record
				.getDiaryId());
		Integer record2 = record.getDiaryId();
		if (record1 == null) {
			diaryRecommendMapper.insertSelective(record);
		} else if (record1.equals(record2)) {
			return diaryRecommendMapper.updateByPrimaryKeySelective(record);
		}
		return 1;
	}

	@Override
	public DiaryRecommend selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryRecommend record) {
		return diaryRecommendMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryRecommend record) {
		return 0;
	}

	@Override
	public List<Diary> queryDiaryRecommendList(PageBean record) {
		return diaryRecommendMapper.queryDiaryRecommendList(record);
	}

	@Override
	public int updateByDiaryId(DiaryRecommend record) {
		// TODO Auto-generated method stub
		return diaryRecommendMapper.updateByDiaryId(record);
	}

}
