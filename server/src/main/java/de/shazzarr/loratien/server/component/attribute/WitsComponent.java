package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class WitsComponent implements Component {

	private int wits;

	public WitsComponent() {
		this(/* Default: */ 1);
	}

	public WitsComponent(int wits) {
		this.wits = wits;
	}

	public int getWits() {
		return wits;
	}

	public void setWits(int wits) {
		this.wits = wits;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("wits") + ": " + this.wits;
	}
}
