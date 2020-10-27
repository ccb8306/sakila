<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<!-- 로고 -->
	<div>
		<h1 class="align-center"><a href="${pageContext.request.contextPath}/auth/IndexServlet" class="color-black">Sakila Movie</a></h1>
	</div>
	<!-- 사용자 -->
	<div style="margin-top: 50px">
		<div class="row align-center">
			<div class="navbar-nav mr-auto" style="margin-left: 20px">
				<img src="${pageContext.request.contextPath}/images/${loginstaff.picture}" width="100px">
			</div>
			<div class="align-right mr-right navbar-nav" style="margin-right: 20px; margin-top: 50px">
				<h5>${loginstaff.storeId} 지점 </h5>
				<h5>${loginstaff.username} 관리자님</h5>
			</div>
		</div>
	</div>
	<!-- 로그아웃 -->
	<div style="margin-top:20px">
		<a class="btn btn-block btn-outline-dark" href="${pageContext.request.contextPath}/auth/LogoutServlet">Log-out</a>
	</div>
	<!-- 메뉴 -->
	<div style="margin-top:80px">
		<div>
			<h2>Menu</h2>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/auth/IndexServlet" class="color-black">HOME</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/auth/RentalListServlet" class="color-black">영화 반납</a>
		</div>
		<hr>
		<div>
			<a href="" class="color-black">회원목록 관리</a>
		</div>
	</div>
</div>