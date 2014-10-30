package com.eventManager.servlets;

import java.io.IOException;
import java.util.List;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (ConnexionUtils.isSessionValid(request)) {
			HttpSession session;
			session = request.getSession(false);
			String userId = (String) session.getAttribute("user_id");
			String rowId = request.getParameter("eventId");
			EventsEntity event = new EventsEntity();
			List<EventsEntity> listEvents = event.getEvent(rowId);
			InscriptionsEntity inscription = new InscriptionsEntity();
			UsersEntity users = new UsersEntity();
			List<UsersEntity> listUsers = users.getUser(userId);
			if (listUsers.size() == 1) {
				inscription.setName(listUsers.get(0).getName());
				inscription.setSurname(listUsers.get(0).getSurname());
				inscription.setMail(listUsers.get(0).getMail());
				if (listEvents.size() == 1)
					inscription.setEvents(listEvents.get(0));
//				event.addInscription(inscription);
			}
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

}
