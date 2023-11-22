package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.gfx.ui.components.Chat.Chat;

public class Play extends Menu {

    public static Play play = new Play();

    public Chat chat = new Chat(0, 200, this);
    //private Button exit = new Button()

    private Play(){
        
    }

    @Override
    public Component mouseClick() {
        Component component = findComponentAtMouse();
        if (chat.card != null) {
            if (component == chat.card.option) {
                chat.card.option.action.actionMethod();
            }
        }
        return component;
    }
}
