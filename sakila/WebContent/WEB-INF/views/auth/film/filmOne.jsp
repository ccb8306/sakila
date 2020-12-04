<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="/sakila/sakila.css" rel="stylesheet" type="text/css" />
</head>
<body class="body-main">
<div class="container-fluid wrap pt-3">
	<div class="row">
		<!-- 메뉴 -->
		<div class="col-sm-3">
		<jsp:include page="/WEB-INF/views/inc/menu.jsp"></jsp:include>
		</div>
		
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<!-- 영화 정보 -->
			<div>
				<table class="table table-borderless">	
					<tr>
						<th style="width: 25%"><h2><br></h2></th>
						<td class="align-right"><a class="btn btn-outline-dark" href="">영화 정보 수정</a></td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<th>영화 ID : </th>
						<td>${facal.film.filmId}</td>
					</tr>
					<tr>
						<th>카테고리 : </th>
						<td>${facal.category.name }</td>
					</tr>
					<tr>
						<th>제목 : </th>
						<td>${facal.film.title }</td>
					</tr>
					<tr>
						<th>내용 : </th>
						<td>${facal.film.description}</td>
					</tr>
					<tr>
						<th>언어 : </th>
						<td>${facal.language.name }</td>
					</tr>
					<tr>
						<th>개봉 연도 : </th>
						<td>${facal.film.releaseYear}</td>
					</tr>
					<tr>
						<th>대여 가능 기간 : </th>
						<td>${facal.film.rentalDuration}</td>
					</tr>
					<tr>
						<th>대여 금액 : </th>
						<td>${facal.film.rentalRate}</td>
					</tr>
					<tr>
						<th>영화 길이 : </th>
						<td>${facal.film.length}</td>
					</tr>
					
					<tr>
						<th>시청 등급 : </th>
						<td>${facal.film.rating}</td>
					</tr>
					<tr>
						<th>특징 : </th>
						<td>${facal.film.specialFeatures}</td>
					</tr>
					<tr>
						<th>분실시 가격 : </th>
						<td>${facal.film.replacementCost}</td>
					</tr>
				</table>
			</div>
			<hr>
			
			<!-- 영화 출연 배우 -->
			<div>
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th colspan="3">출연 배우</th>
						</tr>
					</thead>
					<tr>
						<c:set var="actorCnt" value="0" />
						<c:forEach var="a" items="${actorList}">
							<c:set var="actorCnt" value="${actorCnt+1}" />
							
							<td><a href="${pageContext.request.contextPath}/auth/ActorOneServlet?actorId=${a.actorId}">${a.firstName } ${a.lastName }</a></td>
							
							<c:if test="${actorCnt%3 == 0 }">
								</tr><tr>
							</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="over align-center">
	<br><h2 class="font-lotte-H">영화 상세보기</h2>
</div>