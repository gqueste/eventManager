
/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package com.eventManager.mock;

import java.util.LinkedList;
import java.util.List;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.mock.tool.MockValues;

public class EventsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public EventsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public EventsEntity createInstance( Integer eventId ) {
		EventsEntity entity = new EventsEntity();
		// Init Primary Key fields
		entity.setEventId( eventId) ;
		// Init Data fields
		entity.setUrl( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setName( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setAddress( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setDateBeginning( mockValues.nextDate() ) ; // java.util.Date 
		entity.setDateEnd( mockValues.nextDate() ) ; // java.util.Date 
		entity.setPublished( mockValues.nextShort() ) ; // java.lang.Short 
		// Init Link fields (if any)
		// setListOfInscriptions( TODO ) ; // List<Inscriptions> 
		// setUsers( TODO ) ; // Users 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<EventsEntity> createList(int count) {
		List<EventsEntity> list = new LinkedList<EventsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}