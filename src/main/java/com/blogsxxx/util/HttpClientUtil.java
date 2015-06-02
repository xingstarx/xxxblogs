package com.blogsxxx.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * http
 * 
 * @author QiuShaSha
 * @version 1.0
 * @since 1.0
 * @since 2014.10.29
 */
public class HttpClientUtil {
    Logger logger = Logger.getLogger(HttpClientUtil.class);
    
    private static Map<String, String> headers = new HashMap<String, String>();
	static {
		headers.put("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//		headers.put("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		headers.put(
				"Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Content-Type", "text/html; charset=utf-8");
		headers.put("Accept-Encoding", "gzip, deflate, sdch");
	}
    /**
     * 发送get请求
     *
     * @param url 请求地址
     * @return
     *      K           V
     *      status      1 成功
     *                  0 失败
     *      result      返回的xml字符串
     */
    public static Map<String, Object> get(String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        String result;

        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(url);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());
            
            for (Entry<String, String> entry : headers.entrySet()) {
            	getMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
            
            int statusCode = httpClient.executeMethod(getMethod);

            byte[] responseBody = getMethod.getResponseBody();
            getMethod.releaseConnection();
            result = new String(responseBody);

            map.put("status", 1);
            map.put("result", result);

        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("result", null);
        }
        return map;
    }


    /**
     * 发送Post请求
     *
     * @param url    请求地址
     * @param params post参数
     * @return
     *      K           V
     *      status      1 成功
     *                  0 失败
     *      result      返回的xml字符串
     *
     */
    public static Map<String, Object> post(String url, Map<String, String> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean pretty = true;

        StringBuffer response = new StringBuffer();
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.getParams().setContentCharset("utf8");
            
            for (Entry<String, String> entry : headers.entrySet()) {
            	postMethod.addRequestHeader(entry.getKey(), entry.getValue());
			}
            
            
            NameValuePair[] data = new NameValuePair[params.size()];
            int i = 0;
            for (String key : params.keySet()) {
            	 data[i++] = new NameValuePair(key, params.get(key));
            }

            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);

            if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
            	
            	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(new File("test1.txt"))));
            	out.print(postMethod.getResponseBodyAsString());
//            	out.print("AAAAAAAAAAA");
            	out.flush();
            	out.close();
//            	System.out.println(postMethod.getResponseBodyAsString());
                //读取为 InputStream，在网页内容数据量大时候推荐使用
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(postMethod.getResponseBodyAsStream(),
                                "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
                reader.close();
                postMethod.releaseConnection();
            }

            resultMap.put("status", 1);
            resultMap.put("result", response);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("status", 0);
            resultMap.put("result", null);
        }
        return resultMap;
    }
    
    public static void main(String[] args) {
//    	String str=HttpClientUtil.get("http://blog.csdn.net/xxx823952375/article/").toString();
    	String str=HttpClientUtil.post("http://blog.csdn.net/xxx823952375/article/",new HashMap<String, String>()).toString();
		System.out.println(str);
	}
}
