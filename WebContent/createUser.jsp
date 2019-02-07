<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ユーザー情報入力</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<jsp:include page="header.jsp" />

		<div id="contents">
			<h1>ユーザー情報入力画面</h1>

			<s:if test="!errorMsgList.isEmpty()">
				<div class="error">
					<div class="error-message">
						<s:iterator value="errorMsgList"><s:property /><br></s:iterator>
					</div>
				</div>
			</s:if>

			<s:form action="CreateUserConfirmAction">
				<table class="vertical-list-table">
					<tr>
						<th scope="row">姓</th>
						<td><s:textfield name="familyName" value="%{familyName}" label="姓" placeholder="姓" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">名</th>
						<td><s:textfield name="firstName" value="%{firstName}" label="名" placeholder="名" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">姓ふりがな</th>
						<td><s:textfield name="familyNameKana" value="%{familyNameKana}" label="姓ふりがな" placeholder="姓ふりがな" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">名ふりがな</th>
						<td><s:textfield name="firstNameKana" value="%{firstNameKana}" label="名ふりがな" placeholder="名ふりがな" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">性別</th>
						<td><s:radio name="sex" list="%{sexList}" value="%{sex}" label="性別" placeholder="性別" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">メールアドレス</th>
						<td><s:textfield name="email" value="%{email}" label="メールアドレス" placeholder="メールアドレス" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">ログインID</th>
						<td><s:textfield name="loginId" value="%{loginId}" label="ログインID" placeholder="ログインID" class="txt" /></td>
					</tr>
					<tr>
						<th scope="row">パスワード</th>
						<td><s:textfield name="password" value="" label="パスワード" placeholder="パスワード" class="txt" /></td>
					</tr>
				</table>
				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="確認" class="submit_btn" />
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>