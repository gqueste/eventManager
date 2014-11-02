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
import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet implementation class PublishEvent
 */
public class AddInscriptionEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddInscriptionEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lastAction = "";
		String eventId = request.getParameter("eventId");
		InscriptionsEntity inscription = new InscriptionsEntity();
		EventsEntity events = new EventsEntity();
		EventsEntity event = events.getEvent(eventId);
		RequestDispatcher rd;
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("event", event);
		UsersEntity users = new UsersEntity();
		if (ConnexionUtils.isSessionValid(request)) {
			HttpSession session;
			session = request.getSession(false);
			String userId = (String) session.getAttribute("user_id");
			UsersEntity user = users.getUser(userId);
			lastAction = inscription.add(user.getName(), user.getSurname(),
					eventId, user.getMail(), user.getCompany());
			request.setAttribute("lastActionEvent", lastAction);
			rd = request.getRequestDispatcher("event/" + event.getUrl());
			rd.forward(request, response);
		} else {
			String name = request.getParameter("inscriptionUserName");
			String surname = request.getParameter("inscriptionUserSurname");
			String mail = request.getParameter("inscriptionUserMail");
			String societe = request.getParameter("inscriptionUserSociete");
			List<UsersEntity> usersList = users.getUsersByMail(mail);
			if (usersList.size() > 0)
				lastAction = "userDisconnect";
			else 
				lastAction = inscription.add(name, surname, eventId, mail, societe);
			request.setAttribute("lastActionEvent", lastAction);
			rd = request.getRequestDispatcher("event/" + event.getUrl());
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
