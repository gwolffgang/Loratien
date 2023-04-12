package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class ManipulationComponent implements Component {

	private int manipulation;

	public ManipulationComponent() {
		this(/* Default: */ 1);
	}

	public ManipulationComponent(int manipulation) {
		this.manipulation = manipulation;
	}

	public int getManipulation() {
		return manipulation;
	}

	public void setManipulation(int manipulation) {
		this.manipulation = manipulation;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("manipulation") + ": " + this.manipulation;
	}
}
