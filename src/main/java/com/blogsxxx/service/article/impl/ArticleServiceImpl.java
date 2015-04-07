package com.blogsxxx.service.article.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.ArticleDao;
import com.blogsxxx.model.Article;
import com.blogsxxx.service.article.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	@Override
	public Article findArticleById(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticlesByRecent() {
		// TODO Auto-generated method stub
		return articleDao.findArticlesByRecent();
	}

	@Override
	public Article findPreArticleById(Integer min) {
		// TODO Auto-generated method stub
		return articleDao.findPreArticleById(min);
	}

	@Override
	public Article findNextArticleById(Integer max) {
		// TODO Auto-generated method stub
		return articleDao.findNextArticleById(max);
	}
	
	
}
