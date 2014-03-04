package se.kth.mobdev.ruontime.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.User;

public class GroupDao extends GenericDao<Group> {

	public GroupDao() {
		super(Group.class);
	}

	public Group findByName(String username) {
		// query as string
		String queryString = " from Group as g where g.name = :name";

		Session session = getSession();
		session.getTransaction().begin();
		// generate query object
		Query query = session.createQuery(queryString);
		query.setString("name", username);
		List<Group> matchingGroups = query.list();
		session.getTransaction().commit();
		session.close();

		if (!matchingGroups.isEmpty()) {
			return matchingGroups.get(0);
		} else {
			return null;
		}

	}

	public List<Group> getAllForUser(Integer id) {

		Session session = getSession();
		session.getTransaction().begin();
		// generate query object
		String hql = "select distinct g from Group g join g.supervisors s where s.id = (:id)";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		List<Group> matchingGroups = query.list();

		session.getTransaction().commit();
		session.close();

		System.out.println("got "+matchingGroups.size()+" groups for user with id " + id);
		if (!matchingGroups.isEmpty()) {
			return matchingGroups;
		} else {
			return null;
		}
	}
}
