<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sales</title>
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
			
			<div style="margin-top: 30px">
				<table class="table">				
					<thead class="thead-light">
						<tr>
							<th width="20%">매장 총 수익 : </th>
							<td>${salesStore[0].total_sales}</td>
						</tr>
					</thead>
				</table>
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th width="20%">카테고리</th>
							<th>수익</th>
						</tr>
					</thead>
					<c:forEach var="c" items="${salesCategory}">
						<tr>
							<td>${c.category}</td>
							<td>${c.total_sales}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		</div>
	</div>
</div>
<div class="over align-center">
	<br>
	<h2 class="font-lotte-H">매장 통계</h2>
</div>
</body>
</html>