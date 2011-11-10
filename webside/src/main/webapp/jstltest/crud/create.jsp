<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'forword.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="/webside/css/styles.css">

	</head>

	<body>

<jsp:include page="onlineuser.jsp"/>
		
		
		<form id="inputForm" action="user!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td>登录名:</td>
				<td><input type="text" name="loginName" size="40" id="loginName" value="${loginName}"/></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="name" name="name" size="40" value="${name}"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" id="password" name="password" size="40" value="${password}"/></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="40" value="${password}"/>
				</td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" id="email" name="email" size="40" value="${email}"/></td>
			</tr>
			
			<tr>
				<td colspan="2">
				
					<input class="button" type="submit" value="提交"/>&nbsp;
					<input class="button" type="button" value="返回" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>


	</body>
</html>
