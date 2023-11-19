package com.boxsmith.gfx.ui.components.Chat;

import java.util.ArrayList;

public class Card{
    public Card nextCard; //The next card to be displayed
    public String text = ""; //The text to be displayed

    public ArrayList<Card> nextCards; //The next card to be displayed
    public ArrayList<String> options; //The text to be displayed

    private static final int MESSAGE_LIMIT = 250;

    /**
     * Creates a new text card to be render in the text box.
     * @param text The text to be displayed.
     * @param nextCard The next card to be displayed.
     */
    public Card(String text, Card nextCard) throws Exception{
        this.text = text;
        this.nextCard = nextCard;

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
    public Card(ArrayList<String> options, ArrayList<Card> nextCards){
        this.options = options;
        this.nextCards = nextCards;
    }


}
