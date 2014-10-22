package com.eventManager.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnexionUtils {
	
	public static final String SESSION_USER_ATTRIBUTE = "user_id";
	
	public static boolean isSessionValid(HttpServletRequest request) {
		boolean ret = false;
		HttpSession session = request.getSession();
		if(session != null) {
			String idUtilisateur = (String) session.getAttribute(SESSION_USER_ATTRIBUTE);
			if(idUtilisateur != null) {
				System.out.println("user_id : "+idUtilisateur);
				ret = true;
			}
		}
		return ret;
	}
}
