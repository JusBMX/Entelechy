package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public class Tree extends Entity {
    @Override
    public void render(Screen screen) {
        screen.renderSprite(x, y, Sprite.TREE, false);
    }

    @Override
    public void update() {

    }
}
