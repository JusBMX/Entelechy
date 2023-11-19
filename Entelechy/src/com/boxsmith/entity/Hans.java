package com.boxsmith.entity;


import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Chat.Card;
import com.boxsmith.gfx.ui.menus.Play;

public class Hans extends Mob{

    public Hans(){
        width = Sprite.PLAYER_FORWARD.getWidth();
        height = Sprite.PLAYER_FORWARD.getHeight();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(x,y, Sprite.PLAYER_SIDE, true);
    }

    @Override
    public void mouseClick() {
        try {
            Play.play.chat.card = new Card("You look like an inquisitive sole, in lands like these that can lead to your demise." +
            "To your luck this town, Tinbur, is the most welcoming area you will find yourself in.", null);
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
