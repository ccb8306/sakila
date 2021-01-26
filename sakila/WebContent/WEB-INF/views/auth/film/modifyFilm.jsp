<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyFilm</title>
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
				<form action="${pageContext.request.contextPath}/auth/ModifyFilmServlet" method="post">
					<input type="hidden" class="form-control" name="filmId" value="${filmId}">
					<table class="table">
						<tr>
							<th width="20%">제목</th>
							<td><input type="text" class="form-control" name="title" value="${facal.film.title}"></td>
						</tr>
						<tr>
							<th>설명</th>
							<td><input type="text" class="form-control" name="description" value="${facal.film.description}"></td>
						</tr>
						<tr>
							<th>개봉연도</th>
							<td><input type="text" class="form-control" name="releaseYear" value="${facal.film.releaseYear}"></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select name="categoryId" class="form-control">
									<c:forEach var="c" items="${categoryList}">
										<c:choose>
											<c:when test="${facal.category.name == c.name}">
												<option value="${c.categoryId}" selected="selected">${c.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${c.categoryId}">${c.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>언어</th>
							<td>
								<select name="languageId" class="form-control">
									<c:forEach var="l" items="${languageList}">
										<c:choose>
											<c:when test="${facal.language.name == l.name}">
												<option value="${l.language}" selected="selected">${l.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${l.language}">${l.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>대여 가능 기간</th>
							<td><input type="text" class="form-control" name="rentalDuration" value="${facal.film.rentalDuration}"></td>
						</tr>
						<tr>
							<th>대여 가격</th>
							<td><input type="text" class="form-control" name="rentalRate" value="${facal.film.rentalRate}"></td>
						</tr>
						<tr>
							<th>길이</th>
							<td><input type="text" class="form-control" name="length" value="${facal.film.length}"></td>
						</tr>
						<tr>
							<th>분실시 가격</th>
							<td><input type="text" class="form-control" name="replacementCost" value="${facal.film.replacementCost}"></td>
						</tr>
						<tr>
							<th>등급</th>
							<td>
								<select name="rating" class="form-control">
									<c:choose>
										<c:when test="${facal.film.rating = 'G'}">
											<option value="G" selected="selected">G</option>
										</c:when>
										<c:otherwise>
											<option value="G">G</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${facal.film.rating = 'PG'}">
											<option value="PG" selected="selected">PG</option>
										</c:when>
										<c:otherwise>
											<option value="PG">PG</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${facal.film.rating = 'PG-13'}">
											<option value="PG-13" selected="selected">PG-13</option>
										</c:when>
										<c:otherwise>
											<option value="PG-13">PG-13</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${facal.film.rating = 'R'}">
											<option value="R" selected="selected">R</option>
										</c:when>
										<c:otherwise>
											<option value="R">R</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${facal.film.rating = 'NC-17'}">
											<option value="NC-17" selected="selected">NC-17</option>
										</c:when>
										<c:otherwise>
											<option value="NC-17">NC-17</option>
										</c:otherwise>
									</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-primary" type="submit">수정 완료</button>
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