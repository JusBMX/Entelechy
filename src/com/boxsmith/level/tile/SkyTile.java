package com.boxsmith.level.tile;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public class SkyTile extends Tile {

    public SkyTile(Sprite sprite){
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    @Override
    public boolean solid() {
        return false;
    }
}
