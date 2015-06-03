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


<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="blog,java,xingxing">
<meta name="author" content="一个小码农">
<link rel="icon" href="../../favicon.ico">

<title>星星的blogs</title>

<!-- Bootstrap core CSS -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="../bootstrap/css/blog.css" rel="stylesheet">

<!-- Custom styles for this template -->
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">

		<div class="blog-header">
			<h1 class="blog-title">Xingxing's Blog</h1>
			<p class="lead blog-description">记录每一点的成长!!!</p>
		</div>

		<div class="row">

			<div class="col-sm-8 blog-main">
				<c:forEach items="${articles}" var="article" varStatus="status">

					<div class="blog-post">
						<h2 class="blog-post-title"><a href="<%=basePath%>article/showView?id=${article.id}">${article.title }</a></h2>
						<p class="blog-post-meta">
							<fmt:formatDate value="${article.createtime }" type="both" />
							by <span style="color:red;">${article.creater }</span>
							<span><a href="<%=basePath%>article/showView?id=${article.id}">阅读</a></span>
						</p>
						${article.content }...

					</div>
					<!-- /.blog-post -->

				</c:forEach>
				
			<%-- 	<form:form id="myForm" action="" method="post">
				<input type="hidden" id="article_max" name="max" value="${max }"/>
				<input type="hidden" id="article_min" name="min" value="${min }"/>
				<input type="hidden" id="article_op" name="op" value=""/>
			</form:form>
				
				<nav>
					<ul class="pager">
						<li><a href="javascript:;" id="pre"
							onclick="javascript: return pre();">上一篇</a></li>
						<li><a href="javascript:;" id="next"
							onclick="javascript: return next();">下一篇</a></li>
					</ul>
				</nav>
 --%>
			</div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<!-- <h4>关于</h4>
            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
          </div> -->
					<div class="sidebar-module">
						<h4>时间线</h4>
						<ol class="list-unstyled">
								<c:forEach items="${timeLineList}" var="timeLine" varStatus="status">
									<li><a href="<%=basePath%>article/timeline?id=${timeLine.id }">${timeLine.descr }(${timeLine.articlecount })</a></li>
								</c:forEach>
							</ol>
					</div>
					<div class="sidebar-module">
						<h4>分类</h4>
						<ol class="list-unstyled">
							<c:forEach items="${categoryList}" var="category" varStatus="status">
									<li><a href="<%=basePath%>article/category?id=${category.id }">${category.categoryname }(${category.articlecount })</a></li>
								</c:forEach>
						</ol>
					</div>
				</div>
				<!-- /.blog-sidebar -->

			</div>
			<!-- /.row -->

		</div>
		<!-- /.container -->

		<footer class="blog-footer">
			<p>
				xingstar's blogs! <br>
				个人博客项目 made by java!
			</p>
		</footer>


		<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function() { 
		});
		/* function pre(){
			$("#article_op").val("pre");
			$("#myForm").attr("action","flip");
			$("#myForm").submit();
		}
		function next(){
			$("#article_op").val("next");
			$("#myForm").attr("action","flip");
			$("#myForm").submit();
		} */
		</script>
</body>
</html>
