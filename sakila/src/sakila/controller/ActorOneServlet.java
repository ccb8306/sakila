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

import sakila.service.ActorService;
import sakila.vo.Actor;

@WebServlet("/auth/ActorOneServlet")
public class ActorOneServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int actorId = Integer.parseInt(request.getParameter("actorId"));
		
		Actor actor = new Actor();
		// 서비스에서 맵 받아오기
		ActorService actorService = new ActorService();
		actor = actorService.getActorOne(actorId);
		
		// 뷰에 값들 보내주기
		request.setAttribute("actor", actor);
		request.getRequestDispatcher("/WEB-INF/views/auth/actor/actorOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
