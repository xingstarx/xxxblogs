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
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 根据类别ID排序 列出所有的类别
	 * 
	 * @return
	 */
	List<Category> findAllCategoryList();

	/**
	 * @author xiongxingxing
	 * @desc 根据name查询，返回id
	 * @param categoryname
	 * @return
	 */
	Integer findByCategoryName(String categoryname);
	/**
	 * @author xiongxingxing
	 * @desc 根据name查询，返回id
	 * @param categoryname
	 * @return
	 */
	Category findByCategoryName2(String categoryname);
	/**
	 * @author xiongxingxing
	 * @desc 根据id 查找，返回对象
	 * @param categoryname
	 * @return
	 */
	Category findCategoryById(Integer categoryId);
}