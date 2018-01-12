package com.boxsmith.entity;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.level.Level;

public abstract class Entity {
    public int x, y;
 	protected Level level;
    public int width, height; // The area of the entity.

 	public abstract void tick();

 	public abstract void render(Screen screen);

    public boolean collision(int xAbs, int yAbs) {
        boolean sold = false;
        int size = 2;
        for (int c = 0; c < size * size; c++) {
                int xt = ((x + xAbs) + c % size * 16) / 16; //playerX+nextPixel
                int yt = ((y + yAbs) + c / size * 16) / 16;

                    if (level.getTile(xt, yt).solid())
                        sold = true;
            }
        return sold;
    }

    /**
     * Checks if a point is on the component.
     * Often used to see if the mouse is hovering over the component.
     * @param coordinate The X and Y points.
     * @return The component at <code>coordinate</code>. Null if no component.
     */
    public Entity onComponent(int[] coordinate) {
        if ((coordinate[0] >= x - Game.screen.xOffset && coordinate[0] <= x + width - Game.screen.xOffset)
                && (coordinate[1] >= y - Game.screen.yOffset && coordinate[1] <= y + height - Game.screen.yOffset)) {
            return this;
        }
        return null;
    }

    public abstract void mouseClick();

    public void init(Level level) {
        this.level = level;
    }
}
