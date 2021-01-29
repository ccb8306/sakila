<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addActor</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/sakila.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function () {
		$('#addActorBtn').click(function () {
			if($('#firstName').val().length < 1){
				alert('first_name을 입력해 주세요.');
				return;
			} else if($('#lastName').val().length < 1){
				alert('last_name을 입력해 주세요.');
				return;
			} else {
				$('#addActorForm').submit();
			}
			
		})
	})
</script>
</head>
<body class="body-main">
<div class="container-fluid wrap pt-3">
	<div class="row">
		<!-- 메뉴 -->
		<div class="col-sm-3">
			<jsp:include page="/WEB-INF/views/inc/menu.jsp"></jsp:include>
		</div>
		
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<div class="mt-3">
				<h2><br></h2>
				<hr>
			</div>		
			
			<!-- 목록 -->
			<div style="margin-top: 30px">
				<form id="addActorForm" action="${pageContext.request.contextPath}/auth/AddActorServlet" method="post">
					<table class="table">
						<tr>
							<th width="20%">firstName</th>
							<td><input type="text" class="form-control" id="firstName" name="firstName"></td>
						</tr>
						<tr>
							<th>lastName</th>
							<td><input type="text" class="form-control" id="lastName" name="lastName"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-primary" id="addActorBtn" type="button">등록</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			
		</div>
	</div>
</div>
<div class="over align-center">
	<br>
	<h2 class="font-lotte-H">영화 등록</h2>
</div>
</body>
</html>