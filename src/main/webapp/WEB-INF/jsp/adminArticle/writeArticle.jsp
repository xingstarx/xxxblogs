<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" charset="utf-8"
	src="../ueditor/ueditor.config.js"></script>
<!-- <script type="text/javascript" charset="utf-8"
	src="../ueditor/ueditor.all.min.js"></script> -->
	<script type="text/javascript" charset="utf-8"
	src="../ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css"
	href="../ueditor/themes/default/css/ueditor.css" />
<script type="text/javascript" charset="utf-8"
	src="../ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" charset="utf-8"
	src="../js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" charset="utf-8"
	src="../js/base.js"></script>
	<style type="text/css">
        div{
            width:100%;
        }
    </style>
<title>Insert title here</title>
</head>
<body>
<body>
	<form action="saveArticle" method="post" id="writerForm">
		<div align="center">
			文章标题：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="title" />
			&nbsp;&nbsp;&nbsp;文章类别 &nbsp;&nbsp; <select name="categoryId"
				id="categoryId">
				<option value="">请选择</option>
				<c:forEach items="${categoryList}" var="category" varStatus="status">
					<option value="${category.id }">${category.categoryname }</option>
				</c:forEach>

			</select> <br> <input type="hidden" name="pics" id=pics "/>
		</div>
		<script type="text/plain" id="myEditor" class="myEditor" style="width:1024px;height:500px;align:center">
  </script>
		<input type="button" value="保存" id="saveArticleButton"> 
	<!-- 	<input type="button"
			value="查看内容" onclick="getContent()"> -->
	</form>
	<script type="text/javascript">
		var editor = new baidu.editor.ui.Editor();
		editor.render('myEditor');

		$("#saveArticleButton").click(function(){
			var categoryId=$("#categoryId").val();
			if(categoryId==''){
				alert("请选择文章类别!");
				return false;
			}
			getContent();
			$("#writerForm").submit();
		});
		
		function getContent() {
			var str = UE.getEditor('myEditor').getContent();
			var list=$(str);
			var imgs=$(str).find('img');
			var imgsSrcAll='';
			for(var i=0;i<imgs.length;i++){
				var imgSrc=$(imgs[i]).attr('src');
				imgsSrcAll+=imgSrc+',';
			}
			if(imgsSrcAll!=''){
				imgsSrcAll=imgsSrcAll.substring(0,imgsSrcAll.length);
				$("#pics").val(imgsSrcAll);
			}
			
			//console.log(imgsSrcAll);
			
		}
		 function setContent(isAppendTo) {
		        UE.getEditor('myEditor').setContent(isAppendTo);
		    }
	</script>
</body>
</html>