package de.shazzarr.loratien.server.servlet.menu.dto;

public class RegisterRequest {
	private String name;

	public RegisterRequest() {
	}

	public RegisterRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
