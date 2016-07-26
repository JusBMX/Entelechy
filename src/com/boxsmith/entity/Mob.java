package com.boxsmith.entity;

import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected boolean moving = false;
	public int direction, healthPoints, maxHealthPoints, respawnTime;
	public GameTimer respawnTimer;
	public TileCoordinate spawnpoint;

	public abstract void update();

	public abstract void render(Screen screen);

	public void move(int xAbs, int yAbs) {
		if (xAbs != 0 && yAbs != 0) {
			move(xAbs, 0);
			move(0, yAbs);
			return;
		}
		if (xAbs > 0)
			direction = 1;
		if (xAbs < 0)
			direction = 3;
		if (yAbs > 0)
			direction = 2;
		if (yAbs < 0)
			direction = 0;
		if (!collision(xAbs, yAbs)) {
			x += xAbs;
			y += yAbs;
		}
	}
	
	public void respawn(){
		healthPoints = maxHealthPoints;
		x = spawnpoint.getX();
		y = spawnpoint.getY();
	}



	public boolean isAlive() {
		if (healthPoints < 1) {
			return false;
		}
		return true;
	}

	public int distance(int x, int y) {
		return (int) (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)));
	}


}
