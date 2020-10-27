<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<style>
	.align-center{text-align: center;}
	.align-left{text-align: left;}
	.align-right{text-align: right;}
	.vertical-middle{vertical-align: middle;}
	.vertical-top{vertical-align: top;}
	.vertical-bottom{vertical-align: bottom;}
	.color-black{color: black;}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<!-- 메뉴 -->
		<div class="col-sm-3" style="background-color:#F08080">
		<jsp:include page="/WEB-INF/views/inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- index -->
		<div class="col-sm-9">
			<div style="margin-top:30px"></div>
			<div>
				<h1>Index</h1>
				<hr>
			</div>
			<div>
				Sakila 프로젝트 설명
			</div>
		</div>
	</div>
</div>
</body>
</html>