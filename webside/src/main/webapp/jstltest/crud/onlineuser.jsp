<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

		<h3>
			您好：${username} [
			<a href="logout.jsp">注销</a>]
		</h3>


		<%
		    //scope demo
		    //application
		    Integer countApp = (Integer) application.getAttribute("countApp");
		    if (countApp == null) {
				pageContext.setAttribute("countApp", 1);
		    } else {
				pageContext.setAttribute("countApp", countApp + 1);
		    }
		    //page
		    Integer countPage = (Integer) pageContext.getAttribute("countPage");
		    if (countPage == null) {
				pageContext.setAttribute("countPage", 1);
		    } else {
				pageContext.setAttribute("countPage", countPage + 1);
		    }
		%>
		application count :${countApp }
		<br />
		page count :${countPage }
		<br />
		current user list :
		<table border="1">
			<tr>
				<td>
					id
				</td>
				<td>
					username
				</td>
				<td>
					isFirst
				</td>
				<td>
					isLast
				</td>
			</tr>
			<!----------------	list user----->
			<c:forEach var="u" items="${onlineUserList}" varStatus="status">
				<c:set var="row" value="${status.index % 2 != 0 ? 'odd' : 'even'}" />

				<tr class="table-row-${row}"
					onmouseover="this.className='table-row-selected';"
					onmouseout="this.className='table-row-${row}';">
					<td>
						<%--										${u.getId()}&nbsp;--%>

					</td>
					<td>
						<c:out value="${u}" />
					</td>
					<td>
						${status.first}
					</td>
					<td>
						${status.last}
					</td>
				</tr>

			</c:forEach>
		</table>


	</body>
</html>
