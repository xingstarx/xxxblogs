package com.blogsxxx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.blogsxxx.model.Article;
public interface ArticleDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    /**
	  * @desc 取出最近的二篇文章
	  * @return List<Article>
	  */
	List<Article> findArticlesByRecent();
	/**
	  * @desc 根据ID查找上一篇文章
	  * @param min
	  * @return
	  */
	Article findPreArticleById(@Param("min")Integer min);

	/**
	 * @desc 查找下一篇文章
	 * @param max
	 * @return
	 */
	Article findNextArticleById(@Param("max")Integer max);
/**
 * @desc 根据标题查找文章
 * @param title
 * @return
 */
	Article findArticleByTitle(@Param("title")String title);
/**
 * @desc 查找全部的文章
 * @return
 */
List<Article> findAllArticle();
/**
 * @desc 根据文章表，生成对应的时间线记录
 * @return
 */
List<Map<String, Object>> createTimeLineByArticle();
/**
 * @desc 根据文章表，生成对应的类别记录
 * @return
 */
List<Map<String, Object>> createCategoryByArticle();
}