package com.blogsxxx.service.timer;

import java.util.List;
import java.util.Map;

public interface CsdnArticleService {
	/**
	 * @author xiongxingxing 
	 * @desc 首次同步csdn接口数据 category
	 */
	public void initCsdnCategory();
	/**
	 * @author xiongxingxing
	 * @desc 同步类别下的文章
	 */
	public void initCsdnArticleSetp1(String categoryUrl,String categoryname);
	/**
	 * @desc 根据url取得csdn博客上的文章的内容，只包含主体内容，即<div id="article_content" class="article_content"></div>
	 * @param articleUrl
	 * @return
	 */
	public String getCsdnArticleContent(String articleUrl);
	/**
	 * @author xiongxingxing
	 * @desc 初始化所有类别下的文章
	 */
	public void initCsdnArticleAll();
	/**
	 * @author xiongxingxing
	 * @desc 复制文章到本项目是使用的tb_article表中
	 */
	public void cpCsdnArticleToXXXBlogs();
	/**
	 * @desc 生成时间线，或者是更新 map (createtime  value,  count   value) 
	 * @param mTimeLineList
	 */
	void addTimeLines(List<Map<String, Object>> mTimeLineList);
	
	/**
	 * @author xiongxingxing
	 * @desc 总的调度，即系统第一次调度，把csdn上的文章同步过来
	 */
	public void synchronCSDNBlogsAndCreateTable();
	/**
	 * @desc 更新类别纪录
	 * @param mCategoryList
	 */
	void updateCategory(List<Map<String, Object>> mCategoryList);
	
}
