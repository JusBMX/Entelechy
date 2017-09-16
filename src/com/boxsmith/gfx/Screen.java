package com.boxsmith.gfx;

import com.boxsmith.gfx.sprite.*;
import com.boxsmith.level.tile.Tile;

public class Screen {

	public final int width, height;

	public int[] pixels;

	public int xOffset;
	public int yOffset;

	private static final String FONT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890.:-";

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xA00000;
		}
	}

	public void renderText(String msg, int x, int y, boolean fixed) {
		msg = msg.toUpperCase();
		for (int i = 0; i < msg.length(); i++) {
			int charIndex = FONT.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				renderSprite(x + (i * 8), y, new Sprite(8, charIndex % 16, charIndex / 16, SpriteSheet.font), fixed);
			}
		}
	}

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

	public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixed) {
		if (fixed) {
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

	public void renderAn(int xPos, int yPos, SpriteAnimation animation, boolean fixed) {
		if (fixed) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for (int y = 0; y < animation.getHeight(); y++) {
			int yAbs = yPos + y;
			for (int x = 0; x < animation.getWidth(); x++) {
				int xAbs = xPos + x;
				if (xAbs < 0 || xAbs >= width || yAbs < 0 || yAbs >= height)
					continue;
				if(animation.getPixels()[x + y * animation.getWidth()] !=  0xFFFF00FF)
					pixels[xAbs + yAbs * width] = animation.getPixels()[x + y * animation.getWidth()];
			}
		}
	}

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

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
