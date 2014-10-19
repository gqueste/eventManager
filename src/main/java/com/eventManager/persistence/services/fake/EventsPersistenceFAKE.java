/*
 * Created on 19 oct. 2014 ( Time 15:57:54 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package com.eventManager.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.persistence.commons.fake.GenericFakeService;
import com.eventManager.persistence.services.EventsPersistence;

/**
 * Fake persistence service implementation ( entity "Events" )
 *
 * @author Telosys Tools Generator
 */
public class EventsPersistenceFAKE extends GenericFakeService<EventsEntity> implements EventsPersistence {

	public EventsPersistenceFAKE () {
		super(EventsEntity.class);
	}
	
	protected EventsEntity buildEntity(int index) {
		EventsEntity entity = new EventsEntity();
		// Init fields with mock values
		entity.setEventId( nextInteger() ) ;
		entity.setUrl( nextString() ) ;
		entity.setName( nextString() ) ;
		entity.setAddress( nextString() ) ;
		entity.setDateBeginning( nextDate() ) ;
		entity.setDateEnd( nextDate() ) ;
		entity.setPublished( false ) ;
		return entity ;
	}
	
	
	public boolean delete(EventsEntity entity) {
		log("delete ( EventsEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer eventId ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(EventsEntity entity) {
		log("insert ( EventsEntity : " + entity + ")" ) ;
	}

	public EventsEntity load( Integer eventId ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<EventsEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<EventsEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<EventsEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public EventsEntity save(EventsEntity entity) {
		log("insert ( EventsEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<EventsEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
