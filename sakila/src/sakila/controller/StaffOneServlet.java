package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.StaffService;
import sakila.vo.*;

@WebServlet("/auth/StaffOneServlet")
public class StaffOneServlet extends HttpServlet {
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Staff staff = new Staff();
		staff = (Staff) session.getAttribute("loginStaff");
		StaffService staffService = new StaffService();
		StaffAndStaffList sasl = new StaffAndStaffList();
		sasl = staffService.getStaffOne(staff);
		
		request.setAttribute("sasl", sasl);
		request.setAttribute("loginStaff", session.getAttribute("loginStaff"));
		request.getRequestDispatcher("/WEB-INF/views/auth/staffOne.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
