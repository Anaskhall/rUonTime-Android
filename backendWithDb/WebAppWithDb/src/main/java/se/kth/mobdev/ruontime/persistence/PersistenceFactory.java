/**
 * 
 */
package se.kth.mobdev.ruontime.persistence;

import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.Meeting;
import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */
public class PersistenceFactory {
	
	private static IGenericDao<Group> groupDao = new GenericDao<Group>(Group.class);
	
	private static UserDao userDao = new UserDao(User.class);
//	
	private static IGenericDao<Meeting> meetingDao = new GenericDao<Meeting>(Meeting.class);
//	
	private static IGenericDao<CheckIn> checkinDao = new GenericDao<CheckIn>(CheckIn.class);

	public static IGenericDao<Group> getGroupDao() {
		return groupDao;
	}

	public static UserDao getUserDao() {
		return userDao;
	}

	public static IGenericDao<Meeting> getMeetingDao() {
		return meetingDao;
	}

	public static IGenericDao<CheckIn> getCheckinDao() {
		return checkinDao;
	}


}
