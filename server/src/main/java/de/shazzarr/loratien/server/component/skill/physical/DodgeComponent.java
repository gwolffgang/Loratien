package de.shazzarr.loratien.server.component.skill.physical;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class DodgeComponent implements Component {

	private int dodge;

	public DodgeComponent() {
		this(/* Default: */ 0);
	}

	public DodgeComponent(int dodge) {
		this.dodge = dodge;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("dodge") + ": " + this.dodge;
	}
}
