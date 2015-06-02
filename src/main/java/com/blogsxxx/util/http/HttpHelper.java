package com.blogsxxx.util.http;

/**
 * HttpHelper.java
 * com.jfly.core.httpclient
 * Copyright (c) 2014.
*/
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.junit.Test;
 
/**
 * HTTP工具类，封装HttpClient来对外提供简化的HTTP请求
 * @author   WangShuo && yangjian1004
 * @Date     Aug 5, 2014     
 */
public class HttpHelper {
 
    private static Integer connectionTimeOut = 6000;
 
    private static Integer soTimeOut = 0;
 
    public void setConnectionTimeOut(Integer cTimeOut) {
        connectionTimeOut = cTimeOut;
    }
 
    public void setSoTimeOut(Integer sTimeOut) {
        soTimeOut = sTimeOut;
    }
 
    /**
     * 使用Get方式 根据URL地址，获取ResponseContent对象
     * 
     * @param url
     *            完整的URL地址
     * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
     */
    public static ResponseContent getUrlRespContent(String url) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionTimeOut, soTimeOut);
        ResponseContent response = null;
        try {
            response = hw.getResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
 
    /**
     * 使用Get方式 根据URL地址，获取ResponseContent对象
     * 
     * @param url
     *            完整的URL地址
     * @param urlEncoding
     *            编码，可以为null
     * @return ResponseContent 如果发生异常则返回null，否则返回ResponseContent对象
     */
    public static ResponseContent getUrlRespContent(String url, String urlEncoding) {
        HttpClientWrapper hw = new HttpClientWrapper(connectionTimeOut, soTimeOut);
        ResponseContent response = null;
        try {
            response = hw.getResponse(url, urlEncoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
 
    /**
     * 将参数拼装在url中，进行post请求。
     * 
     * @param url
     * @return
     */
    public static ResponseContent postUrl(String url) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            String[] paramStr = url.split("[?]", 2);
            if (paramStr == null || paramStr.length != 2) {
                return null;
            }
            String[] paramArray = paramStr[1].split("[&]");
            if (paramArray == null) {
                return null;
            }
            for (String param : paramArray) {
                if (param == null || "".equals(param.trim())) {
                    continue;
                }
                String[] keyValue = param.split("[=]", 2);
                if (keyValue == null || keyValue.length != 2) {
                    continue;
                }
                hw.addNV(keyValue[0], keyValue[1]);
            }
            ret = hw.postNV(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
 
    /**
     * 上传文件（包括图片）
     * 
     * @param url
     *            请求URL
     * @param paramsMap
     *            参数和值
     * @return
     */
    public static ResponseContent postUriEntity(String url, Map<String, Object> paramsMap) {
        HttpClientWrapper hw = new HttpClientWrapper();
        ResponseContent ret = null;
        try {
            String[] paramStr = url.split("[?]", 2);
            if (paramStr == null || paramStr.length != 2) {
 
            } else {
                String[] paramArray = paramStr[1].split("[&]");
                if (paramArray == null) {
 
                } else {
                    for (String param : paramArray) {
                        if (param == null || "".equals(param.trim())) {
                            continue;
                        }
                        String[] keyValue = param.split("[=]", 2);
                        if (keyValue == null || keyValue.length != 2) {
                            continue;
                        }
                        hw.addNV(keyValue[0], keyValue[1]);
                    }
                }
            }
            Iterator<String> iterator = paramsMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = paramsMap.get(key);
                if (value instanceof File) {
                    FilePart filePart = new FilePart("file", (File) value);
                    filePart.setCharSet("UTF-8");
                    hw.getPartList().add(filePart);
                } else if (value instanceof byte[]) {
                    byte[] byteVlue = (byte[]) value;
                    ByteArrayPartSource btyePart = new ByteArrayPartSource(key, byteVlue);
                    FilePart filePart = new FilePart("file", btyePart);
                    filePart.setCharSet("UTF-8");
                    hw.getPartList().add(filePart);
                } else {
                    if (value != null && !"".equals(value)) {
                        hw.addNV(key, String.valueOf(value));
                    } else {
                        hw.addNV(key, "");
                    }
                }
            }
            ret = hw.postNVUriEntity(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
 
    /**
     * 使用post方式，发布对象转成的json给Rest服务。
     * 
     * @param url
     * @param jsonBody
     * @return
     */
    public static ResponseContent postJsonEntity(String url, String jsonBody) {
        return postEntity(url, jsonBody, "application/json");
    }
 
    /**
     * 使用post方式，发布对象转成的xml给Rest服务
     * 
     * @param url
     *            URL地址
     * @param xmlBody
     *            xml文本字符串
     * @return ResponseContent 如果发生异常则返回空，否则返回ResponseContent对象
     */
    public static ResponseContent postXmlEntity(String url, String xmlBody) {
        return postEntity(url, xmlBody, "application/xml");
    }
 
    private static ResponseContent postEntity(String url, String body,
            String contentType) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new StringRequestEntity(body, contentType, "utf-8");
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
 
    public static ResponseContent postEntity(String url, byte[] content) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new ByteArrayRequestEntity(content);
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
 
    public static ResponseContent postEntity(String url, InputStream content) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new InputStreamRequestEntity(content,
                    "multipart/form-data; boundary=ABCD");
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
 
    public static ResponseContent postEntity(String url, File content) {
        HttpClientWrapper hw = new HttpClientWrapper();
        RequestEntity entityBody;
        ResponseContent ret = null;
        try {
            entityBody = new FileRequestEntity(content,
                    "multipart/form-data; boundary=ABCD");
            hw.setEntityBody(entityBody);
            ret = hw.postEntity(url);
        } catch (Exception e) {
            // 异常在本级结束，不再抛出
            e.printStackTrace();
        }
        return ret;
    }
 
    public static void main(String[] args) {
        testGet();
        //testUploadFile();
    }
 
//    Test
    public static void testGet() {
//        String url = "http://www.baidu.com/";//http://blog.csdn.net/xxx823952375/article/
        String url = "http://blog.csdn.net/xxx823952375/article/";//http://blog.csdn.net/xxx823952375/article/
        ResponseContent responseContent = getUrlRespContent(url);
        try {
            System.out.println(responseContent.getContent());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    //test
    public static void testUploadFile() {
        String content = "<h1 style=\"white-space:normal;margin-top:0px;margin-right:auto;margin-bottom:15px;margin-left:auto;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;font-size:20px;text-align:center;font-family:微软雅黑, 黑体;font-weight:normal;color:#333333;line-height:21px;background-color:#FFFFFF;\">崔永元谈\"骂战\"：骂对了证明该骂&nbsp;骂错了负荆请罪</h1>";
        try {
            System.out.println(content);
            String url = "http://192.168.18.205:8080/htmlToImage";
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("content", content);
            paramsMap.put("file", new File("d:/1.jpg"));
            ResponseContent ret = postUriEntity(url, paramsMap);
            System.out.println(ret.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}