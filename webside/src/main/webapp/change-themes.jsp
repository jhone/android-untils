<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


 
<html>
	<head>



		<title>System Setup</title>
		<%@ include file="/common/meta.jsp"%>
		
		<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript" src="${ctx}/js/main/sys_setup.js">
</script>



		<script type="text/javascript">

/**
 * event of change option
 */
function change_language() {
	//	alert($("#sel_language").val());
	//change session.attribute("language").value=$("#sel_language").val();

}
function change_theme() {
	//	alert($("#sel_theme").val());
	//setSession("request_theme",$("#sel_theme").val());
}

/**
 * change event 
 **/
function apply() {
	//change session.attribute("language").value=$("#sel_language").val();

<%
session.setAttribute("request_locale", request
		    .getParameter("sel_language"));
session.setAttribute("language", request
		    .getParameter("sel_language"));
	    session.setAttribute("request_theme", request
		    .getParameter("sel_theme"));%>
//referesh window		    
window.location.reload();

}

</script>

	</head>

	<body>
		<br />
		locale: ${pageContext.response.locale}
		
<c:if test="${!empty request_theme }">
		<c:out value="${request_theme}"/>
	</c:if>
		<div id="doc3">
			<%@ include file="/common/header.jsp"%>

			<div id="bd">
				<div id="yui-main">
					<div id="message">
						<s:actionmessage theme="custom" cssClass="success" />
					</div>

					<div id="languages_themes">

						<form id="sysform" method="post" onsubmit="apply()">

							Select Language :
							<select id="sel_language" name="sel_language"
								onchange="change_language()">

								<option id="change_language">
									--change language--
								</option>

							</select>
							<!--	  <button type="submit">change language</button>-->

							Select Theme &nbsp; &nbsp; &nbsp;:
							<select id="sel_theme" name="sel_theme" onchange="change_theme()">
								<option id="change_theme">
									--change theme--
								</option>

							</select>

							<input type="hidden" id="_language" name="_language"
								value="<%=session.getAttribute("request_locale")%>" />
							<input type="hidden" id="_theme" name="_theme"
								value="<%=session.getAttribute("request_theme")%>" />

							<button type="submit">
								change
							</button>
						</form>

					</div>


					<div>

					</div>
				</div>
			</div>
		</div>

	</body>
</html>
