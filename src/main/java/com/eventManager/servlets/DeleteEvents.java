package com.eventManager.servlets;

import java.io.IOException;

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
public class DeleteEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EVENTS_VIEW = "jsp/EventsView.jsp";
	private static final String PARAM_NAME_EVENT = "event";
	private static final String PARAM_ADRESS_EVENT = "adress";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ConnexionUtils.isSessionValid(request)) {
			HttpSession session;
			session = request.getSession(false);
			String userId = (String) session.getAttribute("user_id");
			String rowId = request.getParameter("id");
			EventsEntity event = new EventsEntity();
			String action;
			action = event.delete(Integer.parseInt(userId),Integer.parseInt(rowId));
			session.setAttribute("lastAction", action);
			response.sendRedirect("");
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
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

}
