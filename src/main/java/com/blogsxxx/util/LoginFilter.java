/**
 * 
 */
package com.blogsxxx.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: LoginFilter
 * @Description: 登录验证过滤
 *
 */
public class LoginFilter implements Filter {

	Logger logger = Logger.getLogger(getClass());

	// private String[] excludeUrl = new
	// String[]{"loginSys","exit",".png",".css"};
	private static final String ESCAPE_URL = "/president/presidentApprove";
	private static final String APP_LOGIN_URL = "/president/login";
	private String[] ESCAPE_URLS;
	// = new String[] {
	// "/president/presidentApprove/validate",
	// "/president/presidentApprove/goApprovel",
	// "/president/presidentApprove/getWaitingApproveList",
	// "/president/presidentApprove/getApprovingList",
	// "/president/presidentApprove/getApprovedList",
	// "/president/presidentApprove/pushFlash"
	// };

	{
		this.ESCAPE_URLS = new String[] {
				"/president/presidentApprove/validate",
				"/president/presidentApprove/goApprovel",
				"/president/presidentApprove/getWaitingApproveList",
				"/president/presidentApprove/getApprovingList",
				"/president/presidentApprove/getApprovedList",
				"/president/presidentApprove/pushFlash" };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURL().toString();
		// app端的过滤
		// if(url.contains(ESCAPE_URL)){
		// if( session.getAttribute("employInf") == null ) {
		// logger.info("app端：登录信息已过期，或未登录");
		// for(int i = 0; i < ESCAPE_URLS.length; i++) {
		// if(url.contains(ESCAPE_URLS[i])) {
		// chain.doFilter(request, response);
		// return;
		// }
		// }
		// httpResponse.sendRedirect(httpRequest.getContextPath() +
		// APP_LOGIN_URL);
		// return;
		// }
		// }
		// else
		if (url.endsWith(".jsp")) {
			if ((!url.endsWith("index.jsp")) && (!url.endsWith("error.jsp"))) {
				logger.info("非法访问： " + url);
				// httpRequest.setAttribute("msg", "非法访问");
				// httpResponse.sendRedirect(httpRequest.getContextPath() +
				// "/login.jsp");
				// PrintWriter out = response.getWriter();
				// StringBuffer sf = new StringBuffer();
				// sf.append("<script type=\"text/javascript\">");
				// sf.append("window.location.href = '"+httpRequest.getScheme()
				// + "://" + httpRequest.getServerName() + ":" +
				// httpRequest.getServerPort() + httpRequest.getContextPath() +
				// "/"+"exit';");
				// sf.append("</script>");
				// httpRequest.setAttribute("msg", "非法访问");
				httpResponse.sendRedirect(httpRequest.getContextPath()
						+ "/index.jsp");
				return;
			}
		} else if (session.getAttribute("user") == null) {
			if ((!url.contains(httpRequest.getContextPath() + "/article"))
					&& (!url.endsWith("/adminUser/login"))
					&& (!url.endsWith("/adminUser/loginSys"))
					&& (!url.endsWith("exit"))
					&& (!url.endsWith(".png"))
					&& (!url.endsWith(".jpg"))
					&& (!url.endsWith(".css"))
					&& (!url.endsWith(".woff2"))
					&& (!url.endsWith(".woff"))
					&& (!url.endsWith(".ttf"))
					&& (!url.contains(httpRequest.getContextPath() + "/images"))
					&& (!url.contains(httpRequest.getContextPath()
							+ "/bootstrap"))
					&& (!url.contains(httpRequest.getContextPath() + "/js"))
					&& (!url.contains(httpRequest.getContextPath() + "/ueditor"))) {

				logger.info("登录信息已过期，请重新登录");
				// httpRequest.setAttribute("msg", "您的登录信息已过期，请重新登录" +
				// httpRequest.getContextPath() + "/login.jsp");
//				System.out.println(CommUtil
//						.getBasepath((HttpServletRequest) request));
//				System.out.println(url);
				httpResponse.sendRedirect(httpRequest.getContextPath()
						+ "/index.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
