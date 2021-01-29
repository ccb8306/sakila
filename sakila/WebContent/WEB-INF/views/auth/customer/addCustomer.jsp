<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCustomer</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/sakila.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function () {
		$('#addCustomerBtn').click(function () {
			if($('#firstName').val().length < 1){
				alert('first_name을 입력해 주세요.');
				return;
			} else if($('#lastName').val().length < 1){
				alert('last_name을 입력해 주세요.');
				return;
			} else if($('#email').val().length < 1){
				alert('email을 입력해 주세요.');
				return;
			} else if($('#address').val().length < 1){
				alert('주소를 입력해 주세요.');
				return;
			} else if($('#district').val().length < 1){
				alert('지역을 입력해 주세요.');
				return; 
			} else if($('#postalCode').val().length < 1){
				alert('우편번호를 입력해 주세요.');
				return;	
			} else if($('#phone').val().length < 1){
				alert('전화번호를 입력해 주세요.');
				return;
			} else {
				$('#addCustomerForm').submit();
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
				<form id="addCustomerForm" action="${pageContext.request.contextPath}/auth/AddCustomerServlet" method="post">
					<table class="table">
						<tr>
							<th width="20%">first_name</th>
							<td><input type="text" class="form-control" id="firstName" name="firstName"></td>
						</tr>
						<tr>
							<th>last_name</th>
							<td><input type="text" class="form-control" id="lastName" name="lastName"></td>
						</tr>
						<tr>
							<th>email</th>
							<td><input type="text" class="form-control" id="email" name="email"></td>
						</tr>
						<tr>
							<th>도시/국가</th>
							<td>
								<select name="cityId" class="form-control">
									<c:forEach var="c" items="${cacList}">
										<option value="${c.cityId}">${c.city} - ${c.country}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>주소</th>
							<td><input type="text" class="form-control" id="address" name="address"></td>
						</tr>
						<tr>
							<th>지역</th>
							<td><input type="text" class="form-control" id="district" name="district"></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" class="form-control" id="postalCode" name="postalCode"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" class="form-control" id="phone" name="phone"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-primary" id="addCustomerBtn" type="button">등록</button>
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