/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.eventManager.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;
import com.eventManager.persistence.services.jpa.InscriptionsPersistenceJPA;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;
import com.eventManager.utils.RandomUtils;

/**
 * Persistent class for entity stored in table "INSCRIPTIONS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "INSCRIPTIONS", schema = "APP")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "InscriptionsEntity.countAll", query = "SELECT COUNT(x) FROM InscriptionsEntity x") })
public class InscriptionsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSCRIPTION_ID", nullable = false)
	private Integer inscriptionId;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "MAIL", nullable = false, length = 50)
	private String mail;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 50)
	private String surname;

	@Column(name = "COMPANY", nullable = false, length = 50)
	private String company;

	// "eventId" (column "EVENT_ID") is not defined by itself because used as FK
	// in a link

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID")
	private EventsEntity events;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public InscriptionsEntity() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setInscriptionId(Integer inscriptionId) {
		this.inscriptionId = inscriptionId;
	}

	public Integer getInscriptionId() {
		return this.inscriptionId;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : MAIL ( VARCHAR )
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}

	// --- DATABASE MAPPING : NAME ( VARCHAR )
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// --- DATABASE MAPPING : SURNAME ( VARCHAR )
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}

	// --- DATABASE MAPPING : COMPANY ( VARCHAR )
	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return this.company;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------
	public void setEvents(EventsEntity events) {
		this.events = events;
	}

	public EventsEntity getEvents() {
		return this.events;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(inscriptionId);
		sb.append("]:");
		sb.append(mail);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(surname);
		sb.append("|");
		sb.append(company);
		return sb.toString();
	}

	/**
	 * Get all InscriptionEntity which correspond to an event
	 * @param events
	 * @return list of InscriptionEntity
	 */
	public List<InscriptionsEntity> getInscriptions(EventsEntity events) {
		InscriptionsPersistenceJPA inscriptionsJPA = new InscriptionsPersistenceJPA();
		Map<String, Object> critere = new HashMap<String, Object>();
		critere.put("events", events);
		List<InscriptionsEntity> resultList = inscriptionsJPA.search(critere);
		return resultList;
	}

	/**
	 * Add an Inscription in the database
	 * @param nameUser
	 * @param surnameUser
	 * @param eventId
	 * @param mailUser
	 * @param companyUser
	 * @return message
	 */
	public String add(String nameUser, String surnameUser, String eventId,
			String mailUser, String companyUser) {
		InscriptionsPersistenceJPA inscriptionsJPA = new InscriptionsPersistenceJPA();
		InscriptionsEntity inscription = new InscriptionsEntity();

		EventsEntity events = new EventsEntity();
		EventsEntity event = events.getEvent(eventId);

		boolean mailDejaPresent = false;
		List<InscriptionsEntity> inscriptionsList = inscription
				.getInscriptions(event);
		for (int i = 0; i < inscriptionsList.size(); i++)
			if (inscriptionsList.get(i).getMail().equals(mailUser))
				mailDejaPresent = true;

		if (nameUser == null || surnameUser == null || mailUser == null
				|| companyUser == null)
			return "addFailedMissed";
		else if (mailDejaPresent)
			return "addFailedMail";
		else {
			inscription.setName(nameUser);
			inscription.setSurname(surnameUser);
			inscription.setCompany(companyUser);
			inscription.setMail(mailUser);
			inscription.setEvents(event);

			inscriptionsJPA.insert(inscription);

			return "addSuccess";
		}
	}
}
