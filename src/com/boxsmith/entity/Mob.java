package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	public int direction, healthPoints, maxHealthPoints, respawnTime;

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
	}

	public boolean isAlive() {
		if (healthPoints < 1) {
			return false;
		}
		return true;
	}


}
