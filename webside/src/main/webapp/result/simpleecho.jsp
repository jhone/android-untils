<%@ taglib prefix="s" uri="/struts-tags"%>
<s:property value="errorMessage" escape="%{escape}" />

1111
<s:if test="hasErrors()">

	<s:property value="errorMessage" escape="%{escape}" />

</s:if>