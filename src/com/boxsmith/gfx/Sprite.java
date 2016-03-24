package com.boxsmith.gfx;

public class Sprite {

	private int x, y;
	private SpriteSheet sheet;

	public final int SIZE;
	public int[] pixels;
	private int width, height;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x000000);

	public static Sprite player_forward = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite man_forward = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite man_back = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite man_side = new Sprite(32, 2, 6, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.width = size;
		this.height = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int height, int width, int color) {
		this.width = width;
		this.height = height;
		SIZE = -1;
		pixels = new int[SIZE];
		setColor(color);
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);

	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
