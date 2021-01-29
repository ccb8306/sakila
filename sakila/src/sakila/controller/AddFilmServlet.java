package sakila.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.FilmService;
import sakila.vo.Category;
import sakila.vo.Film;
import sakila.vo.Language;
import sakila.vo.Staff;

@WebServlet("/auth/AddFilmServlet")
public class AddFilmServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmService filmService = new FilmService();
		List<Language> languageList = filmService.getLanguageList();
		List<Category> categoryList = filmService.getCategoryList();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String currentYear = sdf.format(date);

		request.setAttribute("currentYear", currentYear);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("languageList", languageList);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/addFilm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		
		int storeId = staff.getStoreId();

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		// 영화 정보 받기 및 저장
		Film film = new Film();
		film.setTitle(request.getParameter("title"));
		film.setDescription(request.getParameter("description"));
		film.setReleaseYear(request.getParameter("releaseYear"));
		film.setLanguageId(Integer.parseInt(request.getParameter("languageId")));
		film.setRentalDuration(Integer.parseInt(request.getParameter("rentalDuration")));
		film.setRentalRate(Double.parseDouble(request.getParameter("rentalRate")));
		film.setLength(Integer.parseInt(request.getParameter("length")));
		film.setReplacementCost(Double.parseDouble(request.getParameter("replacementCost")));
		film.setRating(request.getParameter("rating"));
		
		// 배열 형식으로 받아오는 값 정리
		if(request.getParameterValues("specialFeatures") != null) {
			String tempSpecialFeatures[] = request.getParameterValues("specialFeatures");
			String specialFeatures = "";
			if(tempSpecialFeatures.length > 1) {
				for(int i = 0; i < tempSpecialFeatures.length - 1; i++) {
					specialFeatures += (tempSpecialFeatures[i] + ",");		
				}
			}
			specialFeatures += tempSpecialFeatures[tempSpecialFeatures.length - 1];
			
			film.setSpecialFeatures(specialFeatures);
		}
		
		FilmService filmService = new FilmService();
		filmService.addFilm(film, storeId, categoryId);
		
		response.sendRedirect(request.getContextPath() + "/auth/FilmListServlet");
	}

}
