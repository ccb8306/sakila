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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function () {
		$('#addFilmBtn').click(function () {
			if($('#title').val().length < 1){
				alert('제목을 입력해 주세요.');
				return;
			} else if($('#description').val().length < 1){
				alert('내용을 입력해 주세요.');
				return;
			} else if($('#rentalDuration').val().length < 1){
				alert('대여 가능 기간을 입력해 주세요.');
				return;
			} else if($('#rentalRate').val().length < 1){
				alert('대여 가격을 입력해 주세요.');
				return;
			} else if($('#length').val().length < 1){
				alert('길이를 입력해 주세요.');
				return;
			} else if($('#replacementCost').val().length < 1){
				alert('분실시 가격을 입력해 주세요.');
				return;
			} else if($('input:checkbox[class=specialFeatures]:checked').length < 1){
				alert('Special Features를 한개이상 선택해 주세요.');
				return;
			} else {
				$('#addFilmForm').submit();
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
				<form id="addFilmForm" action="${pageContext.request.contextPath}/auth/AddFilmServlet" method="post">
					<table class="table">
						<tr>
							<th width="20%">제목</th>
							<td><input type="text" class="form-control" id="title" name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><input type="text" class="form-control" id="description" name="description"></td>
						</tr>
						<tr>
							<th>개봉연도</th>
							<td>
								<select name="releaseYear" class="form-control">
									<c:forEach var="i" begin="2000" end="${currentYear}">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>
							</td>
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
							<td><input type="number" class="form-control" id="rentalDuration" name="rentalDuration"></td>
						</tr>
						<tr>
							<th>대여 가격</th>
							<td><input type="number" step="0.01" class="form-control" id="rentalRate" name="rentalRate"></td>
						</tr>
						<tr>
							<th>길이</th>
							<td><input type="number" class="form-control" id="length" name="length"></td>
						</tr>
						<tr>
							<th>분실시 가격</th>
							<td><input type="number" step="0.01" class="form-control" id="replacementCost" name="replacementCost"></td>
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
								<input type="checkbox" name="specialFeatures" class="specialFeatures" value="Trailers">Trailers<br>
								<input type="checkbox" name="specialFeatures" class="specialFeatures" value="Commentaries">Commentaries<br>
								<input type="checkbox" name="specialFeatures" class="specialFeatures" value="Deleted Scenes">Deleted Scenes<br>
								<input type="checkbox" name="specialFeatures" class="specialFeatures" value="Behind the Scenes">Behind the Scenes
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-primary" type="button" id="addFilmBtn">등록</button>
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