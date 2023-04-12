package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class AquaticComponent implements Component {

	private boolean aquatic = true;

	public AquaticComponent() {
	}

	public AquaticComponent(boolean aquatic) {
		this.aquatic = aquatic;
	}

	public boolean isAquatic() {
		return aquatic;
	}

	public void setAquatic(boolean aquatic) {
		this.aquatic = aquatic;
	}

	@Override
	public String toString() {
		return "is aquatic: " + aquatic;
	}
}
