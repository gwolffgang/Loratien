package de.shazzarr.loratien.server.locale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import de.shazzarr.loratien.server.Loratien;
import de.shazzarr.loratien.server.helper.Utils;

public class Translation {

	private LocaleData data = new LocaleData();

	Gson gson = new Gson();

	private final String fileName = "locale.json";
	String fileHD = Utils.getUserDataDirectory() + fileName;

	public Translation() {
		setLocaleData();
	}

	public String get(String name) {
		return get(name, "default");
	}

	public String get(String name, String detail) {
		Map<String, Map<String, String>> collection;
		switch (Loratien.properties.get("language")) {
		case "de":
			collection = data.getDe();
		default:
			collection = data.getEn();
		}
		if (collection != null && collection.get(name) != null && collection.get(name).get(detail) != null) {
			return collection.get(name).get(detail);
		}
		return "undefined";
	}

	public boolean loadLocaleData() {
		try {
			final FileReader reader = new FileReader(fileHD);
			data = gson.fromJson(reader, LocaleData.class);
			return true;
		} catch (final FileNotFoundException fnfe) {
			setDefaultValues();
			return writeLocaleJSON();
		}
	}

	public void setLocaleData() {
		if (!loadLocaleData()) {
			setDefaultValues();
		}
	}

	private void setDefaultValues() {
		try {
			final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			data = gson.fromJson(new InputStreamReader(inputStream), LocaleData.class);
		} catch (JsonSyntaxException | JsonIOException e) {
			e.printStackTrace();
		}
	}

	public boolean writeLocaleJSON() {
		new File(fileHD).getParentFile().mkdirs();
		final String jsonString = gson.toJson(data);
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileHD))) {
			writer.write(jsonString);
			return true;
		} catch (final IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
}
