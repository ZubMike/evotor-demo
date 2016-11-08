package ru.zubmike.demo.evotor.dao.db;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.zubmike.demo.evotor.dao.UserDAO;
import ru.zubmike.demo.evotor.types.domain.User;
import ru.zubmike.demo.evotor.utils.DataSourceException;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

	@Inject
	public UserDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public int addUser(User user) throws DataSourceException {
		Session session = null;
		try {
			session = openSession();
			beginTransaction(session);
			int id = (int) session.save(user);
			commitTransaction(session);
			return id;
		} catch (Exception e) {
			rollbackTransaction(session);
			throw new DataSourceException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public User getUser(String login) throws DataSourceException {
		Session session = null;
		try {
			session = openSession();
			return (User) session.createQuery("from " + User.class.getSimpleName() + " where login = :login")
					.setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DataSourceException(e);
		} finally {
			closeSession(session);
		}
	}

}
