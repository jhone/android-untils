<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
		    + request.getServerName() + ":" + request.getServerPort()
		    + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="com.redsun.platf.entity.account.*"%>
<jsp:useBean id="user" class="com.redsun.platf.entity.account.User" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>test jstl</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.table-row-odd {
	background-color: blue;
}

;
.table-row-even {
	background-color: maroon;
}
;
.table-row-selected {
	background-color: gray;
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
						&nbsp; set 设置属性
					</td>
					<td>
						&nbsp;
						<!--------------------------------------------------------------------->
						<c:set value="li hong zhi" var="firstName" />
						<c:out value="${firstName}"></c:out>
						<br>
						default:
						<c:out value="${lastName}" default="no lastname"></c:out>
						<br>
						bean:

						<c:set target="${user}" property="name" value="achk" />
						<c:out value="${user.name}" default="no name"></c:out>

					</td>
				</tr>
				<tr>
					<td>
						&nbsp; remove 移除设置的属性
					</td>
					<td>
						&nbsp;
						<!--------------------remove------------------------------------------------->
						<c:remove var="firstName"></c:remove>
						<c:out value="${firstName}" default="no firstname"></c:out>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp; if条件标签 只有在test属性的
						<br>
						值为true是才会执行标签体
					</td>
					<td>
						&nbsp;
						<!------------------------if--------------------------------------------->
						<c:set var="role" value="admin" />
						<c:if test="${role=='admin'}">
							<input type="button" value="delete">

						</c:if>
						<c:if test="${role!='user'}">
							<input type="button" value="add">

						</c:if>

					</td>
				</tr>
				<tr>
					<td valign="top">
						<font face="宋体"><span>choose</span><span>和<span>when</span>
								test=&quot;&quot;的</span> </font>
					</td>
					<td valign="top">
						<!--------------------------choose------------------------------------------->
						<c:set var="score" value="88" />
						<br>
						<c:choose>
							<c:when test="${score>=90}"> perfect
            </c:when>
							<c:when test="${score<90 and score>=80}"> better
            </c:when>
							<c:otherwise>normal</c:otherwise>

						</c:choose>
						<br>
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
								<c:set var="row"
									value="${status.index % 2 != 0 ? 'odd' : 'even'}" />

								<tr class="table-row-${row}" onmouseover="this.className='table-row-selected';"
									onmouseout="this.className='table-row-${row}';">
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
					<td valign="top">
						<span><font face="宋体">forEach begin=&quot;1&quot;
								end=&quot;5&quot; step=&quot;1&quot;</font> </span>
					</td>
					<td valign="top">
						<br>
						<!-------------------------for each begin end-------------------------------------------->
						<table border="1">


							<tr>
								<c:forEach var="i" begin="1" end="10" step="2">
									<td>
										${i}
									</td>
								</c:forEach>
							</tr>


						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<font face="宋体"><span>forTokens&gt;</span><span>字符串迭代标签</span>
						</font>
					</td>
					<td valign="top">
						<br>
						<!--------------------------------------------------------------------->

						<c:forTokens items="a,b,cd,e,d,f" delims="," var="j">
            ${j}
        </c:forTokens>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<font face="宋体"><span>import url=&quot;</span><span>引入内容的<span>url&quot;
									var=&quot;</span>别名<span>&quot;</span> </span> </font>
					</td>
					<td valign="top">
						<br>
						<!--------------------------------------------------------------------->
						<c:import url="./app_var.jsp"></c:import>
					</td>
				</tr>


				<tr>
					<td valign="top">
						<span><font face="宋体">fmt:setLocale
								value=&quot;zh&quot;/&gt;</font> </span>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
						</p>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
							<span><font face="宋体">&lt;fmt:bundel
									basename=&quot;message.MessageResources&quot;&gt;</font> </span>
						</p>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
						</p>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
							<span><font face="宋体">&lt;fmt:message&gt;name&lt;/fmt:message&gt;</font>
							</span>
						</p>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
						</p>

						<p style="margin: 0cm 0cm 0pt;" class="MsoPlainText">
							<span><font face="宋体">&lt;/fmt:bundel&gt;</font> </span>
						</p>
					</td>
					<td valign="top">
						<br>
						<!--------------------------------------------------------------------->

					</td>
				</tr>
				<tr>
					<td valign="top">
						<br>
						url value=&quot;...&quot;
						<br>
						<span><font face="宋体">redirect
								uri=&quot;xxx/xxx/xxx.xx&quot;/,转到新页里了</font> </span>
						<br />
						<span><font face="宋体">forward
								uri=&quot;xxx/xxx/xxx.xx&quot;/,还在当前session</font> </span>
					</td>
					<td valign="top">
						<br>
						<!--------------------------------------------------------------------->

						<c:set var="appliction_var" value=" application maker "
							scope="application" />
						<c:set var="session_var" value=" session maker " scope="session" />
						<c:set var="request_var" value=" request maker " scope="request" />
						<c:url value="../jstltest/app_var.jsp" var="result"> look app value</c:url>
						<a href="${result} ">show value</a>
					</td>
				</tr>
			</tbody>
		</table>

		<br>


	</body>
</html>
