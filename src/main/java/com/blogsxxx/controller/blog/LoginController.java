package com.blogsxxx.controller.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			return "adminarticle/list";
		}
		return "article/index";
	}
}
