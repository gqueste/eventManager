package com.eventManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogIn
 */
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
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

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gere la connexion et l enregistrement d un nouvel entrant
		RequestDispatcher rd;
		HttpSession session = request.getSession(false);
		if (isSessionValid(session)) {
			response.sendRedirect("");
		}
		else {
			//pas encore connecté
			rd = request.getRequestDispatcher("jsp/LogInView.jsp");

			String paramMail = request.getParameter("mail");
			System.out.println(paramMail);
			String paramPassword = request.getParameter("password");
			String paramTentative = request.getParameter("tentative");
			System.out.println(paramTentative);
			
			String noError = "";
			String erreurs = "";
			
			if (paramTentative != null) {
				//tentative de connexion
				if(paramMail.equals("") || paramPassword.equals("")) {
					//champ manquant
					if(paramMail.equals("")) {
						erreurs += "Entrez votre adresse mail pour vous connecter. ";
					}
					else {
						//TODO mail existe ?
					}
					
					if(paramPassword.equals("")) {
						erreurs += "Entrez votre mot de passe pour vous connecter.";
					}
				}
				else{
					//vérification si données entrées sont bonnes
				}
			}
			
			request.setAttribute("noError", noError);
			request.setAttribute("erreurs", erreurs);
			
			rd.forward(request, response);
		}
	}

	private boolean isSessionValid(HttpSession session) {
		boolean ret = false;
		if(session != null) {
			String idUtilisateur = (String) session.getAttribute("id_user");
			if(idUtilisateur != null) {
				ret = true;
			}
		}
		return ret;
	}
}
