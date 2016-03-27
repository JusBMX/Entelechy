package com.boxsmith.entity;

import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0, healthPoints, respawnTime;
	protected boolean moving = false;
	GameTimer respawnTimer;

	public abstract void update();

	public abstract void render(Screen screen);

	public void move(int xAbs, int yAbs) {
		if (xAbs != 0 && yAbs != 0) {
			move(xAbs, 0);
			move(0, yAbs);
			return;
		}
		if (xAbs > 0)
			dir = 1;
		if (xAbs < 0)
			dir = 3;
		if (yAbs > 0)
			dir = 2;
		if (yAbs < 0)
			dir = 0;
		if (!collision(xAbs, yAbs)) {
			x += xAbs;
			y += yAbs;
		}
	}

	public void attack(Weapons w, Mob m) {
		if (distance(m.x, m.y) <= w.range && w.timer.isTime()) {
			m.healthPoints -= w.damage;
		}
	}

	public boolean isAlive() {
		if (healthPoints < 1) {
			return false;
		}
		return true;
	}

	public void respawn() {
		level.removeEntity(this);
		if (respawnTimer == null) {
			respawnTimer = new GameTimer(respawnTime);
		} else if (respawnTimer.isTime()) {
			healthPoints = 10;
			level.addEntity(this);
		}
	}

	public int distance(int x, int y) {
		return (int) (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)));
	}

	private boolean collision(int xAbs, int yAbs) {
		boolean soild = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xAbs) + c % 2 * 10 - 12) / 16;
			int yt = ((y + yAbs) + c % 2 * 10 + 4) / 16;

			if (level.getTile(xt, yt).soild())
				soild = true;
		}
		return soild;
	}
}