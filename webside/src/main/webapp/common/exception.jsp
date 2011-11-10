<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page import="org.apache.commons.lang.StringEscapeUtils"%>

<% String desc = (String)request.getAttribute("exception.desc"); %>


<pre><%=StringEscapeUtils.escapeHtml(desc)%></pre>