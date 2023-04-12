package de.shazzarr.loratien.client.properties;

import java.awt.Dimension;
import java.awt.Toolkit;
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

import de.shazzarr.loratien.client.helper.Utils;

public class Settings {
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final static String fileName = "client.properties";

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

	private String calculateScreenWidth() {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final double hexWidth = screenSize.height / (10 * 2 + 1) / Math.sqrt(3) * 2;
		final int screenWidth = (int) (hexWidth * (10 * 2 * 1.5 + 2));
		return String.valueOf(Math.min(screenSize.width, screenWidth));
	}

	private void setDefaultValues() {
		properties.setProperty("framesPerSecond", "5");
		properties.setProperty("language", "de");
		properties.setProperty("screenRadius", "10");
		properties.setProperty("screenWidth", calculateScreenWidth());
		properties.setProperty("serverPort", "8090");
	}
}
