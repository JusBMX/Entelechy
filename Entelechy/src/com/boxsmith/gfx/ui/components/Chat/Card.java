package com.boxsmith.gfx.ui.components.chat;

import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Action;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.menus.Play;

public class Card {
    public String text = ""; // The text to be displayed

    public Button[] options;
    public Button nexButton = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Next", Play.play.chat.x +
            64, Play.play.chat.y + 110);

    public String type;

    /**
     * Creates a new text card to be render in the text box. Max text length 250
     * charactors.
     * 
     * @param text   The text to be displayed.
     * @param action The next card to be displayed.
     */
    public Card(String text, Action action) {
        type = "Message";
        this.text = text;
        nexButton.action = action;
    }

    /**
     * Creates a new option card to be render in the text box.
     * An option card allows the player to
     * 
     * @param options  The text to be displayed.
     * @param nextCard The next card to be displayed.
     */
    public Card(String[] text, Action[] actions) {
        type = "Options";
        options = new Button[actions.length];
        for (int i = 0; i < actions.length; i++) {
            options[i] = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, text[i], Play.play.chat.x +
                    64, Play.play.chat.y + i * (Sprite.BUTTON.getHeight() + 5));
            options[i].action = actions[i];
        }

    }

}
