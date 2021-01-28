package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.service.ActorService;
import sakila.vo.Actor;

@WebServlet("/auth/AddActorServlet")
public class AddActorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/auth/actor/addActor.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		ActorService actorService = new ActorService();
		actorService.addActor(actor);
		
		response.sendRedirect(request.getContextPath() + "/auth/ActorListServlet");
	}

}
