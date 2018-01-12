package com.boxsmith.gfx.ui.components.Chat;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;

public class Chat extends Component {

    public Card card;
    public Button nextButton;

    public Chat(Sprite background, int x, int y) {
        this.x = x;
        this.y = y;
        nextButton = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Next", x, y);
    }

    @Override
    public void render(Screen screen){
        if(card == null){
            return;
        }
        //screen.renderSprite(x, y, background,false);
        String[] text = card.text.split("(?<=\\G.{31})");
        for(int i = 0; i < text.length; i++){
            screen.renderText(text[i], x + 4, y + i * 16 + 4, false);
        }
        nextButton.render(screen);
    }

    @Override
    public void tick() {

    }

}
