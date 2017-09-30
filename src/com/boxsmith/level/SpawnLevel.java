package com.boxsmith.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import com.boxsmith.entity.Player;
import com.boxsmith.entity.Tree;
import com.boxsmith.level.tile.TileCoordinate;

public class SpawnLevel extends Level {

	private Player player;

	public SpawnLevel(String path) {
		super(path);

		//loadEntities(path + "Entities");

		player = new Player(new TileCoordinate(10, 10));
		addEntity(player);

		player.init(this);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getHeight();
			height = image.getWidth();
			tilesMapData = new int[width * height];
			image.getRGB(0, 0, width, height, tilesMapData, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fail to load level image!");
		}
	}


}
