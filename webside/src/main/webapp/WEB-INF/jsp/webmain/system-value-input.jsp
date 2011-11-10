<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Mini-Web 公司管理</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#companyNo").focus();
			//为inputForm注册validate函数
			$("#inutForm").validate({
				rules: {
					companyNo: {
						required: true,
						remote: "system-company!checkCompanyNo.action?old_CompanyNo=" + encodeURIComponent('${companyNo}')
					},
					companyName: {
						required: true,
						remote: "system-company!checkCompanyName.action?old_CompanyName=" + encodeURIComponent('${companyName}')
					},
					email:"email",
					bankNo: {
						required: true,
						minlength:8
					},
					bankName: {
						required: true,
						minlength:10
					}
				},
				messages: {
					companyNo: {
						remote: "公司代码已存在"
					},
					loginName: {
						remote: "公司名称已存在"
					}
				}
			});
		});
	</script>
</head>

<body>
<div id="doc3">
<%@ include file="/common/header.jsp" %>
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>公司</h2>
	<form id="inputForm" action="system-value!save.action" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<table class="noborder">
			<tr>
				<td>system key:</td>
				<td><input type="text" name="sysKey" size="40" id="sysKey" value="${sysKey}"/></td>
			</tr>
			<tr>
				<td>system value:</td>
				<td><input type="text" id="sysValue" name="sysValue" size="40" value="${sysValue}"/></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input class="button" type="submit" value="提交1"/>&nbsp;
					<security:authorize ifAnyGranted="ROLE_修改用户">
						<input class="button" type="submit" value="提交"/>&nbsp;
					</security:authorize>
					<input class="button" type="button" value="返回" onclick="history.back()"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</div>
<%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>
