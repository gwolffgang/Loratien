package de.shazzarr.loratien.server.exception;

public class DatabaseMissingEntryException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseMissingEntryException(String errorMessage) {
		super(errorMessage);
	}
}