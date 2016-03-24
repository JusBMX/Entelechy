package com.boxsmith.level.tile;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.Sprite;

public abstract class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile rockTile = new RockTile(Sprite.rock);
	public static Tile dirtTile = new FlowerTile(Sprite.dirt);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public final static int COL_GRASS = 0xFF00FF00;
	public final static int COL_ROCK = 0xFFFF0000;
	public final static int COL_DIRT = 0xFF0000FF;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public abstract void render(int x, int y, Screen screen);

	public abstract boolean soild();
}
