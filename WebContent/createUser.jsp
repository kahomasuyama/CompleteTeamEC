<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ユーザー情報入力画面</title>
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
						<td><s:radio name="sex" list="%{sexList}" value="%{sex}" placeholder="性別" class="txt" /></td>
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
				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="確認" class="submit_btn" />
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>