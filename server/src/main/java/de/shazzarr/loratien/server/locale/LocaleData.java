package de.shazzarr.loratien.server.locale;

import java.util.HashMap;
import java.util.Map;

public class LocaleData {

	String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	Map<String, Map<String, String>> de = new HashMap<>();
	Map<String, Map<String, String>> en = new HashMap<>();

	public Map<String, Map<String, String>> getDe() {
		return de;
	}

	public void setDe(Map<String, Map<String, String>> de) {
		this.de = de;
	}

	public Map<String, Map<String, String>> getEn() {
		return en;
	}

	public void setEn(Map<String, Map<String, String>> en) {
		this.en = en;
	}

}
