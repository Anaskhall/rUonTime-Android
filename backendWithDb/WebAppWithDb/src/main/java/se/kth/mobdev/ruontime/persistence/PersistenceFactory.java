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
	
	private static GroupDao groupDao = new GroupDao();
	
	private static UserDao userDao = new UserDao();
//	
	private static IGenericDao<Meeting> meetingDao = new GenericDao<Meeting>(Meeting.class);
//	
	private static CheckInDao checkinDao = new CheckInDao();

	public static GroupDao getGroupDao() {
		return groupDao;
	}

	public static UserDao getUserDao() {
		return userDao;
	}

	public static IGenericDao<Meeting> getMeetingDao() {
		return meetingDao;
	}

	public static CheckInDao getCheckinDao() {
		return checkinDao;
	}


}
