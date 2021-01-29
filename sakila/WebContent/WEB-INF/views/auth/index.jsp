<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
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
		
		<!-- index -->
		<div class="col-sm-9 bg-white mt-5 mb-5">
			<div class="mt-3">
				<h2><br></h2>
				<hr>
			</div>
			<div>
				<h4>[프로젝트 설명]</h4>
			</div>
			<div>
				<div class="mt-3"></div>
				<p><h5>Model2 기법</h5></p>
				<p>▶ Model, View, Controller로 구조가 나뉘어져있습니다.</p>
				<p>▶ 패키지는 Controller, Service, Dao, Vo로 나누어 관리합니다.</p>
				<p>Vo : 로직을 갖고있지 않은 순수한 데이터 객체들을 모아 관리하며 접근하기 위한 getter와 setter 메소드만 가진 클래스들을 모은 패키지입니다.</p>
				<p>Dao : 데이터베이스에 접근하는 클래스들을 한 패키지에 모아 관리합니다.</p>
				<p>Controller : Model과 뷰를 연결하기 위한 클래스들을 모아놓은 패키지입니다.</p>
				<p>Service : Controller와 Dao를 연결하기 위한 클래스를 모아놓은 패키지 입니다.</p>
				
			</div>
			<div>
				<div class="mt-3"></div>
				<p><h5>Sakila Project</h5></p>
				<p>▶ Mysql Sample Database인 Saklia DB를 이용하여 제작한 웹페이지.</p>
				<p>▶ 주로 비디오 대여,반납,관리 등의 역할을 위한 프로젝트이며 회원과 비디오 출연 배우들도 관리할 수 있습니다.</p>
			</div>
		</div>
	</div>
</div>
<div class="over align-center">
	<br><h1 class="font-lotte-H">Index</h1>
</div>
</body>
</html>