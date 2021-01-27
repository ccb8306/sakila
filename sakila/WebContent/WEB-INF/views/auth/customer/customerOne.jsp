<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<!-- 회원 정보 -->
			<div>
				<table class="table table-borderless">	
					<tr>
						<th style="width: 25%"><h2><br></h2></th>
						<td class="align-right"></td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<th>소속 매장 : </th>
						<td>${cacl.customerList.sid}</td>
					</tr>
					<tr>
						<th>이름 : </th>
						<td>${cacl.customerList.name}</td>
					</tr>
					<tr>
						<th>활동 여부 : </th>
						<td>	
							<div class="row">			
								<c:choose>
									<c:when test="${cacl.customerList.notes == 'active'}">
										활동중
									</c:when>
									<c:otherwise>
										<p style="color:#CCA63D">탈퇴</p>
									</c:otherwise>
								</c:choose>&nbsp;&nbsp;
								<form method="post" action="${pageContext.request.contextPath}/auth/CustomerOneServlet">
									<input type="hidden" name="id" id="id" value="${cacl.customerList.id}">
									<input type="hidden" name="notes" id="notes" value="${cacl.customerList.notes}">
									<button class="btn btn-outline-danger btn-sm">상태 변경</button>
								</form>
							</div>		
						</td>
					</tr>
					<tr>
						<th>연락처 : </th>
						<td>${cacl.customerList.phone}</td>
					</tr>
					<tr>
						<th>이메일 : </th>
						<td>${cacl.customer.email}</td>
					</tr>
					<tr>
						<th>주소 : </th>
						<td>${cacl.customerList.address} ${cacl.customerList.city} ${cacl.customerList.country}</td>
					</tr>
				</table>
			</div>
			<hr>
			<!-- 대여 목록 -->
			<div>
				<div><h4>대여 기록</h4></div>
				<div>
					<iframe width="100%" height="300px" src="${pageContext.request.contextPath}/auth/CustomerRentalListServlet?customerId=${cacl.customerList.id}"></iframe>
				</div>
			</div>
			<hr>
			<!-- 연체 목록 -->
			<div>
				<div><h4>미반납 목록</h4></div>
				<div>
					<iframe width="100%" height="300px" src="${pageContext.request.contextPath}/auth/CustomerOverDueListServlet?customerId=${cacl.customerList.id}"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="over align-center">
	<br><h2 class="font-lotte-H">회원 상세보기</h2>
</div>
</body>
</html>