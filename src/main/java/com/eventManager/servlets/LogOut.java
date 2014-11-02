package com.eventManager.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet implementation class LogOut
 * Contrôle de la déconnexion d'un utilisateur
 */
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOut() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Gere la deconnexion de l'utilisateur
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(ConnexionUtils.isSessionValid(request)) {
			session.invalidate();
			System.out.println("Session détruite");
		}
		response.sendRedirect(ConnexionUtils.getLastUrlVisited(request));
	}

}
