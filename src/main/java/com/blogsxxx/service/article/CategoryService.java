package com.blogsxxx.service.article;

import java.util.List;

import com.blogsxxx.model.Category;
/**
 * @desc 博客类别
 * @author xiongxingxing
 *
 */
public interface CategoryService {
	/**
	 * @desc add Category
	 * @param category
	 */
	void addCategory(Category category);
	/**
	 * @desc update category
	 * @param category
	 */
	void updateCategory(Category category);
	/**
	 * @desc findall Category order by id asc
	 * @return
	 */
	List<Category> findAllCategoryList();
	/**
	 * @desc 根据name查询返回id
	 * @param categoryname
	 * @return
	 */
	Integer findByCategoryName(String categoryname);
	/**
	 * 根据name查询返回对象
	 * @param categoryname
	 * @return
	 */
	Category findByCategoryName2(String categoryname);
}
