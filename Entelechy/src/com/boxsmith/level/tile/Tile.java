package com.boxsmith.level.tile;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.sprite.SpriteAnimation;

public abstract class Tile {

	public int x, y;
	public Sprite sprite;
	public SpriteAnimation spriteAnimation;

	public static Tile grassTile = new GrassTile(Sprite.GRASS);
	public static Tile grassTileMENU = new GrassTile(Sprite.GRASS_MAIN_MENU);
	public static Tile skyTile = new SkyTile(Sprite.SKY_MAIN_MENU);
	public static Tile rockTile = new RockTile(Sprite.ROCK);
	public static Tile dirtTile = new FlowerTile(Sprite.DIRT);
	public static Tile voidTile = new VoidTile(Sprite.VOID_SPRITE);

	public final static int COLOR_GRASS = 0xFF00FF00;
	public final static int COLOR_SKY = 0xFF00AAFF;
	public final static int COLOR_ROCK = 0xFFFF0000;
	public final static int COLOR_GRASS_MENU = 0xFFA56400;
	public final static int COLOR_DIRT = 0xFF0000FF;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public Tile (SpriteAnimation spriteAnimation) {
		this.spriteAnimation = spriteAnimation;
	}

	public abstract void render(int x, int y, Screen screen);

	public abstract boolean solid();
}
