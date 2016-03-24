package com.boxsmith.entity;

import java.util.Random;

import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public class Man extends Mob {
	private Random ran = new Random();
	private boolean walking = false;
	private int anim = 0;
	private TileCoordinate spawn = new TileCoordinate(2, 2);
	private Weapons hands = new Weapons(2, 60);
	private GameTimer timer = new GameTimer(2000);

	public Man(int x, int y) {
		this.x = x;
		this.y = y;
		hp = 10;
	}

	public void update() {
		int xA = 0, yA = 0;
		int playerX = level.getPlayer().x;
		int playerY = level.getPlayer().y;
		
		if (distance(playerX, playerY) <= 16) {
			if(timer.isTime())
				attack(hands, (Mob) level.getPlayer());
			return;
		}
		if (TileCoordinate.distance(spawn, TileCoordinate.entityCoords(level.getPlayer())) < 200
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
			if (1 > 480) {
				spawn = new TileCoordinate(ran.nextInt(4) + 2, ran.nextInt(4) + 2);
			}
			if (spawn.getX() > x) {
				xA++;
			}
			if (spawn.getX() < x) {
				xA--;
			}
			if (spawn.getY() > y) {
				yA++;
			}
			if (spawn.getY() < y) {
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
		if (dir == 0) {
			sprite = Sprite.man_forward;
			if (walking) {
				if (anim % 25 > 10) {
					sprite = Sprite.man_forward;
				} else {
					flip = 1;
				}
			}
		}
		if (dir == 1)
			sprite = Sprite.man_side;
		if (dir == 2)
			sprite = Sprite.man_back;
		if (dir == 3) {
			sprite = Sprite.man_side;
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
		// screen.renderText("Hp: " + hp, x, y - 10, true);
	}

}
