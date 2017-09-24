package com.boxsmith.gfx;

import com.boxsmith.gfx.sprite.*;
import com.boxsmith.level.tile.Tile;

public class Screen {

	public final int width, height; // Rendering resolution.
	public int xOffset, yOffset; // Screen offsets.
	public int[] pixels; // Pixel data.

	private static final String FONT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890.:-"; // Used for mapping in game text.

	/**
	 * Creates a new canvas for rendering graphics.
	 * @param width The number of pixels horizontally.
	 * @param height The number of pixels vertically.
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	/**
	 * Clears the screen of old color data with the color red.
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x550000;
		}
	}

	/**
	 * Renders text. Font sprites must be 8x8 pixels.
	 * @param message The text to be render.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param fixedToWorld True to use world coordinates. False to use screen coordinates.
	 */
	public void renderText(String message, int x, int y, boolean fixedToWorld) {
		message = message.toUpperCase();
		for (int i = 0; i < message.length(); i++) {
			int charIndex = FONT.indexOf(message.charAt(i));
			if (charIndex >= 0) {
				renderSprite(x + (i * 8), y,
						new Sprite(8, (charIndex % 16) * 8,(charIndex / 16) * 8, SpriteSheet.font),
						fixedToWorld);
			}
		}
	}

	/**
	 * Renders a world tile. Only renders tiles visible on the screen.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param tile The tile to render.
	 */
	@Deprecated
	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < tile.sprite.getDimension(); y++) {
			int yAbs = yPos + y;
			for (int x = 0; x < tile.sprite.getDimension(); x++) {
				int xAbs = xPos + x;
				if (xAbs < -tile.sprite.getDimension() || xAbs >= width || yAbs < 0 || yAbs >= height)
					break;
				if (xAbs < 0)
					xAbs = 0;
				pixels[xAbs + yAbs * width] = tile.sprite.getPixels()[x + y * tile.sprite.getDimension()];
			}
		}
	}

	/**
	 * Renders a sprite to the screen.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param sprite The sprite to render.
	 * @param fixedToWorld True to use world coordinates. False to use screen coordinates.
	 */
	public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixedToWorld) {
		if (fixedToWorld) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yAbs = yPos + y;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xAbs = xPos + x;
				if (xAbs < 0 || xAbs >= width || yAbs < 0 || yAbs >= height)
					continue;
				if(sprite.getPixels()[x + y * sprite.getWidth()] !=  0xFFFF00FF)
					pixels[xAbs + yAbs * width] = sprite.getPixels()[x + y * sprite.getWidth()];
			}
		}
	}

	/**
	 * Renders a animation to the screen.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param animation The sprite to render.
	 * @param fixedToWorld True to use world coordinates. False to use screen coordinates.
	 */
	public void renderAnimation(int xPos, int yPos, SpriteAnimation animation, boolean fixedToWorld) {
		renderSprite(xPos, yPos, animation.getCurrentFrame(), fixedToWorld);
	}

	/**
	 * Renders the player to the screen.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param sprite The sprite to render.
	 * @param flip Rotate the sprite.
	 */
	@Deprecated
	public void renderPlayer(int xPos, int yPos, Sprite sprite, int flip) {
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0; y < 32; y++) {
			int yAbs = yPos + y;
			int yFlip = y;
			if (flip == 2 || flip == 3) {
				yFlip = sprite.getHeight() - 1 - y;
			}
			for (int x = 0; x < 16; x++) {
				int xAbs = xPos + x;
				int xFlip = x;
				if (flip == 1 || flip == 3) {
					xFlip = sprite.getWidth() - 1 - x;
				}
				if (xAbs < -16 || xAbs >= width || yAbs < 0 || yAbs >= height)
					break;
				if (xAbs < 0)
					xAbs = 0;
				int color = sprite.getPixels()[xFlip + yFlip * sprite.getWidth()];
				if (color != 0xFFFF00FF)
					pixels[xAbs + yAbs * width] = color;
			}
		}
	}

	/**
	 * Renders a 1x1 pixel point on the screen.
	 * @param xPos The x coordinate.
	 * @param yPos The y coordinate.
	 * @param color The color of the dot.
	 * @param fixedToWorld True to use world coordinates. False to use screen coordinates.
	 */
	public void renderPoint(int xPos, int yPos, int color, boolean fixedToWorld){
		if (fixedToWorld) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= height){
			return;
		}
		pixels[xPos + yPos * width] = color;
	}

	/**
	 * Sets both x and y offsets. (Moves the 'camera' around the world)
	 * @param xOffset The x offset.
	 * @param yOffset The y offset.
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
