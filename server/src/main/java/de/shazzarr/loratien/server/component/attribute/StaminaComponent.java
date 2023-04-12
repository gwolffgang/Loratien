package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class StaminaComponent implements Component {

	private int stamina;

	public StaminaComponent() {
		this(/* Default: */ 1);
	}

	public StaminaComponent(int stamina) {
		this.stamina = stamina;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("stamina") + ": " + this.stamina;
	}
}
