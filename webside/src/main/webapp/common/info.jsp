<%--
  Created by IntelliJ IDEA.
  User: Dick Pan
  Date: 2011/3/1
  Time: 下午 04:11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="path">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="text12_grey">
		<tr>
			<td width="60%" nowrap align="left">
				歡迎 ${_epUser.id}，目前您在&nbsp;
				<c:if test="${empty _epMenuFirst}">
					<span class="text12_red">首頁</span>
				</c:if>
				<c:if test="${!empty _epMenuFirst}">
                                                     首頁&nbsp;\&nbsp;<c:out
						value="${_epMenuFirst}" />&nbsp;\&nbsp;
                     <span class="text12_red"><c:out
							value="${_epMenuSecond}" /> </span>

				</c:if>
			</td>
			<td width="40%" nowrap align="right">
				今天日期：
				<span id="sys_date" class="text12_blue"></span>
				<a href="#" onclick="gotoURL('<c:out value="${ctx}"/>/logout.do')">│登出系統</a>
				<a href="#"
					onclick="gotoURL('<c:out value="${ctx}"/>/pwdChange.do')">│變更密碼</a>
				<a href="#" onclick="">|Q_A</a>
			</td>
		</tr>
		<tr>
			<td width="100%" align="right" scope="col">

				<span class="text12_red"> 语言<c:out value="${request_locale}" />
					<%--<%=session.getAttribute("request_locale")%>--%> </span>
			</td>

			<td>

				<span class="text12_red">皮肤<c:out value="${request_theme}" />
					<a href="<c:out value="${ctx}"/>/change-themes.jsp">变更</a> </span>

			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
$(function() {
	startTime();
});

function startTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	//var today_year = today.getYear();
	var today_year = today.getFullYear();
	var today_month = today.getMonth() + 1;
	var today_date = today.getDate();

	// add a zero in front of numbers<10
	m = checkTime(m);
	s = checkTime(s);
	$('#walsin_date').html(
			today_year + "/" + today_month + "/" + today_date + " " + h + ":"
					+ m + ":" + s);
	t = setTimeout('startTime()', 500);
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
function gotoURL(url) {
	document.location = url;
}
</script>