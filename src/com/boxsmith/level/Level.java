package com.boxsmith.level;

import java.util.ArrayList;
import java.util.List;

import com.boxsmith.entity.Entity;
import com.boxsmith.entity.Mob;
import com.boxsmith.entity.Player;
import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;
import com.boxsmith.level.tile.Tile;

public abstract class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();

	public static Level spawnlevel = new SpawnLevel("/Textures/Levels/SLevel.png");

	public Level(int width, int height) {
		this.height = height;
		this.width = width;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}

	public void update() {
		respawnHandler();

		for (Entity e : entities) {
			if (!e.isRemove())
				e.update();
		}
	}

	public void respawnHandler() {
		for (Mob m : getMobs()) {
			if (!m.isAlive()) {
				if (m.respawnTimer == null) {
					m.respawnTimer = new GameTimer(m.respawnTime);
					m.remove();
				} else if (m.respawnTimer.isTime()) {
					m.respawnTimer = null;
					m.add();
					m.healthPoints = 2;
				}
			}
		}
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (Entity e : entities) {
			if (!e.isRemove())
				e.render(screen);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public List<Mob> getMobs() {
		List<Mob> mobs = new ArrayList<Mob>();
		for (Entity e : entities) {
			if (e.getClass().getSuperclass().equals(Mob.class)) {
				mobs.add((Mob) e);
			}
		}
		return mobs;
	}

	public Entity getPlayer() {
		for (Entity e : entities) {
			if (e.getClass().equals(Player.class)) {
				return e;
			}
		}
		return null;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.COL_GRASS)
			return Tile.grassTile;
		if (tiles[x + y * width] == Tile.COL_ROCK)
			return Tile.rockTile;
		if (tiles[x + y * width] == Tile.COL_DIRT)
			return Tile.dirtTile;
		return Tile.voidTile;
	}
}
