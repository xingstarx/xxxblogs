package com.blogsxxx.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
