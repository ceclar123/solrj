<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加文档</title>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	function fn_save_click() {
		$("#form1").submit();
	}
</script>
</head>
<body>
	<fieldset>
		<legend>文档信息</legend>
		<form id="form1" action="<%=basePath%>document/AddDoc" method="post">
			<table width="1000" cellpadding="1" cellspacing="4" border="1">
				<tr>
					<td>文档编号</td>
					<td><input type="text" id="id" name="id" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>文档名称</td>
					<td><input type="text" name="name" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>文档概述</td>
					<td><input type="text" name="summary" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>文档作者</td>
					<td><input type="text" name="author" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>出版日期</td>
					<td><input type="text" name="date" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>文档标题</td>
					<td><input type="text" name="title" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>关键字</td>
					<td><input type="text" name="keywords" style="width: 300;" /></td>
				</tr>
				<tr>
					<td>文档内容</td>
					<td><textarea name="content" style="width: 300px; height: 150px"></textarea></td>
				</tr>
			</table>
			<div style="text-align: center;">
				<input type="button" value="保 存" onclick="fn_save_click()" />
			</div>
			<div style="text-align: center;">${msg}</div>
		</form>
	</fieldset>
</body>
</html>