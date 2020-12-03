package sakila.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.FilmService;
import sakila.vo.*;

@WebServlet("/auth/FilmListServlet")
public class FilmListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		int rowPage = 10;
		int endPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		HttpSession session = request.getSession();
		
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		
		// 영화 목록 불러오기
		List<FilmList> filmList = new ArrayList<FilmList>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		FilmService filmService = new FilmService();
		map = filmService.getFilmList(staff, currentPage, rowPage);
		
		filmList = (List<FilmList>) map.get("filmList");
		endPage = (int)map.get("endPage");
		
		
		// 리스트와 마이막 페이지를 뷰에 보내줌
		request.setAttribute("filmList", filmList);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
