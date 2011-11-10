<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="hd">
	<div id="title">
		<h1>Web-side</h1>
		<h2>--CRUD管理界面</h2>

        <h3>
            语言<%=session.getAttribute("request_locale")%>
            
         皮肤<%=session.getAttribute("request_theme")%><a href="${ctx}/change-themes.jsp">变更</a>
        </h3>
	</div>
	<div id="menu">
		<ul>
			<li><a href="${ctx}/account/user.action">帐号列表</a></li>
			<li><a href="${ctx}/account/role.action">角色列表</a></li>
			<li><a href="${ctx}/j_spring_security_logout">退出登录</a></li>
			<li><a href="${ctx}/webmain/system-company.action">系统公司设定</a></li>

		</ul>
	</div>
	
	
</div>
