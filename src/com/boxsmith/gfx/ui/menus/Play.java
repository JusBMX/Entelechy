package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Chat.Chat;

public class Play extends Menu {

    private Chat chat = new Chat(Sprite.CHAT,0,200);

    public Play(){
        add(chat);
    }

    @Override
    public void mouseClick() {
        if(findComponentAtMouse() == chat.next){

        }
    }
}
