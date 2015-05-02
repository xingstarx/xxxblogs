package com.blogsxxx.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommUtil {
	/**
	 * 时间字符串转化为时间
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String dateToString(Date date) {
		if(date==null) return null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(date);
	}
	/**
	 * @desc 得到basePath的路径
	 * @author xingxing
	 * @param request
	 * @return
	 */
	public static String getBasepath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		return basePath;
	}
}
