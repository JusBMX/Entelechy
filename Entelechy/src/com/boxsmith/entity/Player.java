package com.boxsmith.entity;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Chat.Card;
import com.boxsmith.gfx.ui.menus.Play;
import com.boxsmith.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    int xA = 0, yA = 0;

    @Override
    public void mouseClick() {
        try {
                    Play.play.chat.card = new Card("Hello me, meet the real me And my misfit's way of life A dark black past is" +
                " my Most valued possession Hindsight is always 20-20 But looking back it's still a bit fuzzy " +
                "Speak of mutually assured destruction?", null);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public Player() {
        x = 30;
        y = 10;
        width = Sprite.PLAYER_FORWARD.getWidth();
        height = Sprite.PLAYER_FORWARD.getHeight();
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
        screen.renderSprite(x, y, Sprite.PLAYER_FORWARD,true);
    }
}
