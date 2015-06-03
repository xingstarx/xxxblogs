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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script language="javascript" src="/js/topage.js"></script>
<script type="text/javascript">
//到指定的分页页面
function topage(page){
	var form = document.forms[0];//包含currentPage hidden框的form
	form.currentPage.value=page;
	form.submit();
}
</script>
</head>
<body>
	学生如下：
	<br />
	<form action="adminarticle/testPageView" method="post">
		<input type="hidden" name="currentPage" value="${currentPage }">
		<table style="border: solid 1px green;">
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>年龄</td>
				<td>生日</td>
			</tr>
			<c:forEach items="${pageView.records}" var="entry">
				<tr>
					<td>${entry.id}</td>
					<td>${entry.name}</td>
					<td>${entry.age}</td>
					<td><fmt:formatDate value="${entry.birth}" type="both"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<!-- 将分页JSP包含进来 -->
	<%@ include file="./pageview_inc.jsp"%>
</body>
</html>
