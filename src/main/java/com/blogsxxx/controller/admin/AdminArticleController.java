package com.blogsxxx.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogsxxx.model.Article;
import com.blogsxxx.model.Category;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.article.CategoryService;
import com.blogsxxx.util.CommUtil;
import com.blogsxxx.util.HsFile;
import com.blogsxxx.util.PicsUrls;
import com.blogsxxx.util.UserUtil;

@Controller
@RequestMapping("/adminarticle")
@Scope("session")
public class AdminArticleController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	private Logger log=Logger.getLogger(getClass());
	
	@RequestMapping("/toWriteArticle")
	public String writeArticle(HttpServletRequest request){
		List<Category> categoryList=categoryService.findAllCategoryList();
		
		request.setAttribute("categoryList", categoryList);
		return "/adminArticle/writeArticle";
	}
	
	@RequestMapping("/saveArticle")
	public String saveArticle(HttpServletRequest request,HttpSession session,String title,Integer categoryId,String pics){
		String basePath=CommUtil.getBasepath(request);
		String content=request.getParameter("editorValue");
		Article oldArticle=articleService.findArticleByTitle(title.trim());
		
		Article article=createArticle(pics,content,title,basePath,session,oldArticle);
		//新创建的文章
		if(oldArticle==null){
			articleService.saveArticle(article);
	    //修改的文章
		}else {
			
		}
		
		
		return "redirect:/article/showView?id="+article.getId();
	}
	/**
	 * @author xiongxingxing
	 * @desc 通过条件，生成article对象
	 * @param pics
	 * @param content
	 * @param title
	 * @param basePath 
	 * @param session 
	 * @param oldArticle 
	 * @return
	 */
	private Article createArticle(String pics, String content, String title, String basePath, HttpSession session, Article oldArticle) {
		// TODO Auto-generated method stub
		Article article=new Article();
		String picsArray[]=pics.split(",");
		String xxxpics="";//虚拟路径
		String xxxrealpathpics="";//真实路径
		
		if(picsArray!=null&&picsArray.length>0){
			for(int i=0;i<picsArray.length;i++){
				String picsArr=picsArray[i];
				//picsArr:   http://localhost:8080/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg
				if(StringUtils.isNotBlank(picsArr)&&PicsUrls.containsUeditors(picsArr)){
					String temp=picsArr.replace(basePath, "");
					String realPath=session.getServletContext().getRealPath(temp);//ueditor的真实路径
					log.info("ueditor上传的图片的真实路径"+realPath);
					String strTemp=picsArr.substring(picsArr.indexOf(PicsUrls.PIC_KEYWORD));//:    /upload/image/20150416/1429193129926088002.jpg
					//服务器端重新存放图片的位置，真实路径，和虚拟路径
					String xxxpic=PicsUrls.PIC_PATH+strTemp;
					String xxxrealpathpic=PicsUrls.PIC_REALPATH+strTemp;
					log.info("保存到服务器的指定路径"+xxxrealpathpic);
					//保存图片到服务器端指定的位置
					HsFile.copyfile(realPath, xxxrealpathpic);
					
					xxxpics+=xxxpic+",";
					xxxrealpathpics+=xxxrealpathpic+",";
					
					content=content.replace(picsArr, xxxpic);
					log.info("content:=="+content);
					
					//http://localhost:8080/xxxpics/upload/image/20150416/1429193129926088002.jpg
				}else if(StringUtils.isNotBlank(picsArr)&&PicsUrls.containsXxxblogs(picsArr)){
					String strTemp=picsArr.substring(picsArr.indexOf(PicsUrls.PIC_KEYWORD));//:    /upload/image/20150416/1429193129926088002.jpg
					String xxxpic=PicsUrls.PIC_PATH+strTemp;
					String xxxrealpathpic=PicsUrls.PIC_REALPATH+strTemp;
					
					xxxpics+=xxxpic+",";
					xxxrealpathpics+=xxxrealpathpic+",";
				}
			}
		}
		if(StringUtils.isNotBlank(xxxpics)){
			xxxpics=xxxpics.substring(0,xxxpics.length()-1);
			xxxrealpathpics=xxxrealpathpics.substring(0,xxxrealpathpics.length()-1);
		}
		if(oldArticle==null){
			article.setCreater(UserUtil.USER_NAME);
			article.setCreatetime(new Date());
			article.setLastmodifier(UserUtil.USER_NAME);
			article.setLastmodifytime(new Date());
		}else {
			article.setCreater(oldArticle.getCreater());
			article.setCreatetime(oldArticle.getCreatetime());
			article.setLastmodifier(UserUtil.USER_NAME);
			article.setLastmodifytime(new Date());
		}
		article.setContent(content);
		article.setTitle(title);
		article.setPics(pics);
		article.setXxxpics(xxxpics);
		article.setXxxrealpathpics(xxxrealpathpics);
		
		return article;
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest request,HttpServletResponse response){
		String basePath=CommUtil.getBasepath(request);
		String str="ueditor/jsp/upload/image/20150416/1429193129926088002.jpg";
		String realPath=request.getSession().getServletContext().getRealPath(str);
		System.out.println(realPath);
		File file=new File(realPath);
		System.out.println(file.exists());
		return "/adminArticle/dopost";
	}
}
