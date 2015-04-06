package com.blogsxxx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blogsxxx.dao.ArticleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "file*:applicationContext.xml" })
public class SpringTestJunit4 {
	@Autowired
	ArticleDao articleDao;

	@Test
	public void testStudent() {
		System.out.println("********************");
		System.out.println(articleDao);

	}

}
