package com.eventManager.beanServices;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.bean.jpa.InscriptionsEntity;
import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.persistence.services.jpa.EventsPersistenceJPA;
import com.eventManager.persistence.services.jpa.InscriptionsPersistenceJPA;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;
import com.eventManager.utils.RandomUtils;

/**
 * Services pour Events
 */
public class EventsServices {    
	
	/**Fonction permettant d'ajouter un event
	 * 
	 * @param userId : Id du user ajoutant l'event
	 * @param nameEvent : Nom de l'event
	 * @param adressEvent : Adresse de l'event
	 * @param debut : Date de début de l'event (doit etre < a date de fin et > date actuelle)
	 * @param fin : Date de fin de l'event (doit etre > a date de début et > date actuelle)
	 * @param published : 0 Si non publié, 1 si publié
	 * @return une String contenant un code d'erreur ou de succès :<br>
	 * 				- addFailedBBE : date de début > date de fin<br>
	 * 				- addFailedBEBT : date de début ou de fin < date actuelle<br>
	 * 				- addSuccess : succès de l'ajout
	 */
	public String add(String userId, String nameEvent, String adressEvent, Timestamp debut, Timestamp fin, short published) {
    	EventsPersistenceJPA eventsJPA = new EventsPersistenceJPA();
    	UsersPersistenceJPA eventsUsers = new UsersPersistenceJPA();
    	RandomUtils randomizer = new RandomUtils();
    	EventsEntity event = new EventsEntity();  	
    	Date today = new Date();
    	
    	if(debut.after(fin)){
    		return "addFailedBBE";
    	}
    	if(debut.before(today)||fin.before(today)){
    		return "addFailedBEBT";
    	}
    	event.setName(nameEvent);
    	event.setAddress(adressEvent);
    	event.setPublished(published);
    	event.setDateBeginning(debut);
    	event.setDateEnd(fin);
    	
    	String url = randomizer.randomURL(); 
    	List<EventsEntity> resultList = new ArrayList<EventsEntity>();
    	Map<String, Object> critere = new HashMap<String, Object>();
    	critere.put("url", url);
		resultList = eventsJPA.search(critere);
    	while(resultList.size()!=0){
    		url = randomizer.randomURL();
	    	critere.put("url", url);
			resultList = eventsJPA.search(critere);
    	}
    	event.setUrl(url);
    	event.setUsers(eventsUsers.load(Integer.parseInt(userId)));
    	eventsJPA.insert(event);
    	return "addSuccess";
	}
    
	/**Fonction permettant de récupérer la liste de tout les events créer par un user
	 * 
	 * @param userId : Id du user
	 * @return La list d'event
	 */
    public List<EventsEntity> getAllEventsCreated(String userId) {
    	EventsPersistenceJPA eventsJPA = new EventsPersistenceJPA();
    	UsersPersistenceJPA usersJPA = new UsersPersistenceJPA();
    	UsersEntity user = usersJPA.load(Integer.parseInt(userId));
    	Map<String, Object> critere = new HashMap<String, Object>();
    	critere.put("users", user);
		List<EventsEntity> resultList = eventsJPA.search(critere);
		return resultList;
	}
    
    /**Fonction retournant l'event correspondant à l'id en paramètre
     * 
     * @param id : Id de l'event
     * @return L'event
     */
    public EventsEntity getEvent(String id) {
		EventsPersistenceJPA eventsJPA = new EventsPersistenceJPA();
		Map<String, Object> critere = new HashMap<String, Object>();
		critere.put("eventId", id);
		List<EventsEntity> resultList = eventsJPA.search(critere);
		if (resultList.size() >= 1)
			return resultList.get(0);
		else
			return null;
	}
    
    /**Fonction retournant la list des event auquel participe un user
     * 
     * @param userID : Id du user
     * @return La list d'event
     */
    public List<EventsEntity> getAllEventsParticipated(String userID) {
    	UsersPersistenceJPA userJPA = new UsersPersistenceJPA();
    	UsersEntity user = userJPA.load(Integer.parseInt(userID));
    	
    	InscriptionsPersistenceJPA inscriptionsJPA = new InscriptionsPersistenceJPA();
    	HashMap<String, Object> critere = new HashMap<String, Object>();
    	critere.put("mail", user.getMail());
    	List<InscriptionsEntity> result = inscriptionsJPA.search(critere);
    	List<EventsEntity> resultList = new ArrayList<EventsEntity>();    	
		for (InscriptionsEntity e : result){
			resultList.add(e.getEvents());
		}
    	return resultList;
	}
    
    /**Fonction permettant de supprimer un event
     * 
     * @param userId : Id de l'utilisateur supprimant l'event
     * @param rowId : Id de l'event a supprimer
     * @return une String contenant un code d'erreur ou de succès<br>
     * 				- deleteFailed : erreur lors de la suppression<br>
     * 				- deleteSuccess : réussite de la suppression
     */
    public String delete(int userId, int rowId) {
    	EventsPersistenceJPA em = new EventsPersistenceJPA();
    	EventsEntity event = em.load(rowId);    	
		if(event.getUsers().getUserId()==userId){
			InscriptionsPersistenceJPA inscriptionsJPA = new InscriptionsPersistenceJPA();
	    	HashMap<String, Object> critere = new HashMap<String, Object>();
	    	critere.put("events", event);
	    	List<InscriptionsEntity> result = inscriptionsJPA.search(critere);
	    	for (InscriptionsEntity e : result){
				inscriptionsJPA.delete(e);
			}
			em.delete(event);
			return "deleteSuccess";
		}
		else{
			return "deleteFailed";
		}
	}
    
    /**Fonction permettant de publier un event
     * 
     * @param userId : Id de l'user souhaitant publier l'event
     * @param rowId : Id de l'event
     * @return une String contenant un code d'erreur ou de succès<br>
     * 				- publishFailed : erreur lors de la publication<br>
     * 				- publishSuccess : réussite de la publication
     */
    public String publish(int userId, int rowId) {
    	EventsPersistenceJPA em = new EventsPersistenceJPA();
    	EventsEntity event = em.load(rowId);
    	if(event.getUsers().getUserId()==userId){
			event.setPublished((short)1);
			em.save(event);
			return "publishSuccess";
		}
		else{
			return "publishFailed";
		}
	}
    
    public void addInscription(InscriptionsEntity inscription, String eventId) {
		EventsServices events = new EventsServices();
		EventsEntity event = events.getEvent(eventId);
		if (event != null) {
			ArrayList<InscriptionsEntity> listInscrits = new ArrayList<InscriptionsEntity>();
			if (event.getListOfInscriptions() != null)
				for (int i = 0; i < event.getListOfInscriptions().size(); i++)
					listInscrits.add(event.getListOfInscriptions().get(i));
			listInscrits.add(inscription);
			event.setListOfInscriptions(listInscrits);
			EventsPersistenceJPA service = new EventsPersistenceJPA();
			service.save(event);
		}
    }

}
