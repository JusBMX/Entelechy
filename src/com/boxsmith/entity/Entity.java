package com.boxsmith.entity;

import java.util.Random;

import com.boxsmith.gfx.Screen;
import com.boxsmith.level.Level;
import com.boxsmith.level.TileCoordinate;

public abstract class Entity {
	public int x, y;
	protected Level level;
	protected Random random;
	protected TileCoordinate spawnpoint;
	private boolean removed;

	public abstract void update();
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemove(){
		return removed;
	}

	public void add(){
		removed = false;
	}
	
	public abstract void render(Screen screen);

	public void intit(Level level) {
		this.level = level;
	}
}
