package com.eventManager.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class Event
 */
public class Event extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event() {
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
		String userId = "-1";
		if (ConnexionUtils.isSessionValid(request)) {
			HttpSession session;
			session = request.getSession(false);
			userId = (String) session.getAttribute("user_id");
		}
//		EventsEntity events = new EventsEntity();
		EventsEntity event = (EventsEntity) request.getAttribute("event");
//		String id = request.getPathInfo();
//		String[] idsplit = id.split("/");
//		String eventId = "-1";
//		if (idsplit.length == 2)
//			if (!idsplit[1].equals("*"))
//				eventId = idsplit[1];
//		List<EventsEntity> listEvents = events.getEvent(eventId);
		UsersEntity users = new UsersEntity();
		List<InscriptionsEntity> inscriptions = null;
//		if (listEvents.size() == 0 || listEvents.size() > 1) {
//			eventId = "-1";
			inscriptions = event.getListOfInscriptions();
//		}
//		else {
			users = event.getUsers();
			request.setAttribute("event", event);
			request.setAttribute("users", users);
			request.setAttribute("inscriptions", inscriptions);
			request.setAttribute("userId", userId);
//		}
//		if (eventId.equals("-1")) {
//			RequestDispatcher rd = request
//					.getRequestDispatcher("/jsp/error_404.jsp");
//			rd.forward(request, response);
//		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/EventView.jsp");
			rd.forward(request, response);
//		}
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
