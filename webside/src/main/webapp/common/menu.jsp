<%--
  Created by IntelliJ IDEA.
  User: Dick Pan
  Date: 2011/3/1
  Time: 下午 03:30:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
		$(function(){
	    	$(".mainMenu").buildMenu({
		        menuWidth:150,
		        openOnRight:false,
		        menuSelector: ".menuContainer",
		        hasImages:false,
		        fadeInTime:100,
		        fadeOutTime:300,
		        adjustLeft:0,
		        minZindex:"auto",
		        adjustTop:10,
		        opacity:.95,
		        shadow:false,
		        shadowColor:"#ccc",
		        hoverIntent:0,
		        openOnClick:false,
		        closeOnMouseOut:false,
		        closeAfter:1000,
		        submenuHoverIntent:200
	      	});
		});

</script>


<table width="100%" bgcolor="0"><tr><td class="mainMenu" height="32" background="<c:out value="${contextPath}"/>/images/iconBg3-1.gif" align="center">
	<table class="rootVoices"><tr>

		<%-- 主選單 --%>
		<c:forEach var="txn" items="${_epUserMenu}">
			<td class="rootVoice {menu: '<c:out value="${txn.id}"/>'}" ><c:out value="${txn.txnName}"/></td>
		</c:forEach>

	</tr></table>
</tr></table>

<%-- 子選單 --%>
<c:forEach var="txn" items="${_epUserMenu}">
	<div id="<c:out value="${txn.id}"/>" class="mbmenu">
		<c:forEach var="_txn" items="${txn.menu}">
			<c:if test="${empty _txn.childTxn}">
				<a class="{action: 'document.location=(\'<c:out value="${contextPath}"/><c:out value="${_txn.txnUrl}"/>\')'}"><c:out value="${_txn.txnName}"/></a>
			</c:if>
			<c:if test="${!empty _txn.childTxn}">
				<a class="{menu: '<c:out value="${_txn.id}"/>'}"><c:out value="${_txn.txnName}"/></a>
			</c:if>
		</c:forEach>
	</div>
</c:forEach>

<%-- 二層子選單 --%>
<c:forEach var="txn" items="${_epUserMenu}">
	<c:forEach var="_txn" items="${txn.menu}">
		<c:if test="${!empty _txn.childTxn}">
			<div id="<c:out value="${_txn.id}"/>" style="display: none;">
				<c:forEach var="__txn" items="${_txn.childTxn}">
					<a class="{action: 'document.location=(\'<c:out value="${contextPath}"/><c:out value="${__txn.txnUrl}"/>\')'}"><c:out value="${__txn.txnName}"/></a>
				</c:forEach>
			</div>
		</c:if>
	</c:forEach>
</c:forEach>
