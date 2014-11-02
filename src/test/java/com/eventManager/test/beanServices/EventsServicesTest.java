package com.eventManager.test.beanServices;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eventManager.persistence.services.EventsPersistence;
import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;

public class EventsServicesTest {
	
	private EventsPersistence persistence;

	@Before
	public void setUp() throws Exception {
		persistence = new EventsPersistenceJPA();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		
	}

}
