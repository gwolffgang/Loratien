package de.shazzarr.loratien.server.entity;

abstract public class TileObject extends GameObject {

	private GameObject position = null;

	public TileObject() {
	}

	public TileObject(Tile position) {
	}

	public GameObject getPosition() {
		return position;
	}

	public void setPosition(GameObject position) {
		this.position = position;
	}

}
