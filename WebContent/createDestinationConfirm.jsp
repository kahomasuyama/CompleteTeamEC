<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
 <meta charset="UTF-8">
 <link rel="stylesheet" href="./css/style.css">
 <title>宛先情報確認</title>

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
		<div id="contents">
			<h1>宛先情報入力確認画面</h1>

			<s:form id="destinationForm" action="">
				<table class="vertical-list-table">
					<tr>
						<th scope="row"><s:label value="姓"/></th>
						<td><s:property value="familyName"/><s:hidden name="familyName" value="%{familyName}"/></td>
					</tr>
					<tr>
						<th scope="row"><s:label value="名"/></th>
						<td><s:property value="firstName"/><s:hidden name="firstName" value="%{firstName}"/></td>
					</tr>
					<tr>
						<th scope="row"><s:label value="姓ふりがな"/></th>
						<td><s:property value="familyNameKana"/><s:hidden name="familyNameKana" value="%{familyNameKana}"/></td>
					</tr>
					<tr>
						<th scope="row"><s:label value="名ふりがな"/></th>
						<td><s:property value="firstNameKana"/><s:hidden name="firstNameKana" value="%{firstNameKana}"/></td>
					</tr>

					<tr>
						<th scope="row"><s:label value="住所"/></th>
						<td><s:property value="userAddress"/><s:hidden name="userAddress" value="%{userAddress}"/></td>
					</tr>
					<tr>
						<th scope="row"><s:label value="電話番号"/></th>
						<td><s:property value="tellNumber"/><s:hidden name="tellNumber" value="%{tellNumber}"/></td>
					</tr>
					<tr>
						<th scope="row"><s:label value="メールアドレス"/></th>
						<td><s:property value="email"/><s:hidden name="email" value="%{email}"/></td>
					</tr>
				</table>

				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="登録" onclick="goCreateDestinationCompleteAction()" class="submit_btn" />
					</div>
				</div>

				<div class="submit_btn_box">
					<div id=".contents-btn-set">
						<s:submit value="戻る" onclick="goCreateDestinationAction()" class="submit_btn" />
					</div>
				</div>

			</s:form>
		</div>
	</body>
</html>