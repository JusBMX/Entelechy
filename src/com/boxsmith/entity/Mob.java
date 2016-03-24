package com.boxsmith.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected int hp;

	protected List<Projectile> projectile = new ArrayList<Projectile>();
	
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
	
	public void attack(Weapons w, Mob m){
		m.hp -= w.damage;
	}

	public void walkToTile(TileCoordinate tile){

	}

	public int distance(int x, int y) {
		return (int) (Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)));
	}
	
	public static int distance(Entity e1, Entity e2) {
		return (int) (Math.sqrt(Math.pow(e1.x - e2.x, 2) + Math.pow(e1.y - e2.y, 2)));
	}
	
	private boolean collision(int xAbs, int yAbs) {
		boolean soild = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xAbs) + c % 2 * 10 - 6) / 16;
			int yt = ((y + yAbs) + c % 2 * 10 + 4) / 16;

			if (level.getTile(xt, yt).soild())
				soild = true;
		}
		return soild;
	}
}
