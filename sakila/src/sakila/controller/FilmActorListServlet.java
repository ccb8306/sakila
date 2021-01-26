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

@WebServlet("/auth/FilmActorListServlet")
public class FilmActorListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
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
		request.setAttribute("filmId", filmId);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmActorList.jsp").forward(request, response);
	}

	// 영화에 출연 배우 추가하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		int actorId = Integer.parseInt(request.getParameter("actorId"));
		ActorService actorService = new ActorService();
		actorService.addFilmActor(actorId, filmId);
		
		response.sendRedirect(request.getContextPath() + "/auth/FilmOneServlet?filmId=" + filmId);
	}

}
