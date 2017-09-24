package com.boxsmith.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import com.boxsmith.entity.Player;
import com.boxsmith.level.tile.TileCoordinate;

public class SpawnLevel extends Level {

	private Player player;

	public SpawnLevel(String path) {
		super(path);

		player = new Player(new TileCoordinate(10, 10));
		addEntity(player);

		player.init(this);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int h = width = image.getHeight();
			int w = height = image.getWidth();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fail to load level image!");
		}
	}

	protected void generateLevel() {
	}
}
