package ru.zubmike.demo.evotor;

import javax.inject.Singleton;

import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;

import ru.zubmike.demo.evotor.dao.UserDAO;
import ru.zubmike.demo.evotor.dao.db.UserDAOImpl;
import ru.zubmike.demo.evotor.logic.UserLogic;
import ru.zubmike.demo.evotor.resources.UserResource;

public class EvotorDemoBindingModule extends AbstractModule {

	private final ResourceConfig resourceConfig;
	private final SessionFactory sessionFactory;

	public EvotorDemoBindingModule(ResourceConfig resourceConfig, SessionFactory sessionFactory) {
		this.resourceConfig = resourceConfig;
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void configure() {

		bind(ResourceConfig.class).toInstance(resourceConfig);
		bind(SessionFactory.class).toInstance(sessionFactory);

		bind(UserDAO.class).to(UserDAOImpl.class).in(Singleton.class);

		bind(UserLogic.class).in(Singleton.class);

		bind(UserResource.class).asEagerSingleton();
	}

}
