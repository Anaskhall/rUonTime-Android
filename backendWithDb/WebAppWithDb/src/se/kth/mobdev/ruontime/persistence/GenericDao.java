package se.kth.mobdev.ruontime.persistence;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDao<T extends IEntity> implements IGenericDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clazz;

	private String tableName;

	private SessionFactory sessionFactory;

	/**
	 * Default constructor. Use for extend this class.
	 */
	public GenericDao() {
		this.sessionFactory = HibernateUtil.getSessionFactory();

	}

	private Session getSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Constructor with given {@link IEntity} implementation. Use for creting
	 * DAO without extending this class.
	 * 
	 * @param clazz
	 *            class with will be accessed by DAO methods
	 */
	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Set entity manager.
	 * 
	 * @param entityManager
	 *            entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#load(java.util.UUID)
	 */
	@Override
	public T load(Integer id) throws EntityNotFoundException {
		T entity = get(id);
		if (entity == null) {
			return null;
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#get(java.util.UUID)
	 */
	@Override
	public T get(Integer id) {

		return (T) getSession().get(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#getAll()
	 */
	@Override
	@SuppressWarnings(value = "unchecked")
	public List<T> getAll() {
		Session session = getSession();
		session.getTransaction().begin();
		List<T> list = session.createCriteria(clazz).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#save(T)
	 */
	@Override
	public T save(final T object) {
		Session session = getSession();
		session.getTransaction().begin();
		T merged = object;
		if (object.getId() != null) {
			merged = (T) session.merge(object);
		} else {
			session.save(object);
		}
		session.getTransaction().commit();
		session.close();
		return merged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#save(T)
	 */
	@Override
	public void save(final T... objects) {
		for (T object : objects) {
			save(object);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.kth.mobdev.ruontime.persistence.IGenericDao#delete(T)
	 */
	@Override
	public void delete(T object) throws UnsupportedOperationException {
		Session session = getSession();
		session.getTransaction().begin();
		session.delete(object);
		session.getTransaction().commit();
		session.close();
	}

}
