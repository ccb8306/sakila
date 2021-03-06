<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerList</title>
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
		
		<!-- customerList -->
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<div class="mt-3">
				<h2><br></h2>
				<hr>
			</div>
			
			<!-- 회원등록 / 회원검색 -->
			<div class="row input-group">
				<div style="margin-left: 10px"><a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/auth/AddCustomerServlet">신규 회원 등록</a></div>
				<div class="row" style="margin-left: auto">
					<form class="form-inline" method="get" action="${pageContext.request.contextPath}/auth/CustomerListServlet">
						<div class="form-group">
							<input placeholder="Search Customer Name" class="form-control" type="text" name="name" id="name" value="${name}">
							<button type="submit" class="btn btn-outline-dark">검색</button>
						</div>
					</form>
				</div>
			</div>
			
			<!-- 목록 -->
			<div style="margin-top: 30px">
				<table class="table">
					<thead>
						<tr>
							<th>No</th>
							<th>이름</th>
							<th>주소</th>
							<th>연락처</th>
							<th>활동</th>
							<th>대여여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}">
							<tr>
								<td>${item.id}</td>
								<td><a href="${pageContext.request.contextPath}/auth/CustomerOneServlet?customerId=${item.id}">${item.name}</a></td>
								<td>${item.address}</td>
								<td>${item.phone}</td>
								<td>
									<c:choose>
										<c:when test="${item.notes == 'active'}">
											활동중
										</c:when>
										<c:otherwise>
											<p style="color:#CCA63D">탈퇴</p>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${item.overdue == 'Y'}">
											<p style="color:red">대여중</p>
										</c:when>
										<c:otherwise>
											정상
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
			
			
			<!-- 페이징 -->	
			<div style="margin-left:35%"> 
				<ul class="pagination">
					<!-- when : 현재 페이지가 1보다 클시 -->
					<!-- other : 현재 페이지가 1일 시 -->
					<c:choose>
						<c:when test="${currentPage > '1'}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/CustomerListServlet?currentPage=1&name=${name}">처음</a></li>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/CustomerListServlet?currentPage=${currentPage-1}&name=${name}">이전</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item disabled"><a class="page-link">처음</a></li>		
							<li class="page-item disabled"><a class="page-link">이전</a></li>		
						</c:otherwise>
					</c:choose>
					<!-- 현재 페이지 표시 -->
					<li class="page-item"><a class="page-link">${currentPage}</a></li>
					<!-- when : 현재 페이지가 마지막 페이지 보다 작을 시 -->
					<!-- other : 현재 페이지가 마지막 페이지 일 시 -->
					<c:choose>
						<c:when test="${currentPage < endPage}">
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/CustomerListServlet?currentPage=${currentPage+1}&name=${name}">다음</a></li>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/CustomerListServlet?currentPage=${endPage}&name=${name}">맨끝</a></li>
						</c:when>
						<c:otherwise>		
							<li class="page-item disabled"><a class="page-link">다음</a></li>		
							<li class="page-item disabled"><a class="page-link">맨끝</a></li>		
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="over align-center">
	<br><h2 class="font-lotte-H">회원 목록</h2>
</div>
</body>
</html>