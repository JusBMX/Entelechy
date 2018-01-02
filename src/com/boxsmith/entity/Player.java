package com.boxsmith.entity;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;

    public Player() {
        x = 30;
        y = 10;
    }

    @Override
    public void tick() {
        int xA = 0, yA = 0;
        input = Game.keys;
        if (input.up)
            yA--;
        if (input.down)
            yA++;
        if (input.left)
            xA--;
        if (input.right)
            xA++;
        if (xA != 0 || yA != 0) {
            move(xA, yA);
        }
        level.xScroll = x;
        level.yScroll = y;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(x,y, Sprite.TREE,true);
    }
}
