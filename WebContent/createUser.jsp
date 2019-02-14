<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/pageTitle.css">
		<title>ユーザー情報入力画面</title>
		<link rel="stylesheet" href="./css/yellow.css">
	</head>
	<body>
		<jsp:include page="header.jsp" />

		<div id="contents">
			<h1 class="pageTitle">ユーザー情報入力画面</h1>
			
			<!-- エラーメッセージ表示 -->
			<s:if test="!familyNameErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="familyNameErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!firstNameErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="firstNameErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!familyNameKanaErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="familyNameKanaErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!firstNameKanaErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="firstNameKanaErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!emailErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="emailErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!loginErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="loginErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!passwordErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="passwordErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			<s:if test="!ErrorMsgList.isEmpty()" >
				<div class="errorMsgBox">
					<s:iterator value="ErrorMsgList">
						<s:property /><br>
					</s:iterator>
				</div>
			</s:if>
			
			<s:form action="CreateUserConfirmAction" theme="simple">
				<table class="inputTable">
					<tr>
						<th scope="row">姓</th>
						<td><s:textfield name="familyName" value="%{familyName}" placeholder="姓" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">名</th>
						<td><s:textfield name="firstName" value="%{firstName}" placeholder="名" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">姓ふりがな</th>
						<td><s:textfield name="familyNameKana" value="%{familyNameKana}" placeholder="姓ふりがな" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">名ふりがな</th>
						<td><s:textfield name="firstNameKana" value="%{firstNameKana}" placeholder="名ふりがな" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">性別</th>
						<td><s:radio name="sex" list="%{sexList}" value="%{sex}" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">メールアドレス</th>
						<td><s:textfield name="email" value="%{email}" placeholder="メールアドレス" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">ユーザーID</th>
						<td><s:textfield name="loginId" value="%{loginId}" placeholder="ユーザーID" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">パスワード</th>
						<td><s:textfield name="password" value="" placeholder="パスワード" class="txt" /></td>
					</tr>
				</table>
				<div class="button_box">
					<div id=".contents-btn-set">
						<s:submit value="確認" class="button" />
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>