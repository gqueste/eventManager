/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package com.eventManager.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.persistence.commons.jpa.GenericJpaService;
import com.eventManager.persistence.commons.jpa.JpaOperation;
import com.eventManager.persistence.services.EventsPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Events" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class EventsPersistenceJPA extends GenericJpaService<EventsEntity, Integer> implements EventsPersistence {

	/**
	 * Constructor
	 */
	public EventsPersistenceJPA() {
		super(EventsEntity.class);
	}

	@Override
	public EventsEntity load( Integer eventId ) {
		return super.load( eventId );
	}

	@Override
	public boolean delete( Integer eventId ) {
		return super.delete( eventId );
	}

	@Override
	public boolean delete(EventsEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getEventId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("EventsEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
