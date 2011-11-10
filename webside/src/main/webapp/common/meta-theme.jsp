<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>


<%-- theme --%>
<c:if test="${!empty request_theme }">
    <sj:head debug="true" compressed="false" jquerytheme="%{request_theme}"
        customBasepath="themes" defaultLoadingText="Please wait ..." />
</c:if>
<c:if test="${empty request_theme }">
    <sj:head debug="true" compressed="false" jquerytheme="start"
        customBasepath="themes" defaultLoadingText="Please wait ..." />
      <c:set var="request_theme" value="start"/>
</c:if>

theme:<c:out value="${request_theme}"/>


 <%--
        //language
        if (session.getAttribute("request_locale") != null) {
            String getParam = session.getAttribute("request_locale")
                .toString();
            if (getParam != null) {
                session.setAttribute("language", getParam);
                session.setAttribute("request_locale", getParam);//默认为中文简体
            }

        } else {
            session.setAttribute("language", "zh_CN");//默认为中文简体
            session.setAttribute("request_locale", "zh_CN");//默认为中文简体
        }

        //theme
        if (session.getAttribute("request_theme") != null) {
            String getTheme = session.getAttribute("request_theme")
                .toString();
            if (getTheme != null) {
                session.setAttribute("theme", getTheme);
            }

        } else {
            session.setAttribute("getTheme", "start");//默认为start
        }
    --%>

