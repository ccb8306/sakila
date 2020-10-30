package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.service.RentalService;
import sakila.vo.RentalAndFilm;

@WebServlet("/auth/CustomerOverDueListServlet")
public class CustomerOverDueListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		// 연체 리스트 불러오기
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalService rentalService = new RentalService();
		list = rentalService.getCustomerOverDueList(customerId);
		System.out.println(list + "<--CustomerOverDueListServlet list");
		// 뷰에 연체리스트 전달
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/auth/rental/customerOverDueList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
