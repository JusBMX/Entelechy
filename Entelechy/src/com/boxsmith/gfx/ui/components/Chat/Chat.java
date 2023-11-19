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

        String[] words = card.text.split("\\s+"); // Splits the text every white space.

        int charlength = 0, newLineCount = 0;
        String line = "";

        for(int i = 0; i <= words.length - 1;){
            while (charlength + words[i].length() + 1 <= Sprite.CHAT.getWidth()/7) {
                charlength += words[i].length() + 1; // The plus one is to make up for the white space removed.
                line += words[i] + " ";
                i++;
                if (i > words.length - 1) break;
            }
            screen.renderText(line, x + 4, y + newLineCount * 16 + 4, false);
            newLineCount++;
            line = "";
            charlength = 0;
            if (newLineCount > 7) break;
            
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
