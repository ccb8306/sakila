<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 구글 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="/sakila/sakila.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="container">
	<div class="row">
		<!-- 메뉴 -->
		<div class="col-sm-3">
		<jsp:include page="/WEB-INF/views/inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- Rental List -->
		<div class="col-sm-9 bg-white" style="margin-top:50px; margin-bottom: 50px">
			<div>
				<h2>영화 반납</h2>
				<hr>
			</div>
			
			<!-- 검색 -->
			<div class="input-group">
				<input class="form-control" placeholder="Search Rental ID" type="text"><a style="width:120px" class="btn btn-outline-dark" href="">검색</a>
			</div>
			
			<!-- 내용 -->
			<div style="margin-top:50px">
				<table class="table">
					<thead>
						<tr>
							<th>대여 ID</th>
							<th>영화 제목</th>
							<th>대여일</th>
							<th>대여일수</th>
							<th>반납 예정일</th>
							<th>반납</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${rentalList}" varStatus="list.">
							<tr>
								<td>${item.rental.rentalId}</td>
								<td>${item.film.title}</td>
								<td>${item.rental.rentalDate}</td>
								<td>${item.film.rentalDuration}일</td>
								<td>${item.rental.returnDueDate}</td>
								<td><a class="btn btn-outline-danger" href="">반납</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<!-- 페이징 -->	
			<ul class="pagination">
				<!-- when : 현재 페이지가 1보다 클시 -->
				<!-- other : 현재 페이지가 1일 시 -->
				<c:choose>
					<c:when test="${currentPage > '1'}">
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/RentalListServlet?currentPage=1">처음</a></li>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/RentalListServlet?currentPage=${currentPage-1}">이전</a></li>
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
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/RentalListServlet?currentPage=${currentPage+1}">다음</a></li>
						<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/RentalListServlet?currentPage=${endPage}">맨끝</a></li>
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
</body>
</html>