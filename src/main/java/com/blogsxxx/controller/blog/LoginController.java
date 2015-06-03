package com.blogsxxx.controller.blog;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogsxxx.model.User;
import com.blogsxxx.util.UserUtil;
@RequestMapping
@Controller
public class LoginController {
	/**
	 * @desc 404页面
	 * @return
	 */
	@RequestMapping("/404")
	public String to404(){
		return "/404";
	}
	@RequestMapping("/adminUser/login")
	public String login(){
		return "/adminUser/login";
	}
	@RequestMapping("/adminUser/loginSys")
	public String loginSys(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName") == null?null:request.getParameter("userName").trim();
		String password = request.getParameter("password");
		if(UserUtil.USER_NAME.equals(userName)&&UserUtil.USER_PASSWORD.equals(password)){
			User user=new User();
			user.setUserName(userName);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			return "backEnd/backIndex";
		}
		return "article/index";
	}
	
	@RequestMapping("/top")
	public String loadTop(){
		System.out.println("top---");
		return "backEnd/top";
	}
	
	@RequestMapping("/left")
	public String loadLeft(){
		System.out.println("left---");
		return "backEnd/left";
	}
	
	@RequestMapping("/main")
	public String loadMain(){
		System.out.println("main---");
		return "backEnd/list";
	}
	/**
	 * 系统退出
	 * @return
	 */
	@RequestMapping("/exit")
	public String sysExit(HttpServletRequest request){
		//session.invalidate();
		HttpSession session= request.getSession();
		 Enumeration em = session.getAttributeNames();
		  while(em.hasMoreElements()){
		   session.removeAttribute(em.nextElement().toString());
		  }
		System.out.println("exit---");
		return "redirect:article/index";
	}
	
}
