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

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<!-- style -->
<style>
	.align-left{text-align:left;}
	.align-center{text-align:center;}
	.align-right{text-align:right;}
</style>
</head>
<body>
<div style="margin-top:200px"></div>
<div style="width:500px" class="container form-group">
	<h2 class="align-center">로그인 폼</h2>
	<h6 class="align-center">오늘 접속자 수 : ${stats.count}명</h6>	

	<form id="loginForm">
		<table class="table table-borderless">			
			<tr>		
				<td style="width:10%"><i class='fas fa-user-alt' style='font-size:36px; color:gray'></i></td> 
				<td><input class="form-control" type="text" placeholder="email" id="email"></td>
			</tr>
			<tr>		
				<td><i class='fas fa-key' style='font-size:36px; color:gray'></i></td>
				<td><input class="form-control" type="password" placeholder="pw" id="pw"></tr>
			</tr>
			<tr>
				<td colspan="2"><button class="btn btn-block btn-outline-dark" id="btn" type="button">Log-In</button></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>