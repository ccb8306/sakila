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

import sakila.dao.RentalDao;
import sakila.service.CustomerService;
import sakila.service.RentalService;
import sakila.vo.CustomerList;
import sakila.vo.Rental;
import sakila.vo.Staff;

@WebServlet("/auth/FilmCustomerListServlet")
public class FilmCustomerListServlet extends HttpServlet {
	// 회원 목록
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
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		// 세션 불러와서 저장
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		
		// 서비스에서 맵 받아오기
		Map<String, Object> map = new HashMap<String, Object>();
		CustomerService customerService = new CustomerService();
		map = customerService.getCustomerList(staff, currentPage, rowPage);
		
		// 맵에서 데이터 추출
		List<CustomerList> list = new ArrayList<CustomerList>();
		list = (List<CustomerList>)map.get("list");
		endPage = (Integer)map.get("endPage");
		
		// 뷰에 값들 보내주기
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("inventoryId", inventoryId);
		request.getRequestDispatcher("/WEB-INF/views/auth/film/filmCustomerList.jsp").forward(request, response);
	}
	// 영화 대여하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		int staffId = staff.getStaffId();
		
		Rental rental = new Rental();
		rental.setCusotomerId(customerId);
		rental.setInventoryId(inventoryId);
		rental.setStaffId(staffId);
		
		RentalService rentalService = new RentalService();
		rentalService.addRental(rental);
		
		response.sendRedirect(request.getContextPath() + "/auth/RentalListServlet");
	}

}
