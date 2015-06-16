package com.blogsxxx.service.timer;
/**
 * @desc 更新文章的一些service
 * @author xiongxingxing
 *
 */
public interface UpdateArticleService {
	/**
	 * @desc 调整时间线出错的service调度，即判断时间线的每一条的count数是否对的上.
	 */
	public void updateArticleTimeLines();
	
	/**
	 * @desc 调整类别表出错的service调度，即判断类别表的的每一条的count数是否对的上.
	 */
	public void updateArticleCategorys();
	/**
	 * @author xiongxingxing
	 * @desc 更新文章的整体调度方法
	 */
	public void sychronUpdateArticle();
}
