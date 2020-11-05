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
import javax.servlet.http.HttpSession;

import sakila.service.FilmStockService;
import sakila.vo.FilmList;
import sakila.vo.Staff;

@WebServlet("/auth/FilmStockListServlet")
public class FilmStockListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 페이징 변수들
		int currentPage = 1;
		int rowPage = 10;
		int endPage = 0;
		
		// 현재 페이지 받기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 세션 불러와서 저장
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		
		List<FilmList> filmList = new ArrayList<FilmList>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		FilmStockService filmStockService = new FilmStockService();
		map = filmStockService.getFilmStockList(staff, currentPage, rowPage);
		
		filmList = (List<FilmList>) map.get("filmList");
		endPage = (int)map.get("endPage");
		
		// 뷰 연결
		request.setAttribute("filmList", filmList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/views/auth/filmStock/filmStockList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
