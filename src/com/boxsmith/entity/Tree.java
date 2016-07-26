package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;
import com.boxsmith.level.TileCoordinate;

public class Tree extends Entity {

	public Tree(TileCoordinate spawnpoint){
		x = spawnpoint.getX();
		y = spawnpoint.getY();
	}
	
	public void action() {
		System.out.println("This is a tree.");
	}

	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, Sprite.tree, true);
	}

}
