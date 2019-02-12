<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>商品一覧画面</title>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<s:if test="!errorMsgList.isEmpty()">
			<div class="errorMsgBox">
				<s:iterator value="errorMsgList">
					<s:property /><br>
				</s:iterator>
			</div>
		</s:if>

		<div id="contents">
			<h1>商品一覧画面</h1>
			<s:if test="productInfoList == null || productInfoList.isEmpty()">
				検索結果がありません。
			</s:if>
			<s:else>
				<ul class="productList">
					<s:iterator value="productInfoList">
						<li class="productListBox">
							<a href='<s:url action="ProductDetailsAction"> <s:param name="productId" value="%{productId}" /> </s:url>'>
								<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' width="100" />
							</a>
							<s:property value="productName" /><br/>
							<s:property value="productNameKana" /><br/>
							<s:property value="price" />円<br/>
						</li>
					</s:iterator>
				</ul>
			</s:else>
		</div>

	</body>
</html>