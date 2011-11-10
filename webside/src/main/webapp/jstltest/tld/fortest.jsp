<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%--
  Created by IntelliJ IDEA.
  User: dick pan
  Date: 2011/3/7
  Time: 上午 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.*"%>
<%@taglib prefix="ft" uri="http://mycompany.com" %>
<%--<%@taglib prefix="ft"  uri="/WEB-INF/tld/fortag.tld"%>--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Simple jsp page</title>
	</head>
	<body>
		Place your content here
		<c:set var="contact" value="null"/>
		
		
		<%
	    Collection<String> list = new ArrayList<String>();
	    for (int i = 0; i < 10; i++) {
			list.add("str" + i);
	    }
	    pageContext.setAttribute("contact", list);
	%>

	<table>
		<tr>
			<td>
				row
			</td>

		</tr>

		<ft:myfor var="vv" items="${contact}">
			<tr>
				<td>
					 ${vv} |
					 ${vv_row}|
					 ${vv_index}
					 
				</td>
			</tr>

		</ft:myfor>
	</table>
	</body>
	
</html>