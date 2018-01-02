package com.boxsmith.level;

import java.util.ArrayList;
import java.util.List;

import com.boxsmith.entity.Entity;
import com.boxsmith.entity.Mob;
import com.boxsmith.entity.Player;
import com.boxsmith.gfx.Screen;
import com.boxsmith.level.tile.Tile;

public abstract class Level {

	int width, height;
	int[] tilesMapData;

	public int xScroll = 0, yScroll = 0;

	private List<Entity> entities = new ArrayList<>();

	public Level(String path) {
		loadLevel(path);
	}

	protected abstract void loadLevel(String path);

	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
	}

	public void render(Screen screen) {
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
			e.render(screen);
		}
	}

	public void addEntity(Entity e) {
		e.init(this);
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public List<Entity> getEntities(){
		return entities;
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

	public Player getPlayer() {
		for (Entity e : entities) {
			if (e.getClass().equals(Player.class)) {
				return (Player) e;
			}
		}
		return null;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tilesMapData[x + y * width] == Tile.COLOR_GRASS)
			return Tile.grassTile;
		if (tilesMapData[x + y * width] == Tile.COLOR_ROCK)
			return Tile.rockTile;
		if (tilesMapData[x + y * width] == Tile.COLOR_DIRT)
			return Tile.dirtTile;
		if (tilesMapData[x + y * width] == Tile.COLOR_GRASS_MENU)
			return Tile.grassTileMENU;
		if (tilesMapData[x + y * width] == Tile.COLOR_SKY)
			return Tile.skyTile;
		return Tile.voidTile;
	}
}
