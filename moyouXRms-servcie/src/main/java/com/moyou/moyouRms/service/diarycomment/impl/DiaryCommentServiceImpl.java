package com.moyou.moyouRms.service.diarycomment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diary.DiaryCommentMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryComment;
import com.moyou.moyouRms.service.diarycomment.DiaryCommentService;
@Service
public class DiaryCommentServiceImpl implements DiaryCommentService {
@Resource
private DiaryCommentMapper diaryCommentMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DiaryComment record) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryComment record) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.insertSelective(record);
	}

	@Override
	public DiaryComment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryComment record) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryComment record) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DiaryComment> selectDiaryCommentListByDiaryId(Integer id) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.selectDiaryCommentListByDiaryId(id);
	}

	@Override
	public List<DiaryComment> selectDiaryCommentListByDiaryIdLimit(PageBean pb) {
		// TODO Auto-generated method stub
		return diaryCommentMapper.selectDiaryCommentListByDiaryIdLimit( pb);
	}

}
