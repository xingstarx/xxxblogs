package com.blogsxxx.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;

public class HtmlUtils {
	/**
	 * @desc 针对url换行
	 * @param content
	 * @return
	 */
	public static String appendWbrTag(String content){
		if(StringUtils.isBlank(content)){
			return content;
		}
		StringBuffer sbf=new StringBuffer();
		for(int i=0;i<=content.length()-1;i+=20){
			sbf.append(content.substring(i, i+20>content.length()?content.length():i+20)).append("<wbr>");
		}
		return sbf.toString();
	}
	/**
	 * 生成select 的选项Html代码
	 * @param strMap key为select选项的值，value为select显示的内容
	 * @param selectKey 选中项的key
	 * @return 经过html敏感字符处理的html代码
	 */
	public static final String createSelectOptions(Map<String, Object> strMap, String selectKey) {
		StringBuilder sbf = new StringBuilder();
		for (Entry<String, Object> entry : strMap.entrySet()) {
			String key = entry.getKey();
			sbf.append("<option value=\"").append(key);
			sbf.append("\"").append(key.equals(selectKey) ? "selected >" : ">");
			sbf.append(htmlEncode(strMap.get(key))).append("</option>\r\n");
		}
		return sbf.toString();
	}

	/**
	 * 处理引起javaScript语法错误的字符，例如"
	 * 在javascript里面,例如onClick=ShowInfo("hello,i am \"boy\" ")
	 * 把hello,i am "boy"变成hello,i am \"boy\"
	 * 给与javascript语法有冲突的字符加上转义字符
	 * @param strInput 显示在网页的字符内容
	 * @return 处理后的字符串
	 */
	public static final String escapeScript(String strInput) {
		if (strInput == null) {
			return "";
		}

		strInput = strInput.trim();

		StringBuilder output = new StringBuilder();
		int len = strInput.length();
		char ch;
		for (int i = 0; i < len; i++) {
			ch = strInput.charAt(i);
			if (ch == '"') {
				output.append("\\");
			}
			output.append(ch);
		}
		return output.toString();
	}

	/**
	 * 当要把字符串正确显示在网页上
	 * 或者设置控件的值例如： <input type="text" name="txt" value="fdsfsd&lt;sf&gt;">
	 * 替换字符串里面跟HTML标签冲突的字符
	 * 去掉字符串的前后空格
	 * 如果字符串为null,返回空串
	 * @param strInput 要显示的字符串
	 * @return 处理后的字符串
	 */
	public static String htmlEncode(Object input) {
		if (input == null) {
			return "";
		}

		String strInput = input.toString().trim();

		StringBuilder output = new StringBuilder();
		int len = strInput.length();
		char ch;
		for (int i = 0; i < len; i++) {
			ch = strInput.charAt(i);
			if (ch == '&') {
				output.append("&amp;");
				continue;
			}
			if (ch == '<') {
				output.append("&lt;");
				continue;
			}
			if (ch == '>') {
				output.append("&gt;");
				continue;
			}
			if (ch == '"') {
				output.append("&quot;");
			} else {
				output.append(ch);
			}
		}
		return output.toString();
	}

	/**
	 * 去除html代码
	 *
	 * @param inputString
	 */
	public static String htmltoText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}

//	public static final String splitRow(String strContent, String symbol) {
//		if (strContent == null || strContent.length() == 0) {
//			return "";
//		}
//		StringBuilder sbf = new StringBuilder();
//		String[] aryStr = StringUtil.toArray(strContent, symbol);
//		for (String element : aryStr) {
//			sbf.append(element).append(symbol).append("<br>");
//		}
//		return sbf.toString();
//	}
}
