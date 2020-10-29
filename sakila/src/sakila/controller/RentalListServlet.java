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

import sakila.service.RentalService;
import sakila.vo.*;

@WebServlet("/auth/RentalListServlet")
public class RentalListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int currentPage = 1;
		int rowPage = 10;
		int endPage = 1;
		
		// 페이지를 받아옴
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println(request.getParameter("currentPage") + "<--rentalList request currentPage");
		}
		System.out.println(currentPage + "<--rentalList currentPage");
		
		// 세션에서 상점 id가져오기
		Staff staff = (Staff)session.getAttribute("loginStaff");
		int storeId = staff.getStoreId();
		
		// 해당 상점의 대여 리스트와 최대 페이지 구하기
		RentalService rentalService = new RentalService();
		Map<String, Object> map = new HashMap<String, Object>();
		map = rentalService.getRentalList(storeId, currentPage, rowPage);
				
		List<RentalAndFilm> rentalList = new ArrayList<RentalAndFilm>();	
		rentalList = (List<RentalAndFilm>) map.get("list");
		endPage = (Integer)map.get("endPage");
		
		// 대여 리스트를 뷰에 보내줌
		request.setAttribute("rentalList", rentalList);
		// 현재 페이지를 보냄
		request.setAttribute("currentPage", currentPage);
		// 최대 페이지를 보냄
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/views/auth/rental/rentalList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
