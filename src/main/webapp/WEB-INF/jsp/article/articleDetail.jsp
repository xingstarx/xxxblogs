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
						<h2 class="blog-post-title">${article.title }</h2>
						<p class="blog-post-meta">
							<fmt:formatDate value="${article.createtime }" type="both" />
							by <a href="#">${article.creater }</a>
						</p>
						${article.content }

					</div>
					<!-- /.blog-post -->

				</c:forEach>
				
				<form:form id="myForm" action="" method="post">
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
							<li><a href="#">2015年3月</a></li>
							<li><a href="#">February 2014</a></li>
							<li><a href="#">January 2014</a></li>
							<li><a href="#">December 2013</a></li>
							<li><a href="#">November 2013</a></li>
							<li><a href="#">October 2013</a></li>
							<li><a href="#">September 2013</a></li>
							<li><a href="#">August 2013</a></li>
							<li><a href="#">July 2013</a></li>
							<li><a href="#">June 2013</a></li>
							<li><a href="#">May 2013</a></li>
							<li><a href="#">April 2013</a></li>
						</ol>
					</div>
					<div class="sidebar-module">
						<h4>分类</h4>
						<ol class="list-unstyled">
							<li><a href="#">java</a></li>
							<li><a href="#">python</a></li>
							<li><a href="#">Facebook</a></li>
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
				Blog template built for <a href="https://getbootstrap.com">java</a>
				by <a href="https://twitter.com/mdo">@mdo</a>.
			</p>
		</footer>


		<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function() { 
		}
		function pre(){
			$("#article_op").val("pre");
			$("#myForm").attr("action","../payTaxes/payTaxes");
			$("#myForm").submit();
		}
		function next(){
			$("#article_op").val("next");
			$("#myForm").attr("action","../payTaxes/payTaxes");
			$("#myForm").submit();
		}
		</script>
</body>
</html>
