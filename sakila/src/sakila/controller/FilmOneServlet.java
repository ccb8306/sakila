package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.service.FilmService;
import sakila.vo.FIlmAndCategoryAndLanguage;

@WebServlet("/auth/FilmOneServlet")
public class FilmOneServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		// 영화 상세 정보 가져오기
		FilmService filmService = new FilmService();
		FIlmAndCategoryAndLanguage facal = new FIlmAndCategoryAndLanguage();
		facal = filmService.getFilmOne(filmId);
		
		// 뷰 연결
		request.setAttribute("facal",facal);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmOne.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
