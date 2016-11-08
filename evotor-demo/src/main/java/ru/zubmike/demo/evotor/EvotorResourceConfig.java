package ru.zubmike.demo.evotor;

import javax.ws.rs.ApplicationPath;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;

import ru.zubmike.demo.evotor.types.domain.User;
import ru.zubmike.demo.evotor.utils.ConfigurationException;
import ru.zubmike.demo.evotor.utils.ConfigurationFactory;
import ru.zubmike.demo.evotor.utils.DataBaseConfiguration;
import ru.zubmike.demo.evotor.utils.HibernateFactory;

@ApplicationPath("/")
public class EvotorResourceConfig extends ResourceConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvotorResourceConfig.class);
	private static final String CONFIGURATION_FILE = "configuration.yml";

	public EvotorResourceConfig() throws ConfigurationException {
		init();
	}

	public static void main(String[] args) {
		try {
			new EvotorResourceConfig().init();
		} catch (Exception e) {
			LOGGER.error("Exception when start service", e);
			stop(args);
		}
	}

	public static void stop(String[] args) {
		LOGGER.info("stop service...");
		System.exit(0);
	}

	private void init() throws ConfigurationException {
		LOGGER.info("init service...");

		System.getProperties().setProperty("javax.xml.bind.context.factory", JAXBContextFactory.class.getName());

		DataBaseConfiguration dataBaseConfiguration = ConfigurationFactory.create(CONFIGURATION_FILE, DataBaseConfiguration.class);
		SessionFactory sessionFactory = HibernateFactory.createSessionFactory(dataBaseConfiguration, User.class);

		LOGGER.info("binding modules...");
		Guice.createInjector(new EvotorDemoBindingModule(this, sessionFactory));

		LOGGER.info("init is finished");
	}
	
}
