package de.shazzarr.loratien.server.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.shazzarr.loratien.server.helper.Utils;

public class Settings {
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final static String fileName = "server.properties";

	private static Properties properties = new Properties();

	public Settings() {
		reset();
	}

	public String get(String propertyName) {
		return properties.getProperty(propertyName);
	}

	public Integer getInt(String string) {
		try {
			return Integer.parseInt(get(string));
		} catch (final NumberFormatException nfe) {
			log.error(nfe.getMessage());
			return null;
		}
	}

	public boolean load() {
		final File propertiesFile = new File(Utils.getUserDataDirectory() + fileName);
		propertiesFile.getParentFile().mkdirs();
		if (propertiesFile.isFile()) {
			try (InputStream inputStream = new FileInputStream(Utils.getUserDataDirectory() + fileName)) {
				properties.load(inputStream);
				return true;
			} catch (final IOException ioe) {
				log.error(ioe.getMessage());
			}
		} else { // create with default
			try {
				propertiesFile.createNewFile();
			} catch (final IOException ioe) {
				log.error(ioe.getMessage());
			}
			setDefaultValues();
			write();
		}
		return false;
	}

	public void reset() {
		if (!load()) {
			setDefaultValues();
		}
	}

	public void set(String propertyName, String value) {
		properties.setProperty(propertyName, value);
	}

	public boolean write() {
		try (OutputStream outputStream = new FileOutputStream(Utils.getUserDataDirectory() + fileName)) {
			properties.store(outputStream, null);
			return true;
		} catch (final IOException ioe) {
			log.error(ioe.getMessage());
			return false;
		}
	}

	private void setDefaultValues() {
		properties.setProperty("language", "de");
		properties.setProperty("jettyIdleTimeout", "120");
		properties.setProperty("jettyMaxThreads", "100");
		properties.setProperty("jettyMinThreads", "10");
		properties.setProperty("jettyServerPort", "8090");
	}
}
