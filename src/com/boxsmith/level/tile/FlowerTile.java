package com.boxsmith.level.tile;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public class FlowerTile extends Tile {

	public FlowerTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	@Override
	public boolean soild() {
		return false;
	}

}
