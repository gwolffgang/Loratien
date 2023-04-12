package de.shazzarr.loratien.server.entity;

abstract public class Tile extends GameObject {

	private final int col, row;
	private double altitude;

	public Tile(int col, int row, double altitude) {
		this.col = col;
		this.row = row;
		this.altitude = altitude;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
}
