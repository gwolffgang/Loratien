package de.shazzarr.loratien.server.entity.tileobject;

import de.shazzarr.loratien.server.entity.TileObject;
import de.shazzarr.loratien.server.enumeration.Components;

abstract public class Lifeform extends TileObject {

	public Lifeform() {
		addComponent(Components.health, 1);
	}
}
