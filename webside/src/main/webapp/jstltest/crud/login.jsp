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



		<form action="#">
			用户名：
			<input type="text" name="username" />
			<br />
			<input type="submit" value="登录" />

		</form>

		<%
		    request.setCharacterEncoding("gbk");

		    // 取得登录的用户名
		    String username = request.getParameter("username");

		    if (username != null) {
				// 把用户名保存进session
				session.setAttribute("username", username);

				// 把用户名放入在线列表
				List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
				// 第一次使用前，需要初始化
				if (onlineUserList == null) {
				    onlineUserList = new ArrayList<String>();
				    application.setAttribute("onlineUserList", onlineUserList);
				}
				onlineUserList.add(username);

				// 成功
				response.sendRedirect("/webside/jstltest/crud/list.jsp");
		    }
		%>
	</body>
</html>
