<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	function fn_query_click() {

	}
</script>
</head>
<body>
	<fieldset>
		<legend>查询条件</legend>
		<table>
			<tr>
				<td>关键词：<input type="text" name="text" /> <input type="button" value="查 询" onclick="fn_query_click()" />
				</td>
			</tr>
		</table>
	</fieldset>
	<div>
		<table cellpadding="1" cellspacing="4" border="1">
			<thead>
				<tr>
					<th>文档编号</th>
					<th>文档作者</th>
					<th>文档概述</th>
					<th>文档标题</th>					
				</tr>
			</thead>			
		</table>
	</div>
</body>
</html>