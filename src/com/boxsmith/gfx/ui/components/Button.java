package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public class Button extends Component {

    private String text;

    public Button(Sprite sprite, String text, int x, int y) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = text;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public Button(Sprite sprite, int x, int y) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = "";
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Screen screen){
        screen.renderSprite(x, y, sprite, false);
        screen.renderText(text, x - (text.length() * 4) / 2, y + 4, false);
    }

}