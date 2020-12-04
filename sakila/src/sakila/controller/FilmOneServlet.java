package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.service.FilmService;
import sakila.vo.Actor;
import sakila.vo.FIlmAndCategoryAndLanguage;

@WebServlet("/auth/FilmOneServlet")
public class FilmOneServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));

		FilmService filmService = new FilmService();
		// 맵 가져오기
		Map<String, Object> map =  filmService.getFilmOne(filmId);
		
		// 영화 배우들 가져오기
		List<Actor> actorList = (List<Actor>) map.get("actorList");
		
		// 영화 상세 정보 가져오기
		FIlmAndCategoryAndLanguage facal = (FIlmAndCategoryAndLanguage) map.get("facal");
		
		// 뷰 연결
		request.setAttribute("actorList",actorList);
		request.setAttribute("facal",facal);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmOne.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
