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
				<form action="${pageContext.request.contextPath}/auth/AddCustomerServlet" method="post">
					<table class="table">
						<tr>
							<th width="20%">first_name</th>
							<td><input type="text" class="form-control" name="firstName"></td>
						</tr>
						<tr>
							<th>last_name</th>
							<td><input type="text" class="form-control" name="lastName"></td>
						</tr>
						<tr>
							<th>email</th>
							<td><input type="text" class="form-control" name="email"></td>
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
							<td><input type="text" class="form-control" name="address"></td>
						</tr>
						<tr>
							<th>지역</th>
							<td><input type="text" class="form-control" name="district"></td>
						</tr>
						<tr>
							<th>우편번호</th>
							<td><input type="text" class="form-control" name="postalCode"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" class="form-control" name="phone"></td>
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