package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.StaffService;
import sakila.service.StatsService;
import sakila.vo.Staff;
import sakila.vo.Stats;

@WebServlet({"/","/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private StatsService statsService;
	private StaffService staffService;
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
		HttpSession session = request.getSession();
		staffService = new StaffService();
		
		// 디버깅
		System.out.println(request.getParameter("email") + "<--request email");
		System.out.println(request.getParameter("pw") + "<--request pw");
		
		// 로그인 정보 받아오기, staff에 정보 저장
		Staff staff = new Staff();
		staff.setEmail(request.getParameter("email"));
		staff.setPassword(request.getParameter("pw"));
		
		// 서비스에서 인증 결과 받아오기
		Staff returnStaff = staffService.getStaffByKey(staff);
		
		// 로그인 실패 -> 로그인 페이지로 이동
		if(returnStaff == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			System.out.println("로그인 실패");
			
		// 로그인 성공 -> 세션에 아이디 저장, 메인 페이지로 이동
		}else {
			session.setAttribute("loginStaff", returnStaff);
			response.sendRedirect(request.getContextPath() + "/auth/IndexServlet");
			System.out.println(returnStaff.getStaffId() + "<--로그인 성공");
		}
	}

}
