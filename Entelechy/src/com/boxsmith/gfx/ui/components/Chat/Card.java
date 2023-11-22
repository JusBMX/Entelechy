package com.boxsmith.gfx.ui.components.Chat;

import java.util.ArrayList;

import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.menus.Play;

public class Card{
    public String text = ""; //The text to be displayed

    public ArrayList<Button> options; //The text to be displayed
    public Button option; //= new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Next", Play.play.chat.x + 64, Play.play.chat.y + 110);

    private static final int MESSAGE_LIMIT = 250;
    private static final int OPTION_LIMIT = 4;

    public String type;

    /**
     * Creates a new text card to be render in the text box. Max text length 250 charactors.
     * @param text The text to be displayed.
     * @param nextCard The next card to be displayed.
     */
    public Card(String text, Button option) throws Exception{
        this.text = text;
        this.option = option;
        type = "Message";
        if (text.length() > MESSAGE_LIMIT) {
            throw new Exception("Message length too long: " + text.length() + " of " + MESSAGE_LIMIT);
        }
    }

    /**
     * Creates a new option card to be render in the text box.
     * An option card allows the player to 
     * @param options The text to be displayed.
     * @param nextCard The next card to be displayed.
     */
    public Card(ArrayList<Button> options) throws Exception{
        this.options = options;
        type = "Options";
        if (options.size() > OPTION_LIMIT) {
            throw new Exception("To many options: " + options.size() + " of " + OPTION_LIMIT);
        }

    }


}
