<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/style.css">
		<title>ヘッダー</title>
		<script>
			function goLoginAction()
			{
				document.getElementById("form").action="GoLoginAction";
			}
			function goMyPageAction()
			{
				document.getElementById("form").action="MyPageAction";
			}
			function goCartAction()
			{
				document.getElementById("form").action="CartAction";
			}
			function goProductListAction()
			{
				document.getElementById("categoryId").value = 1;
				document.getElementById("form").action="ProductListAction";
			}
			function goLogoutAction()
			{
				document.getElementById("form").action="LogoutAction";
			}
			function goSearchItemAction()
			{
				document.getElementById("form").action="SearchItemAction";
			}
		</script>
	</head>
	<body>
		<header>
			<div>Yellow</div>
			<ul>
				<s:form id="form">

					<s:if test='#session.containsKey("mCategoryDtoList")'>
						<li><s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" /></li>
					</s:if>

					<li><s:textfield name="keywords" placeholder="検索ワード" /></li>

					<li><s:submit value="商品検索" onclick="goSearchItemAction();" /></li>

					<s:if test="#session.logined == 1">
						<li><s:submit value="ログアウト" onclick="goLogoutAction();" /></li>
					</s:if>

					<s:else>
						<li><s:submit value="ログイン" onclick="goLoginAction();" /></li>
					</s:else>

					<li><s:submit value="カート" onclick="goCartAction();" /></li>

					<li><s:submit value="商品一覧" onclick="goProductListAction();" /></li>

					<s:if test="#session.logined == 1">
						<li><s:submit value="マイページ" onclick="goMyPageAction();" /></li>
					</s:if>

				</s:form>
			</ul>
		</header>
	</body>
</html>