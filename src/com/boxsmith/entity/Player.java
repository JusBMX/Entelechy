package com.boxsmith.entity;

import java.awt.event.MouseEvent;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.input.Keyboard;
import com.boxsmith.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Mouse mouse;
	private Sprite sprite;
	private boolean walking = false;
	private int anim = 0;
	private Weapons hands = new Weapons(2, 2000, 16);

	public Player(int x, int y, Keyboard input, Mouse mouse) {
		this.x = x;
		this.y = y;
		this.mouse = mouse;
		this.input = input;
		healthPoints = 20;
	}

	public void update() {
		int xA = 0, yA = 0;
		if (input.attack) {
			attack(hands, dir);
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
		if (Mouse.getButton() == MouseEvent.BUTTON1) {
			System.out.println(mouse.screenToWorld(Game.screen)[1]);
		}
		if (xA != 0 || yA != 0) {
			move(xA, yA);
			walking = true;
		} else {
			walking = false;
		}
		if(!isAlive()){
			respawn();
		}

	}


	public void attack(Weapons w, int dir) {
		int weaponX = x, weaponY = y;
		switch (dir) {
		case 0:
			weaponY -= w.range;
			break;
		case 1:
			weaponX += w.range;
			break;
		case 2:
			weaponY += w.range;
			break;
		case 3:
			weaponX -= w.range;
			break;
		}
		for (Mob m : level.getMobs()) {
			if (Math.abs(m.x - weaponX) < 8 && Math.abs(m.y - weaponY) < 16 && !m.equals(this)) {
				System.out.println(w.timer.timeLeft());
				if (w.timer.isTime()) {
					m.healthPoints -= w.damage;
				}
			}
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 25 > 10) {
					sprite = Sprite.player_forward;
				} else {
					flip = 1;
				}
			}
		}
		if (dir == 1)
			sprite = Sprite.player_side;
		if (dir == 2)
			sprite = Sprite.player_back;
		if (dir == 3) {
			sprite = Sprite.player_side;
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
		screen.renderText("Hp: " + healthPoints, x, y - 10, true);
	}
}
