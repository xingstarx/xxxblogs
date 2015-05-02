<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<%
		//表单提交到了dopost.jsp,编辑器中编写的内容默认名称是editorValue
		//所以通过request.getParameter("editorValue")可以得到
		//接收到数据后打印到后台，你可以把它保存到数据库中。
		System.out.println(request.getParameter("editorValue"));
	%>
	<h1>${result }</h1>
</body>
</html>