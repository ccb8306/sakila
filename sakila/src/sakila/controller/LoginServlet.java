package sakila.controller;

import java.io.IOException;
import java.util.Map;

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
		// 로그인 정보가 세션에 있을 경우 -> 메인 페이지로 이동 
		// 로그인 정보가 세션에 없을경우 -> 로그인 페이지로 이동
		if(session.getAttribute("loginStaff") != null) {
			response.sendRedirect(request.getContextPath() + "/auth/IndexServlet");
			return;
		}
		
		// 오늘 접속자 수 폼에 넘겨주기
		statsService = new StatsService();
		Map<String, Object> map = statsService.getStats();
		Stats todayStats = (Stats) map.get("todayStats");
		int totalCnt = (Integer) map.get("totalCnt");
		request.setAttribute("todayStats", todayStats);
		request.setAttribute("totalCnt", totalCnt);
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
