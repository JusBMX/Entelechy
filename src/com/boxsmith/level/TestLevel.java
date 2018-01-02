package com.boxsmith.level;

import com.boxsmith.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestLevel extends Level {

    public TestLevel(String path) {
        super(path);
        addEntity(new Player());
    }

    @Override
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(MainMenuLevel.class.getResource(path));
            height = image.getHeight();
            width = image.getWidth();
            tilesMapData = new int[width * height];
            image.getRGB(0, 0, width, height, tilesMapData, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fail to load level image!");
        }
    }
}
