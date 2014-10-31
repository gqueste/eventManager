package com.eventManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;
import com.eventManager.utils.ConnexionUtils;
import com.eventManager.utils.LastURLVisited;

/**
 * Servlet implementation class LogIn
 */
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERREUR_MAIL_INCORRECT = "Le mail entré est incorrect. ";
	private static final String ERREUR_PASSWORD_INCORRECT = "Le mot de passe est incorrect. ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
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

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gere la connexion
		//TODO revenir à la page précédente (le mettre dans session : paramètre fiasco)
		RequestDispatcher rd;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		boolean redirectionFaite = false;		
		
		if (ConnexionUtils.isSessionValid(request)) {
			redirectionFaite = true;
			response.sendRedirect(LastURLVisited.getInstance().getLastUrl());
		}
		else {
			//pas encore connecté
			rd = request.getRequestDispatcher(ConnexionUtils.LOGIN_VIEW);

			String paramMail = request.getParameter(ConnexionUtils.PARAM_MAIL);
			String paramPassword = request.getParameter(ConnexionUtils.PARAM_PASSWORD);
			String paramTentative = request.getParameter(ConnexionUtils.PARAM_TENTATIVE);
			
			String erreurs = ConnexionUtils.EMPTY_STRING;
			
			if (paramTentative != null) {
				//tentative de connexion
				if(paramMail.equals(ConnexionUtils.EMPTY_STRING) || paramPassword.equals(ConnexionUtils.EMPTY_STRING)) {
					//champ manquant
					if(paramMail.equals(ConnexionUtils.EMPTY_STRING)) {
						erreurs += ConnexionUtils.ERREUR_MAIL_MANQUANT;
					}
					else {
						//TODO mail existe ?
					}
					
					if(paramPassword.equals(ConnexionUtils.EMPTY_STRING)) {
						erreurs += ConnexionUtils.ERREUR_PASSWORD_MANQUANT;
					}
				}
				else{
					//vérification si données entrées sont bonnes	
					UsersPersistenceJPA em = new UsersPersistenceJPA();
					Map<String,Object> critere = new HashMap<String, Object>();
					critere.put("mail", paramMail);
					ArrayList<UsersEntity> listeUsers = (ArrayList<UsersEntity>) em.search(critere);
					if(listeUsers.size() == 0) {
						//Mail incorrect
						erreurs += ERREUR_MAIL_INCORRECT;
					}
					else {
						UsersEntity user = listeUsers.get(0);
						if(! user.getPassword().equals(paramPassword)) {
							//password incorrect
							erreurs += ERREUR_PASSWORD_INCORRECT;
						}
						else {
							//ConnectionOK
							session = request.getSession(true);
							session.setAttribute(ConnexionUtils.SESSION_USER_ATTRIBUTE, user.getUserId().toString());
							
							//redirection à l'adresse voulue
							redirectionFaite = true;
							response.sendRedirect(LastURLVisited.getInstance().getLastUrl());
						}
					}
				}
			}
			if (!redirectionFaite) {
				request.setAttribute(ConnexionUtils.PARAM_MAIL, paramMail);
				request.setAttribute(ConnexionUtils.PARAM_PASSWORD, paramPassword);
				request.setAttribute("noError", ConnexionUtils.EMPTY_STRING);
				request.setAttribute(ConnexionUtils.ERREURS_ID, erreurs);
				
				rd.forward(request, response);
			}
		}
	}
}
