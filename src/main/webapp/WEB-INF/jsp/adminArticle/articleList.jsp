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
<link rel="stylesheet" href="../css/css.css" type="text/css" />
<link rel="icon" href="../images/xxxblogs.jpg">
</head>
<body>
	<form name="form1" method="post"
		action="<%=basePath%>adminarticle/articleList">
		<input type="hidden" name="currentPage" value="${currentPage }">
		<table width="97%" border=0 align=center cellpadding=2 cellspacing=1
			bordercolor="#799AE1" class="tableBorder">
			<tbody>
				<tr>
					<th align=center colspan=16 style="height: 23px">文章管理</th>
				</tr>
				<tr bgcolor="#DEE5FA">
					<td colspan="14" align="center" class=txlrow>&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#799AE1">
					<td width="4%" align="center" class="txlHeaderBackgroundAlternate">编号</td>
					<td width="10%" align="center" class=txlHeaderBackgroundAlternate>文章标题</td>
					<td width="20%" align="center" class=txlHeaderBackgroundAlternate>内容</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>创建人</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>创建时间</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>修改人</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>最后修改时间</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>来源</td>
					<td width="6%" align="center" class=txlHeaderBackgroundAlternate>文章类别</td>
					<td width="4%" align="center" class=txlHeaderBackgroundAlternate>类别id</td>
					<td width="3%" align="center" class=txlHeaderBackgroundAlternate>编辑文章</td>
					<td width="3%" align="center" class=txlHeaderBackgroundAlternate>删除文章</td>
					<td width="6%" align="center" class=txlHeaderBackgroundAlternate>隐藏文章|显示文章</td>
					<td width="4%" align="center" class=txlHeaderBackgroundAlternate>选定</td>
				</tr>

				<c:forEach items="${pageView.records}" var="article"
					varStatus="status">

					<tr align="center" bgcolor="#DEE5FA">
						<td width="4%" align="center">${status.count }</td>
						<td width="10%" align="center" >${article.title }</td>
						<td width="20%" align="center" >${article.content }...</td>
						<td width="8%" align="center" >${article.creater }</td>
						<td width="8%" align="center" ><fmt:formatDate
								value="${article.createtime }" type="both" /></td>
						<td width="8%" align="center" >${article.lastmodifier }</td>
						<td width="8%" align="center" ><fmt:formatDate
								value="${article.lastmodifytime }" type="both" /></td>
						<td width="8%" align="center" >${article.fromurl }</td>
						<td width="6%" align="center" >${article.categoryname }</td>
						<td width="4%" align="center" >${article.categoryid }</td>
						<td width="3%" align="center" ><a href="<%=basePath%>adminarticle/toEditorArticle?id=${article.id}">编辑文章</a></td>
						<td width="3%" align="center" >删除文章</td>
						<td width="6%" align="center" >隐藏文章|显示文章</td>
						<td width="4%" align="center" >选定</td>
					</tr>

				</c:forEach>
				<!-- 将分页JSP包含进来 -->
				<tr align="center" bgcolor="#DEE5FA">
					<td colspan="14"><%@ include file="../common/pageview_inc.jsp"%>
					</td>
				</tr>
				<!-- <tr bgcolor="#DEE5FA">
					<td colspan="14" align=center bgcolor="#DEE5FA" class=txlrow>设定：
						<label> <input type="radio" name="do" value="2" /> 设置为已开奖

							<input type="radio" name="do" value="3" /> 返回未开奖 <input
							type="radio" name="do" value="1" /> 删除
					</label> <label></label> <span class="tablebody2"> <input
							type="button" value="全选" name="Submit2" onClick="selectAll()" />
					</span> <input type="submit" name="ok" value="确定" class="but">
					</td>
				</tr> -->
				<tr bgcolor="#DEE5FA">
					<td colspan=14 align=center class=txlrow></td>
				</tr>
			</tbody>
		</table>
	</FORM>
	<script language="javascript">
		function selectAll() {
			var arrObj = document.all;
			for (var i = 0; i < arrObj.length; i++) {

				if (typeof arrObj[i].type != "undefined"
						&& arrObj[i].type == 'checkbox')
					arrObj[i].checked = true;
			}
		}

		function unSelectAll() {
			var arrObj = document.all;
			for (var i = 0; i < arrObj.length; i++) {
				if (typeof arrObj[i].type != "undefined"
						&& arrObj[i].type == 'checkbox')
					arrObj[i].checked = false;
			}
		}
		
		//到指定的分页页面
		function topage(page){
			var form = document.forms[0];//包含currentPage hidden框的form
			form.currentPage.value=page;
			form.submit();
		}
	</script>

</BODY>
</HTML>