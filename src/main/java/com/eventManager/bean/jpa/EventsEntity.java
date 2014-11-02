/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.eventManager.bean.jpa;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;
import com.eventManager.persistence.services.jpa.InscriptionsPersistenceJPA;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;
import com.eventManager.utils.RandomUtils;

/**
 * Persistent class for entity stored in table "EVENTS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="EVENTS", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EventsEntity.countAll", query="SELECT COUNT(x) FROM EventsEntity x" )
} )
public class EventsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="EVENT_ID", nullable=false)
    private Integer    eventId      ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="URL", nullable=false, length=50)
    private String     url          ;

    @Column(name="NAME", nullable=false, length=50)
    private String     name         ;

    @Column(name="ADDRESS", nullable=false, length=50)
    private String     address      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_BEGINNING")
    private Date       dateBeginning ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_END")
    private Date       dateEnd      ;

    @Column(name="PUBLISHED")
    private Short      published    ;

	// "creatorId" (column "CREATOR_ID") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="events", targetEntity=InscriptionsEntity.class)
    private List<InscriptionsEntity> listOfInscriptions;

    @ManyToOne
    @JoinColumn(name="CREATOR_ID", referencedColumnName="USER_ID")
    private UsersEntity users       ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EventsEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setEventId( Integer eventId ) {
        this.eventId = eventId ;
    }
    public Integer getEventId() {
        return this.eventId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : URL ( VARCHAR ) 
    public void setUrl( String url ) {
        this.url = url;
    }
    public String getUrl() {
        return this.url;
    }

    //--- DATABASE MAPPING : NAME ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : ADDRESS ( VARCHAR ) 
    public void setAddress( String address ) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    //--- DATABASE MAPPING : DATE_BEGINNING ( TIMESTAMP ) 
    public void setDateBeginning( Date dateBeginning ) {
        this.dateBeginning = dateBeginning;
    }
    public Date getDateBeginning() {
        return this.dateBeginning;
    }

    //--- DATABASE MAPPING : DATE_END ( TIMESTAMP ) 
    public void setDateEnd( Date dateEnd ) {
        this.dateEnd = dateEnd;
    }
    public Date getDateEnd() {
        return this.dateEnd;
    }

    //--- DATABASE MAPPING : PUBLISHED ( SMALLINT ) 
    public void setPublished( Short published ) {
        this.published = published;
    }
    public Short getPublished() {
        return this.published;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfInscriptions( List<InscriptionsEntity> listOfInscriptions ) {
        this.listOfInscriptions = listOfInscriptions;
    }
    public List<InscriptionsEntity> getListOfInscriptions() {
        return this.listOfInscriptions;
    }

    public void setUsers( UsersEntity users ) {
        this.users = users;
    }
    public UsersEntity getUsers() {
        return this.users;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(eventId);
        sb.append("]:"); 
        sb.append(url);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(dateBeginning);
        sb.append("|");
        sb.append(dateEnd);
        sb.append("|");
        sb.append(published);
        return sb.toString(); 
    } 
    
}
