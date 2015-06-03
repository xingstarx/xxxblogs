package com.blogsxxx.service.article;

import java.util.List;
import java.util.Map;

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
	/**
	 * @author xiongxingxing
	 * @desc 根据标题查找文章
	 * @param title
	 * @return
	 */
	Article findArticleByTitle(String title);
	/**
	 * @desc 保存文章
	 * @param article
	 */
	void saveArticle(Article article);
	/**
	 * @author xiongxingxing
	 * @desc 查询全部的文章
	 * @return
	 */
	List<Article> findAllArticle();
	/**
	 * @author xiongxingxing
	 * @desc 根据文章表做统计，生成对应的记录
	 * @return
	 */
	List<Map<String, Object>> createTimeLineByArticle();
	/**
	 * @author xiongxingxing
	 * @desc 根据文章表做统计，生成对应的类别记录
	 * @return
	 */
	List<Map<String, Object>> createCategoryByArticle();
	/**
	 * @desc 根据时间线id，查找对应的文章
	 * @param id
	 * @return
	 */
	List<Article> findArticleByTimeLineId(Integer id);
	/**
	 * @desc 根据categoryid，查找对应的文章
	 * @param id
	 * @return
	 */
	List<Article> findArticleByCategoryId(Integer id);
}
