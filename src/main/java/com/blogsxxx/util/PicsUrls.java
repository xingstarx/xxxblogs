package com.blogsxxx.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * @desc 项目图片存放目录
 */
public class PicsUrls {
	public static Logger logger = Logger.getLogger("PicsUrls");
	/**
	 * 原图存放路径
	 */
	// public static final String PIC_PATH = "d:/JAVA/hlhsPics";
	/**
	 * @desc 图片的真实路径
	 */
	public static String PIC_REALPATH_DEV = "/Users/xiongxingxing/Documents/uploads";//本地开发环境的图片存放的真实路径
	
	public static String PIC_REALPATH = "/usr/java/pics";//线上centos环境的图片存放的真实路径
	
	public static String PIC_REALPATH_TEMP = "";//临时变量
	
	public static final String PIC_KEYWORD = "/upload";
	public static final String UEDITOR = "ueditor";
	/**
	 * @desc 图片的虚拟路径
	 */
	public static final String PIC_PATH = "xxxpics";

	/**
	 * @desc 判断当前所处的环境，是开发环境，还是先上环境，方便切换图片的存放路径
	 * @return
	 */
	public static String getPicRealPath(){
		String name=System.getProperty("os.name").toLowerCase();
		logger.info("当前环境是在那种系统上呢，Mac，windows为开发环境，Linux为生产环境======"+name);
		//开发环境
		if(name.contains("mac")||name.contains("windows")){
			return PIC_REALPATH_DEV;
		}
		//生产环境
		return PIC_REALPATH;
	}
	/**
	 * @desc 判断url是否包含ueditor字符串
	 * @param url
	 * @return
	 */
	public static boolean containsUeditors(String url) {
		boolean flag = false;
		if (StringUtils.isNotBlank(url) && url.contains(UEDITOR)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @desc 判断url是否包含xxxblogs字符串
	 * @param url
	 * @return
	 */
	public static boolean containsXxxblogs(String url) {
		boolean flag = false;
		if (StringUtils.isNotBlank(url) && url.contains(PIC_PATH)) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 
	 * @Title: initPicUrls
	 * @Description: 初始化图片目录。
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void initPicUrls() {
		// 原图目录
		File fileDir = new File(PicsUrls.PIC_PATH);
		if (!fileDir.exists())
			fileDir.mkdirs();
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
		System.out.println(getPicRealPath());
	}
}
