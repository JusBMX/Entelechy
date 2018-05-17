package com.boxsmith.gfx.ui.components.Chat;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;

public class Chat extends Component {

    public Card card; // The current text card
    public Button nextButton; // The next text card button

    /**
     * Creates a new chat box. Origin at top left.
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public Chat(int x, int y) {
        this.x = x;
        this.y = y;
        nextButton = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Next", x+64, y + 110);
    }

    /**
     * Renders the text card and next button.
     * @param screen The screen to render to.
     */
    @Override
    public void render(Screen screen){
        if(card == null){
            return;
        }
        screen.renderSprite(x, y, Sprite.CHAT,false);
        String[] text = card.text.split("(?<=\\G.{31})"); // Splits the text every 31 characters.
        for(int i = 0; i < text.length; i++){
            screen.renderText(text[i], x + 4, y + i * 16 + 4, false);
        }
        nextButton.render(screen);
    }

    /**
     * Updates the chat box.
     */
    @Override
    public void tick() {
        //nextButton.tick();
    }

}
