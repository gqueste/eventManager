package com.eventManager.beanServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eventManager.bean.jpa.EventsEntity;
import com.eventManager.bean.jpa.InscriptionsEntity;
import com.eventManager.persistence.services.jpa.InscriptionsPersistenceJPA;

public class InscriptionsService {
	
	public InscriptionsService(){};
	
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

		EventsServices events = new EventsServices();
		EventsEntity event = events.getEvent(eventId);

		boolean mailDejaPresent = false;
		List<InscriptionsEntity> inscriptionsList = this.getInscriptions(event);
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
