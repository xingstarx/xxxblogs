package com.blogsxxx.service.timer.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.CsdnArticleDao;
import com.blogsxxx.dao.CsdnCategoryDao;
import com.blogsxxx.dao.TimeLineDao;
import com.blogsxxx.model.Article;
import com.blogsxxx.model.Category;
import com.blogsxxx.model.CsdnArticle;
import com.blogsxxx.model.CsdnCategory;
import com.blogsxxx.model.TimeLine;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.article.CategoryService;
import com.blogsxxx.service.timer.CsdnArticleService;
import com.blogsxxx.util.JsoupParserUtil;
import com.blogsxxx.util.UserUtil;
import com.blogsxxx.util.http.HttpHelper;
import com.blogsxxx.util.http.ResponseContent;

@Service
public class CsdnArticleServiceImpl implements CsdnArticleService {
	private static final String BASE_URL = "http://blog.csdn.net";// 博客的url
	@Autowired
	private CsdnCategoryDao csdnCategoryDao;
	@Autowired
	private CsdnArticleDao csdnArticleDao;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TimeLineDao timeLineDao;

	private Logger log = Logger.getLogger(getClass());

	@Override
	public void initCsdnCategory() {
		// TODO Auto-generated method stub
		String url = "http://blog.csdn.net/xxx823952375/article/";// http://blog.csdn.net/xxx823952375/article/
		ResponseContent responseContent = HttpHelper.getUrlRespContent(url);
		List<CsdnCategory> csdnCategories = new ArrayList<CsdnCategory>();
		log.info("初始化记录csdnCategory！！");
		try {
			String str = responseContent.getContent();
			Document document = Jsoup.parse(str, url);
			Element panelCategoryDiv = JsoupParserUtil.getElementById(document,
					"panel_Category");
			Elements panelCategoryLis = JsoupParserUtil.getElementsByTagName(
					panelCategoryDiv, "li");
			for (Element e : panelCategoryLis) {
				// System.out.println(e.html());
				Element a = JsoupParserUtil.getElementsByTagName(e, "a").get(0);
				Element span = JsoupParserUtil.getElementsByTagName(e, "span")
						.get(0);
				CsdnCategory csdnCategory = new CsdnCategory();
				csdnCategory.setCategoryname(a.text());
				String num = span.text();
				csdnCategory.setCount(Integer.valueOf(num.substring(1,
						num.length() - 1)));
				csdnCategory.setUrl(a.attr("href"));
				csdnCategories.add(csdnCategory);
			}

			for (CsdnCategory c : csdnCategories) {
				csdnCategoryDao.insert(c);
				log.info("初始化csdnCategory：＝＝" + c);
			}
			// System.out.println(panelCategoryDiv.html());
			// System.out.println(responseContent.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"initCsdnCategory transaction roll back! ");
		}
	}

	@Override
	public void initCsdnArticleSetp1(String categoryUrl, String categoryname) {
		// TODO Auto-generated method stub
		// String url =
		// "http://blog.csdn.net/xxx823952375/article/category/2147989";//http://blog.csdn.net/xxx823952375/article/category/1731575
		ResponseContent responseContent = HttpHelper
				.getUrlRespContent(categoryUrl);
		try {
			String str = responseContent.getContent();
			log.info("同步每一个category的文章列表！");
			// 增加一个判断，看看是否有分页的内容呢？id＝papelist
			// 目前本人博客里面没有分页的，暂时不考虑
			Document document = Jsoup.parse(str, categoryUrl);

			Element pageList = JsoupParserUtil.getElementById(document,
					"pagelist");
			// 为null,那么就是不存在分页的内容的。
			// 如果有的话。那么后续处理下，http://blog.csdn.net/xxx823952375/article/category/2147989/2
			System.out.println(pageList);
			Element articleListDiv = JsoupParserUtil.getElementById(document,
					"article_list");// 文章列表

			if (articleListDiv == null) {
				log.warn("该类别地址的文章列表不存在！");
				return;
			}
			// System.out.println(articleListDiv.text());
			Elements articleListItemDiv = JsoupParserUtil
					.getElementsByClassName(articleListDiv, "list_item");

			if (articleListItemDiv == null || articleListItemDiv.size() == 0) {
				log.warn("该类别地址的文章列表不存在！");
				return;
			}

			List<CsdnArticle> csdnArticles = new ArrayList<CsdnArticle>();

			for (Element e : articleListItemDiv) {
				// System.out.println(e.html());
				Element h1 = JsoupParserUtil.getElementsByTagName(e, "h1").get(
						0);
				Element a = JsoupParserUtil.getElementsByTagName(h1, "a")
						.get(0);
				// link_postdate
				Element linkPostdate = JsoupParserUtil.getElementsByClassName(
						e, "link_postdate").get(0);

				CsdnArticle csdnArticle = new CsdnArticle();
				csdnArticle.setTitle(a.text());
				csdnArticle.setFromurl(BASE_URL + a.attr("href"));
				csdnArticle.setCreatetime(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm").parse(linkPostdate.text()));
				csdnArticle.setContent(getCsdnArticleContent(csdnArticle
						.getFromurl()));
				csdnArticle.setCategoryname(categoryname);

				System.out.println(csdnArticle);

				csdnArticles.add(csdnArticle);

			}
			for (CsdnArticle csdnArticle : csdnArticles) {
				csdnArticleDao.insert(csdnArticle);
				log.info("同步的文章列表：" + csdnArticle);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"initCsdnArticleSetp1 transaction roll back! ");
		}
	}

	@Override
	public String getCsdnArticleContent(String articleUrl) {
		ResponseContent responseContent = HttpHelper
				.getUrlRespContent(articleUrl);
		try {
			String str = responseContent.getContent();
			log.info("抓取文章的主题内容！");
			// 增加一个判断，看看是否有分页的内容呢？id＝papelist
			// 目前本人博客里面没有分页的，暂时不考虑
			Document document = Jsoup.parse(str, articleUrl);

			Element articleContent = JsoupParserUtil.getElementById(document,
					"article_content");// 文章列表
			String content = articleContent.html();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"getCsdnArticleContent transaction roll back! ");
		}
	}

