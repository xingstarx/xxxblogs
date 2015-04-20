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
	public static final String PIC_REALPATH = "/Users/xiongxingxing/Documents/uploads";
	public static final String PIC_KEYWORD = "/upload";
	public static final String UEDITOR = "ueditor";
	/**
	 * @desc 图片的虚拟路径
	 */
	public static final String PIC_PATH = "xxxpics";

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
}
