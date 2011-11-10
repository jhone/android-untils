<%@ page language="java" import="java.util.*" pageEncoding="BIG5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'redirect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	

  </head>
  
  <body>
  
<!--    <jsp:forward page="/jstltest/app_var.jsp">-->
    <c:redirect url="/webside/jstltest/app_var.jsp">
    <jsp:param value="appliction value" name="appliction_var"/>
    <jsp:param value="session value" name="session_var"/>
    <jsp:param value="request value" name="request_var"/>
    </c:redirect>
    
  </body>
</html>
