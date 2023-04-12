package de.shazzarr.loratien.server.bean.worldmap.sector;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class Sector {
	private final Map<Integer, Double> altitudes = new HashMap<>();

	private final int radius = 5;
	private final int diameter = radius * 2 + 1;
	private final Random rand;
	private final SplineInterpolator interpolator = new SplineInterpolator();

	public Sector(long seed) {
		this.rand = new Random(seed);
		for (int row = 0; row < diameter; row += radius) {
			for (int col = 0; col < diameter; col += radius) {
				altitudes.put(row * diameter + col, rand.nextInt(21) - 10.0);
			}
		}

		for (int row = 0; row < diameter; row += radius) {
			interpolateRow(row * diameter, row * diameter + radius, row * diameter + radius * 2);
		}
		for (int col = 0; col < diameter; col++) {
			interpolateCol(0 * diameter + col, radius * diameter + col, radius * 2 * diameter + col);
		}
	}

	private void interpolateCol(int top, int middle, int bottom) {
		final int first = top / diameter;
		final int last = bottom / diameter;
		final double x[] = { first, middle / diameter, last };
		final double y[] = { altitudes.get(top), altitudes.get(middle), altitudes.get(bottom) };
		final PolynomialSplineFunction spline = this.interpolator.interpolate(x, y);
		for (int row = top; row < bottom + 1; row += diameter) {
			altitudes.put(row, spline.value(row / diameter));
		}
	}

	private void interpolateRow(int first, int middle, int last) {
		final double x[] = { first, middle, last };
		final double y[] = { altitudes.get(first), altitudes.get(middle), altitudes.get(last) };
		final PolynomialSplineFunction spline = this.interpolator.interpolate(x, y);
		for (int col = first; col < last + 1; col++) {
			altitudes.put(col, spline.value(col));
		}
	}

	public Map<Integer, Double> getAltitudes() {
		return altitudes;
	}

	@Override
	public String toString() {
		final StringBuilder out = new StringBuilder();
		final int diameter = radius * 2 + 1;
		for (int row = 0; row < diameter; row++) {
			for (int col = 0; col < diameter; col++) {

				out.append(altitudes.get(row * diameter + col) != null
						? " " + Math.round(altitudes.get(row * diameter + col) * 100) / 100.
						: "    ");
			}
			out.append("\n");
		}
		return out.toString();
	}
}
