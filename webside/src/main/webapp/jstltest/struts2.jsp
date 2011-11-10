<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.List"%>
<%@ page import="com.redsun.platf.entity.account.User" %>

<jsp:useBean id="user" class="com.redsun.platf.entity.account.User"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>test jstl</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.tableStyle-tr1 {
	background-color: blue;
}

;
.tableStyle-tr2 {
	background-color: maroon;
}
;
</style>

	</head>

	<body>
		Test base jstl core .
		<br>
		<table width="477" border="1" height="150">
			<tbody>
				<tr>
					<td>
						&nbsp;输出action中属性值&lt;s:property value=&quot;属性名&quot; /&gt;
					</td>
					<td>
						&nbsp;
						<!--------------------------------------------------------------------->

					</td>
				</tr>
				<tr>
					<td valign="top">
						<span><font face="宋体">forEach var=&quot;book&quot;
								item=&quot;${store.books}&quot; varStatus=&quot;status&quot;</font> </span>
					</td>
					<td valign="top">
						<br>
						<!----------------------------for Each----------------------------------------->
						<%
							List<User> list = new ArrayList<User>();
										    for (int i = 0; i < 4; i++) {
										    	User u = new User();
												u.setId(new Long(i));
												u.setName("user:" + i);
												list.add(u);
										    }
										   
										    request.setAttribute("users",list);
						%>
						<!--												<c:set var="userslist" value="<%=list%>"></c:set>-->
						size of list:${userslist.size()}
						<!--<c:out value="${userslist}"></c:out>-->
						<%--
								out.println("<br>");
						    for (int i = 0; i < list.size(); i++) {
								User u = (User) list.get(i);
								out.println("--");
								out.println(u.getUsername());
								
						    }
						--%>


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
							<!--						list-->
							<c:forEach var="u" items="${userslist}" varStatus="status">
								<tr>
									<td>
										${u.getId()}&nbsp;
									</td>
									<td>
										<c:out value="${u.name}" />
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

					</td>
				</tr>
				<tr>
					<td>
						&nbsp; &lt;s:iterator value=&quot;#request.inOutAccountList&quot;
						id=&quot;data&quot; status=&quot;listStat&quot;&gt;
						<br>
						&lt;s:property value=&quot;#listStat.index&quot;/&gt;.count odd
						.event
						<br>
						.first .last
						<br>
						index&quot;/&gt; ndex&quot;/&gt;
					</td>
					<td>
						&nbsp;
						<!--------------------iterator------------------------------------------------->
						<table border="1" cellspacing="0">
							<tr>
								<td>
									no
								</td>
								<td>
									username
								</td>
							</tr>
							<s:action name="userBean!list"></s:action>
<!-- iterator() -->
							
							<s:iterator var="iter" value="#users.iterator()"
								status="listStat">

								<tr
									class="<s:if test='#listStat.odd==true'>tableStyle-tr1</s:if><s:else>tableStyle-tr2</s:else>">
									<td>
										<s:property value="#listStat.index" />
									</td>
									<td>
										${iter.username}

									</td>
								</tr>
							</s:iterator>


							<s:iterator var="num" value="{1,2,3,4,5}" begin="2" end="4"
								status="listStat">

								<tr
									class="<s:if test='#listStat.odd==true'>tableStyle-tr1</s:if><s:else>tableStyle-tr2</s:else>">
									<td>
										<s:property value="#listStat.index" />
									</td>
									<td>
										<s:property value="top" />

									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						checklist radio checkboxlist
					</td>
					<td>
						&nbsp;
						<!--------------------------------------------------------------------->
						<s:radio name="uncarInsPolicy.policyStateCode"
							list="#{'5':'通过' , '2':'不通过'}" listKey="key" listValue="value"
							value='5' />
						&nbsp;
						<s:checkboxlist list="{'Red', 'Blue', 'Green'}"
							name="favoriteColor" />
					</td>
				</tr>
				<tr>
					<td>
						two list
					</td>
					<td>
						&nbsp;
						<!--------------------------------------------------------------------->
						<s:set name="foobar"
							value="#{'Java': {'Spring', 'Hibernate', 'Struts 2'}, '.Net': {'Linq', ' ASP.NET 2.0'}, 'Database': {'Oracle', 'SQL Server', 'DB2', 'MySQL'}}" />
						<s:doubleselect list="#foobar.keySet()" doubleName="technology"
							doubleList="#foobar[top]" label="Technology" />

					</td>
				</tr>
				<tr>
					<td>
						date
					</td>
					<td>
						&nbsp;
						<!--------------------------------------------------------------------->
						<s:date name="unCarInsModificationInfo.createTime"
							format="yyyy-MM-dd" nice="false" />

					</td>
				</tr>
			</tbody>
		</table>

		<br>


	</body>
</html>
