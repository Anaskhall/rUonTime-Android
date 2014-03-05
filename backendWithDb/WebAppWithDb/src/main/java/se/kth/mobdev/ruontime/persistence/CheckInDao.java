/**
 * 
 */
package se.kth.mobdev.ruontime.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.Group;

/**
 * @author Jasper
 * 
 */
public class CheckInDao extends GenericDao<CheckIn> {

	public CheckInDao() {
		super(CheckIn.class);
	}

	public List<CheckIn> getCheckInsForUser(String userName) {
		List<CheckIn> result;
		// query as string
		String queryString = " from CheckIn as c where c.associatedUser.userName = :name";

		Session session = getSession();
		session.getTransaction().begin();
		// generate query object
		Query query = session.createQuery(queryString);
		query.setString("name", userName);
		result = query.list();
		session.getTransaction().commit();
		session.close();

		if (!result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}

}
