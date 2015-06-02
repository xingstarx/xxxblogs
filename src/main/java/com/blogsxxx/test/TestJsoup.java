package com.blogsxxx.test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.blogsxxx.model.CsdnArticle;
import com.blogsxxx.model.CsdnCategory;
import com.blogsxxx.util.JsoupParserUtil;
import com.blogsxxx.util.http.HttpHelper;
import com.blogsxxx.util.http.ResponseContent;

public class TestJsoup {
	@Test
	public void test1(){
		 String url = "http://blog.csdn.net/xxx823952375/article/";//http://blog.csdn.net/xxx823952375/article/
	        ResponseContent responseContent = HttpHelper.getUrlRespContent(url);
	        try {
	        	String str=responseContent.getContent();
	        	Document document=Jsoup.parse(str, url);
	        	Element panelCategoryDiv=JsoupParserUtil.getElementById(document, "panel_Category");
	        	Elements panelCategoryLis=JsoupParserUtil.getElementsByTagName(panelCategoryDiv, "li");
	        	for(Element e: panelCategoryLis){
//	        		System.out.println(e.html());
	        		Element a=JsoupParserUtil.getElementsByTagName(e,"a").get(0);
	        		Element span=JsoupParserUtil.getElementsByTagName(e, "span").get(0);
	        		CsdnCategory csdnCategory=new CsdnCategory();
	        		csdnCategory.setCategoryname(a.text());
	        		String num=span.text();
	        		csdnCategory.setCount(Integer.valueOf(num.substring(1, num.length()-1)));
	        		csdnCategory.setUrl(a.attr("href"));
	        		System.out.println(csdnCategory.toString());
	        	}
	        	
//	        	System.out.println(panelCategoryDiv.html());
//	            System.out.println(responseContent.getContent());
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        
	}
	private static String BASE_URL="blog.csdn.net";
	
	@Test
	public void test2(){
		 String url = "http://blog.csdn.net/xxx823952375/article/category/2147989";//http://blog.csdn.net/xxx823952375/article/category/1731575
	        ResponseContent responseContent = HttpHelper.getUrlRespContent(url);
	        try {
	        	String str=responseContent.getContent();
	        	//增加一个判断，看看是否有分页的内容呢？id＝papelist
	        	
	        	
	        	Document document=Jsoup.parse(str, url);
	        	
	        	Element pageList=JsoupParserUtil.getElementById(document, "pagelist");
	        	//为null,那么就是不存在分页的内容的。
	        	//如果有的话。那么后续处理下，http://blog.csdn.net/xxx823952375/article/category/2147989/2
	        	System.out.println(pageList);
	        	Element articleListDiv=JsoupParserUtil.getElementById(document, "article_list");//文章列表
	        	
//	        	System.out.println(articleListDiv.text());
	        	Elements articleListItemDiv=JsoupParserUtil.getElementsByClassName(articleListDiv, "list_item");
	        	
	        	for(Element e: articleListItemDiv){
//	        		System.out.println(e.html());
	        		Element h1=JsoupParserUtil.getElementsByTagName(e,"h1").get(0);
	        		Element a=JsoupParserUtil.getElementsByTagName(h1, "a").get(0);
	        		
//	        		link_postdate
	        		Element linkPostdate=JsoupParserUtil.getElementsByClassName(e,"link_postdate").get(0);
	        		
	        		CsdnArticle csdnArticle=new CsdnArticle();
	        		csdnArticle.setTitle(a.text());
	        		csdnArticle.setFromurl(BASE_URL+a.attr("href"));
	        		csdnArticle.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(linkPostdate.text()));
	        		System.out.println(csdnArticle);
	        	}
	        	
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
	@Test
	public void test3(){
		String articleUrl="http://blog.csdn.net/xxx823952375/article/details/38471883";
		ResponseContent responseContent = HttpHelper.getUrlRespContent(articleUrl);
		try {
			String str = responseContent.getContent();
			// 增加一个判断，看看是否有分页的内容呢？id＝papelist
			//目前本人博客里面没有分页的，暂时不考虑
			Document document = Jsoup.parse(str, articleUrl);

			Element articleContent = JsoupParserUtil.getElementById(document,
					"article_content");// 文章列表
			String content=articleContent.html();
			System.out.println(content);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getCsdnArticleContent transaction roll back! ");
		}
	}
}
