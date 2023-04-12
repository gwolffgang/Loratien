package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class IntelligenceComponent implements Component {

	private int intelligence;

	public IntelligenceComponent() {
		this(/* Default: */ 1);
	}

	public IntelligenceComponent(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("intelligence") + ": " + this.intelligence;
	}
}
