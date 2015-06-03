package com.blogsxxx.util;

public class HtmlUtils {
	/**
	 * @desc 针对url换行
	 * @param content
	 * @return
	 */
	public static String appendWbrTag(String content){
		StringBuffer sbf=new StringBuffer();
		for(int i=20;i<=content.length()-1;i+=20){
			sbf.append(content.substring(i-20, i)).append("<wbr>");
		}
		return sbf.toString();
	}
}
