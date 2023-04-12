package de.shazzarr.loratien.server.component.skill.physical;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class AcrobaticsComponent implements Component {

	private int acrobatics;

	public AcrobaticsComponent() {
		this(/* Default: */ 0);
	}

	public AcrobaticsComponent(int acrobatics) {
		this.acrobatics = acrobatics;
	}

	public int getAcrobatics() {
		return acrobatics;
	}

	public void setAcrobatics(int acrobatics) {
		this.acrobatics = acrobatics;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("acrobatics") + ": " + this.acrobatics;
	}
}
