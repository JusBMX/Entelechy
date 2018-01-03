package com.boxsmith.entity;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    int xA = 0, yA = 0;
    public Player() {
        x = 30;
        y = 10;
    }

    @Override
    public void tick() {
        xA = 0; yA = 0;
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
        level.xScroll = x - Game.screen.width / 2;
        level.yScroll = y - Game.screen.height / 2;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(x, y, Sprite.DIRT,true);

        for (int c = 0; c < 2; c++) {
            int xt = ((x + xA));
            int yt = ((y + yA) + c * 16);
            screen.renderPoint(xt, yt, 0xFFFFFFFF,true);
        }


    }
}
