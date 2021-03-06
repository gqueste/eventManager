/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package com.eventManager.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.eventManager.bean.jpa.InscriptionsEntity;
import com.eventManager.persistence.commons.jpa.GenericJpaService;
import com.eventManager.persistence.commons.jpa.JpaOperation;
import com.eventManager.persistence.services.InscriptionsPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Inscriptions" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class InscriptionsPersistenceJPA extends GenericJpaService<InscriptionsEntity, Integer> implements InscriptionsPersistence {

	/**
	 * Constructor
	 */
	public InscriptionsPersistenceJPA() {
		super(InscriptionsEntity.class);
	}

	@Override
	public InscriptionsEntity load( Integer inscriptionId ) {
		return super.load( inscriptionId );
	}

	@Override
	public boolean delete( Integer inscriptionId ) {
		return super.delete( inscriptionId );
	}

	@Override
	public boolean delete(InscriptionsEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getInscriptionId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("InscriptionsEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
