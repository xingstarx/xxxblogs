package com.blogsxxx.service.article;

import com.blogsxxx.model.Article;

public interface ArticleService {

	/**
	 * @desc 查找指定的article
	 * @param id
	 * @return
	 */
	 Article findArticleById(Integer id);
}
