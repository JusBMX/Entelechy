package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

public abstract class Component {

    public int x, y; // Component's location.
    public int width, height; // The area of the component.
    public Sprite sprite;

    public Action action;

    /**
     * Renders the component to the screen.
     * @param screen The screen to render to.
     */
    public abstract void render(Screen screen);

    /**
     * Updates the component.
     */
    public abstract void tick();

    /**
     * Checks if a point is on the component.
     * Often used to see if the mouse is hovering over the component.
     * @param coordinate The X and Y points.
     * @return The component at <code>coordinate</code>. Null if no component.
     */
    public Component onComponent(int[] coordinate) {
        if ((coordinate[0] >= x && coordinate[0] <= x + width) && (coordinate[1] >= y && coordinate[1] <= y + height)) {
            return this;
        }
        return null;
    }

}
