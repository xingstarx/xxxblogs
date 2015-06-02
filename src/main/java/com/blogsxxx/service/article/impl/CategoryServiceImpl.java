package com.blogsxxx.service.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.CategoryDao;
import com.blogsxxx.model.Category;
import com.blogsxxx.service.article.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
private CategoryDao categoryDao;
	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.insert(category);
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> findAllCategoryList() {
		// TODO Auto-generated method stub
		return categoryDao.findAllCategoryList();
	}

	@Override
	public Integer findByCategoryName(String categoryname) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryName(categoryname);
	}

	@Override
	public Category findByCategoryName2(String categoryname) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryName2(categoryname);
	}

}
