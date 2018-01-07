package com.boxsmith.gfx.sprite;

public class Sprite {

	private SpriteSheet sheet; // The sprite sheet the sprite is located on.

	private int x, y; // The coordinate of the sprite on the sprite sheet.
	private int[] pixels; // PNG pixel data for the sprite.
	private int width, height; // The width and height of the sprite in pixels.
    private int dimension; // The width and height of the sprite in pixels.

	public static final Sprite GRASS = new Sprite(16, 0, 0, SpriteSheet.TILES);
	public static final Sprite ROCK = new Sprite(16, 16, 0, SpriteSheet.TILES);
	public static final Sprite DIRT = new Sprite(16, 32, 0, SpriteSheet.TILES);
	public static final Sprite TREE = new Sprite(16*7, 16*2, 16, SpriteSheet.TILES);
	public static final Sprite GRASS_MAIN_MENU = new Sprite(16, 0, 16*4, SpriteSheet.TILES);
	public static final Sprite SKY_MAIN_MENU = new Sprite(16, 16*7, 0, SpriteSheet.TILES);
	public static final Sprite VOID_SPRITE = new Sprite(16, 0x000000);

	public static final Sprite PLAYER_FORWARD = new Sprite(32, 16, 0, 224, SpriteSheet.TILES);
	public static final Sprite PLAYER_BACK = new Sprite(32, 16, 16, 224, SpriteSheet.TILES);
	public static final Sprite PLAYER_SIDE = new Sprite(32, 16, 32, 224, SpriteSheet.TILES);

	public static final Sprite BUTTON = new Sprite(16, 112, 0, 0, SpriteSheet.MENUS);
	public static final Sprite BUTTON_ACTIVE = new Sprite(16, 112, 0, 16, SpriteSheet.MENUS);
	public static final Sprite CHAT = new Sprite(128, 256, 0, 32, SpriteSheet.MENUS);

    /**
     * Creates a new sprite with the same width and height.
     * Width and height are set equal to <code>dimension</code>
     *
     * @param dimension The width and height of the sprite in pixels.
     * @param x The x location of the sprite in the sprite sheet <code>sheet</code>
     * @param y The y location of the sprite in the sprite sheet <code>sheet</code>
     * @param sheet The sprite sheet the sprite is located on.
     */
	public Sprite(int dimension, int x, int y, SpriteSheet sheet) {
		pixels = new int[dimension * dimension];
        this.dimension = dimension;
		this.width = dimension;
		this.height = dimension;
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		load();
	}

    /**
     * Creates a new sprite. <code>dimension</code> is null.
     *
     * @param height The height of the sprite in pixels.
     * @param width The width of the sprite in pixels.
     * @param x The x location of the sprite in the sprite sheet <code>sheet</code>
     * @param y The y location of the sprite in the sprite sheet <code>sheet</code>
     * @param sheet The sprite sheet the sprite is located on.
     */
	public Sprite(int height, int width, int x, int y, SpriteSheet sheet) {
		pixels = new int[height * width];
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.sheet = sheet;
		load();
	}

    /**
     * Creates a square sprite of one color. Used for debugging.
     * @param dimension The width and height of the sprite in pixels.
     * @param color The color of the sprite (RGB: 255,255,255)
     */
	public Sprite(int dimension, int color) {
        pixels = new int[dimension * dimension];
        this.dimension = dimension;
		this.width = dimension;
		this.height = dimension;
		setColor(color);
	}

    /**
     * Sets the color of the pixels.
     * @param color The color of the sprite (RGB: 255,255,255)
     */
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

    /**
     * Loads the sprite's pixels data into the pixels array. Loads left to right, top to bottom.
     */
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getDIMENSION()];
			}
		}
	}

    /**
     * @return The width of the sprite in pixels.
     */
	public int getWidth() {
		return width;
	}

    /**
     * @return The height of the sprite in pixels.
     */
	public int getHeight() {
		return height;
	}

    /**
     * @return The dimension of the sprite in pixels. Returns <code>null</code> if width and height are not equal.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @return A copy of the pixel array. This way the array is immutable outside the class.
     */
    public int[] getPixels(){
        return pixels;
    }
}
