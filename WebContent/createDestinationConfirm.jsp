<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>宛先情報入力確認確認</title>
		<script>
		function goCreateDestinationAction()
		{
			document.getElementById("destinationForm").action="CreateDestinationAction";
		}
		function goCreateDestinationCompleteAction()
		{
			document.getElementById("destinationForm").action="CreateDestinationCompleteAction";
		}
		</script>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">宛先情報入力確認画面</h1>

		<s:form id="destinationForm" action="">
			<table class="inputTable horizonTable">
				<tr>
					<th scope="row"><s:label value="姓"/></th>
					<td><s:property value="familyName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名"/></th>
					<td><s:property value="firstName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="姓ふりがな"/></th>
					<td><s:property value="familyNameKana"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="名ふりがな"/></th>
					<td><s:property value="firstNameKana"/></td>
				</tr>

				<tr>
					<th scope="row"><s:label value="住所"/></th>
					<td><s:property value="userAddress"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="電話番号"/></th>
					<td><s:property value="tellNumber"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="メールアドレス"/></th>
					<td><s:property value="email"/></td>
				</tr>
			</table>

			<div class="button_box">
				<s:submit value="登録" onclick="goCreateDestinationCompleteAction()" class="button" />
			</div>

			<div class="button_box">
				<s:submit value="戻る" onclick="goCreateDestinationAction()" class="button" />
			</div>

			<s:hidden name="loginId" value="%{loginId}"/>
			<s:hidden name="password" value="%{password}"/>
			<s:hidden name="familyName" value="%{familyName}"/>
			<s:hidden name="firstName" value="%{firstName}"/>
			<s:hidden name="familyNameKana" value="%{familyNameKana}"/>
			<s:hidden name="firstNameKana" value="%{firstNameKana}"/>
			<s:hidden name="userAddress" value="%{userAddress}"/>
			<s:hidden name="tellNumber" value="%{tellNumber}"/>
			<s:hidden name="email" value="%{email}"/>

		</s:form>
	</body>
</html>
