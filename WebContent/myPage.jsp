<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>マイページ画面</title>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">マイページ画面</h1>

		<s:form action="MyPageAction">

			<s:if test="userInfoDTO == null">
				<div class="notFoundMsgBox">
					ユーザー情報がありません。
				</div>
			</s:if>
			<s:else>
				<table class="inputTable horizonTable"">
					<tr>
						<th><s:label value="姓"/></th>
						<td><s:property value="userInfoDTO.familyName"/></td>
					</tr>
					<tr>
						<th><s:label value="名"/></th>
						<td><s:property value="userInfoDTO.firstName"/></td>
					</tr>

					<tr>
						<th><s:label value="ふりがな"/></th>
						<td><s:property value="userInfoDTO.familyNameKana"/><span> </span><s:property value="userInfoDTO.firstNameKana"/></td>
					</tr>
					<tr>
						<th><s:label value="性別"/></th>
						<td><s:if test="userInfoDTO.sex==0">男性</s:if><s:if test="userInfoDTO.sex==1">女性</s:if></td>
					</tr>
					<tr>
						<th><s:label value="メールアドレス"/></th>
						<td><s:property value="userInfoDTO.email"/></td>
					</tr>
				</table>
				<div class="button_box">
					<input type="button" value="購入履歴" onClick="location.href='PurchaseHistoryAction'" class="button">
				</div>
			</s:else>
		</s:form>
	</body>
</html>
