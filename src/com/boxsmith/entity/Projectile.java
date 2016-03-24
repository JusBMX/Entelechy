package com.boxsmith.entity;

import com.boxsmith.gfx.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double distance;
	protected double speed, rateOfFire, range, damge;

	public Projectile(int x, int y, double dir) {
		this.angle = dir;
		this.x = x;
		this.y = y;
		xOrigin = x;
		yOrigin = y;
	}

	protected void move() {
		if (calcDistance() > range)
			remove();
	}

	public double calcDistance() {
		double dis = 0;
		dis = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dis;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}
}
