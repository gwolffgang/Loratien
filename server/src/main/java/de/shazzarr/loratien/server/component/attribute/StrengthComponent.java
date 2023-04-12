package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class StrengthComponent implements Component {

	private int strength;

	public StrengthComponent() {
		this(/* Default: */ 1);
	}

	public StrengthComponent(int strength) {
		this.strength = strength;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("strength") + ": " + this.strength;
	}
}
