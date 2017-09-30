package com.boxsmith.level;

import com.boxsmith.entity.Player;
import com.boxsmith.entity.Tree;
import com.boxsmith.level.tile.TileCoordinate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu extends Level {

    public MainMenu(String path) {
        super(path);

        loadEntities("/Levels/Main Menu/MainMenuTilesEntities.png");
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            height = image.getHeight();
            width = image.getWidth();
            tilesMapData = new int[width * height];
            image.getRGB(0, 0, width, height, tilesMapData, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fail to load level image!");
        }
    }

    public void loadEntities(String path){
        try {
            int[] temp = new int[width * height];
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            image.getRGB(0,0, width, height, temp, 0, width);
            for(int i = 0; i < temp.length; i++){
                if(temp[i] == 0xFF3B00FF){
                    Tree tree = new Tree();
                    tree.x = i % width * 16;
                    tree.y = i / width * 16;
                    addEntity(tree);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
