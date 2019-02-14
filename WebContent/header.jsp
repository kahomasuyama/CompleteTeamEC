<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
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
				document.getElementById("form").action="ProductListAction";
			}
			function goLogoutAction()
			{
				document.getElementById("form").action="LogoutAction";
			}
			function goSearchItemAction()
			{
				document.getElementById("form").action = "SearchItemAction";
			}
		</script>
	</head>
	<body>
		<header>
			<ul>
				<li class="siteTitle">Yellow</li>
				<s:form id="form" action="HomeAction">

					<s:if test='#session.containsKey("mCategoryDtoList")'>
						<li>
							<s:if test='#session.containsKey("selectCategoryId")'>
								<s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" value="#session.selectCategoryId" class="categorySelect" />
							</s:if>
							<s:else>
								<s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" class="categorySelect" />
							</s:else>
						</li>
					</s:if>

					<li>
						<s:textfield name="searchWord" placeholder="検索ワード" class="searchWordInput" />
					</li>

					<li>
						<s:submit value="商品検索" onclick="goSearchItemAction();" class="button" />
					</li>

					<s:if test="#session.logined == 1">
						<li>
							<s:submit value="ログアウト" onclick="goLogoutAction();" class="button" />
						</li>
					</s:if>
					<s:else>
						<li>
							<s:submit value="ログイン" onclick="goLoginAction();" class="button" />
						</li>
					</s:else>

					<li>
						<s:submit value="カート" onclick="goCartAction();" class="button" />
					</li>

					<li>
						<s:submit value="商品一覧" onclick="goProductListAction();" class="button" />
					</li>

					<s:if test="#session.logined == 1">
						<li>
							<s:submit value="マイページ" onclick="goMyPageAction();" class="button" />
						</li>
					</s:if>

				</s:form>
			</ul>
		</header>
	</body>
</html>
