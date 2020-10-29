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
<link href="/sakila/sakila.css" rel="stylesheet" type="text/css" />
</head>
<body class="bg-white">
<div class="container-fluid bg-gray">
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th>대여 번호</th>
				<th>영화 이름</th>
				<th>대여일</th>
				<th>반납일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.rental.rentalId}</td>
					<td>${item.film.title}</td>
					<td>${item.rental.rentalDate}</td>
					<td>${item.rental.returnDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>