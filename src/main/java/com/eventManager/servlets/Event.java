package com.eventManager.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.bean.jpa.InscriptionsEntity;
import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.beanServices.InscriptionsService;
import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet implementation class Event
 * Contrôle pour l'afficha de la page d'inscription à un event
 */
public class Event extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = "-1";
		EventsEntity event = (EventsEntity) request.getAttribute("event");
		UsersEntity users = new UsersEntity();
		HttpSession session;
		session = request.getSession(false);
		InscriptionsService inscriptionService = new InscriptionsService();
		List<InscriptionsEntity> inscriptionsList = inscriptionService.getInscriptions(event);
		if (ConnexionUtils.isSessionValid(request)) {
			userId = (String) session.getAttribute("user_id");
		}
		users = event.getUsers();
		request.setAttribute("event", event);
		request.setAttribute("users", users);
		request.setAttribute("inscriptions", inscriptionsList);
		request.setAttribute("userId", userId);
		request.setAttribute("lastAction", session.getAttribute(ConnexionUtils.SESSION_LAST_ACTION));
		RequestDispatcher rd = request
				.getRequestDispatcher("/jsp/EventView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
