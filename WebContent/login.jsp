<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ログイン画面</title>
		<script>
			function goLoginAction()
			{
				document.getElementById("form").action="LoginAction";
			}
			function goCreateUserAction()
			{
				document.getElementById("form").action="CreateUserAction";
			}
			function goResetPassword()
			{
				document.getElementById("form").action="ResetPasswordAction";
			}
		</script>
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div id="contents">
			<h1>ログイン画面</h1>
			<s:form id="form" action="LoginAction">
				<s:if test="errorMsgList != null && !errorMsgList.isEmpty()">
					<div class="error">
						<div class="error-message">
							<s:iterator value="errorMsgList"><s:property /><br></s:iterator>
						</div>
					</div>
				</s:if>

				<table class="vertcal-list-table">
					<tr>
						<th scope="row"><s:label value="ユーザーID:"/></th>
						<s:if test="#session.savedLoginId">
							<td><s:textfield name="loginId" class="txt" placeholder="ユーザーID" value='%{#session.loginId}' autocomplete="off"/></td>
						</s:if>
						<s:else>
							<td><s:textfield name="loginId" class="txt" placeholder="ユーザーID" autocomplete="off"/></td>
						</s:else>
					</tr>
					<tr>
						<th scope="row"><s:label value="パスワード:"/></th>
						<td><s:password name="password" class="txt" placeholder="パスワード" autocomplete="off"/></td>
					</tr>
				</table>
				<div class="box">
					<s:if test="#session.savedLoginId">
						<s:checkbox name="savedLoginId" checked="checked"/>
					</s:if>
					<s:else>
						<s:checkbox name="savedLoginId"/>
					</s:else>
					<s:label value="ユーザーID保存"/><br>
				</div>
				<div class="submit_btn_box">
					<s:submit value="ログイン" class="submit_btn" onclick="goLoginAction();"/>
				</div>
			</s:form>
			<br>
			<div class="submit_btn_box">
				<div id=".contents-btn-set">
					<s:form action="CreateUserAction">
						<s:submit value="新規ユーザー登録" class="submit_btn"/>
					</s:form>
				</div>
			</div>
			<div class="submit_btn_box">
				<div id=".contents-btn-set">
					<s:form action="ResetPasswordAction">
						<s:submit value="パスワード再設定" class="submit_btn"/>
					</s:form>
				</div>
			</div>
		</div>
	</body>
</html>