package com.blogsxxx.test;

import org.junit.Test;

public class StringTest {
	@Test
	public void test1(){
		String picsArr="http://localhost:8080/xxxblogs/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg";
		String strTemp=picsArr.substring(picsArr.indexOf("upload"));
		
		String str = "/Users/xiongxingxing/Documents/uploads/upload/image/20150416/1429193129926088002.jpg";
		str=str.substring(0,str.lastIndexOf('/'));
		System.out.println(strTemp);
		System.out.println(str);
	}
	
	@Test
	public void test2(){
		String picsArr="112312312312314414141424234124141,11323http://localhost:8080/xxxblogs/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg,123231";
		
		System.out.println(picsArr.replace("http://localhost:8080/xxxblogs/ueditor/jsp/upload/image/20150416/1429193129926088002.jpg", "TTTTTTTT"));
	}
}
