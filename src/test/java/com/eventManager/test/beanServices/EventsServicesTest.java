package com.eventManager.test.beanServices;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eventManager.beanServices.EventsServices;
import com.eventManager.persistence.services.EventsPersistence;
import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;

public class EventsServicesTest {
	
	private EventsPersistence persistence;
	private EventsServices service;

	@Before
	public void setUp() throws Exception {
		persistence = new EventsPersistenceJPA();
		service = new EventsServices();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		short published = 0;
		String retour = service.add("2","nom", "adresse", new Timestamp(10), new Timestamp(0), published);
		assertTrue(retour.equals("addFailedBBE"));
		
		retour = service.add("2","nom", "adresse", new Timestamp(0), new Timestamp(10), published);
		assertTrue(retour.equals("addFailedBEBT"));		
	}
	
	@Test
	public void testGetAllEventsCreated() {
		//TODO
		assertTrue(true);
	}
	
	@Test
	public void testGetEvent() {
		//TODO
		assertTrue(true);
	}
	
	@Test
	public void getAllEventsParticipated() {
		//TODO
		assertTrue(true);
	}
	
	@Test
	public void delete() {
		//TODO
		assertTrue(true);
	}
	
	@Test
	public void publish() {
		//TODO
		assertTrue(true);
	}
	
	@Test
	public void addInscription() {
		//TODO
		assertTrue(true);
	}
}
