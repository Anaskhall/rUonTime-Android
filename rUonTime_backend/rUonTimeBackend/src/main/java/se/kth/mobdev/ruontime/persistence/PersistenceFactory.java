/**
 * 
 */
package se.kth.mobdev.ruontime.persistence;

import se.kth.mobdev.ruontime.model.CheckIn;
import se.kth.mobdev.ruontime.model.Group;
import se.kth.mobdev.ruontime.model.Meeting;
import se.kth.mobdev.ruontime.model.User;

/**
 * @author Jasper
 *
 */
public class PersistenceFactory {
	
	private static IGenericDao<Group> groupDao = new GenericDao<Group>(Group.class);
	
	private static IGenericDao<User> userDao = new GenericDao<User>(User.class);
	
	private static IGenericDao<Meeting> meetingDao = new GenericDao<Meeting>(Meeting.class);
	
	private static IGenericDao<CheckIn> checkinDao = new GenericDao<CheckIn>(CheckIn.class);

	public static IGenericDao<Group> getGroupDao() {
		return groupDao;
	}

	public static IGenericDao<User> getUserDao() {
		return userDao;
	}

	public static IGenericDao<Meeting> getMeetingDao() {
		return meetingDao;
	}

	public static IGenericDao<CheckIn> getCheckinDao() {
		return checkinDao;
	}


}
