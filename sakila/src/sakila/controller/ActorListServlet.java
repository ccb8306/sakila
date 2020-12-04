package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.service.ActorService;
import sakila.vo.Actor;

@WebServlet("/auth/ActorListServlet")
public class ActorListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 변수들
		int currentPage = 1;
		int rowPage = 10;
		int endPage = 0;
		
		// 현재 페이지 받기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 서비스에서 맵 받아오기
		Map<String, Object> map = new HashMap<String, Object>();
		ActorService actorService = new ActorService();
		map = actorService.getActorList(currentPage, rowPage);
		
		// 맵에서 데이터 추출
		List<Actor> list = new ArrayList<Actor>();
		list = (List<Actor>)map.get("list");
		endPage = (Integer)map.get("endPage");
		
		// 뷰에 값들 보내주기
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/WEB-INF/views/auth/actor/actorList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
