package com.blogsxxx.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogsxxx.dao.ArticleDao;
import com.blogsxxx.model.Category;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.article.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml" })
public class SpringTestJunit4 {
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleService articleService;

	@Test
	public void testStudent() {
		System.out.println("********************");
		System.out.println(articleDao);
	}

	@Autowired
	private CategoryService categoryService;
	@Test
	public void testaddCategory(){
		Category category=new Category();
		category.setArticlecount(1);
		category.setCategoryname("算法");
		categoryService.addCategory(category);
		System.out.println(category);
	}
	
	@Test
	public void test2(){
		List<Map<String,Object>> mTimeLineList=articleService.createTimeLineByArticle();
		for(Map<String, Object> map: mTimeLineList){
			System.out.println(map.get("createtime").toString()+"====="+map.get("count").toString());
		}
	}
	
	@Test
	public void test3(){
		List<Map<String, Object>> mCategoryList = articleService
				.createCategoryByArticle();
		for(Map<String, Object> map: mCategoryList){
			String categoryname=map.get("categoryname").toString();
			Integer categoryid=(Integer) map.get("categoryid");
			Integer count=Integer.valueOf(map.get("count").toString());
			System.out.println(categoryname+"=="+categoryid+"=="+count);
		}
	}
}
