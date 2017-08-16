<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>用户登录页面</title>
</head>

<body>
	<h2>用户登录</h2>
	<hr>
	<form action="login.action" method="post">
		<table border="1">
			<tr>
				<td>用户名：</td>
				<td><select name="name">
						<option value="1">张三</option>
						<option value="2">李四</option>
						<option value="3">王五</option>
						<option value="4">赵六</option>
						<option value="5">组长</option>
						<option value="6">经理</option>
						<option value="7">总经理</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value=" 登录 " /></td>
			</tr>
		</table>
	</form>
</body>
</html>