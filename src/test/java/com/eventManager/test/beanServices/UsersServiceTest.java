package com.eventManager.test.beanServices;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.beanServices.UsersService;
import com.eventManager.persistence.services.UsersPersistence;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;

public class UsersServiceTest {
	
	private UsersPersistence persistence;
	private UsersService service;

	@Before
	public void setUp() throws Exception {
		persistence = new UsersPersistenceJPA();
		service = new UsersService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertAndGetID() {
		UsersEntity user = new UsersEntity("mail", "name", "surname", "password", "company");
		int id1 = service.insertAndGetID(user);
		assertTrue(id1 != -1);
		
		int id2 = service.insertAndGetID(user);
		assertTrue(id2 == -1);
		UsersEntity user2 = new UsersEntity("mail2", "name", "surname", "password", "company");
		id2 = service.insertAndGetID(user2);
		assertTrue(id2 == (id1 + 1));
		persistence.delete(id1);
		persistence.delete(id2);
	}

}
