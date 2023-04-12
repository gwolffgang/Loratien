package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {

	private int movement = 1;

	public MovementComponent() {
	}

	public MovementComponent(int movement) {
		this.movement = movement;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}

	@Override
	public String toString() {
		return "Movement: " + movement;
	}
}
