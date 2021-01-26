<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addFilm</title>
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
				<form action="${pageContext.request.contextPath}/auth/AddFilmServlet" method="post">
					<table class="table">
						<tr>
							<th width="20%">제목</th>
							<td><input type="text" class="form-control" name="title"></td>
						</tr>
						<tr>
							<th>설명</th>
							<td><input type="text" class="form-control" name="description"></td>
						</tr>
						<tr>
							<th>개봉연도</th>
							<td><input type="text" class="form-control" name="releaseYear"></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select name="categoryId" class="form-control">
									<c:forEach var="c" items="${categoryList}">
										<option value="${c.categoryId}">${c.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>언어</th>
							<td>
								<select name="languageId" class="form-control">
									<c:forEach var="l" items="${languageList}">
										<option value="${l.language}">${l.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>대여 가능 기간</th>
							<td><input type="text" class="form-control" name="rentalDuration"></td>
						</tr>
						<tr>
							<th>대여 가격</th>
							<td><input type="text" class="form-control" name="rentalRate"></td>
						</tr>
						<tr>
							<th>길이</th>
							<td><input type="text" class="form-control" name="length"></td>
						</tr>
						<tr>
							<th>분실시 가격</th>
							<td><input type="text" class="form-control" name="replacementCost"></td>
						</tr>
						<tr>
							<th>등급</th>
							<td>
								<select name="rating" class="form-control">
									<option value="G">G</option>
									<option value="PG">PG</option>
									<option value="PG-13">PG-13</option>
									<option value="R">R</option>
									<option value="NC-17">NC-17</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Special Features</th>
							<td>
								<input type="checkbox" name="specialFeatures" value="Trailers">Trailers<br>
								<input type="checkbox" name="specialFeatures" value="Commentaries">Commentaries<br>
								<input type="checkbox" name="specialFeatures" value="Deleted Scenes">Deleted Scenes<br>
								<input type="checkbox" name="specialFeatures" value="Behind the Scenes">Behind the Scenes
							</td>
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