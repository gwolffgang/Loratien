package de.shazzarr.loratien.server.exception;

public class PlayerNotFoundException extends DatabaseMissingEntryException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}