package com.boxsmith.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.boxsmith.entity.Hostile;
import com.boxsmith.entity.Player;
import com.boxsmith.entity.Tree;

public class SpawnLevel extends Level {

	private Player player;
	private Hostile hostile;
	private Tree tree;

	public SpawnLevel(String path) {
		super(path);
		tree = new Tree(new TileCoordinate(10, 5));
		player = new Player(new TileCoordinate(10, 10));
		hostile = new Hostile(new TileCoordinate(5, 5));
		addEntity(tree);
		addEntity(hostile);
		addEntity(player);

		player.intit(this);
		hostile.intit(this);
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
