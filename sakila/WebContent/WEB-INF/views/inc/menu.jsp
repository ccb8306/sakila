<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<!-- 로고 -->
	<div>
		<a href="${pageContext.request.contextPath}/auth/IndexServlet" class="color-title font-lotte-H fs-45 text-shadow">Sakila Movie</a>
	</div>
	
	<!-- 사용자 -->
	<div style="margin-top: 50px">
		<div class="row align-center">
			<div class="navbar-nav mr-auto" style="margin-left: 20px">
				<a href="${pageContext.request.contextPath}/auth/StaffOneServlet"><img src="${pageContext.request.contextPath}/images/${loginStaff.picture}" width="100px"></a>
			</div>
			<div class="align-right mr-right navbar-nav" style="margin-right: 20px; margin-top: 50px">
				<h5 class="font-lotte-H">${loginStaff.storeId} 지점 </h5>
				<h5 class="font-lotte-H">${loginStaff.username} 관리자님</h5>
			</div>
		</div>
	</div>
	
	<!-- 로그아웃 -->
	<div style="margin-top:20px">
		<a class="btn btn-block btn-outline-dark font-lotte-H" href="${pageContext.request.contextPath}/auth/LogoutServlet">Log-out</a>
	</div>
	
	<!-- 메뉴 -->
	<div style="margin-top:80px">
		<div>
			<h2 class="font-lotte-H">Menu</h2>
		</div>
		<div style="margin-top:30px">
			<a href="${pageContext.request.contextPath}/auth/IndexServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">HOME</a>
		</div>
		<hr>
		<div style="margin-top:10px">
			<a href="${pageContext.request.contextPath}/auth/FilmListServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">영화 대여/관리</a>	
		</div>
		<div style="margin-top:10px">
			<a href="${pageContext.request.contextPath}/auth/RentalListServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">영화 반납</a>
		</div>
		<hr>
		<div style="margin-top:10px">	
			<a href="${pageContext.request.contextPath}/auth/CustomerListServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">회원목록 관리</a>
		</div>
		<div style="margin-top:10px">
			<a href="${pageContext.request.contextPath}/auth/ActorListServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">영화배우 관리</a>	
		</div><hr>
		<div style="margin-top:10px">
			<a href="${pageContext.request.contextPath}/auth/SalesServlet" class="align-left btn btn-block color-black font-NotoSans fs-middle">매장 통계</a>	
		</div>
	</div>
</div>