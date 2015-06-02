package com.blogsxxx.util.http;

/**
 * HttpClientWrapper.java
 * com.jfly.core.httpclient
 * Copyright (c) 2014.
*/
 
 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
 
/**
 * 封装HttpClient
 * <p>
 * @author   WangShuo && yangjian1004
 * @Date     Aug 5, 2014     
 */
public class HttpClientWrapper {
 
    private enum VERBTYPE {
        GET, NVPOST, ENTITYPOST
    }
 
    private HttpClient client;
    private List<Part> partList;
    private RequestEntity entityBody;
    private List<NameValuePair> nameValuePostBody;
 
    public List<Part> getPartList() {
        return partList;
    }
 
    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }
 
    public HttpClientWrapper() {
        this.client = new HttpClient();
        this.client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);// 设置连接超时时间(单位毫秒)
        this.client.getHttpConnectionManager().getParams().setSoTimeout(20000);// 设置读数据超时时间(单位毫秒)
        this.nameValuePostBody = new LinkedList<NameValuePair>();
        this.partList = new ArrayList<Part>();
    }
 
    public HttpClientWrapper(Integer connectionTimeOut, Integer soTimeOut) {
        this.client = new HttpClient();
        this.client.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeOut);// 设置连接超时时间(单位毫秒)
        this.client.getHttpConnectionManager().getParams().setSoTimeout(soTimeOut);// 设置读数据超时时间(单位毫秒)
        this.nameValuePostBody = new LinkedList<NameValuePair>();
    }
 
    /**
     * Get方式访问URL
     * 
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public ResponseContent getResponse(String url) throws HttpException, IOException {
        return this.getResponse(url, "UTF-8", VERBTYPE.GET);
    }
 
    /**
     * Get方式访问URL
     * 
     * @param url
     * @param urlEncoding
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public ResponseContent getResponse(String url, String urlEncoding) throws HttpException, IOException {
        return this.getResponse(url, urlEncoding, VERBTYPE.GET);
    }
 
    /**
     * POST方式发送名值对请求URL
     * 
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public ResponseContent postNV(String url) throws HttpException, IOException {
        return this.getResponse(url, "UTF-8", VERBTYPE.NVPOST);
    }
 
    /**
     * POST方式发送实体请求URL
     * 
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public ResponseContent postEntity(String url) throws HttpException, IOException {
        return this.getResponse(url, "UTF-8", VERBTYPE.ENTITYPOST);
    }
 
    /**
     * 根据url编码，请求方式，请求URL
     * 
     * @param urlstr
     * @param urlEncoding
     * @param bodyType
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @SuppressWarnings("null")
    public ResponseContent getResponse(String urlstr, String urlEncoding, VERBTYPE bodyType) throws HttpException,
            IOException {
        if (urlstr == null)
            return null;
        String url = urlstr;
        if (urlEncoding != null)
            url = HttpClientWrapper.encodeURL(url.trim(), urlEncoding);
 
        HttpMethodBase hm = null;
        try {
            if (VERBTYPE.GET == bodyType) {
                hm = new GetMethod(url);
            } else if (VERBTYPE.NVPOST == bodyType) {
                PostMethod pm = new PostMethod(url);
                NameValuePair[] nvps = this.getNVBodyArray();
                pm.setRequestBody(nvps);
                hm = pm;
            } else if (VERBTYPE.ENTITYPOST == bodyType) {
                PostMethod pm = new PostMethod(url);
                RequestEntity entity = getEntityBody();
                pm.setRequestEntity(entity);
                hm = pm;
            }
            hm.addRequestHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
            DefaultHttpMethodRetryHandler retryhandler = new DefaultHttpMethodRetryHandler(5, true);
            hm.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, retryhandler);
            this.client.executeMethod(hm);
            int statuscode = hm.getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                return this.getRedirect(url, hm, 1);
            } else {
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statuscode);
                String charset = hm.getResponseCharSet();
                if (charset != null)
                    charset = charset.toUpperCase();
                ret.setEncoding(charset);
                String contenttype = this.getResponseContentType(hm);
                ret.setContentType(contenttype);
                ret.setContentTypeString(this.getResponseContentTypeString(hm));
                ret.setContentBytes(this.getResponseBody(hm));
                return ret;
            }
        } finally {
            if (hm != null)
                hm.releaseConnection();
        }
    }
 
    /**
     * POST方式发送名值对请求URL,上传文件（包括图片）
     * 
     * @param url
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public ResponseContent postNVUriEntity(String url) throws HttpException, IOException {
        if (url == null)
            return null;
        PostMethod method = null;
        try {
            this.parseUrl(url);
            method = new PostMethod(this.uri);
            int i = 0, partListSize = partList.size();
            Part[] parts = new Part[this.getNVBodyArray().length + partListSize];
            for (; i < partListSize; i++) {
                parts[i] = partList.get(i);
            }
            for (NameValuePair nameValuePair : this.getNVBodyArray()) {
                StringPart stringPart = new StringPart(nameValuePair.getName(), nameValuePair.getValue(), "UTF-8");
                parts[i] = stringPart;
                i++;
            }
 
            // 对于MIME类型的请求，httpClient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, method.getParams());
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(mre);
 
            client.getHostConfiguration().setHost(this.host, this.port, this.protocol);
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
            int status = 404;
            try {
                status = client.executeMethod(method);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResponseContent ret = new ResponseContent();
            ret.setStatusCode(status);
            String charset = method.getResponseCharSet();
            if (charset != null) {
                charset = charset.toUpperCase();
                ret.setEncoding(charset);
            }
            ret.setContentType(this.getResponseContentType(method));
            ret.setContentTypeString(this.getResponseContentTypeString(method));
            ret.setContentBytes(this.getResponseBody(method));
            return ret;
        } finally {
            if (method != null)
                method.releaseConnection();
        }
    }
 
    /**
     * 获取转发的返回
     * 
     * @param baseURL
     * @param method
     * @param redirectCount
     * @return
     * @throws HttpException
     * @throws IOException
     */
    private ResponseContent getRedirect(String baseURL, HttpMethodBase method, int redirectCount) throws HttpException,
            IOException {
        if (method == null)
            return null;
        GetMethod redirect = null;
        try {
            Header header = method.getResponseHeader("location");
            if (header != null) {
                String newuri = header.getValue();
                if ((newuri == null) || (newuri.equals(""))) {
                    newuri = "/";
                }
                newuri = this.dealUrl(baseURL, newuri);
                redirect = new GetMethod(newuri);
                redirect.addRequestHeader("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
 
                client.executeMethod(redirect);
                int statuscode = redirect.getStatusCode();
                if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                        || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                    int rc = redirectCount + 1;
                    if (rc > 5) {
                        ResponseContent ret = new ResponseContent();
                        ret.setStatusCode(statuscode);
                        String charset = redirect.getResponseCharSet();
                        if (charset != null)
                            charset = charset.toUpperCase();
                        ret.setEncoding(charset);
                        String contenttype = this.getResponseContentType(redirect);
                        ret.setContentType(contenttype);
                        ret.setContentTypeString(this.getResponseContentTypeString(redirect));
                        ret.setContentBytes(this.getResponseBody(redirect));
                        return ret;
                    }
                    return this.getRedirect(newuri, redirect, rc);
                }
                ResponseContent ret = new ResponseContent();
                ret.setStatusCode(statuscode);
                String charset = redirect.getResponseCharSet();
                if (charset != null)
                    charset = charset.toUpperCase();
                ret.setEncoding(charset);
                ret.setContentType(this.getResponseContentType(redirect));
                ret.setContentTypeString(this.getResponseContentTypeString(redirect));
                ret.setContentBytes(this.getResponseBody(redirect));
                return ret;
            } else {
                return null;
            }
        } finally {
            if (redirect != null)
                redirect.releaseConnection();
        }
    }
 
    private byte[] getResponseBody(HttpMethod method) {
        byte[] input = null;
 
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = method.getResponseBodyAsStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int size = is.read(buffer);
            while (size > 0) {
                baos.write(buffer, 0, size);
                size = is.read(buffer);
            }
            input = baos.toByteArray();
            return input;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (is != null)
                    is.close();
            } catch (Exception e1) {
            }
        }
    }
 
    public NameValuePair[] getNVBodyArray() {
        List<NameValuePair> list = this.getNVBodies();
        if (list == null || list.isEmpty())
            return null;
        NameValuePair[] nvps = new NameValuePair[list.size()];
        Iterator<NameValuePair> it = list.iterator();
        int count = 0;
        while (it.hasNext()) {
            NameValuePair nvp = it.next();
            nvps[count++] = nvp;
        }
        return nvps;
    }
 
    public List<NameValuePair> getNVBodies() {
        return Collections.unmodifiableList(this.nameValuePostBody);
    }
 
    private String getResponseContentType(HttpMethod method) {
        Header contenttype = method.getResponseHeader("Content-Type");
        if (contenttype == null)
            return null;
        String ret = null;
        try {
            HeaderElement[] hes = contenttype.getElements();
            if (hes != null && hes.length > 0) {
                ret = hes[0].getName();
            }
        } catch (Exception e) {
        }
        return ret;
    }
 
    private String getResponseContentTypeString(HttpMethod method) {
        Header contenttype = method.getResponseHeader("Content-Type");
        if (contenttype == null)
            return null;
        return contenttype.getValue();
    }
 
    static Set<Character> BEING_ESCAPED_CHARS = new HashSet<Character>();
    static {
        char[] signArray = { ' ', '\\', '‘', ']', '!', '^', '#', '`', '$', '{', '%', '|', '}', '(', '+', ')', '<', '>',
                ';', '[' };
        for (int i = 0; i < signArray.length; i++) {
            BEING_ESCAPED_CHARS.add(new Character(signArray[i]));
        }
    }
 
    public static String encodeURL(String url, String encoding) {
        if (url == null)
            return null;
        if (encoding == null)
            return url;
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (c == 10) {
                continue;
            } else if (BEING_ESCAPED_CHARS.contains(new Character(c)) || c == 13 || c > 126) {
                try {
                    sb.append(URLEncoder.encode(String.valueOf(c), encoding));
                } catch (Exception e) {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString().replaceAll("\\+", "%20");
    }
 
    private String protocol;
    private String host;
    private int port;
    private String dir;
    private String uri;
    private final static int DefaultPort = 80;
    private final static String ProtocolSeparator = "://";
    private final static String PortSeparator = ":";
    private final static String HostSeparator = "/";
    private final static String DirSeparator = "/";
 
    private String dealUrl(String OriginalUrl, String url) {
        if (url == null || url.length() == 0) {
            return OriginalUrl;
        }
        int p = url.indexOf(ProtocolSeparator);
        if (p > 0) {
            return url;
        }
 
        this.parseUrl(OriginalUrl);
 
        String u = url;
        p = u.lastIndexOf("/");
        if (u.startsWith("/")) {
            this.dir = u;
        } else {
            this.dir += u;
        }
 
        return this.toUrl();
    }
 
    private void parseUrl(String url) {
        this.protocol = null;
        this.host = null;
        this.port = DefaultPort;
        this.dir = "/";
        this.uri = dir;
 
        if (url == null || url.length() == 0)
            return;
        String u = url.trim();
        boolean MeetProtocol = false;
        int pos = u.indexOf(ProtocolSeparator);
        if (pos > 0) {
            MeetProtocol = true;
            this.protocol = u.substring(0, pos);
            pos += ProtocolSeparator.length();
        }
        int posStartDir = 0;
        if (MeetProtocol) {
            int pos2 = u.indexOf(PortSeparator, pos);
            if (pos2 > 0) {
                this.host = u.substring(pos, pos2);
                pos2 = pos2 + PortSeparator.length();
                int pos3 = u.indexOf(HostSeparator, pos2);
                String PortStr = null;
                if (pos3 > 0) {
                    PortStr = u.substring(pos2, pos3);
                    posStartDir = pos3;
                } else {
                    int pos4 = u.indexOf("?");
                    if (pos4 > 0) {
                        PortStr = u.substring(pos2, pos4);
                        posStartDir = -1;
                    } else {
                        PortStr = u.substring(pos2);
                        posStartDir = -1;
                    }
                }
                try {
                    this.port = Integer.parseInt(PortStr);
                } catch (Exception e) {
                }
            } else {
                pos2 = u.indexOf(HostSeparator, pos);
                if (pos2 > 0) {
                    this.host = u.substring(pos, pos2);
                    posStartDir = pos2;
                } else {
                    this.host = u.substring(pos);
                    posStartDir = -1;
                }
            }
 
            pos = u.indexOf(HostSeparator, pos);
            pos2 = u.indexOf("?");
            if (pos > 0 && pos2 > 0) {
                this.uri = u.substring(pos, pos2);
            } else if (pos > 0 && pos2 < 0) {
                this.uri = u.substring(pos);
            }
        }
 
        if (posStartDir >= 0) {
            int pos2 = u.lastIndexOf(DirSeparator, posStartDir);
            if (pos2 > 0) {
                this.dir = u.substring(posStartDir, pos2 + 1);
            }
        }
 
    }
 
    private String toUrl() {
        StringBuffer ret = new StringBuffer();
        if (this.protocol != null) {
            ret.append(this.protocol);
            ret.append(ProtocolSeparator);
            if (this.host != null)
                ret.append(this.host);
            if (this.port != DefaultPort) {
                ret.append(PortSeparator);
                ret.append(this.port);
            }
        }
        ret.append(this.dir);
        return ret.toString();
    }
 
    public void addNV(String name, String value) {
        NameValuePair nvp = new NameValuePair();
        nvp.setName(name);
        nvp.setValue(value);
        this.nameValuePostBody.add(nvp);
    }
 
    public void clearNVBodies() {
        this.nameValuePostBody.clear();
    }
 
    public RequestEntity getEntityBody() {
        return entityBody;
    }
 
    public void setEntityBody(RequestEntity entityBody) {
        this.entityBody = entityBody;
    }
 
}