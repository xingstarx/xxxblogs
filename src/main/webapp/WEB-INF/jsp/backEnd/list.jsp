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
<link rel="stylesheet" href="css/css.css" type="text/css" />
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
<body>
	<form name="form1" method="post" action="">
		<table width="1052" border=0 align=center cellpadding=2 cellspacing=1
			bordercolor="#799AE1" class=tableBorder>
			<tbody>
				<tr>
					<th align=center colspan=16 style="height: 23px">竞拍项目管理</th>
				</tr>
				<tr bgcolor="#DEE5FA">
					<td colspan="16" align="center" class=txlrow>&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#799AE1">
					<td width="6%" align="center" class="txlHeaderBackgroundAlternate">拍中操行</td>
					<td width="7%" align="center" class=txlHeaderBackgroundAlternate>期号数</td>
					<td width="9%" align="center" class=txlHeaderBackgroundAlternate>竞拍次数</td>
					<td width="8%" align="center" class=txlHeaderBackgroundAlternate>游戏币价</td>
					<td align="center" class=txlHeaderBackgroundAlternate>起始时间</td>
					<td colspan="2" align="center" class=txlHeaderBackgroundAlternate>开奖时间</td>
					<td width="9%" align="center" class=txlHeaderBackgroundAlternate>结果</td>
					<td width="7%" align="center" class=txlHeaderBackgroundAlternate>竞拍人数</td>
					<td width="6%" align="center" class=txlHeaderBackgroundAlternate>金币总数</td>
					<td width="7%" align="center" class=txlHeaderBackgroundAlternate>奖去金币总数</td>
					<td width="6%" align="center" class=txlHeaderBackgroundAlternate>目前胜出者</td>
					<td width="3%" align="center" class=txlHeaderBackgroundAlternate>状态</td>
					<td width="4%" align="center" class=txlHeaderBackgroundAlternate>竞拍详情</td>
					<td width="6%" align="center" class=txlHeaderBackgroundAlternate>操作</td>
					<td width="4%" align="center" class=txlHeaderBackgroundAlternate>选定</td>
				</tr>

				<tr bgcolor="#DEE5FA">
					<td align="center" class="txlrow">&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center bgcolor="#DEE5FA" class=txlrow>&nbsp;</td>
					<td width="8%" align=center class=txlrow>&nbsp;</td>
					<td colspan="2" align=center class=txlrow></td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow>&nbsp;</td>
					<td align=center class=txlrow></td>
					<td align=center class=txlrow></td>
					<td align=center class=txlrow></td>
					<td align=center class=txlrow><input type="checkbox"
						name="ch[1]" value="0"></td>
				</tr>

				<tr bgcolor="#DEE5FA">
					<td colspan="16" align=center bgcolor="#DEE5FA" class=txlrow>设定：
						<label> <input type="radio" name="do" value="2" /> 设置为已开奖

							<input type="radio" name="do" value="3" /> 返回未开奖 <input
							type="radio" name="do" value="1" /> 删除
					</label> <label></label> <span class="tablebody2"> <input
							type="button" value="全选" name="Submit2" onClick="selectAll()" />
					</span> <input type="submit" name="ok" value="确定" class="but">
					</td>
				</tr>
				<tr bgcolor="#DEE5FA">
					<td colspan=16 align=center class=txlrow></td>
				</tr>
				<?
}else{ echo "<tr><td height=20 colspan=8 align=middle valign=top bgcolor=#ffffff>暂无记录</td></tr>";}

?>
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
	</script>

</BODY>
</HTML>