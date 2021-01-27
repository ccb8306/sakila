package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.CustomerService;
import sakila.vo.CustomerAndCustomerList;
import sakila.vo.CustomerList;

@WebServlet("/auth/CustomerOneServlet")
public class CustomerOneServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		System.out.println(customerId + "<-- customerOne customerId");
		
		CustomerService customerService = new CustomerService();
		Map<String, Object> map = new HashMap<String, Object>();
		map = customerService.getCustomerOne(customerId);
		CustomerAndCustomerList cacl = new CustomerAndCustomerList();
		cacl = (CustomerAndCustomerList) map.get("cacl");
		
		request.setAttribute("cacl", cacl);
		request.getRequestDispatcher("/WEB-INF/views/auth/customer/customerOne.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String notes = request.getParameter("notes");
		int active = 0;
		if(notes.equals("active")) {
			active = 0;
		} else {
			active = 1;
		}
		CustomerService customerService = new CustomerService();
		customerService.modifyCustomerActive(id, active);
		
		response.sendRedirect(request.getContextPath() + "/auth/CustomerOneServlet?customerId=" + id);
	}

}
