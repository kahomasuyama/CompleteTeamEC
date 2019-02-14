<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>商品詳細画面</title>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<div id="contents">
			<h1 class="pageTitle">商品詳細画面</h1>

			<s:if test="productInfoDTO == null">
				<div  class="notFoundMsgBox">
					商品の詳細情報がありません
				</div>
			</s:if>
			<s:else>
				<s:form action="AddCartAction" theme="simple">

					<div class="productDetails">
							<div class="left">
								<img src='<s:property value="%{productInfoDTO.imageFilePath}"/>/<s:property value="%{productInfoDTO.imageFileName}"/>' /><br>
							</div>

							<div class="right">
								<table class="inputTable-mini">
									<tr>
										<th scope="row"><s:label value="商品名" /></th>
										<td><s:property value="%{productInfoDTO.productName}" /></td>
									</tr>
									<tr>
										<th scope="row"><s:label value="商品名ふりがな" /></th>
										<td><s:property value="%{productInfoDTO.productNameKana}" /></td>
									</tr>
									<tr>
										<th scope="row"><s:label value="値段" /></th>
										<td><s:property value="%{productInfoDTO.price}" />円</td>
									</tr>
									<tr>
										<th scope="row"><s:label value="購入個数" /></th>
										<td><s:select name="productCount" list="%{productCountArray}" />個</td>
									</tr>
									<tr>
										<th scope="row"><s:label value="発売会社名" /></th>
										<td><s:property value="%{productInfoDTO.releaseCompany}" /></td>
									</tr>
									<tr>
										<th scope="row"><s:label value="発売年月日" /></th>
										<td><s:property value="%{productInfoDTO.releaseDate}" /></td>
									</tr>
									<tr>
										<th scope="row"><s:label value="商品詳細情報" /></th>
										<td><s:property value="%{productInfoDTO.productDescription}" /></td>
									</tr>
								</table>
							</div>

						<s:hidden name="productId" value="%{productInfoDTO.productId}" />
						<s:hidden name="productName" value="%{productInfoDTO.productName}" />
						<s:hidden name="productNameKana" value="%{productInfoDTO.productNameKana}" />
						<s:hidden name="imageFilePath" value="%{productInfoDTO.imageFilePath}" />
						<s:hidden name="imageFileName" value="%{productInfoDTO.imageFileName}" />
						<s:hidden name="price" value="%{productInfoDTO.price}" />
						<s:hidden name="releaseCompany" value="%{productInfoDTO.releaseCompany}" />
						<s:hidden name="releaseDate" value="%{productInfoDTO.releaseDate}" />
						<s:hidden name="productDescription" value="%{productInfoDTO.productDescription}" />
					</div>

					<div class="button_box">
						<s:submit value="カートに追加" class="button" />
					</div>

				</s:form>
				<!-- 関連商品 -->
				<ul class="productList">
					<s:iterator value="productInfoDtoList">
						<li>
							<div class="relatedProductBox">
								<a href='<s:url action="ProductDetailsAction"> <s:param name="productId" value="%{productId}"/> </s:url>'>
									<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' /><br/>
								</a>
								<s:property value="productName" /><br>
							</div>
						</li>
					</s:iterator>
				</ul>
			</s:else>

		</div>

	</body>
</html>