package com.moyou.moyouRms.service.diaryfolder.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diary.DiaryFolderMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryFolder;
import com.moyou.moyouRms.service.diaryfolder.DiaryFolderService;
@Service
public class DiaryFolderServiceImpl implements DiaryFolderService{
	@Resource
	private DiaryFolderMapper diaryFolderMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DiaryFolder record) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryFolder record) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.insertSelective(record);
	}

	@Override
	public DiaryFolder selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryFolder record) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryFolder record) {
		// TODO Auto-generated method stub
		return diaryFolderMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DiaryFolder> selectDiaryFolderBySelective(PageBean pb) {
		return diaryFolderMapper.selectDiaryFolderBySelective(pb);
	}
	
}
