/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.eventManager.bean.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
}
