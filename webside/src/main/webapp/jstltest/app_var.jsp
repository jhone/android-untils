<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>test jstl</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
	</head>

	<body>
	the application value:
	<c:out value="${appliction_var}" default=" no value "/>
	<br/>
	the sessio value:
	<c:out value="${session_var}" default=" no session value "/>
	
	<br/>
	the request value:
	<c:out value="${request_var}" default=" no request value "/>
	
		
		<br>
		

		<br>


	</body>
</html>
