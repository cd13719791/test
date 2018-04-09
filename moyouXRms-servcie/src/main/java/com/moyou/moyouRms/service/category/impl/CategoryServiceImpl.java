package com.moyou.moyouRms.service.category.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.category.CategoryMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.category.Category;
import com.moyou.moyouRms.service.category.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Resource
	private CategoryMapper categoryMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Category record) {
		// TODO Auto-generated method stub
		return categoryMapper.insert(record);
	}

	@Override
	public int insertSelective(Category record) {
		// TODO Auto-generated method stub
		return categoryMapper.insertSelective(record);
	}

	@Override
	public Category selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Category record) {
		// TODO Auto-generated method stub
		return categoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Category record) {
		// TODO Auto-generated method stub
		return categoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Category> selectCategoryList(PageBean pb) {
		// TODO Auto-generated method stub
		return categoryMapper.selectCategoryList(pb);
	}

}
