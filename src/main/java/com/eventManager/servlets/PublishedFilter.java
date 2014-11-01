package com.eventManager.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;
import com.eventManager.utils.ConnexionUtils;

/**
 * Servlet Filter implementation class PublishedFilter
 */
public class PublishedFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PublishedFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		RequestDispatcher rd;
		ConnexionUtils.setLastUrlVisited(req, req.getRequestURL().toString());
		
		EventsPersistenceJPA eventJPA = new EventsPersistenceJPA();
		EventsEntity event = new EventsEntity();
		String url = req.getPathInfo();
		String[] urlsplit = url.split("/");
		String eventUrl = "-1";
		if (urlsplit.length == 2)
			if (!urlsplit[1].equals("*"))
				eventUrl = urlsplit[1];
		Map<String, Object> critere = new HashMap<String, Object>();
		critere.put("url", eventUrl);
		List<EventsEntity> listeEvents = eventJPA.search(critere);
		if(listeEvents.size() == 1) {
			event = listeEvents.get(0);
			if (event.getPublished() != 0) {
				// pass the request along the filter chain
				req.setAttribute("event", event);
				chain.doFilter(req, rep);
			}
			else {
				//Not yet published
				rd = req.getRequestDispatcher("/jsp/error_not_published.jsp");
				rd.forward(req, rep);
			}
		}
		else {
			//404
			rd = req.getRequestDispatcher("/jsp/error_404.jsp");
			rd.forward(req, rep);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
