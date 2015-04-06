package com.blogsxxx.controller.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
		Article article=articleService.findArticleById(0);
		request.setAttribute("article", article);
		System.out.println("ArticleController");
		return "article/articleDetail";
	}     
}
