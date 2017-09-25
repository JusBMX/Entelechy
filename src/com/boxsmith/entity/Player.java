package com.boxsmith.entity;

import java.awt.event.MouseEvent;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.input.*;
import com.boxsmith.level.tile.TileCoordinate;

public class Player extends Mob {

	private Keyboard input;
	private boolean walking = false;
	private int anim = 0;


	public Player(TileCoordinate spawnpoint) {
		this.spawnpoint = spawnpoint;
		x = spawnpoint.getX();
		y = spawnpoint.getY();

		maxHealthPoints = 10;
		healthPoints = maxHealthPoints;
		respawnTime = 2000;
	}

	public void update() {
		int xA = 0, yA = 0;
		input = Game.keys;
		if (input.use) {
			use();
		}
		if (anim < 100) {
			anim++;
		} else {
			anim = 0;
		}
		if (input.up)
			yA--;
		if (input.down)
			yA++;
		if (input.left)
			xA--;
		if (input.right)
			xA++;
		if (Mouse.getButton() == MouseEvent.BUTTON1) { // create a field in
														// Mouse for Button1

		}
		if (xA != 0 || yA != 0) {
			move(xA, yA);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void use() {

	}

	public void render(Screen screen) {
		int flip = 0;
		if (direction == 0) {
			sprite = Sprite.PLAYER_FORWARD;
			if (walking) {
				if (anim % 25 > 10) {
					sprite = Sprite.PLAYER_FORWARD;
				} else {
					flip = 1;
				}
			}
		}
		if (direction == 1)
			sprite = Sprite.PLAYER_SIDE;
		if (direction == 2)
			sprite = Sprite.PLAYER_BACK;
		if (direction == 3) {
			sprite = Sprite.PLAYER_SIDE;
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);

	}

}
