<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<!-- 배우 정보 -->
			<div>
				<table class="table table-borderless">	
					<tr>
						<th style="width: 25%"><h2><br></h2></th>
						<td class="align-right"><a class="btn btn-outline-dark" href="">정보 수정</a></td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					<tr>
						<th>배우 ID : </th>
						<td>${actor.actorId}</td>
					</tr>
					<tr>
						<th>이름 : </th>
						<td>${actor.firstName} ${actor.lastName}</td>
					</tr>
					<tr>
						<th>출연 영화 : </th>
						<td>${actor.filmInfo}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="over align-center">
	<br><h2 class="font-lotte-H">배우 상세보기</h2>
</div>
</body>
</html>