package com.boxsmith.entity;

import java.util.Random;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public class Hostile extends Mob {
	private Random ran = new Random();
	private boolean walking = false;
	private int anim = 0;
	private Weapons hands = new Weapons(2, 2000, 16);

	public Hostile(TileCoordinate spawnpoint) {
		this.spawnpoint = spawnpoint;
		x = spawnpoint.getX();
		y = spawnpoint.getY();
		healthPoints = 10;
		respawnTime = 5000;
	}

	public void update() {
		int xA = 0, yA = 0;
		int playerX = level.getPlayer().x;
		int playerY = level.getPlayer().y;
		
		attack(hands, (Mob) level.getPlayer());
		
		if (distance(playerX, playerY) <= 16) {
			return;
		}
		
		if (TileCoordinate.distance(spawnpoint, TileCoordinate.entityCoords(level.getPlayer())) < 200
				&& distance(playerX, playerY) > 16 && distance(playerX, playerY) < 100) {
			if (playerX > x) {
				xA++;
			}
			if (playerX < x) {
				xA--;
			}
			if (playerY > y) {
				yA++;
			}
			if (playerY < y) {
				yA--;
			}

		} else {
			//if (1 > 480) {
			//	spawn = new TileCoordinate(ran.nextInt(4) + 2, ran.nextInt(4) + 2);
			//}
			if (spawnpoint.getX() > x) {
				xA++;
			}
			if (spawnpoint.getX() < x) {
				xA--;
			}
			if (spawnpoint.getY() > y) {
				yA++;
			}
			if (spawnpoint.getY() < y) {
				yA--;
			}
		}
		if (anim < 100) {
			anim++;
		} else {
			anim = 0;
		}
		if (xA != 0 || yA != 0) {
			move(xA, yA);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if (direction == 0) {
			sprite = Sprite.man_forward;
			if (walking) {
				if (anim % 25 > 10) {
					sprite = Sprite.man_forward;
				} else {
					flip = 1;
				}
			}
		}
		if (direction == 1)
			sprite = Sprite.man_side;
		if (direction == 2)
			sprite = Sprite.man_back;
		if (direction == 3) {
			sprite = Sprite.man_side;
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
		screen.renderText("Hp: " + healthPoints, x, y - 10, true);
	}

}
