package com.boxsmith.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.boxsmith.entity.Man;
import com.boxsmith.entity.Player;
import com.boxsmith.game.Game;

public class SpawnLevel extends Level {

	private Player player;
	private Man man;

	public SpawnLevel(String path) {
		super(path);
		TileCoordinate playerSpawn = new TileCoordinate(10, 10);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), Game.keys, Game.mouse);
		man = new Man(2*16, 2*16);
		addEntity(man);
		addEntity(player);
		player.intit(this);
		man.intit(this);
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
