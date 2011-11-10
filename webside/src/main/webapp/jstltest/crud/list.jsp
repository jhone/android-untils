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
		<jsp:include page="onlineuser.jsp" />
		
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
			<s:action name="/webside/account/user!list.action" />
				<s:iterator value="#page.result" status="status">
					<c:set var="row" value="${status.index % 2 != 0 ? 'odd' : 'even'}" />

				<tr class="table-row-${row}"
					onmouseover="this.className='table-row-selected';"
					onmouseout="this.className='table-row-${row}';">
					
						<td>${loginName}&nbsp;</td>
						<td>${name}&nbsp;</td>
						<td>${email}&nbsp;</td>
						<td>${roleNames}&nbsp;</td>
						<td>&nbsp;
								<a href="user!input.action?id=${id}">查看</a>&nbsp;
							
								<a href="user!input.action?id=${id}">修改</a>&nbsp;
								<a href="user!delete.action?id=${id}">删除</a>
						
						</td>
					</tr>
				</s:iterator>
				
			
		</table>


	</body>
</html>
