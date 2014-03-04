/**
 * 
 */
package se.kth.mobdev.ruontime.service;

import java.util.List;

import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */
public class GroupService {
	
	public List<Group> getAllGroups(User user){
		Integer id = user.getId();
		return PersistenceFactory.getGroupDao().getAllForUser(id);
	}

}
