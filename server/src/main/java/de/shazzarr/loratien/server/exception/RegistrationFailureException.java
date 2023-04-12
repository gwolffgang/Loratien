package de.shazzarr.loratien.server.exception;

public class RegistrationFailureException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegistrationFailureException(String errorMessage) {
		super(errorMessage);
	}
}
