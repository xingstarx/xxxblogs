package com.blogsxxx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.blogsxxx.model.Article;

public class MybatisUtil {
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory getSessionFactory() throws IOException {
		if (sqlSessionFactory == null) {
			String resource = "mybatisConfigTest.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			return new SqlSessionFactoryBuilder().build(inputStream);
		} else {
			return sqlSessionFactory;
		}
	}
	public static void main(String[] args) throws Exception {
		sqlSessionFactory=getSessionFactory();
		  SqlSession session = sqlSessionFactory.openSession();  
		  Article mlist=session.selectOne("selectByPrimaryKey", 1);
		  System.out.println(mlist);
	}
}
