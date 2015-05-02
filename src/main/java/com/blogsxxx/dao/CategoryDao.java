package com.blogsxxx.dao;

import java.util.List;

import com.blogsxxx.model.Category;

public interface CategoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
/**
 * 修改 类别
 * @param category
 */
	void updateCategory(Category category);
/**
 * 根据类别ID排序 列出所有的类别
 * @return
 */
	List<Category> findAllCategoryList();
}