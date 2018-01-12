package com.boxsmith.gfx.ui.components.Chat;


public class Card{
    public Card nextCard;

    public String text = "";

    public Card(String text, Card nextCard){
        this.text = text;
        this.nextCard = nextCard;
    }


}
