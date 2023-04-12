package de.shazzarr.loratien.server.entity.tileobject.item;

import de.shazzarr.loratien.server.entity.tileobject.Item;
import de.shazzarr.loratien.server.entity.tileobject.Lifeform;

public class Body extends Item {

	private Lifeform kind = null;

	public Body() {
	}

	public Lifeform getKind() {
		return kind;
	}

	public void setKind(Lifeform kind) {
		this.kind = kind;
	}

}
