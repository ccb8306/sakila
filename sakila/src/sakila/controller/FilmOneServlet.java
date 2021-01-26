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
import javax.servlet.http.HttpSession;

import sakila.service.FilmService;
import sakila.vo.Actor;
import sakila.vo.FIlmAndCategoryAndLanguage;
import sakila.vo.Rental;
import sakila.vo.Staff;

@WebServlet("/auth/FilmOneServlet")
public class FilmOneServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
	
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		int storeId = staff.getStoreId();
		FilmService filmService = new FilmService();
		// 맵 가져오기
		Map<String, Object> map =  filmService.getFilmOne(storeId, filmId);

		// 영화 배우들 가져오기
		List<Actor> actorList = (List<Actor>) map.get("actorList");
		
		// 재고 목록 가져오기
		List<Rental> stockList = (List<Rental>) map.get("stockList");
		
		// 영화 상세 정보 가져오기
		FIlmAndCategoryAndLanguage facal = (FIlmAndCategoryAndLanguage) map.get("facal");
		
		// 뷰 연결
		request.setAttribute("stockList",stockList);
		request.setAttribute("actorList",actorList);
		request.setAttribute("facal",facal);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmOne.jsp").forward(request, response);
	}

	// 재고 추가하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		int storeId = staff.getStoreId();
		FilmService filmService = new FilmService();
		filmService.addInventory(filmId, storeId);
		
		response.sendRedirect(request.getContextPath() + "/auth/FilmOneServlet?filmId=" + filmId);
	}

}
