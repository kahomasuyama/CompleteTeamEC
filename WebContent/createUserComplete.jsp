<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/yellow.css">
		<title>ユーザー情報入力完了画面</title>
		<script>
		    window.onload = function()
		    {
		    	var form = document.getElementById('form1');
		    	setTimeout(function(){form.submit()},3000);
		    }
		</script>
	</head>
	<body>

		<jsp:include page="header.jsp" />

		<h1 class="pageTitle">ユーザー情報入力完了画面</h1>

		<div class="completeMsgBox">
			ユーザー情報入力が完了しました。
		</div>

		<s:form id="form1" action="LoginAction">
		    <s:hidden name="loginId" value="%{loginId}" />
		    <s:hidden name="password" value="%{password}" />
		</s:form>

	</body>
</html>
