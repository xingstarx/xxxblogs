<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>博客后台</title>
<base href="<%=basePath%>" />
<link rel="icon" href="<%=basePath%>images/xxxblogs.jpg">
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?d1ebc9a1d5eeec4e5fb82456d337dae6";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</head>
<frameset rows="*" cols="180,*" frameborder="no" border="0"
	framespacing="0">
	<frame src="<%=basePath%>left" name="leftFrame" scrolling="auto"
		noresize="noresize" id="leftFrame" title="leftFrame" />
	<frameset rows="50,*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=basePath%>top" name="topFrame" scrolling="No"
			noresize="noresize" id="topFrame" title="topFrame" />
		<frame src="<%=basePath%>main" name="mainFrame" id="mainFrame"
			title="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
		<p>该浏览器不兼容此框架!</p>
	</body>
</noframes>
</html>
