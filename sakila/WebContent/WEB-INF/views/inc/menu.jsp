<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table table-borderless">
	<tr>
		<th><h1 class="align-center">Sakila Movie</h1></th>
	</tr>
	<tr>
		<th class="align-right">
			<p>${loginstaff.storeId} 지점 </p>
			<p>${loginstaff.username} 관리자님</p>
		</th>
	</tr>
	<tr>
		<th><a class="btn btn-block btn-outline-dark" href="${pageContext.request.contextPath}/auth/LogoutServlet">Log-out</a></th>
	</tr>
	
	<tr>
		<td><div style="margin-top:80px"></div></td>
	</tr>
	<tr>
		<th><h2>Menu</h2></th>
	</tr>
</table>