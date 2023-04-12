package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class NameComponent implements Component {

	private String name = null;

	public NameComponent() {
	}

	public NameComponent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name: " + name != null ? name : "unnamed";
	}
}
