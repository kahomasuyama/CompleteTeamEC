<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>パスワード再設定確認</title>
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

		<div id="contents">
			<h1>パスワード再設定確認画面</h1>
			<s:form id="resetPassword_form" action="ResetPasswordCompleteAction">
				<table class="vertical-list-table">
					<tr>
						<th>ログインID：<s:property value="loginId" /><br></th>
						<s:hidden name="userId" value="%{loginId}"/>
					</tr>
					<tr>
						<th>新しいパスワード:<s:property value="concealedPassword"/><br></th>
					</tr>
				</table>
				<div class="submit_btn_box">
					<s:submit value="戻る" class="submit_buttun" onclick="goResetPasswordAction();" />
				</div>
				<div class="submit_btn_box">
					<s:submit value="パスワード再設定" class="submit_buttun" onclick="goResetPasswordCompleteAction();" />
				</div>
			</s:form>
		</div>
	</body>
</html>