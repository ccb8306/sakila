package sakila.controller;

import java.io.IOException;
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
import sakila.vo.Category;
import sakila.vo.FIlmAndCategoryAndLanguage;
import sakila.vo.Film;
import sakila.vo.Language;
import sakila.vo.Rental;
import sakila.vo.Staff;

@WebServlet("/auth/ModifyFilmServlet")
public class ModifyFilmServlet extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		int storeId = staff.getStoreId();
		FilmService filmService = new FilmService();
		// 맵 가져오기
		Map<String, Object> map =  filmService.getFilmOne(storeId, filmId);
		// 영화 상세 정보 가져오기
		FIlmAndCategoryAndLanguage facal = (FIlmAndCategoryAndLanguage) map.get("facal");

		List<Language> languageList = filmService.getLanguageList();
		List<Category> categoryList = filmService.getCategoryList();
		
		// 뷰 연결
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("languageList", languageList);
		request.setAttribute("facal",facal);
		request.setAttribute("filmId",filmId);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/modifyFilm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		// 영화 정보 받기 및 저장
		Film film = new Film();
		film.setFilmId(Integer.parseInt(request.getParameter("filmId")));
		film.setTitle(request.getParameter("title"));
		film.setDescription(request.getParameter("description"));
		film.setReleaseYear(request.getParameter("releaseYear"));
		film.setLanguageId(Integer.parseInt(request.getParameter("languageId")));
		film.setRentalDuration(Integer.parseInt(request.getParameter("rentalDuration")));
		film.setRentalRate(Double.parseDouble(request.getParameter("rentalRate")));
		film.setLength(Integer.parseInt(request.getParameter("length")));
		film.setReplacementCost(Double.parseDouble(request.getParameter("replacementCost")));
		film.setRating(request.getParameter("rating"));
		
		FilmService filmService = new FilmService();
		filmService.modifyFilm(film, categoryId);
		
		response.sendRedirect(request.getContextPath() + "/auth/FilmOneServlet?filmId=" + film.getFilmId());
	}

}
