package de.shazzarr.loratien.server.bean.worldmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.shazzarr.loratien.server.bean.worldmap.sector.Sector;
import de.shazzarr.loratien.server.entity.Tile;
import de.shazzarr.loratien.server.entity.tile.water.Ocean;

public class Worldmap {
	final long seed = 123456L;
	final Random rand = new Random(seed);
	private final List<Tile> tiles;
	private final int height, width;

	public Worldmap(int radius) {
		this(radius * 2 + 1, radius * 2 + 1);
	}

	public Worldmap(int width, int height) {
		this.height = height;
		this.width = width;
		this.tiles = createWorldmap(width, height);
		// createRandomizedSector();
	}

	private Sector createRandomizedSector() {
		final Sector sector = new Sector(rand.nextLong());
		System.out.println(sector);
		return sector;
	}

	private List<Tile> createWorldmap(int width, int height) {
		final List<Tile> hexes = new ArrayList<>();
		for (int col = 0; col < this.width; col++) {
			for (int row = 0; row < this.height; row++) {
				hexes.add(new Ocean(col, row, -99.0));
			}
		}
		return hexes;
	}

	public List<Tile> getNeighborTiles(int col, int row, int radius, boolean withOriginTile) {
		radius = radius < 0 ? 0 : radius;
		final List<Tile> neighbors = new ArrayList<>();

		for (int modCol = -radius; modCol <= radius; modCol++) {
			final int rowShiftLow = Math.abs(modCol) / 2 + (col % 2) * (Math.abs(modCol) % 2);
			final int rowShiftHigh = (int) (Math.ceil(Math.abs(modCol) / 2.0) + (col % 2) * (Math.abs(modCol) % 2));
			for (int modRow = -radius + rowShiftLow; modRow <= radius - rowShiftHigh; modRow++) {
				if ((withOriginTile || modCol != 0 || modRow != 0) && (-1 < col + modCol && col + modCol < this.width)
						&& -1 < row + modRow && row + modRow < this.height) {
					neighbors.add(this.getTile((this.width + col + modCol) % this.width, row + modRow));
				}
			}
		}
		return neighbors;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int col, int row) {
		if (col > -1 && row > -1 && col < this.width && row < this.height) {
			return tiles.get(row * this.width + col);
		}
		return null;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public int getWidth() {
		return width;
	}

}