package com.eventManager.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.beanServices.EventsServices;
import com.eventManager.beanServices.InscriptionsService;
import com.eventManager.beanServices.UsersService;
import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet implementation class AddInscriptionEvent
 * Contrôleur pour l'inscription à un événement
 */
public class AddInscriptionEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddInscriptionEvent() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String lastAction = "";
		HttpSession session;
		session = request.getSession(false);
		UsersService userService = new UsersService();
		InscriptionsService inscriptionService = new InscriptionsService();
		String eventId = request.getParameter("eventId");
		EventsServices events = new EventsServices();
		EventsEntity event = events.getEvent(eventId);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("event", event);
		if (ConnexionUtils.isSessionValid(request)) {
			String userId = (String) session.getAttribute("user_id");
			UsersEntity user = userService.getUser(userId);
			lastAction = inscriptionService.add(user.getName(), user.getSurname(),
					eventId, user.getMail(), user.getCompany());
			session.setAttribute("lastAction", lastAction);
			response.sendRedirect(ConnexionUtils.getLastUrlVisited(request));
		} 
		else {
			String name = request.getParameter("inscriptionUserName");
			String surname = request.getParameter("inscriptionUserSurname");
			String mail = request.getParameter("inscriptionUserMail");
			String societe = request.getParameter("inscriptionUserSociete");
			List<UsersEntity> usersList = userService.getUsersByMail(mail);
			if (usersList.size() > 0)
				lastAction = "userDisconnect";
			else 
				lastAction = inscriptionService.add(name, surname, eventId, mail, societe);
			session.setAttribute("lastAction", lastAction);
			response.sendRedirect(ConnexionUtils.getLastUrlVisited(request));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
