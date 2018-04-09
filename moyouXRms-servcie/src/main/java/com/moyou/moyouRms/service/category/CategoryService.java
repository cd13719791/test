package com.moyou.moyouRms.service.category;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.category.Category;

public interface CategoryService {
    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    /**
	 * 群分类集合查询
	 * 
	 * @param pageNumber pageSize
	 * @return
	 */
    List<Category> selectCategoryList(PageBean pb);
}
