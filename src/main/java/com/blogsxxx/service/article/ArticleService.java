package com.blogsxxx.service.article;

import java.util.List;

import com.blogsxxx.model.Article;

public interface ArticleService {

	/**
	 * @desc 查找指定的article
	 * @param id
	 * @return
	 */
	 Article findArticleById(Integer id);
	 /**
	  * @desc 取出最近的二篇文章
	  */
	 List<Article> findArticlesByRecent();
	 /**
	  * @desc 根据ID查找上一篇文章
	  * @param min
	  * @return
	  */
	Article findPreArticleById(Integer min);
	/**
	 * @desc 查找下一篇文章
	 * @param max
	 * @return
	 */
	Article findNextArticleById(Integer max);
}
