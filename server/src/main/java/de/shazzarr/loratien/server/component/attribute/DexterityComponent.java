package de.shazzarr.loratien.server.component.attribute;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.Loratien;

public class DexterityComponent implements Component {

	private int dexterity;

	public DexterityComponent() {
		this(/* Default: */ 1);
	}

	public DexterityComponent(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	@Override
	public String toString() {
		return Loratien.locale.get("dexterity") + ": " + this.dexterity;
	}
}
