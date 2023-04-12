package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {

	private int health = 1;

	public HealthComponent() {
	}

	public HealthComponent(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Health: " + health;
	}
}
