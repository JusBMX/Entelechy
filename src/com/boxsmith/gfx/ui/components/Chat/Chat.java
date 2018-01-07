package com.boxsmith.gfx.ui.components.Chat;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.gfx.ui.components.ContentBox;

public class Chat extends ContentBox {

    private String text = "Test";
    public Button next = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Next",
            x + 10, y + 102);

    public Chat(Sprite background, int x, int y) {
        super(background, x, y);
        add(next);
    }

    public void setText(String text){
        this.text = text;
    }

    private void textRendering(Screen screen){
        for(int i = 0; i< text.length()/15; i++){
            screen.renderText(text.substring(15*i, ), x, y, false);
        }

    }


    @Override
    public void render(Screen screen){
        screen.renderSprite(x,y,background,false);
        textRendering(screen);
        for(Component component : components){
            component.render(screen);
        }

    }

}
