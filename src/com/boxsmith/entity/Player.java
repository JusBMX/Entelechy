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

	public Player(int x, int y, Keyboard input, Mouse mouse) {
		this.x = x;
		this.y = y;
		this.mouse = mouse;
		this.input = input;
		hp = 20;
	}

	public void update() {
		int xA = 0, yA = 0;
		
		if(input.attack){
			
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
		clear();
	}

	private void clear() {
		for (int i = 0; i < projectile.size(); i++) {
			Projectile p = projectile.get(i);
			if (p.isRemoved())
				projectile.remove(i);
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
		screen.renderText("Hp: " + hp, x, y - 10, true);
	}
}
