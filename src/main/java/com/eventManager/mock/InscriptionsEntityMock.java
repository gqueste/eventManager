
/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package com.eventManager.mock;

import java.util.LinkedList;
import java.util.List;

import com.eventManager.bean.jpa.InscriptionsEntity;
import com.eventManager.mock.tool.MockValues;

public class InscriptionsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public InscriptionsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public InscriptionsEntity createInstance( Integer inscriptionId ) {
		InscriptionsEntity entity = new InscriptionsEntity();
		// Init Primary Key fields
		entity.setInscriptionId( inscriptionId) ;
		// Init Data fields
		entity.setMail( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setName( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setSurname( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setCompany( mockValues.nextString(50) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setEvents( TODO ) ; // Events 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<InscriptionsEntity> createList(int count) {
		List<InscriptionsEntity> list = new LinkedList<InscriptionsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
