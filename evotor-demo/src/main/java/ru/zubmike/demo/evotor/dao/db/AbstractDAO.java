package ru.zubmike.demo.evotor.dao.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDAO {

	private final SessionFactory sessionFactory;

	public AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session openSession() {
		return sessionFactory.openSession();
	}

	protected void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	protected void beginTransaction(Session session) {
		session.getTransaction().begin();
	}

	protected void commitTransaction(Session session) {
		session.getTransaction().commit();
	}

	protected void rollbackTransaction(Session session) {
		session.getTransaction().rollback();
	}

}
