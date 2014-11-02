package com.eventManager.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet Filter implementation class ConnexionFilter
 * Filtre pour vérifier la connexion d'un utilisateur
 */
public class ConnexionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ConnexionFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * Sauvegarde l'url depuis laquelle on arrive
	 * Vérifie l'état de la connexion: si user connecté, doFilter
	 * Sinon, redirection vers page de login
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		ConnexionUtils.setLastUrlVisited(req, req.getRequestURL().toString());
		
		if (! ConnexionUtils.isSessionValid(req) ) {
			rep.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
