<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="/board/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="/board/resources/dist/css/AdminLTE.min.css" rel="stylesheet"
	type="text/css" />
<link href="/board/resources/plugins/iCheck/square/blue.css"
	rel="stylesheet" type="text/css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>Spring MVC Project</b>
		</div>
		<div id="loginfail" align="center"></div>
		<div class="login-box-body">
			<p class="login-box-msg">아이디와 비밀번호를 입력하세요</p>

			<form action="loginPost" method="post">
				<div class="form-group has-feedback">
					<input type="text" name="id" class="form-control"
						placeholder="아이디를 입력하세요" required="required" /> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="pw" class="form-control"
						placeholder="비밀번호를 입력하세요" required="required" /> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
					</div>
					<br /> <br />
					<div class="col-xs-6">
						<button class="btn btn-warning btn-block btn-flat">비밀번호
							찾기</button>
					</div>
					<div class="col-xs-2"></div>
					<div class="col-xs-6">
						<button class="btn btn-success btn-block btn-flat">회원가입</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="/board/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="/board/resources/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>

	<c:if test="${fail != null }">
		<script>
			$(function() {
				$("#loginfail").html("아이디가 없거나 비밀번호가 틀렸습니다.");
			})
		</script>
	</c:if>
</body>
</html>
