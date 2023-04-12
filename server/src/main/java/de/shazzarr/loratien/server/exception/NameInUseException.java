package de.shazzarr.loratien.server.exception;

public class NameInUseException extends RegistrationFailureException {

	private static final long serialVersionUID = 1L;

	public NameInUseException(String errorMessage) {
		super(errorMessage);
	}
}
