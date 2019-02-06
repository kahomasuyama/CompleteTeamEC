<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>パスワード再設定</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="contents">
		<h1>パスワード再設定画面</h1>
		<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
				<s:iterator value="#session.loginIdErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:if test="!#session.passwordErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
					<s:iterator value="#session.passwordErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:if test="!#session.passwordIncorrectErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
					<s:iterator value="#session.passwordIncorrectErrorList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:if test="!#session.newPasswordErrorMessageList.inEmpty()">
			<div class="error">
				<div class="error-message">
					<s:iterator value="#session.reConfirmactionNewPasswordErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:if test="!#session.reConfirmactionNewPasswordErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
					<s:iterator value="#session.reConfirmactionNewPasswordErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:if test="!#session.newPasswordIncorrectErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
					<s:iterator value="#session.newPasswordIncorrectErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>

		<s:form action="ResetPasswordConfirmAction">
			<table class="vertical-list-table">
				<tr>
					<th scope="row"><s:label value="ユーザーID"/></th>
					<td><s:textfield name="loginId" placeholder="ユーザーID" class="txt"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="現在のパスワード"/></th>
					<td><s:password name="password" placeholder="現在のパスワード" class="txt"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="新しいパスワード"/></th>
					<td><s:password name="nesPassword" placeholder="新しいパスワード" class="txt"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="(再確認)"/></th>
					<td><s:password name="reConfirmactionPassword" placeholder="新しいパスワード(再確認)" class="txt"/></td>
				</tr>
			</table>
			<s:submit value="確認" class="submit_btn"/>
		</s:form>
	</div>
	<s:include value="footer.jsp"/>

</body>
</html>