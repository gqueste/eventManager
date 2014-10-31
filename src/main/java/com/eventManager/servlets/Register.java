package com.eventManager.servlets;

import java.io.IOException;

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
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERREUR_NOM_MANQUANT= "Entrez votre nom. ";
	private static final String ERREUR_PRENOM_MANQUANT = "Entrez votre prénom. ";
	private static final String ERREUR_CONFIRMATION_MANQUANTE = "Confirmez votre password. ";
	private static final String ERREUR_MAIL_DEJA_UTILISE = "Ce mail est déjà utilisé. ";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		RequestDispatcher rd;
		request.setCharacterEncoding("UTF-8");
		boolean redirectionFaite = false;
		if (ConnexionUtils.isSessionValid(request)) {
			redirectionFaite = true;
			response.sendRedirect(LastURLVisited.getInstance().getLastUrl());
		}else {
			rd = request.getRequestDispatcher(ConnexionUtils.REGISTER_VIEW);

			String paramTentative = request.getParameter(ConnexionUtils.PARAM_TENTATIVE);
			String paramMail = request.getParameter(ConnexionUtils.PARAM_MAIL);
			String paramName = request.getParameter(ConnexionUtils.PARAM_NAME);
			String paramSurname = request.getParameter(ConnexionUtils.PARAM_SURNAME);
			String paramCompany = request.getParameter(ConnexionUtils.PARAM_COMPANY);
			String paramPassword = request.getParameter(ConnexionUtils.PARAM_PASSWORD);
			String paramPasswordConfirm = request.getParameter(ConnexionUtils.PARAM_CONFIRMPASSWORD);

			String erreurs = ConnexionUtils.EMPTY_STRING;

			if(paramTentative != null) {
				//tentative d'inscription

				//gestion des champs manquant
				if(paramMail.equals(ConnexionUtils.EMPTY_STRING)) {
					erreurs += ConnexionUtils.ERREUR_MAIL_MANQUANT;
				}
				if(paramName.equals(ConnexionUtils.EMPTY_STRING)) {
					erreurs += ERREUR_NOM_MANQUANT;
				}
				if(paramSurname.equals(ConnexionUtils.EMPTY_STRING)) {
					erreurs += ERREUR_PRENOM_MANQUANT;
				}
				if(paramPassword.equals(ConnexionUtils.EMPTY_STRING)) {
					erreurs += ConnexionUtils.ERREUR_PASSWORD_MANQUANT;
				}
				if(paramPasswordConfirm.equals(ConnexionUtils.EMPTY_STRING) || ! paramPassword.equals(paramPasswordConfirm)) {
					erreurs += ERREUR_CONFIRMATION_MANQUANTE;
				}

				if(erreurs.equals(ConnexionUtils.EMPTY_STRING)) {
					//pas de champ manquant
					//vérification si données entrées sont bonnes	
					UsersPersistenceJPA em = new UsersPersistenceJPA();

					UsersEntity user = new UsersEntity(paramMail, paramName, paramSurname, paramPassword, paramCompany);
					int user_id = em.insertAndGetID(user);
					if(user_id == -1) {
						erreurs += ERREUR_MAIL_DEJA_UTILISE;
					}
					else {
						//insertion ok
						HttpSession session = request.getSession(true);
						session.setAttribute(ConnexionUtils.SESSION_USER_ATTRIBUTE, ((Integer)user_id).toString());
						
						//redirection à l'adresse voulue
						redirectionFaite = true;
						response.sendRedirect(LastURLVisited.getInstance().getLastUrl());
					}
				}
			}

			if (!redirectionFaite) {
				request.setAttribute(ConnexionUtils.PARAM_MAIL, paramMail);
				request.setAttribute(ConnexionUtils.PARAM_PASSWORD, paramPassword);
				request.setAttribute(ConnexionUtils.PARAM_NAME, paramName);
				request.setAttribute(ConnexionUtils.PARAM_SURNAME, paramSurname);
				request.setAttribute(ConnexionUtils.PARAM_COMPANY, paramCompany);
				request.setAttribute(ConnexionUtils.PARAM_CONFIRMPASSWORD, ConnexionUtils.EMPTY_STRING);
				request.setAttribute("noError", ConnexionUtils.EMPTY_STRING);
				request.setAttribute(ConnexionUtils.ERREURS_ID, erreurs);

				rd.forward(request, response);
			}			
		}
	}

}
