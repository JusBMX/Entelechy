package com.boxsmith.entity;

import java.util.Random;

import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public class Hostile extends Mob {
	private Random ran = new Random();
	private boolean walking = false;
	private int anim = 0;
	private Weapons hands = new Weapons(2, 2000, 16);
	private GameTimer timer = new GameTimer(3000);
	private int mobLevel;

	public Hostile(TileCoordinate spawnpoint) {
		this.spawnpoint = spawnpoint;
		x = spawnpoint.getX();
		y = spawnpoint.getY();
		mobLevel = 2;
		maxHealthPoints = (int) Math.exp(mobLevel/10) + 10;
		healthPoints = maxHealthPoints;
		respawnTime = 2000;

	}
	


	public void update() {
		int xA = 0, yA = 0;
		int playerX = level.getPlayer().x;
		int playerY = level.getPlayer().y;
		if (!isAlive()) {
			if (respawnTimer == null) {
				respawnTimer = new GameTimer(respawnTime);
			} else if (respawnTimer.isTime()) {
				respawnTimer = null;
				respawn();
			}
			return;
		}
		
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
			if (timer.isTime()) {
				spawnpoint = new TileCoordinate(ran.nextInt(4) + 2, ran.nextInt(4) + 2);
			}
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

	public void attack(Weapons w, Mob m) {
		if (distance(m.x, m.y) <= w.range && w.timer.isTime()) {
			m.healthPoints -= w.damage;
		}
	}
	
	public void render(Screen screen) {
		int flip = 0;
		
		if (!isAlive()) {
			return;
		}
		
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
