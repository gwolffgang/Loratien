package de.shazzarr.loratien.server.component.skill.physical;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class WeaponryComponent implements Component {

	private int weaponry;

	public WeaponryComponent() {
		this(/* Default: */ 0);
	}

	public WeaponryComponent(int weaponry) {
		this.weaponry = weaponry;
	}

	public int getWeaponry() {
		return weaponry;
	}

	public void setWeaponry(int weaponry) {
		this.weaponry = weaponry;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("weaponry") + ": " + this.weaponry;
	}
}
