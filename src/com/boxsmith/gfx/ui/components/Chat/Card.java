package com.boxsmith.gfx.ui.components.Chat;


public class Card{
    public Card nextCard; //The next card to be displayed

    public String text = ""; //The text to be displayed

    /**
     * Creates a new text card to be render in the text box.
     * @param text The text to be displayed.
     * @param nextCard The next card to be displayed.
     */
    public Card(String text, Card nextCard){
        this.text = text;
        this.nextCard = nextCard;
    }


}
