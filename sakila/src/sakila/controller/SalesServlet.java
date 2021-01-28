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

import sakila.service.SalesService;
import sakila.vo.Staff;

@WebServlet("/auth/SalesServlet")
public class SalesServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Staff staff = new Staff();
		staff = (Staff)session.getAttribute("loginStaff");
		String manager = staff.getFirstName() + " " + staff.getLastName();
		
		SalesService salesService = new SalesService();
		Map<String, List<Map<String, Object>>> map = salesService.getSales(manager);
		List<Map<String, Object>> salesStore = map.get("salesStore");
		List<Map<String, Object>> salesCategory = map.get("salesCategory");
		
		
		request.setAttribute("salesStore", salesStore);
		request.setAttribute("salesCategory", salesCategory);
		request.getRequestDispatcher("/WEB-INF/views/auth/sales/sales.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
