package com.blogsxxx.controller.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogsxxx.model.Article;
import com.blogsxxx.model.Category;
import com.blogsxxx.model.TimeLine;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.article.CategoryService;
import com.blogsxxx.service.article.TimeLineService;
import com.blogsxxx.util.HtmlUtils;

@RequestMapping("/article")
@Scope("session")
@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TimeLineService timeLineService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		List<Article> articles = articleService.findArticlesByRecent();

		if (articles != null && articles.size() > 0) {
			request.setAttribute("max", articles.get(0).getId());
			request.setAttribute("min", articles.get(articles.size() - 1)
					.getId());
		}
		request.setAttribute("articles", articles);

		List<TimeLine> timeLineList = timeLineService.findAllTimeLineList();
		request.setAttribute("timeLineList", timeLineList);

		List<Category> categoryList = categoryService.findAllCategoryList();
		request.setAttribute("categoryList", categoryList);
		return "article/articleDetail";
	}

	/**
	 * @desc 上下页切换
	 * @author xiongxingxing
	 * @param max
	 * @param min
	 * @param op
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/flip")
	public String showFlip(Integer max, Integer min, String op, ModelMap map,
			HttpSession session) {
		Article article = null;
		if ("pre".equals(op)) {
			article = articleService.findPreArticleById(min);
		} else if ("next".equals(op)) {
			article = articleService.findNextArticleById(max);
		}
		if (article == null) {
			int index = 0;
			if ("pre".equals(op)) {
				index = min;
			} else if ("next".equals(op)) {
				index = max;
			}
			article = articleService.findArticleById(index);
		}
		if (article == null) {
			return "/404";
		}
		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		map.put("articles", articles);
		map.put("max", article.getId());
		map.put("min", article.getId());

		List<TimeLine> timeLineList = timeLineService.findAllTimeLineList();
		map.put("timeLineList", timeLineList);

		List<Category> categoryList = categoryService.findAllCategoryList();
		map.put("categoryList", categoryList);

		return "article/articleDetail";
	}

	/**
	 * @desc 显示添加完一篇博客后的页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/showView")
	public String showView(Integer id, ModelMap map) {
		Article article = null;
		article = articleService.findArticleById(id);
		if (article == null) {
			return "/404";
		}
		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		map.put("articles", articles);
		map.put("max", article.getId());
		map.put("min", article.getId());

		List<TimeLine> timeLineList = timeLineService.findAllTimeLineList();
		map.put("timeLineList", timeLineList);

		List<Category> categoryList = categoryService.findAllCategoryList();
		map.put("categoryList", categoryList);

		return "article/articleDetail";
	}
	
	
	/**
	 * @desc 根据时间线id，显示对应的文章列表
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/timeline")
	public String timeline(Integer id, ModelMap map) {
		Article article = null;
		List<Article> articles=null;
		articles = articleService.findArticleByTimeLineId(id);
		if (articles == null||articles.size()==0) {
			return "/404";
		}
		for (Article a : articles) {
			a.setTextContent();
		}
		map.put("articles", articles);
		List<TimeLine> timeLineList = timeLineService.findAllTimeLineList();
		map.put("timeLineList", timeLineList);
		List<Category> categoryList = categoryService.findAllCategoryList();
		map.put("categoryList", categoryList);
		return "article/articleAllList";
	}
	
	
	/**
	 * @desc 根据时间线id，显示对应的文章列表
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/category")
	public String category(Integer id, ModelMap map) {
		Article article = null;
		List<Article> articles=null;
		articles = articleService.findArticleByCategoryId(id);
		if (articles == null||articles.size()==0) {
			return "/404";
		}
		for (Article a : articles) {
			a.setTextContent();
		}
		map.put("articles", articles);
		List<TimeLine> timeLineList = timeLineService.findAllTimeLineList();
		map.put("timeLineList", timeLineList);
		List<Category> categoryList = categoryService.findAllCategoryList();
		map.put("categoryList", categoryList);
		return "article/articleAllList";
	}
}
