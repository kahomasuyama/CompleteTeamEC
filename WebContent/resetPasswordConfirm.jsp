<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>パスワード再設定確認画面</title>
		<script>
			function goResetPasswordCompleteAction()
			{
				document.getElementById("resetPassword_form").action="ResetPasswordCompleteAction";
			}
			function goResetPasswordAction()
			{
				document.getElementById("resetPassword_form").action="ResetPasswordAction";
			}
		</script>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">パスワード再設定確認画面</h1>

		<s:form id="resetPassword_form" action="ResetPasswordCompleteAction">
			<table class="inputTable horizonTable"">
				<tr>
					<th>ユーザーID</th>
					<td><s:property value="userId" /></td>
					<s:hidden name="userId" value="%{userId}"/>
				</tr>
				<tr>
					<th>新しいパスワード</th>
					<td><s:property value="concealedPassword"/></td>
				</tr>
			</table>
			<div class="button_box">
				<s:submit value="パスワード再設定" class="button" onclick="goResetPasswordCompleteAction();" />
			</div>
			<div class="button_box">
				<s:submit value="戻る" class="button" onclick="goResetPasswordAction();" />
			</div>
		</s:form>
	</body>
</html>
