package de.shazzarr.loratien.client.helper;

import java.io.File;

public abstract class Utils {

	public static String getUserDataDirectory() {
		return System.getProperty("user.home") + File.separator + ".loratien" + File.separator;
	}
}