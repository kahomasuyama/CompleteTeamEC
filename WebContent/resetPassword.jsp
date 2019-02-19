<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>パスワード再設定画面</title>
	</head>
	<body>

		<jsp:include page="header.jsp"/>

		<h1 class="pageTitle">パスワード再設定画面</h1>

		<s:if test="!errorMsgList.isEmpty()">
			<div class="errorMsgBox">
					<s:iterator value="errorMsgList"><s:property/><br></s:iterator>
			</div>
		</s:if>
		<s:if test="!loginIdMsgList.isEmpty()">
			<div class="errorMsgBox">
					<s:iterator value="loginIdMsgList"><s:property/><br></s:iterator>
			</div>
		</s:if>
		<s:if test="!passwordMsgList.isEmpty()">
			<div class="errorMsgBox">
					<s:iterator value="passwordMsgList"><s:property/><br></s:iterator>
			</div>
		</s:if>
		<s:if test="!newPasswordMsgList.isEmpty()">
			<div class="errorMsgBox">
					<s:iterator value="newPasswordMsgList"><s:property/><br></s:iterator>
			</div>
		</s:if>
		<s:if test="!reConfirmationPasswordMsgList.isEmpty()">
			<div class="errorMsgBox">
					<s:iterator value="reConfirmationPasswordMsgList"><s:property/><br></s:iterator>
			</div>
		</s:if>

		<s:form action="ResetPasswordConfirmAction">
			<table class="inputTable">
				<tr>
					<th scope="row"><s:label value="ユーザーID"/></th>
					<td><s:textfield name="userId" placeholder="ユーザーID" class="txt" value="%{userId}"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="現在のパスワード"/></th>
					<td><s:password name="password" placeholder="現在のパスワード" class="txt"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="新しいパスワード"/></th>
					<td><s:password name="newPassword" placeholder="新しいパスワード" class="txt"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="新しいパスワード(再確認)"/></th>
					<td><s:password name="reConfirmationPassword" placeholder="新しいパスワード(再確認)" class="txt"/></td>
				</tr>
			</table>
			<div class="button_box">
				<s:submit value="確認" class="button"/>
			</div>
		</s:form>

	</body>
</html>
