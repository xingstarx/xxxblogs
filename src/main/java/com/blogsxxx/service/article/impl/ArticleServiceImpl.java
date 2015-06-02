package com.blogsxxx.service.article.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public Article findArticleByTitle(String title) {
		// TODO Auto-generated method stub
		return articleDao.findArticleByTitle(title);
	}

	@Override
	public void saveArticle(Article article) {
		// TODO Auto-generated method stub
		articleDao.insert(article);
//		throw new RuntimeException("rollback! test");
	}

	@Override
	public List<Article> findAllArticle() {
		// TODO Auto-generated method stub
		return articleDao.findAllArticle();
	}

	@Override
	public List<Map<String, Object>> createTimeLineByArticle() {
		// TODO Auto-generated method stub
		return articleDao.createTimeLineByArticle();
	}

	@Override
	public List<Map<String, Object>> createCategoryByArticle() {
		// TODO Auto-generated method stub
		return articleDao.createCategoryByArticle();
	}
	
	
}
