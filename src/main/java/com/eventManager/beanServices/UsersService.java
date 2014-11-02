package com.eventManager.beanServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.eventManager.bean.jpa.UsersEntity;
import com.eventManager.persistence.services.UsersPersistence;
import com.eventManager.persistence.services.jpa.UsersPersistenceJPA;

/**
 * Services pour users
 */
public class UsersService {
	
	public UsersService(){}
	
	/**
	 * Insere un user et retourne son ID
	 * @param user UserEntity, l'user à ajouter
	 * @return ID de l'user tout juste inséré, -1 si pas inséré
	 */
	public int insertAndGetID(UsersEntity user) {
		int ret = -1;
		UsersPersistence persistence = new UsersPersistenceJPA();
		Map<String,Object> critere = new HashMap<String, Object>();
		critere.put("mail", user.getMail());
		ArrayList<UsersEntity> listeUsers = (ArrayList<UsersEntity>) persistence.search(critere);
		if(listeUsers.size() == 0) {
			persistence.insert(user);
			listeUsers = (ArrayList<UsersEntity>) persistence.search(critere);
			if(listeUsers.size() == 1) {
				UsersEntity userFound = listeUsers.get(0);
				ret = userFound.getUserId();
			}		
		}		
		return ret;
	}
}
