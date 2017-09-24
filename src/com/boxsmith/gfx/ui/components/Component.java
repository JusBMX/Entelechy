package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public abstract class Component {

    public int x, y;
    public int width, height;
    public Sprite sprite;

    public abstract void render(Screen screen);

    public Component onComponent(int[] coordinate) {
        if ((coordinate[0] >= x && coordinate[0] <= x + width) && (coordinate[1] >= y && coordinate[1] <= y + height)) {
            return this;
        }
        return null;
    }

}
