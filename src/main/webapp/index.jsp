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
<html>
<head>
<script type="text/javascript" charset="utf-8" src="js/base.js"></script>
<link rel="icon" href="<%=basePath%>images/xxxblogs.jpg">
</head>
<body>
	<h2>欢迎访问xing xing的blogs!</h2>
	<script type="text/javascript">
		window.location = basePath + "article/index";
	</script>
</body>
</html>
