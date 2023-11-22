package com.boxsmith.gfx.ui.components.Chat;

import java.util.ArrayList;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.gfx.ui.menus.Menu;

public class Chat extends Component {

    public Card card; // The current text card
    private Menu menu;

    /**
     * Creates a new chat box. Origin at top left.
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public Chat(int x, int y, Menu menu) {
        this.x = x;
        this.y = y;
        this.menu = menu;
    }

    /**
     * Renders the text card and next button.
     * @param screen The screen to render to.
     */
    @Override
    public void render(Screen screen){


        if (card == null) return;
        screen.renderSprite(x, y, Sprite.CHAT, false);
        if (card.type == "Message"){
            ArrayList<String> lines = wordWrap(card.text);
            for (int newLineCount = 0; newLineCount < lines.size(); newLineCount++){
                screen.renderText(lines.get(newLineCount), x + 4, y + newLineCount * 16 + 4, false);
            }

            card.option.render(screen);
        }
    }

    /**
     * Updates the chat box.
     */
    @Override
    public void tick() {

    }

    public void show(Card card){
        this.card = card;
        menu.add(this);
        menu.add(card.option);
    }

    public void hide(){
        menu.remove(this);
        menu.remove(card.option);
    }

    private ArrayList<String> wordWrap(String message){
        ArrayList<String> lines = new ArrayList<>();
        String[] words = message.split("\\s+"); // Splits the text every white space.
        int charlength = 0, newLineCount = 0;
        String line = "";

        for(int i = 0; i <= words.length - 1;){
            while (charlength + words[i].length() + 1 <= Sprite.CHAT.getWidth() / 7) {
                charlength += words[i].length() + 1; // The plus one is to make up for the white space removed.
                line += words[i] + " ";
                i++;
                if (i > words.length - 1) break;
            }
            lines.add(line);
            newLineCount++;
            line = "";
            charlength = 0;
            if (newLineCount > 7) break;
        }

        return lines;
    }

}