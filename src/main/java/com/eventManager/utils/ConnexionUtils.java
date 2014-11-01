package com.eventManager.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnexionUtils {
	
	public static final String SESSION_USER_ATTRIBUTE = "user_id";
	public static final String SESSION_LAST_URL = "lastUrl";
	public static final String SESSION_LAST_ACTION = "lastAction";
	
	public static final String LOGIN_VIEW = "jsp/LogInView.jsp";
	public static final String REGISTER_VIEW = "jsp/RegisterView.jsp";	
	
	public static final String PARAM_MAIL = "mail";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_TENTATIVE = "tentative";
	public static final String EMPTY_STRING = "";
	public static final String ERREURS_ID = "erreurs";
	public static final String ERREUR_MAIL_MANQUANT = "Entrez votre adresse mail. ";
	public static final String ERREUR_PASSWORD_MANQUANT = "Entrez votre mot de passe. ";

	public static final String PARAM_NAME = "name";

	public static final String PARAM_SURNAME = "surname";

	public static final String PARAM_COMPANY = "company";

	public static final String PARAM_CONFIRMPASSWORD = "confirm-password";
	
	
	public static boolean isSessionValid(HttpServletRequest request) {
		boolean ret = false;
		HttpSession session = request.getSession();
		if(session != null) {
			String idUtilisateur = (String) session.getAttribute(SESSION_USER_ATTRIBUTE);
			if(idUtilisateur != null) {
				System.out.println(SESSION_USER_ATTRIBUTE + " : "+idUtilisateur);
				ret = true;
			}
		}
		return ret;
	}
	
	public static String getLastUrlVisited(HttpServletRequest request) {
		String ret = "";
		HttpSession session = request.getSession();
		if(session != null) {
			String lastUrl = (String) session.getAttribute(SESSION_LAST_URL);
			if(lastUrl != null) {
				ret = lastUrl;
			}
		}
		return ret;
	}
	
	public static void setLastUrlVisited(HttpServletRequest request, String url) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_LAST_URL, url);
	}
}
