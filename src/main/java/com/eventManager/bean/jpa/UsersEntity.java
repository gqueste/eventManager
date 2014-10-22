/*
 * Created on 20 oct. 2014 ( Time 09:24:20 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.eventManager.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

import com.eventManager.persistence.PersistenceServiceProvider;
import com.eventManager.persistence.services.UsersPersistence;

/**
 * Persistent class for entity stored in table "USERS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="USERS", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UsersEntity.countAll", query="SELECT COUNT(x) FROM UsersEntity x" )
} )
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="USER_ID", nullable=false)
    private Integer    userId       ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="MAIL", nullable=false, length=50)
    private String     mail         ;

    @Column(name="NAME", nullable=false, length=50)
    private String     name         ;

    @Column(name="SURNAME", nullable=false, length=50)
    private String     surname      ;

    @Column(name="PASSWORD", nullable=false, length=50)
    private String     password     ;

    @Column(name="COMPANY", nullable=false, length=50)
    private String     company      ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="users", targetEntity=EventsEntity.class)
    private List<EventsEntity> listOfEvents;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UsersEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setUserId( Integer userId ) {
        this.userId = userId ;
    }
    public Integer getUserId() {
        return this.userId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : MAIL ( VARCHAR ) 
    public void setMail( String mail ) {
        this.mail = mail;
    }
    public String getMail() {
        return this.mail;
    }

    //--- DATABASE MAPPING : NAME ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : SURNAME ( VARCHAR ) 
    public void setSurname( String surname ) {
        this.surname = surname;
    }
    public String getSurname() {
        return this.surname;
    }

    //--- DATABASE MAPPING : PASSWORD ( VARCHAR ) 
    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    //--- DATABASE MAPPING : COMPANY ( VARCHAR ) 
    public void setCompany( String company ) {
        this.company = company;
    }
    public String getCompany() {
        return this.company;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfEvents( List<EventsEntity> listOfEvents ) {
        this.listOfEvents = listOfEvents;
    }
    public List<EventsEntity> getListOfEvents() {
        return this.listOfEvents;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(userId);
        sb.append("]:"); 
        sb.append(mail);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(surname);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(company);
        return sb.toString(); 
    }
    
    public static void main(String[] args) {
    	UsersPersistence service = PersistenceServiceProvider.getService(UsersPersistence.class);
    	UsersEntity user = new UsersEntity();
    	user.setCompany("company");
    	user.setMail("czeeeu");
    	user.setName("zererze");
    	user.setPassword("zerzerza");
    	user.setSurname("arzraz");
    	service.insert(user);
    	
    	UsersEntity user2 =  service.load(2);
    	System.out.println(user2);
	}

}
