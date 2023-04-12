package de.shazzarr.loratien.client.screen;

import java.awt.Polygon;

public class Hex extends Polygon {

	private static final long serialVersionUID = 1L;

	public Hex(int col, int row, double diameter) {
		final double width = diameter;
		final double height = diameter * Math.sqrt(3) * 0.5;
		final double x = width * col * 0.75;
		final double y = height * (row + (col % 2) * 0.5);

		this.addPoint((int) (x + width * 0.25), (int) (y + height * 0.0));
		this.addPoint((int) (x + width * 0.75), (int) (y + height * 0.0));
		this.addPoint((int) (x + width * 1.00), (int) (y + height * 0.5));
		this.addPoint((int) (x + width * 0.75), (int) (y + height * 1.0));
		this.addPoint((int) (x + width * 0.25), (int) (y + height * 1.0));
		this.addPoint((int) (x + width * 0.00), (int) (y + height * 0.5));
	}
}