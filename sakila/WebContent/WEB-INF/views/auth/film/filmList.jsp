<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmList</title>
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
		
		<!-- 영화 관리 -->
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<div class="mt-3">
				<h2><br></h2>
				<hr>
			</div>
			<div class="row input-group">
				<!-- 영화등록 -->
				<div style="margin-left: 10px">
					<a href="${pageContext.request.contextPath}/auth/AddFilmServlet" class="btn btn-outline-primary">영화 등록</a>
				</div>
				<!-- 영화 검색 -->
				<div style="margin-left: auto">
					<form class="form-inline" method="get" action="${pageContext.request.contextPath}/auth/FilmListServlet">
						<div class="form-group">
							<input placeholder="Search Film Title" class="form-control" type="text" name="filmTitle" id="filmTitle" value="${filmTitle}">
							<button type="submit" class="btn btn-outline-dark">검색</button>
						</div>
					</form>
				</div>
			</div>
			
			<!-- 영화 목록 -->
			<div style="margin-top: 30px">
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>제목</th>
							<th>가격</th>
							<th>등급</th>
							<th>상영시간</th>
							<th>재고</th>
							<th>전체</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="f" items="${filmList}">
							<tr>
								<td>${f.fid}</td>
								<td><a href="${pageContext.request.contextPath}/auth/FilmOneServlet?filmId=${f.fid}">${f.title}</a></td>
								<td>${f.price}</td>
								<td>${f.rating}</td>
								<td>${f.length}분</td>
								<td>${f.stock }개</td>
								<td>${f.total }개</td>
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
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/FilmListServlet?currentPage=1&filmTitle=${filmTitle}">처음</a></li>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/FilmListServlet?currentPage=${currentPage-1}&filmTitle=${filmTitle}">이전</a></li>
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
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/FilmListServlet?currentPage=${currentPage+1}&filmTitle=${filmTitle}">다음</a></li>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/auth/FilmListServlet?currentPage=${endPage}&filmTitle=${filmTitle}">맨끝</a></li>
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
	<br><h2 class="font-lotte-H">영화 목록</h2>
</div>
</body>
</html>