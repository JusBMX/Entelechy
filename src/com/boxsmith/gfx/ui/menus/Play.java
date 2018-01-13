package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

import com.boxsmith.gfx.ui.components.Chat.Chat;

public class Play extends Menu {

    public static Play play = new Play();

    public Chat chat = new Chat(0,200);

    private Play(){
        add(chat);
        add(chat.nextButton);
    }

    @Override
    public void render(Screen screen) {
        chat.render(screen);
    }

    @Override
    public void tick() {
        chat.tick();
    }

    @Override
    public void mouseClick() {
        if (chat.card != null) {
            if (findComponentAtMouse() == chat.nextButton) {
                System.out.println("Hi");
                chat.card = chat.card.nextCard;
            }
        }
    }
}
