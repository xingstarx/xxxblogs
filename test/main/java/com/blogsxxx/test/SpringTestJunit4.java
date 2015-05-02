package com.blogsxxx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogsxxx.dao.ArticleDao;
import com.blogsxxx.model.Category;
import com.blogsxxx.service.article.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml" })
public class SpringTestJunit4 {
	@Autowired
	ArticleDao articleDao;

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
}
