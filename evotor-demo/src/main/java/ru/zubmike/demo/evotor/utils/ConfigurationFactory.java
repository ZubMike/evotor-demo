package ru.zubmike.demo.evotor.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigurationFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);

	public static <T> T create(String configurationFile, Class<T> klass) throws ConfigurationException {
		T configuration = parse(configurationFile, klass);
		return configuration;
	}

	private static <T> T parse(String configurationFile, Class<T> klass) throws ConfigurationException {
		try {
			LOGGER.info("configuration: {}", configurationFile);
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			return mapper.readValue(new File(configurationFile), klass);
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
}
