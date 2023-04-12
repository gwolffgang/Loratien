package de.shazzarr.loratien.server.component;

import com.badlogic.ashley.core.Component;

import de.shazzarr.loratien.server.entity.tileobject.Lifeform;

public class OwnageComponent implements Component {

	private Lifeform owner = null;

	public OwnageComponent() {
	}

	public OwnageComponent(Lifeform owner) {
		this.owner = owner;
	}

	public Lifeform getOwner() {
		return owner;
	}

	public void setOwner(Lifeform owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Owner: " + owner != null ? owner.toString() : "unowned";
	}
}