	@Override
	public void initCsdnArticleAll() {
		// TODO Auto-generated method stub
		List<CsdnCategory> csdnCategories = csdnCategoryDao
				.findAllCsdnCategory();
		for (CsdnCategory c : csdnCategories) {
			initCsdnArticleSetp1(c.getUrl(), c.getCategoryname());
		}
	}

	/**
	 * @author xiongxingxing
	 * @desc 生成类别表，添加到文章表，生成时间线表的记录，统计类别表count数量
	 */
	@Override
	public void cpCsdnArticleToXXXBlogs() {
		// TODO Auto-generated method stub
		try {
			List<CsdnCategory> mCategories = csdnCategoryDao
					.findAllCsdnCategory();
			AddCategorys(mCategories);
			List<CsdnArticle> csdnArticles = csdnArticleDao
					.findAllCsdnArticle();
			AddArticles(csdnArticles);
			// 查询所有的文章，为期生成时间线记录，以及更新类别表的count记录
			// List<Article> articles=articleService.findAllArticle();
			List<Map<String, Object>> mTimeLineList = articleService
					.createTimeLineByArticle();
			// 生成时间线，或者是更新
			addTimeLines(mTimeLineList);

			List<Map<String, Object>> mCategoryList = articleService
					.createCategoryByArticle();
			//更新类别
			updateCategory(mCategoryList);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("csdn博客移植到本项目中！");
		}
	}
/**
 * @desc 更新Category类别，主要是统计count
 * @param mCategoryList
 */
	private void updateCategory(List<Map<String, Object>> mCategoryList) {
		// TODO Auto-generated method stub
		for (Map<String, Object> map : mCategoryList) {
			String categoryname=map.get("categoryname").toString();
			Integer categoryid=(Integer) map.get("categoryid");
			Integer count=Integer.valueOf(map.get("count").toString());
			Category category=categoryService.findByCategoryName2(categoryname);
			if(category!=null){
				category.setArticlecount(count);
				categoryService.updateCategory(category);
			}
		}
	}

	// 生成时间线，或者是更新
	private void addTimeLines(List<Map<String, Object>> mTimeLineList) {
		for (Map<String, Object> map : mTimeLineList) {
			String createTime = map.get("createtime").toString();
			String count = map.get("count").toString();
			TimeLine timeLine = timeLineDao.findTimelineByTimestr(Integer
					.valueOf(createTime));

			if (timeLine != null) {
				timeLine.setArticlecount(Integer.valueOf(count));
				timeLineDao.updateTimeLine(timeLine);
			} else {
				timeLine = new TimeLine();
				timeLine.setArticlecount(Integer.valueOf(count));
				timeLine.setDescr(createTime.substring(0, 4) + "年"
						+ createTime.substring(4) + "月");
				timeLine.setTimestr(Integer.valueOf(createTime));
				timeLineDao.insert(timeLine);
			}
			log.info("add Timeline:" + timeLine);
			// System.out.println(map.get("createtime").toString() + "====="
			// + map.get("count").toString());
		}
	}

	/**
	 * @author xiongxingxing
	 * @desc 添加文章
	 * @param csdnArticles
	 */
	private void AddArticles(List<CsdnArticle> csdnArticles) {
		// TODO Auto-generated method stub
		try {
			if (csdnArticles == null || csdnArticles.size() == 0) {
				return;
			}
			for (CsdnArticle csdnArticle : csdnArticles) {
				Article article = new Article();
				article.setContent(csdnArticle.getContent());
				article.setCreater(UserUtil.USER_NAME);
				article.setCreatetime(csdnArticle.getCreatetime());
				article.setTitle(csdnArticle.getTitle());
				article.setCategoryname(csdnArticle.getCategoryname());
				article.setFromurl(csdnArticle.getFromurl());
				article.setCategoryid(categoryService
						.findByCategoryName(csdnArticle.getCategoryname()));
				articleService.saveArticle(article);
				log.info("article== " + article);
				log.info("添加article到tb_article表里面！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("添加文章失败！");
		}
	}

	/**
	 * @desc 添加category
	 * @param mCategories
	 */
	private void AddCategorys(List<CsdnCategory> mCategories) {
		// TODO Auto-generated method stub
		try {
			if (mCategories == null || mCategories.size() == 0) {
				return;
			}
			for (CsdnCategory c : mCategories) {
				Category category = new Category();
				category.setArticlecount(1);
				category.setCategoryname(c.getCategoryname());
				categoryService.addCategory(category);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("插入条目失败！");
		}
	}

}
