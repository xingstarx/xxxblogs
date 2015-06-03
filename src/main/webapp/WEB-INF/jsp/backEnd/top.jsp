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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<link rel="stylesheet" href="css/css.css" type="text/css">

<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.2180" name=GENERATOR></HEAD>
<BODY bgColor=#d6dff7 leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
  <TBODY>
  <TR>
    <TD class=txlHeaderBackgroundAlternate id=TableTitleLink vAlign=center 
    width="43%" height=23>-->欢迎进入博客后台管理系统</TD>
    <TD class=txlHeaderBackgroundAlternate id=TableTitleLink vAlign=center 
    width="21%">&nbsp;</TD>
    <TD class=txlHeaderBackgroundAlternate id=TableTitleLink vAlign=center 
    align=right width="36%"><A 
      href="<%=basePath %>article/index" 
      target=mainFrame>返回前台首页面</A></TD>
  </TR></TBODY></TABLE></BODY></HTML>
