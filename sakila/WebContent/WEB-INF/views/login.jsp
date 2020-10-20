<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 구글 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- jQuery -->
<script>
	$(document).ready(function() {	
		$("#btn").click(function() {	
			if ($("#email").val().length < 1) {
				alert("이메일을 입력해주세요");
				return;
			}
			else if ($("#pw").val() < 1) {
				alert("비밀번호를 입력해주세요");
				return;
			}
			$("#loginForm").submit();
		});	
	});
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
<div style="text-align: center" class="container">
	<h1>로그인 폼</h1>
	<div>
		오늘 접속자 수 : ${stats.count}
	</div>
	<form id="loginForm">
		<div>						
			<i class='fas fa-user-alt' style='font-size:36px; color:gray'></i> 
			<input type="text" placeholder="email" id="email">
		</div>
		<div>
			<i class='fas fa-key' style='font-size:36px; color:gray'></i> 
			<input type="password" placeholder="pw" id="pw">
		</div>
		<div>						
			<button class="btn btn-outline-primary" id="btn" type="button">Log-In</button>
		</div>
	</form>
</div>
</body>
</html>