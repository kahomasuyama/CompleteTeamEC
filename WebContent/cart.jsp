<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>カート画面</title>
		<script>
			function checkValue(check)
			{
				var checkList = document.getElementsByClassName("checkList");
				var checkFlag = 0;
				for( var i = 0; i < checkList.length; i++ )
				{
					if(checkFlag == 0)
					{
						if(checkList[i].checked)
						{
							checkFlag = 1;
							break;
						}
					}
				}
				if(checkFlag == 1)
				{
					document.getElementById('delete_btn').disabled="";
				}
				else
				{
					document.getElementById('delete_btn').disabled="true";
				}
			}
		</script>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">カート画面</h1>

		<s:if test="#session.cartInfoDtoList.size()>0">
			<s:form id="form" action="SettlementConfirmAction">
				<table class="inputTable verticalTable">
					<thead>
						<tr>
							<th><s:label value="#" /></th>
							<th><s:label value="商品名" /></th>
							<th><s:label value="商品名ふりがな" /></th>
							<th><s:label value="商品画像" /></th>
							<th><s:label value="値段" /></th>
							<th><s:label value="発売会社名" /></th>
							<th><s:label value="発売年月日" /></th>
							<th><s:label value="購入個数" /></th>
							<th><s:label value="合計金額" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#session.cartInfoDtoList">
							<tr>
								<td><s:checkbox name="checkList" class="checkList" value="checked" fieldValue="%{productId}" onchange="checkValue(this)" /></td>
								<td><s:property value="productName" /></td>
								<td><s:property value="productNameKana" /></td>
								<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="50px" height="50px" /></td>
								<td><s:property value="price" />円</td>
								<td><s:property value="releaseCompany" /></td>
								<td><s:property value="releaseDate" /></td>
								<td><s:property value="productCount" /></td>
								<td><s:property value="subtotal" />円</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<p class="totalPrice">
					カート合計金額 : <span><s:property value="#session.totalPrice" />円</span>
				</p>
				<div class="button_box">
					<s:submit value="決済" class="button" />
				</div>

				<div class="button_box">
					<s:submit value="削除" id="delete_btn" class="button" onclick="this.form.action='DeleteCartAction';" disabled="true" />
				</div>
			</s:form>
		</s:if>
		<s:else>
			<div class="notFoundMsgBox">
				カート情報がありません。
			</div>
		</s:else>
	</body>
</html>
