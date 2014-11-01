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
import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet implementation class Events
 */
public class Events extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EVENTS_VIEW = "jsp/EventsView.jsp";
	private static final String PARAM_NAME_EVENT = "event";
	private static final String PARAM_ADRESS_EVENT = "adress";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Events() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ecran de gestion des evenements deja crees
		if (ConnexionUtils.isSessionValid(request)) {
			HttpSession session;
			session = request.getSession(false);
			String userId = (String) session.getAttribute("user_id");
			EventsEntity events = new EventsEntity();
			List<EventsEntity> listEventsCreated;
			listEventsCreated = events.getAllEventsCreated(userId);
			List<EventsEntity> listEventsParticipated;
			listEventsParticipated = events.getAllEventsParticipated(userId);
			for (EventsEntity e : listEventsParticipated){
				System.out.println(e.getName());
				System.out.println(e.getUrl());
			}
			request.setAttribute("lastAction", session.getAttribute("lastAction"));
			request.setAttribute("list", listEventsCreated);
			request.setAttribute("listInscription", listEventsParticipated);
			
			RequestDispatcher rd = request.getRequestDispatcher("jsp/EventsView.jsp");
			rd.forward(request, response);
		}
		else{
			response.sendRedirect("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		HttpSession session;
		session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(EVENTS_VIEW);
		String userId = (String) session.getAttribute("user_id");
		String nameEvent = request.getParameter(PARAM_NAME_EVENT);
		String adressEvent = request.getParameter(PARAM_ADRESS_EVENT);
		short published = 0;
		if(request.getParameter("published")!=null){
			published = 1;
		}
		String debut = request.getParameter("debut")+" "+request.getParameter("debutH");
		String fin = request.getParameter("fin")+" "+request.getParameter("finH");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    Date parsedDateDeb = dateFormat.parse(debut);
	    Date parsedDateFin = dateFormat.parse(fin);
	    Timestamp timestampDebut = new java.sql.Timestamp(parsedDateDeb.getTime());
	    Timestamp timestampFin = new java.sql.Timestamp(parsedDateFin.getTime());
		EventsEntity event = new EventsEntity();
		String action;
		action = event.add(userId, nameEvent, adressEvent, timestampDebut, timestampFin, published);
		session.setAttribute("lastAction", action);
		System.out.println(nameEvent);
		System.out.println(adressEvent);
		response.sendRedirect("");
	}

}
