<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'forword.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>

		<%
		    // 取得登录的用户名
		    String username = (String) session.getAttribute("username");

		    // 销毁session
		    session.invalidate();

		    // 从在线列表中删除用户名
		    List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
		    onlineUserList.remove(username);

		    // 成功
		    response.sendRedirect("list.jsp");
		%>


	</body>
</html>
