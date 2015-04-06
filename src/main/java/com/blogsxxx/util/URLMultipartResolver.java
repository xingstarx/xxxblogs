package com.blogsxxx.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @ClassName: URLMultipartResolver
 * @Description: 解决非springMVC的上传被过滤的问题
 * @author LY
 * @date 2014年8月26日
 * 
 */
public class URLMultipartResolver extends CommonsMultipartResolver {

	private String[] excludeUrls;

	public String[] getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		for (String exclude : excludeUrls) {
			if (request.getRequestURL().toString().contains(exclude)) {
				return false;
			}
		}

		return super.isMultipart(request);
	}

}
