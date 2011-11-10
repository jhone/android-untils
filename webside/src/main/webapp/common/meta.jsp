<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<%-- language --%>
<c:if test="${empty request_locale }">
      <c:set var="request_theme" value="zh_CN"/>
      <c:set var="language" value="zh_CN"/>
</c:if>
local:<c:out value="${request_locale}" />