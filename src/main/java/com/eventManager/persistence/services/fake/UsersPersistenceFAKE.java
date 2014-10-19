/*
 * Created on 19 oct. 2014 ( Time 15:57:32 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package com.eventManager.persistence.services.fake;

import java.util.List;
import java.util.Map;

import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.persistence.commons.fake.GenericFakeService;
import com.eventManager.persistence.services.UsersPersistence;

/**
 * Fake persistence service implementation ( entity "Users" )
 *
 * @author Telosys Tools Generator
 */
public class UsersPersistenceFAKE extends GenericFakeService<UsersEntity> implements UsersPersistence {

	public UsersPersistenceFAKE () {
		super(UsersEntity.class);
	}
	
	protected UsersEntity buildEntity(int index) {
		UsersEntity entity = new UsersEntity();
		// Init fields with mock values
		entity.setUserId( nextInteger() ) ;
		entity.setMail( nextString() ) ;
		entity.setName( nextString() ) ;
		entity.setSurname( nextString() ) ;
		entity.setPassword( nextString() ) ;
		entity.setCompany( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(UsersEntity entity) {
		log("delete ( UsersEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer userId ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(UsersEntity entity) {
		log("insert ( UsersEntity : " + entity + ")" ) ;
	}

	public UsersEntity load( Integer userId ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<UsersEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<UsersEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<UsersEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public UsersEntity save(UsersEntity entity) {
		log("insert ( UsersEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<UsersEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}