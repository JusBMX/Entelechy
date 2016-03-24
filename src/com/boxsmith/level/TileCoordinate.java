package com.boxsmith.level;

import com.boxsmith.entity.Entity;

public class TileCoordinate {

	private int x, y;
	private final static int TILE_SIZE = 16;

	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int distance(int x, int y) {
		return (int) (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)));
	}

	public static int distance(TileCoordinate t1, TileCoordinate t2) {
		return (int) (Math.sqrt(Math.pow(t1.x - t2.x, 2) + Math.pow(t1.y - t2.y, 2)));
	}

	public static TileCoordinate entityCoords(Entity e1) {
		return new TileCoordinate(e1.x / TILE_SIZE, e1.y / TILE_SIZE);
	}

	public boolean equals(TileCoordinate coords) {
		if (coords.x == x && coords.y == y) {
			return true;
		}
		return false;
	}

}
