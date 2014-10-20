/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package com.eventManager.persistence.services;

import java.util.List;
import java.util.Map;

import com.eventManager.bean.jpa.EventsEntity;

/**
 * Basic persistence operations for entity "Events"
 * 
 * This Bean has a basic Primary Key : Integer
 *
 * @author Telosys Tools Generator
 *
 */
public interface EventsPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param events
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(EventsEntity events) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param eventId
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Integer eventId) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param events
	 */
	public void insert(EventsEntity events) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param eventId
	 * @return the entity loaded (or null if not found)
	 */
	public EventsEntity load(Integer eventId) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<EventsEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<EventsEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<EventsEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param events
	 * @return
	 */
	public EventsEntity save(EventsEntity events) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<EventsEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}
