package com.blogsxxx.test;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	@Test
	public void testSpringMybatis(){

	
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		
		SqlSessionFactoryBean sqlSessionFactoryBean= context.getBean(SqlSessionFactoryBean.class);
		
		DataSource dataSource=context.getBean(BasicDataSource.class);
		System.out.println(dataSource);
		System.out.println(sqlSessionFactoryBean);
	}
}
