package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Action;
import com.boxsmith.gfx.ui.components.chat.Card;
import com.boxsmith.gfx.ui.menus.Play;

public class Hans extends Mob {


    private Card answer = new Card(new String[]{"Sure, I got time.","Nah."}, new Action[]{
        (() -> {
            System.out.println("1");
        }),
        (() -> {
            Play.play.chat.hide();
        })
    });

    private Card question = new Card(
        "Do you have time to hear me out?",
        (() -> {
            Play.play.chat.show(answer);
        })
    );

    private Card welcome = new Card(
        "You look like an inquisitive sole, in lands like these that can lead to your demise." +
        "To your luck this town, Tinbur, is the most welcoming area you will find yourself in.",
        (() -> {
            Play.play.chat.show(question);
        })
    );

    public Hans() {
        width = Sprite.PLAYER_FORWARD.getWidth();
        height = Sprite.PLAYER_FORWARD.getHeight();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(x, y, Sprite.PLAYER_SIDE, true);
    }

    @Override
    public void mouseClick() {
        Play.play.chat.show(welcome);
    }
}
