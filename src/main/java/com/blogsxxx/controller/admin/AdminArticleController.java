package com.blogsxxx.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogsxxx.model.Article;
import com.blogsxxx.model.Category;
import com.blogsxxx.service.article.ArticleService;
import com.blogsxxx.service.article.CategoryService;
import com.blogsxxx.service.article.LogService;
import com.blogsxxx.service.article.TimeLineService;
import com.blogsxxx.service.timer.CsdnArticleService;
import com.blogsxxx.test.PageView;
import com.blogsxxx.test.QueryResult;
import com.blogsxxx.test.Student;
import com.blogsxxx.util.CommUtil;
import com.blogsxxx.util.HsFile;
import com.blogsxxx.util.HtmlUtils;
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
	@Autowired
	private CsdnArticleService csdnArticleService;
	@Autowired
	private TimeLineService timeLineService;
	@Autowired
	private LogService logService;

	private Logger log = Logger.getLogger(getClass());

	/**
	 * @desc 写文章
	 * @author xiongxingxing
	 * @param request
	 * @return
	 */
	@RequestMapping("/toWriteArticle")
	public String writeArticle(HttpServletRequest request) {
		List<Category> categoryList = categoryService.findAllCategoryList();

		request.setAttribute("categoryList", categoryList);
		return "/adminArticle/writeArticle";
	}

	/**
	 * @desc 编辑文章，即更新文章
	 * @author xiongxingxing
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditorArticle")
	public String editorArticle(HttpServletRequest request,Integer id) {
		List<Category> categoryList = categoryService.findAllCategoryList();
		Article article=articleService.findArticleById(id);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("article", article);
		return "/adminArticle/writeArticle";
	}

	@RequestMapping("/saveArticle")
	public String saveArticle(HttpServletRequest request, HttpSession session,
			String title, Integer categoryId, String pics) {
		String basePath = CommUtil.getBasepath(request);
		String content = request.getParameter("editorValue");
		Article oldArticle = articleService.findArticleByTitle(title.trim());
		Category category = categoryService.findCategoryById(categoryId);

		Article article = createArticle(pics, content, title, basePath,
				session, oldArticle);
		// 新创建的文章
		article.setCategoryid(categoryId);
		article.setCategoryname(category.getCategoryname());

		if (oldArticle == null) {
			article.setFromurl("");// 自己创建的没有来源
			articleService.saveArticle(article);
			category.setArticlecount(category.getArticlecount() + 1);
			categoryService.updateCategory(category);

			Integer temp = Integer.valueOf(new SimpleDateFormat("yyyyMM")
					.format(new Date()));
			timeLineService.addOrUpdateTimeLineByTimestr(temp);

			// 修改的文章
		} else {
			article.setFromurl(oldArticle.getFromurl());// 沿用原来的来源
			logService.addArticleLog(oldArticle.getId());
			article.setId(oldArticle.getId());
			articleService.updateArticle(article);
		}

		return "redirect:/article/showView?id=" + article.getId();
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
	private Article createArticle(String pics, String content, String title,
			String basePath, HttpSession session, Article oldArticle) {
		// TODO Auto-generated method stub
		Article article = new Article();
		String picsArray[] = pics.split(",");
		String xxxpics = "";// 虚拟路径
		String xxxrealpathpics = "";// 真实路径
		PicsUrls.PIC_REALPATH_TEMP = PicsUrls.getPicRealPath();

		if (picsArray != null && picsArray.length > 0) {
			for (int i = 0; i < picsArray.length; i++) {
				String picsArr = picsArray[i];
				// picsArr:
				// http://localhost:8080/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg
				if (StringUtils.isNotBlank(picsArr)
						&& PicsUrls.containsUeditors(picsArr)) {
					log.info("basePath的基本路径为：" + basePath);
					String temp = picsArr.replace(basePath, "");
					log.info("截取后的临时路径为：" + temp);
					String realPath = session.getServletContext().getRealPath(
							temp);// ueditor的真实路径
					log.info("ueditor上传的图片的真实路径" + realPath);
					String strTemp = picsArr.substring(picsArr
							.indexOf(PicsUrls.PIC_KEYWORD));// :
															// /upload/image/20150416/1429193129926088002.jpg
					// 服务器端重新存放图片的位置，真实路径，和虚拟路径
					// 修改一下程序，存在一个bug：这样改了之后，图片的全部连接地址不全，无法加载出页面的，需要修改。
					// 加上basePath前缀
					String xxxpic = basePath + PicsUrls.PIC_PATH + strTemp;
					String xxxrealpathpic = PicsUrls.PIC_REALPATH_TEMP
							+ strTemp;
					log.info("保存到服务器的指定路径" + xxxrealpathpic);
					// 保存图片到服务器端指定的位置
					HsFile.copyfile(realPath, xxxrealpathpic);

					xxxpics += xxxpic + ",";
					xxxrealpathpics += xxxrealpathpic + ",";

					content = content.replace(picsArr, xxxpic);
					log.info("content:==" + content);

					// http://localhost:8080/xxxpics/upload/image/20150416/1429193129926088002.jpg
				} else if (StringUtils.isNotBlank(picsArr)
						&& PicsUrls.containsXxxblogs(picsArr)) {
					String strTemp = picsArr.substring(picsArr
							.indexOf(PicsUrls.PIC_KEYWORD));// :
															// /upload/image/20150416/1429193129926088002.jpg
					String xxxpic = basePath + PicsUrls.PIC_PATH + strTemp;
					String xxxrealpathpic = PicsUrls.PIC_REALPATH_TEMP
							+ strTemp;

					xxxpics += xxxpic + ",";
					xxxrealpathpics += xxxrealpathpic + ",";
				}
			}
		}
		if (StringUtils.isNotBlank(xxxpics)) {
			xxxpics = xxxpics.substring(0, xxxpics.length() - 1);
			xxxrealpathpics = xxxrealpathpics.substring(0,
					xxxrealpathpics.length() - 1);
		}
		if (oldArticle == null) {
			article.setCreater(UserUtil.USER_NAME);
			article.setCreatetime(new Date());
			article.setLastmodifier(UserUtil.USER_NAME);
			article.setLastmodifytime(new Date());
		} else {
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
	public String test(HttpServletRequest request, HttpServletResponse response) {
		String basePath = CommUtil.getBasepath(request);
		String str = "ueditor/jsp/upload/image/20150416/1429193129926088002.jpg";
		String realPath = request.getSession().getServletContext()
				.getRealPath(str);
		System.out.println(realPath);
		File file = new File(realPath);
		System.out.println(file.exists());
		return "/adminArticle/dopost";
	}

	@RequestMapping("/articleList")
	public String articleList(HttpServletRequest request, Integer currentPage) {
		// List<Category> categoryList=categoryService.findAllCategoryList();
		// request.setAttribute("categoryList", categoryList);
		if (currentPage == null) {
			currentPage = 1;
		}
		List<Article> mArticles = articleService.findAllArticle();

		for (Article a : mArticles) {
			String content = a.getContent();
			content = Jsoup.parse(content).text();
			if (content != null) {
				content = content.length() > 40 ? content.substring(0, 40)
						: content;
				a.setContent(content);
			}
			// <wbr>
			String url = a.getFromurl();
			a.setFromurl(HtmlUtils.appendWbrTag(url));
		}

		PageView<Article> pageView = new PageView<Article>(10, currentPage);
		int start = pageView.getStartRecordIndex();
		int lenght = pageView.getRecordPerPage();
		QueryResult<Article> qr = new QueryResult<Article>(mArticles.subList(
				start, start + lenght > mArticles.size() ? mArticles.size()
						: start + lenght), mArticles.size());
		pageView.setQueryResult(qr);
		request.setAttribute("pageView", pageView);
		request.setAttribute("currentPage", currentPage);

		return "/adminArticle/articleList";
	}

	/**
	 * @desc 分页测试
	 * @param request
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("/testPageView")
	public String testPageView(HttpServletRequest request, Integer currentPage) {
		if (currentPage == null) {
			currentPage = 1;
		}
		List<Student> list = new ArrayList<Student>();
		// 模拟数据
		for (int i = 1; i <= 300; i++) {
			Student student = new Student();
			student.setId(String.valueOf(i));
			student.setBirth(new Date());
			student.setAge(i);
			student.setName((i) + "" + i);
			list.add(student);
		}
		PageView<Student> pageView = new PageView<Student>(12, currentPage);
		int start = pageView.getStartRecordIndex();
		int lenght = pageView.getRecordPerPage();
		QueryResult<Student> qr = new QueryResult<Student>(list.subList(start,
				start + lenght), list.size());
		pageView.setQueryResult(qr);
		request.setAttribute("pageView", pageView);
		request.setAttribute("currentPage", currentPage);
		return "common/test";
	}
}
