package com.boxsmith.level.tile;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.sprite.SpriteAnimation;

public abstract class Tile {

	public int x, y;
	public Sprite sprite;
	public SpriteAnimation spriteAnimation;

	public static Tile grassTile = new GrassTile(Sprite.GRASS);
	public static Tile rockTile = new RockTile(Sprite.ROCK);
	public static Tile dirtTile = new FlowerTile(Sprite.DIRT);
	public static Tile voidTile = new VoidTile(Sprite.VOID_SPRITE);

	public final static int COL_GRASS = 0xFF00FF00;
	public final static int COL_ROCK = 0xFFFF0000;
	public final static int COL_DIRT = 0xFF0000FF;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public Tile (SpriteAnimation spriteAnimation) {
		this.spriteAnimation = spriteAnimation;
	}

	public abstract void render(int x, int y, Screen screen);

	public abstract boolean solid();
}
