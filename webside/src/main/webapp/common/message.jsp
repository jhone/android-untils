<%--
  Created by IntelliJ IDEA.
  User: Dick Pan
  Date: 2011/3/1
  Time: 下午 04:26:31
  To change this template use File | Settings | File Templates.
--%>
<%@include file="taglibs.jsp"%>

<c:if test="${!empty _epInfoMsg}">
    
	<div class="wpsStatusMsg">
		<table><tr>
			<td><img src="<c:out value="${ctx}"/>/images/info.gif"></td>
			<td style="color:#C00;font-size:16px;"><c:out value="${_epInfoMsg}"/></td>
		</tr></table>
	</div>
</c:if>
<c:if test="${!empty _epWarnMsg}">
	<div class="wpsStatusMsg">
		<table><tr>
			<td><img src="<c:out value="${ctx}"/>/images/warning.gif"></td>
			<td style="color:#C00;font-size:16px;"><c:out value="${_epWarnMsg}"/></td>
		</tr></table>
	</div>
</c:if>
<c:if test="${!empty _epErrorMsg}">
	<div class="wpsStatusMsg">
		<table><tr>
			<td><img src="<c:out value="${ctx}"/>/images/error.gif"></td>
			<td style="color:#C00;font-size:16px;"><c:out value="${_epErrorMsg}"/></td>
		</tr></table>
	</div>
</c:if>