package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.StatsService;
import sakila.vo.Stats;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private StatsService statsService;
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginStaff") != null) {
			response.sendRedirect(request.getContextPath() + "/auth/IndexServlet");
			return;
		}
		// 오늘 접속자 수 출력
		statsService = new StatsService();
		Stats stats = statsService.getStats();
		request.setAttribute("stats", stats);
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
