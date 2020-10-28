<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="/sakila/sakila.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container-fluid pt-3">
	<div class="row">
		<!-- 메뉴 -->
		<div class="col-sm-3">
		<jsp:include page="/WEB-INF/views/inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- staffOne -->
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<table class="table table-borderless">	
				<tr>
					<th style="width: 25%"><h2 class="mt-2">관리자 정보</h2></th>
					<td class="align-right"><a class="btn btn-outline-dark" href="">정보 수정</a></td>
				</tr>
				<tr><td colspan="2"><hr></td></tr>
				<tr>
					<th>지점 : </th>
					<td>${sasl.staff.storeId}</td>
				</tr>
				<tr>
					<th>닉네임 : </th>
					<td>${sasl.staff.username}</td>
				</tr>
				<tr>
					<th>연락처 : </th>
					<td>${sasl.staffList.phone}</td>
				</tr>
				<tr>
					<th>이름 : </th>
					<td>${sasl.staffList.name}</td>
				</tr>
				<tr>
					<th>이메일 : </th>
					<td>${sasl.staff.email}</td>
				</tr>
				<tr>
					<th>주소 : </th>
					<td>${sasl.staffList.address} ${sasl.staffList.city} ${sasl.staffList.country}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>