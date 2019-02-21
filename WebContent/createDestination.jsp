<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>宛先情報入力画面</title>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">宛先情報入力画面</h1>

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
		<s:if test="!tellNumberErrorMsgList.isEmpty()" >
			<div class="errorMsgBox">
				<s:iterator value="tellNumberErrorMsgList">
					<s:property /><br>
				</s:iterator>
			</div>
		</s:if>
		<s:if test="!userAddressErrorMsgList.isEmpty()" >
			<div class="errorMsgBox">
				<s:iterator value="userAddressErrorMsgList">
					<s:property /><br>
				</s:iterator>
			</div>
		</s:if>

		<s:form action="CreateDestinationConfirmAction">
			<table class="inputTable horizonTable">
				<tr>
					<th scope="row"><s:label value="姓"/></th>
					<td><s:textfield name="familyName" value="%{familyName}" placeholder="姓" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="名"/></th>
					<td><s:textfield name="firstName" value="%{firstName}" placeholder="名" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="姓ふりがな"/></th>
					<td><s:textfield name="familyNameKana" value="%{familyNameKana}" placeholder="姓ふりがな" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="名ふりがな"/></th>
					<td><s:textfield name="firstNameKana" value="%{firstNameKana}" placeholder="名ふりがな" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="住所"/></th>
					<td><s:textfield name="userAddress" value="%{userAddress}" placeholder="住所" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="電話番号"/></th>
					<td><s:textfield name="tellNumber" value="%{tellNumber}" placeholder="電話番号" class="txt" /></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="メールアドレス"/></th>
					<td><s:textfield name="email" value="%{email}" placeholder="メールアドレス" class="txt" /></td>
				</tr>
			</table>

			<div class="button_box">
				<s:submit value="確認" class="button" />
			</div>

		</s:form>

	</body>
</html>
