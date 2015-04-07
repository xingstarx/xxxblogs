package com.blogsxxx.controller.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogsxxx.model.Article;
import com.blogsxxx.service.article.ArticleService;

@RequestMapping("/article")
@Scope("session")
@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		List<Article> articles=articleService.findArticlesByRecent();
		
		if(articles!=null&&articles.size()>0){
			request.setAttribute("max",articles.get(0).getId());
			request.setAttribute("min", articles.get(articles.size()-1).getId());
		}
		request.setAttribute("articles",articles);
		return "article/articleDetail";
	}     
	
	@RequestMapping("/flip")
	public String showFlip(Integer max,Integer min,String op,ModelMap map,HttpSession session){
		Article article=null;
		if("pre".equals(op)){
			article=articleService.findPreArticleById(min);
		}else if ("next".equals(op)) {
			article=articleService.findNextArticleById(max);
		}
		if(article==null){
			
		}
		map.put("articles", new ArrayList<Article>().add(article));
		map.put("max", article.getId());
		map.put("min", article.getId());
		return "article/articleDetail";
	}     
}
