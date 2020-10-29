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

@WebServlet("/auth/CustomerRentalListServlet")
public class CustomerRentalListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		System.out.println(customerId + "<--CustomerRentalListServlet customerId");
		
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalService rentalService = new RentalService();
		list = rentalService.getCustomerRentalList(customerId);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/auth/rental/customerRentalList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
