package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.sprite.Sprite;

public class Button extends Component {

    private int width, height;
    public String text;

    public static Button start = new Button(Sprite.rock, "Start");

    public Button(Sprite sprite, String text) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = text;
        super.sprite = sprite;
    }

    public Button(Sprite sprite) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = "";
        super.sprite = sprite;
    }

    public Button onButton(int[] coord) {
        if ((coord[0] >= x && coord[0] <= x + width) && (coord[1] >= y && coord[1] <= y + height)) {
            return this;
        }
        return null;
    }

}