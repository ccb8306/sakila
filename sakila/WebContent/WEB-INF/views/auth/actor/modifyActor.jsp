<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyActor</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/sakila.css" rel="stylesheet" type="text/css" />
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
				<form action="${pageContext.request.contextPath}/auth/ModifyActorServlet" method="post">
					<input type="hidden" class="form-control" name="actorId" value="${actor.actorId}">
					<table class="table">
						<tr>
							<th width="20%">firstName</th>
							<td><input type="text" class="form-control" name="firstName" value="${actor.firstName}"></td>
						</tr>
						<tr>
							<th>lastName</th>
							<td><input type="text" class="form-control" name="lastName" value="${actor.lastName}"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-primary" type="submit">등록</button>
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