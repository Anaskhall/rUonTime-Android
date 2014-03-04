/**
 * 
 */
package se.kth.mobdev.ruontime.service;

import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */
public class UserAuthenticationService {

	
	
	public boolean login(String username, String password) {
		
		User userToAuthenticate = findUserByName(username);
		return (password.equals(userToAuthenticate.getPwHash()));
	}

	private User findUserByName(String username) {
		return PersistenceFactory.getUserDao().findByName(username);
	}
	
}
