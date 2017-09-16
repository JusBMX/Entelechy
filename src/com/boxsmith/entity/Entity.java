package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.level.Level;
import com.boxsmith.level.TileCoordinate;

public abstract class Entity{
	public int x, y;
	protected Level level;
	protected TileCoordinate spawnpoint;
	//private boolean removed;

	public abstract void update();
	

	public abstract void render(Screen screen);

	public boolean collision(int xAbs, int yAbs) {
		boolean sold = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xAbs) + c % 2 * 10 - 12) / 16;
			int yt = ((y + yAbs) + c % 2 * 10 + 4) / 16;

			if (level.getTile(xt, yt).soild())
				sold = true;
		}
		return sold;
	}
	
	
	
	public void init(Level level) {
		this.level = level;
	}
}
