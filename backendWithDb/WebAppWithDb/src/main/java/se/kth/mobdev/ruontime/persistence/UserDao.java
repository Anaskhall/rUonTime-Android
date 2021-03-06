/**
 * 
 */
package se.kth.mobdev.ruontime.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */
public class UserDao extends GenericDao<User> {

	public UserDao() {

		super(User.class);
	}

	public User findByName(String username) {
		//query as string
		String queryString = " from User as u where u.userName = :name";
		
		Session session = getSession();
		session.getTransaction().begin();
		//generate query object
		Query query = session.createQuery(queryString);
		query.setString("name", username);
		List<User> matchingUsers = query.list();
		session.getTransaction().commit();
		session.close();
		
		if(!matchingUsers.isEmpty()){
			return matchingUsers.get(0);
		}
		else {
			return null;
		}
		
	}

}
