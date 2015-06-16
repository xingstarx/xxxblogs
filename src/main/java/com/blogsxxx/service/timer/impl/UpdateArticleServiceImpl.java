package com.blogsxxx.service.timer.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.TimeLineDao;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.timer.CsdnArticleService;
import com.blogsxxx.service.timer.UpdateArticleService;

@Service
public class UpdateArticleServiceImpl implements UpdateArticleService {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CsdnArticleService csdnArticleService;
	@Autowired
	private TimeLineDao timeLineDao;
	Logger log=Logger.getLogger(getClass());
	@Override
	public void updateArticleTimeLines() {
		try {
			List<Map<String, Object>> mTimeLineList = articleService
					.createTimeLineByArticle();
			// 生成时间线，或者是更新
			csdnArticleService.addTimeLines(mTimeLineList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateArticleCategorys() {
		try {
			List<Map<String, Object>> mCategoryList = articleService
					.createCategoryByArticle();
			// 更新类别
			csdnArticleService.updateCategory(mCategoryList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sychronUpdateArticle() {
		try {
			log.info("定时器调度开始，处理有错误的时间线，或者是文章列别的数量");
			updateArticleTimeLines();
			updateArticleTimeLines();
			log.info("定时器调度结束，处理有错误的时间线，或者是文章列别的数量");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("定时器调度出现错误，不能处理有错误的时间线，或者是文章列别的数量，开始回滚！");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
