package com.eventManager.beanServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	 * Get back the user which corresponds to the id
	 * @param id
	 * @return UserEntity
	 */
	public UsersEntity getUser(String id) {
		UsersPersistenceJPA usersJPA = new UsersPersistenceJPA();
		Map<String, Object> critere = new HashMap<String, Object>();
		critere.put("userId", id);
		List<UsersEntity> resultList = usersJPA.search(critere);
		if (resultList.size() == 1)
			return resultList.get(0);
		else
			return null;
	}

	/**
	 * Get back all users
	 * @return List of UsersEntity
	 */
	public List<UsersEntity> getUsers() {
		UsersPersistenceJPA usersJPA = new UsersPersistenceJPA();
		List<UsersEntity> resultList = usersJPA.search(new HashMap<String, Object>());
		return resultList;
	}
	
	/**
	 * Get back Users which correspond to the mail
	 * @param mail
	 * @return List of UsersEntity
	 */
	public List<UsersEntity> getUsersByMail(String mail) {
		UsersPersistenceJPA usersJPA = new UsersPersistenceJPA();
		Map<String, Object> critere = new HashMap<String, Object>();
		critere.put("mail", mail);
		List<UsersEntity> resultList = usersJPA.search(critere);
		return resultList;
	}
	
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
